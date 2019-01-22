package com.king.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import com.king.entity.AtdbUser;
import com.king.service.business.AtdbUserService;
import com.king.service.utils.DEScode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.king.dto.CallbackDataDTO;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(value = "/autotest-portal")
@Validated
public class WebController {

	@Autowired
	AtdbUserService atdbUserService;

//	@RequestMapping("/")
//	public String index(String account, Model model) {
//		model.addAttribute("name", account);
//		return "login";
//	}
//
//	@RequestMapping("/login")
//	public String login() {	return "login";	}

	@PostMapping(value = "/loginPost")
	@ApiOperation(value = "loginPost")
	//public CallbackDataDTO loginPost(String account, String password, HttpSession session) {
	public CallbackDataDTO loginPost(String account, String passwd) throws Exception{
		AtdbUser atdbUser = atdbUserService.findUserByAccount(account);
		if (atdbUser == null)
			return CallbackDataDTO.build(false, "不存在用户：" + account);

		String dbPwdHexStr = atdbUser.getPassword();
		String dbPwd = atdbUserService.decodePasswd(dbPwdHexStr);
		if (!dbPwd.equals(passwd))
			return CallbackDataDTO.build(false, "密码验证失败") ;

		// 设置session
		//session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
		return CallbackDataDTO.build(true, atdbUser,"登录成功") ;
	}

	@GetMapping(value = "/logout")
	@ApiOperation(value = "logout")
//	public String logout(HttpSession session) {
//			// 移除session
//			session.removeAttribute(WebSecurityConfig.SESSION_KEY);

	public String logout() {
		return "redirect:/login";
	}

}
