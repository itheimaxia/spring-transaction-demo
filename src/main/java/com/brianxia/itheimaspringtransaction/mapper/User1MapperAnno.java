package com.brianxia.itheimaspringtransaction.mapper;

import com.brianxia.itheimaspringtransaction.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface User1MapperAnno {

    @Insert("insert into user1(username,password,name) values(#{username}, #{password}, #{name})")
    int insert(User user);
}
