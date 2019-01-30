package com.king.service.business;

import com.king.dao.mapper.AtdbUserMapper;
import com.king.entity.AtdbUser;
import com.king.entity.TbSendUser;
import com.king.service.utils.DEScode;
import com.king.service.utils.GetProcessID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AtdbUserService {
	
	@Autowired
	private AtdbUserMapper atdbUserMapper;

//	@Autowired
//	private AtdbUser atdbUser;

	public List<AtdbUser> listAllUsers()	{
		return atdbUserMapper.listAllUsers();
	}

	public AtdbUser findUserByGuid(Integer guid)	{
		return atdbUserMapper.findUserByGuid(guid);
	}

	public AtdbUser findUserByAccount(String account){
		return atdbUserMapper.findUserByAccount(account);
	}

	// 增加一个新用户或者修改用户信息
	public int insertUser(TbSendUser tbSendUser) throws Exception {
		int guid = tbSendUser.getGuid();
		String passwd = tbSendUser.getPassword();

		if (atdbUserMapper.findUserByGuid(guid) == null)	{
			System.out.printf("\n找不到该用户guid:%d，将执行insert插入新用户\n",guid);
			String hexstr = generateHexDEScode("20251230", passwd);
			tbSendUser.setPassword(hexstr);
			return atdbUserMapper.insertUser(tbSendUser);
		}

		System.out.printf("\n找到该用户guid:%d，将进行update修改用户信息\n",guid);
		if (passwd != null) {
			// 如果TB修改密码，会正常发password过来，我们先加密，然后入库；
			String hexstr = generateHexDEScode("20251230", passwd);
			tbSendUser.setPassword(hexstr);
		}else {
			// 如果不属于修改密码，TB传过来的password字段为null, 我将数据库里的passwd取出来，塞到tbSendUser里（不再加密），然后执行insert或者update入库
			passwd = atdbUserMapper.getPasswdByGuid(guid);
			tbSendUser.setPassword(passwd);
		}
		return atdbUserMapper.updateUserFromTB(tbSendUser);

	}

	// 测试接口用服务，测完请删除 ...
	public  String getPwdTest(Integer guid){
		return  atdbUserMapper.getPasswdByGuid(guid);
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
