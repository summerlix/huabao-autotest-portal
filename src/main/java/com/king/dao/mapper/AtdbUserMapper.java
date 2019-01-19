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

    int insertUser(TbSendUser tbSendUser);

    int deleteUserByGuid(Integer guid);

    int updateUserFromTB(TbSendUser tbSendUser);

}