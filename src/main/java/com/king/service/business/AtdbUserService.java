package com.king.service.business;

import com.king.dao.mapper.AtdbUserMapper;
import com.king.entity.AtdbUser;
import com.king.entity.TbSendUser;
import com.king.service.utils.DEScode;
import com.king.service.utils.GetProcessID;
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

	public AtdbUser findUserByAccount(String account){
		return atdbUserMapper.findUserByAccount(account);
	}

	public int insertUser(TbSendUser tbSendUser) throws Exception{
		int guid = tbSendUser.getGuid();
		// 对密码进行加密
		String passwd = tbSendUser.getPassword();
		String hexstr = generateHexDEScode("20251230", passwd);
		tbSendUser.setPassword(hexstr);

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

	// 通过processid,date,passwd 生成十六进制DES加密字符串
	public String generateHexDEScode(String date, String passwd) throws Exception{

		GetProcessID getProcessID = new GetProcessID();
		String processId = getProcessID.getid();
		//System.out.println("ProcessID: " + processId);

		DEScode desCode = new DEScode();
		String inMsg = processId + date + passwd;
		//System.out.println("加密前字符串: " + inMsg);

		byte[] encodeByteArr = desCode.Encrytor(inMsg);
		String encodeStr = desCode.byteArrayToHexString(encodeByteArr);
		System.out.println("加密后十六进制字符串: " + encodeStr);     //存数据库

		return encodeStr;
	}

	// 对十六进制DES加密串进行解密
	public String decodePasswd(String inStr) throws Exception{

		DEScode desCode = new DEScode();
		byte[] decodeByteArr = desCode.hexString2Bytes(inStr);
		byte[] outStrByteArr = desCode.Decryptor(decodeByteArr);
		String outStr = new String(outStrByteArr);
		//System.out.println("解密后字符串: " + outStr);
		outStr = outStr.substring(24);	//processid 16位, YYYYMMDD 8位, 所以密码从第24位开始
		//System.out.println("解密后密码: " + outStr);
		return outStr;
	}


}
