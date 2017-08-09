/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.web.common.file.controller.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.file.basic.bo.FileBO;
import com.asiainfo.meo.common.file.basic.model.vo.FileBasicVO;
import com.asiainfo.meo.common.file.basic.model.vo.FileItemVO;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author ruanming@asiainfo.com
 * @Date 2015-5-6
 */
@RestController
public class FileController
{
    //private static final Log LOG = LogFactory.getLog(FileController.class);
    
    @Resource
    private FileBO fileBO;
    
    @RequestMapping(value="/file",method = RequestMethod.POST,params = "method=meo.file.upload")
    public FileBasicVO uploadFile(@RequestBody @Valid FileItemVO file)
    {
       return fileBO.uploadFile(file);
    }
    
    
    
    @RequestMapping(value="/public/file/uploaded/{name:.+}",method = RequestMethod.GET)
    public void downloadFile(@PathVariable @NotEmpty String name,HttpServletRequest request,HttpServletResponse response)
    {
        try
        {
            InputStream in = fileBO.downloadFile(name);
            OutputStream os = response.getOutputStream();
            IOUtils.copy(in, os);
        }
        catch(IOException e)
        {
            throw new MeoException("4049001005", e);
        }
    }
}
