package com.king.controller;

import com.king.dto.CallbackDataDTO;
import com.king.entity.AtdbUser;
import com.king.entity.TbSendUser;
import com.king.service.business.AtdbUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
public class AtdbUserController
{
	@Autowired
	AtdbUserService atdbUserService;

	@GetMapping(value = "/listAllUsers")
	@ApiOperation(value = "列出所有用户")
	public CallbackDataDTO listAllUsers()	{
		return CallbackDataDTO.build(true, atdbUserService.listAllUsers()) ;
	}

	@GetMapping(value = "/findUserByGuid")
    @ApiOperation(value = "列出某guid的用户")
	public CallbackDataDTO findUsersByName(@ApiParam("guid")@RequestParam(value="guid")Integer guid)    {
		return CallbackDataDTO.build(true, atdbUserService.findUserByGuid(guid)) ;
    }

	@PostMapping(value = "/insertUser")
	@ApiOperation(value = "插入一条用户记录")
	public CallbackDataDTO insertUser(@ApiParam("用户记录数据") TbSendUser tbSendUser)    {
		return CallbackDataDTO.build(true, atdbUserService.insertUser(tbSendUser)) ;
	}

	@DeleteMapping(value = "/deleteUser")
	@ApiOperation(value = "删除某guid的用户")
	public CallbackDataDTO deleteUser(@RequestParam(value="guid") Integer guid)    {
		return CallbackDataDTO.build(true, atdbUserService.deleteUserByGuid(guid)) ;
	}


}
 