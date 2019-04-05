package com.bway.springproject.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bway.springproject.HomeController;
import com.bway.springproject.dao.EmployeeDao;
import com.bway.springproject.dao.UserDao;
import com.bway.springproject.dao.UserDaoImpl;
import com.bway.springproject.model.User;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private EmployeeDao edao;

	@RequestMapping(value = "/userlogin", method = RequestMethod.GET)
	public String getLoginForm() {

		logger.info("Inside getloginform() method");

		return "login";
	}

	@Autowired
	private UserDao udao;

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String isExist(@ModelAttribute User u, Model model, HttpSession session, HttpServletRequest request)
			throws IOException {

		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));

		String input = request.getParameter("g-recaptcha-response");

		Boolean result = VerifyRecaptcha.verify(input);

		if (result) {

			boolean regUser = udao.userLogin(u.getUsername(), u.getPassword());

			if (regUser) {

				logger.info("Login success!");

				session.setAttribute("activeuser", u.getUsername());
				session.setMaxInactiveInterval(3 * 60);

				model.addAttribute("user", u.getUsername());

				model.addAttribute("elist", edao.getAll());

				return "home";
			} else {
				model.addAttribute("error", "Invalid User");
				return "login";
			}

		}
		logger.info("Login failed!");

		model.addAttribute("error", "you are not human");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.invalidate();
		logger.info("Logout Success!");
		return "login";
	}

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String fbLogin() {

		// String secret_key = "c85c3bbaeded9ce1ea6af891cc8733c2";
		// String app_id = "1439123129660532";

		return "redirect:https://www.facebook.com/dialog/oauth?client_id=2021691634623091&redirect_uri=http://localhost:8080/springproject/authorized/facebook&response_type=code&scope=email";
	}

	@RequestMapping(value = "/authorized/facebook", method = RequestMethod.GET)
	public String saveFbUser(String code, Model model, HttpServletRequest request) {

		model.addAttribute("elist", edao.getAll());

		return "home";

	}

}
