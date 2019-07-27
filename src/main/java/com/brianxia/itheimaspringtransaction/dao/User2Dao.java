package com.brianxia.itheimaspringtransaction.dao;

import com.brianxia.itheimaspringtransaction.domain.User;
import com.brianxia.itheimaspringtransaction.mapper.User2MapperAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class User2Dao {
    @Autowired
    User2MapperAnno user2;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser1(){

        User user = new User();
        user.setName("2");
        user.setPassword("2");
        user.setUsername("2");

        user2.insert(user);
        int j =  10/ 0;  // 内层报错抛出异常
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addUser2(){

        User user = new User();
        user.setName("2");
        user.setPassword("2");
        user.setUsername("2");

        user2.insert(user);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addUser3(){

        User user = new User();
        user.setName("2");
        user.setPassword("2");
        user.setUsername("2");

        user2.insert(user);
        int j =  10/ 0;  // 内层报错抛出异常
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addUser4(){

        User user = new User();
        user.setName("2");
        user.setPassword("2");
        user.setUsername("2");

        user2.insert(user);
        int j =  10/ 0;  // 内层报错抛出异常
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addUser5(){

        User user = new User();
        user.setName("2");
        user.setPassword("2");
        user.setUsername("2");

        user2.insert(user);
        int j =  10/ 0;  // 内层报错抛出异常
    }

    @Transactional(propagation = Propagation.NEVER)
    public void addUser7(){

        User user = new User();
        user.setName("2");
        user.setPassword("2");
        user.setUsername("2");

        user2.insert(user);
        int j =  10/ 0;  // 内层报错抛出异常
    }
}
