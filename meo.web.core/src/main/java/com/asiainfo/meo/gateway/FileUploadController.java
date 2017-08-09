//package com.asiainfo.meo.gateway;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.OutputStream;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
////@Controller
//public class FileUploadController 
//{
//	private static final String PATH = "E:\\";
//	
//	@RequestMapping(value="/fileUpload",method = RequestMethod.POST )
//	public @ResponseBody String upload(
//			@RequestParam("name") String name, @RequestParam("file")  MultipartFile file) throws Exception
//	{
//		String c = String.valueOf(System.currentTimeMillis())+name;
//		File out = new File(PATH + c);
//		FileCopyUtils.copy(file.getBytes(), out);
//		return c;
////		throw new RuntimeException("error");
//	}
//	
////	@RequestMapping(value="{name:.+}")
//	public void getPic(@PathVariable String name,HttpServletRequest request,HttpServletResponse response) throws Exception
//	{
//		File input = new File(PATH + name);
//		OutputStream os =  response.getOutputStream();
//		FileCopyUtils.copy(new FileInputStream(input), os);
////		throw new RuntimeException("error");
//	}
//	
//}
// 