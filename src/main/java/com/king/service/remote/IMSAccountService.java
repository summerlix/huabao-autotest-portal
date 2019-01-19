package com.king.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.king.service.fallback.IMSAccountFallback;

@FeignClient(value = "ims-account" , path="/ims/ims-account" , fallback = IMSAccountFallback.class)
public interface IMSAccountService
{
	@PutMapping(value = "/accountConsume")  
    boolean accountConsume(@RequestParam(value = "account") String account, @RequestParam(value = "consumeNumber") Integer consumeNumber);

}