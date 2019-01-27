package com.king.controller;

import java.util.HashMap;
import java.util.Map;
import com.king.dto.CallbackDataDTO;
import com.alibaba.fastjson.JSONObject;
import com.king.entity.AtdbUser;
import com.king.service.business.AtdbUserService;
import com.king.service.http.HttpConnect;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/autotest-portal")
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
	public CallbackDataDTO loginPost(@ApiParam(name = "account",value = "用户名", required = true) @RequestParam String account,
                                     @ApiParam(name = "passwd",value = "密码", required = true) @RequestParam String passwd) throws Exception{
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
	public String logout() {
		return "redirect:/login";
	}
//	public String logout(HttpSession session) {
//			// 移除session
//			session.removeAttribute(WebSecurityConfig.SESSION_KEY);


	@GetMapping(value = "/getTBtoken")
	@ApiOperation(value = "getTBtoken")
	public CallbackDataDTO getTBtoken(@ApiParam(name = "email",value = "邮箱", required = true) @RequestParam String email ,
                                      @ApiParam(name = "passwd",value = "密码", required = true) @RequestParam String passwd)
	{
		String urlParam = "http://quail.lab.tb/api/customer/obtain-auth-token/";
		Map<String, Object> mapobj = new HashMap<String, Object>();
		mapobj.put("email",email);
		mapobj.put("password", passwd);
		HttpConnect httpConnect = new HttpConnect();
		String rst = httpConnect.httpClientGet(urlParam, mapobj, "utf-8");
 		JSONObject jsobj = JSONObject.parseObject(rst);
 		if (jsobj.containsKey("token"))
			return CallbackDataDTO.build(true, jsobj,"获取token成功") ;
 		else
			return CallbackDataDTO.build(false, jsobj,"获取token失败") ;
	}




}
