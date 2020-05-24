package com.showme.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String showIndex(){
		return "index";     // ------> This the JSP file to be rendered(to load)
	}

	@RequestMapping("/show")
	public String show(){
		return "views/instagram_main_feed";     // ------> This the JSP file to be rendered(to load)
	}
	
	@RequestMapping("/submitImage")
	public String submitImage(HttpServletRequest request){
		
		
		String str = request.getParameter("data-url").toString();
		
		System.out.println(str);
		System.out.println("1");
		
		if(str==null)
			return "index"; 
		
		System.out.println("2");
		
		
		 byte[] imagedata = DatatypeConverter.parseBase64Binary(str.substring(str.indexOf(",") + 1));
		 BufferedImage bufferedImage; 
		 try { 
			 bufferedImage = ImageIO.read(new ByteArrayInputStream(imagedata)); 
			 File pathFile = new File("img.jpg");
			 ImageIO.write(bufferedImage, "jpg", pathFile);
			 System.out.println(pathFile.getPath());
		  
		  } catch (IOException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		 
		
		return "index"; 
	}
	
	@RequestMapping("/showImage")
	public String showImage(Model model){
		
		model.addAttribute("imgPath", "img.jpg");
		
		return "index"; 
	}
	
	@RequestMapping("/sub")
	public String sub(HttpServletRequest request) {
		
		
		
		return "index";
	}
}
