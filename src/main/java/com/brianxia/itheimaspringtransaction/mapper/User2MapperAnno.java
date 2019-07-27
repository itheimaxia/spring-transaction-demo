package com.brianxia.itheimaspringtransaction.mapper;

import com.brianxia.itheimaspringtransaction.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface User2MapperAnno {

    @Insert("insert into user2(username,password,name) values(#{username}, #{password}, #{name})")
    int insert(User user);
}
