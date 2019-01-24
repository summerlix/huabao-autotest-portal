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
//@RequestMapping(value = "/autotest-portal")
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
	public CallbackDataDTO findUsersByName(@ApiParam(value = "guid", required = true) @RequestParam Integer guid) {
		return CallbackDataDTO.build(true, atdbUserService.findUserByGuid(guid)) ;
    }

	@PostMapping(value = "/insertUser")
	@ApiOperation(value = "插入一条用户记录")
	public CallbackDataDTO insertUser(TbSendUser tbSendUser) throws Exception {
		return CallbackDataDTO.build(true, atdbUserService.insertUser(tbSendUser), "成功") ;
	}

	@DeleteMapping(value = "/deleteUser")
	@ApiOperation(value = "删除某guid的用户")
	public CallbackDataDTO deleteUser(@ApiParam(value="guid", required = true) @RequestParam Integer guid) {
		int delrst = atdbUserService.deleteUserByGuid(guid);
		if (delrst == 0) {
			return CallbackDataDTO.build(true, 0, "没有该guid的用户") ;
		}
		return CallbackDataDTO.build(true, delrst, "删除成功") ;
	}


}
 