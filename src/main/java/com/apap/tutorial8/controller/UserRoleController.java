package com.apap.tutorial8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial8.model.PasswordModel;
import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user, Model model) {
		System.out.println(user.getPassword());
		System.out.println(validatePassword(user.getPassword()));
		if (validatePassword(user.getPassword())) {
			userService.addUser(user);
			model.addAttribute("status", "Akun berhasil dibuat");
		}
		else {
			model.addAttribute("status", "Tidak bisa membuat akun");
		}
		return "home";
	}
	
	@RequestMapping(value="/updatePass", method = RequestMethod.POST)
	private String updatePassSubmit(@ModelAttribute PasswordModel password, Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UserRoleModel user = userService.findUserByUsername
				(SecurityContextHolder.getContext().getAuthentication().getName());
		String message = "";
		
		if (password.getNewPass().equals(password.getConPass())) {
			if(passwordEncoder.matches(password.getOldPass(), user.getPassword())) {
				if(validatePassword(password.getNewPass())) {
					userService.changePassword(user, password.getNewPass());
					message = "Update password berhasil";
				}
				else {
					message = "Password anda setidaknya ada 1 huruf besar, 1 huruf kecil, dan "
							+ "terdiri 8 karakter atau lebih";
				}
			}
			else {
				message = "Password lama tidak sesuai";
			}
		}
		else {
			message = "Password baru tidak sesuai";
		}
		model.addAttribute("message", message);
		return "home";
	}
	
	private boolean validatePassword(String password) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
		boolean flag = password.length() >= 8 && password.matches(pattern);
		return flag;
	}

}
