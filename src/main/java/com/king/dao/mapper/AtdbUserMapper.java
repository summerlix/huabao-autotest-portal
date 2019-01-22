package com.king.dao.mapper;

import com.king.entity.AtdbUser;
import com.king.entity.TbSendUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//import org.apache.ibatis.annotations.*;
//import org.apache.ibatis.type.JdbcType;

@Repository
@Mapper
public interface AtdbUserMapper {

    List<AtdbUser> listAllUsers();

    AtdbUser findUserByGuid(Integer guid);

    AtdbUser findUserByAccount(String account);    //通过账户名account，来查询账户信息：用户是否存在，密码多少，accflag多少

    int insertUser(TbSendUser tbSendUser);  //insert TB过来的用户信息

    int deleteUserByGuid(Integer guid);

    int updateUserFromTB(TbSendUser tbSendUser);    //update TB过来的用户信息

}