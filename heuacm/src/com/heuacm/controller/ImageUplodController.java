package com.heuacm.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * 图片上传测试类
 */
@Controller
public class ImageUplodController {

	@RequestMapping(value = "upload.do")
	public String uploadImage(MultipartFile upload, String CKEditorFuncNum,HttpServletResponse response ) throws IOException {
		// upload即图片文件可直接转换成字节数组存数据库或其他云空间
		/*
		try {
			byte[] imageBytes = upload.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		 PrintWriter out = response.getWriter();
		 String fr = "F:/JavaSave/";
		 String name =  RandomStringUtils.randomAlphanumeric(20) + "." +
				 upload.getOriginalFilename().substring(upload.getOriginalFilename().lastIndexOf(".") + 1);
		 String path =  fr + name;
		 try {
			upload.transferTo(new File(path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(name);
		// 处理图片....返回一个图片的URL
		// code......
		// 最后返回一个URL给ckeditor;其中image/imagePath.png为image存后的路径；第一个参数来自前台的一个标志，第三个参数错误返回码
		 out.println("<script type=\"text/javascript\">");  
        out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
                + ", 'http://localhost:8880/JavaSave/"+ name +"', '');</script> ");  
        out.println("</script>");  
        return null;  
	}
}