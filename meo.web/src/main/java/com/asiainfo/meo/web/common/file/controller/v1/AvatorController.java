/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.web.common.file.controller.v1;

import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.file.photo.avator.bo.AvatorBO;
import com.asiainfo.meo.common.file.photo.avator.model.vo.AvatorItemVO;
import com.asiainfo.meo.common.file.photo.avator.model.vo.AvatorVO;

/**
 * @Description: 上传头像的CONTROLLER
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-11
 */
@RestController
@RequestMapping
public class AvatorController
{
    @Resource
    private AvatorBO avatorBO;
    
    @RequestMapping(value="/avatar",method = RequestMethod.POST,params = "method=meo.avatar.upload")
    public AvatorVO uploadAvator(@RequestBody @Validated AvatorItemVO file)
    {
        return avatorBO.uploadAvator(file);
    }
    
    @RequestMapping(value="/public/avatar/uploaded/{name:.+}",method = RequestMethod.GET)
    public void download(@PathVariable @NotEmpty String name,HttpServletRequest request,HttpServletResponse response)
    {
        try
        {
            InputStream in = avatorBO.downloadAvator(name);
            OutputStream os = response.getOutputStream();
            IOUtils.copy(in, os);
//            response.sendRedirect("http://www.163.com");
        }
        catch(Exception e)
        {
            throw new MeoException("4049001005", e);
        }
    }
}
