package com.macro.mall.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.macro.mall.util.CacheEngine;
import com.macro.mall.util.ValidateCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

/**
 * ClassName:ValidateCodeController <br/>
 * Function: 验证码Controller <br/>
 * Date: 2018年2月7日 上午10:50:36 <br/>
 * 
 * @author CHENZHOU
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
public class ValidateCodeController {
	static final int WIDTH = 70;
	static final int HEIGHT = 30;
	static final int CODE_COUNT = 4;
	static final int LINE_COUNT = 20;

	@Autowired CacheEngine defaultCacheEngine;
	
	/**
	 * 响应验证码页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/validateCode")
	public void validateCode(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(10);

		Map<String, String> map = new HashMap<>();

		ValidateCode vCode = new ValidateCode(WIDTH, HEIGHT, CODE_COUNT, LINE_COUNT);
		defaultCacheEngine.add("VALIDATE_CODE", vCode.getCode()+System.currentTimeMillis());
		
		BufferedImage image = vCode.getBuffImg();
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", bos);
		byte[] imageBytes = bos.toByteArray();
		showImg(response,imageBytes);
		bos.close();
//		try {
//			ImageIO.write(image, "png", bos);
//			byte[] imageBytes = bos.toByteArray();
//			Base64 encoder = new Base64();
//			imageString = encoder.encodeToString(imageBytes);
//			map.put("image", imageString);
//			bos.close();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		session.invalidate();
//		response.setContentType("text/json;charset=utf-8");
//		Gson g= new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
//		String json = g.toJson(map);
//		response.getWriter().write(json);
	}
	
	private void showImg(HttpServletResponse response,byte[] data) throws Exception {
		MagicMatch match = Magic.getMagicMatch(data);
		String mimeType = match.getMimeType();
		try {
			// 设置响应的类型格式为图片格式      
			response.setContentType(mimeType);      
			//禁止图像缓存。      
			response.setHeader("Pragma", "no-cache");      
			response.setHeader("Cache-Control", "no-cache");      
			response.setDateHeader("Expires", 0);       
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());      
			bos.write(data);
			bos.close();   
		}catch (Exception e){      
			e.printStackTrace();   
		}
	}
	
}
