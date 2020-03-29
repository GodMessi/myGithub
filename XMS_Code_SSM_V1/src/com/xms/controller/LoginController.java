package com.xms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xms.dao.UserDao;
import com.xms.entity.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private UserDao userDao;

	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/login.do")
	public String login(String email, String password, HttpServletRequest request) {
		User user = userDao.findUserByEmail(email);

		if (user == null || !user.getPassword().equals(password)) {
			request.setAttribute("message", "账号或密码错误");
			return "login";
		} else {
			request.getSession().setAttribute("user", user);
			return "redirect:/main/toIndex.do";
		}

	}

	@RequestMapping("/loginOut.do")
	public String loginOut(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return "redirect:/main/toIndex.do";
	}
}
