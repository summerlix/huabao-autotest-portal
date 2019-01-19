package com.king.service.fallback;

import org.springframework.stereotype.Component;

import com.king.dto.CallbackDataDTO;
import com.king.service.remote.IMSClientService;

@Component
public class IMSClientFallback implements IMSClientService
{

	@Override
	public CallbackDataDTO getEmployeesInGroups(String account)
	{
		// TODO Auto-generated method stub
		return CallbackDataDTO.build(false, "获取失败!");
		
	}
}
