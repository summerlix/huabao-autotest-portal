package com.king.service.business;

import com.king.dao.mapper.AtdbUserMapper;
import com.king.entity.AtdbUser;
import com.king.entity.TbSendUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtdbUserService {
	
	@Autowired
	private AtdbUserMapper atdbUserMapper;

	public List<AtdbUser> listAllUsers()	{
		return atdbUserMapper.listAllUsers();
	}

	public AtdbUser findUserByGuid(Integer guid)	{
		return atdbUserMapper.findUserByGuid(guid);
	}

	public int insertUser(TbSendUser tbSendUser) {
		int guid = tbSendUser.getGuid();
		if (atdbUserMapper.findUserByGuid(guid) == null)	{
			System.out.printf("\n找不到该用户guid:%d,直接insert插入\n",guid);
			return atdbUserMapper.insertUser(tbSendUser);
		}
		System.out.printf("\n找到该用户guid:%d,将进行update\n",guid);
		return atdbUserMapper.updateUserFromTB(tbSendUser);
	}

	public int deleteUserByGuid(Integer guid) {
		return atdbUserMapper.deleteUserByGuid(guid);
	}
}
