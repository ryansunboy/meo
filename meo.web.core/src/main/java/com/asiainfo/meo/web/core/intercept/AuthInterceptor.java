package com.asiainfo.meo.web.core.intercept;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.SignUtil;
import com.asiainfo.meo.common.core.utils.StringUtil;
import com.asiainfo.meo.passport.token.app.TokenBO;
import com.asiainfo.meo.passport.token.model.entity.Token;
import com.asiainfo.meo.web.core.VersionInfo;
import com.asiainfo.meo.web.core.constant.WebConstant;
import com.asiainfo.meo.web.core.constant.WebErrorCodeConstant;

public class AuthInterceptor extends HandlerInterceptorAdapter
{
    private static final Log LOG = LogFactory.getLog(AuthInterceptor.class);
    
    private static final String AMP = "&";
    
    private static final String EQUAL = "=";
    
    private long interval = 60 * 60 * 1000;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        initContext(request);
        
        checkTimestamp();
        
        checkAccessToken();
        
        checkSignature(request);
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception
    {
        System.out.println(modelAndView);
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        System.out.println(ex);
    }
    
    private void checkTimestamp()
    {
        long clientTimestamp = BoContext.getBoContext().getContent(SystemParameters.class).getTimestamp();
        long serverTime = DateTimeUtil.getCurrentTimeMillis();
        long intervalTime = Math.abs(serverTime - clientTimestamp) ;
        if (intervalTime > interval)
        {
            throw new MeoException(WebErrorCodeConstant.TIMESTAMP_EXPIRED);
        }
    }
    
    private void checkAccessToken()
    {
        String accessToken = BoContext.getBoContext().getContent(SystemParameters.class).getAccessToken();
        TokenBO tokenBO = ServiceLocatorFactory.getService(TokenBO.class);
        Token token = tokenBO.getValidToken(accessToken);
      
        BoContext.getBoContext().setUserId(token.getUserId());
        BoContext.getBoContext().setContent(Token.class, token);
    }
    
    private void checkSignature(HttpServletRequest request)
    {
        String schema = request.getScheme();
        
        if (WebConstant.HTTP.equals(schema))
        {
            TreeMap<String, String> params = stringToMap(request.getQueryString());
            String signature = params.remove(WebConstant.SIGNATURE);
            if (StringUtil.isBlank(signature))
            {
                throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{WebConstant.SIGNATURE });
            }
            
            String body = null;
            if (WebConstant.POST.equals(request.getMethod()))
            {
                try
                {
                    body = IOUtils.toString(request.getInputStream());
                    BoContext.getBoContext().setMessageBody(body);
                }
                catch(IOException e)
                {
                    LOG.error("get messgae body error", e);
                }
            }
            if (!signature.equals(SignUtil.md5Signature(params, BoContext.getBoContext().getContent(Token.class).getSecretKey(), body)))
            {
                throw new MeoException(WebErrorCodeConstant.SIGNATURE_ERROR);
            }
        }
    }
    
    private void initContext(HttpServletRequest request)
    {
        SystemParameters systemParameters = initSystemParams(request);
        
        BoContext context = BoContext.getBoContext();
        //设置系统参数
        context.setContent(SystemParameters.class, systemParameters);
        
        // 设置SCHEME http https
        context.setSchema(request.getScheme());
        context.setQueryString(request.getQueryString());
        context.setLocale(request.getLocale());
        context.setHttpMethod(request.getMethod());
    }
    
    private SystemParameters initSystemParams(HttpServletRequest request)
    {
        SystemParameters sysParameters = new SystemParameters();
        
        String clientTimestamp = request.getParameter(WebConstant.TIMESTAMP);
        checkSystemParameters(clientTimestamp, WebConstant.TIMESTAMP);
        sysParameters.setTimestamp(Long.valueOf(clientTimestamp));
        
        String accessToken = request.getParameter(WebConstant.ACCESS_TOKEN);
        checkSystemParameters(accessToken, WebConstant.ACCESS_TOKEN);
        sysParameters.setAccessToken(accessToken);
        
        String method = request.getParameter(WebConstant.METHOD);
        checkSystemParameters(method, WebConstant.METHOD);
        sysParameters.setMethod(method);
        
        String v = request.getParameter(WebConstant.VERSION);
        if (StringUtil.isBlank(v))
        {
            sysParameters.setV(VersionInfo.majorVersion);
        }
        sysParameters.setSignature(request.getParameter(WebConstant.SIGNATURE));
        return sysParameters;
    }
    
    private TreeMap<String, String> stringToMap(String queryString)
    {
        TreeMap<String, String> params = new TreeMap<String, String>();
        String[] keyValues = queryString.split(AMP);
        for (String keyValue : keyValues)
        {
            String[] kv = keyValue.split(EQUAL);
            params.put(kv[0], kv[1]);
        }
        return params;
    }
    
    private void checkSystemParameters(String value, String key)
    {
        if (StringUtil.isBlank(value))
        {
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY, new Object[]{key });
        }
    }
}
