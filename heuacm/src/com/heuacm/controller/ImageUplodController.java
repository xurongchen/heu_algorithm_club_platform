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
 * ͼƬ�ϴ�������
 */
@Controller
public class ImageUplodController {

	@RequestMapping(value = "upload.do")
	public String uploadImage(MultipartFile upload, String CKEditorFuncNum,HttpServletResponse response ) throws IOException {
		// upload��ͼƬ�ļ���ֱ��ת�����ֽ���������ݿ�������ƿռ�
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
		// ����ͼƬ....����һ��ͼƬ��URL
		// code......
		// ��󷵻�һ��URL��ckeditor;����image/imagePath.pngΪimage����·������һ����������ǰ̨��һ����־���������������󷵻���
		 out.println("<script type=\"text/javascript\">");  
        out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
                + ", 'http://localhost:8880/JavaSave/"+ name +"', '');</script> ");  
        out.println("</script>");  
        return null;  
	}
}