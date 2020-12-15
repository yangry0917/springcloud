package com.cloud.oauth2resourceserver.dao;

import model.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    //@Select( "select id , username , password from user where username = #{username}" )
    User loadUserByUsername(@Param("username") String username);
    //@Select("select 1")
    int test();

}