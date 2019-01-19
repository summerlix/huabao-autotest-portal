package com.king.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.king.dto.CallbackDataDTO;
import com.king.service.fallback.IMSClientFallback;

@FeignClient(value = "ims-client" ,  path="/ims/ims-client",fallback = IMSClientFallback.class)
public interface IMSClientService
{
	@GetMapping(value = "/getEmployeesInGroups")  
	CallbackDataDTO getEmployeesInGroups(@RequestParam(value = "data") String account);

}