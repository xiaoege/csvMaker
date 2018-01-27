package com.chen.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chen.service.UserServiceI;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Resource
	UserServiceI userServiceI;

	// private static Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping("/toMain")
	public ModelAndView toMain() throws IOException {
		ModelAndView mav = new ModelAndView();
		File f = new File("F:\\data3.sql");

		mav.setViewName("main");
		return mav;
	}

	@RequestMapping("/upload")
	public String upload(MultipartFile file, HttpServletRequest request, ModelMap model) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String check = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			if (check.equals("sql")) {
				// 文件保存路径
				// 临时路径
				/*
				 * String filePath =
				 * request.getSession().getServletContext().getRealPath("/") +
				 * "upload/"+ file.getOriginalFilename();
				 */
				String s = "F:/fileUpload/" + fileName;
				try {
					// 转存文件
					file.transferTo(new File(s));
					// 执行上传的sql文件
					DbUtil.executeSqlFileByMysql(s);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:toMain";
	}

}
