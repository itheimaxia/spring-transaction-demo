package com.brianxia.itheimaspringtransaction.controller;

import com.brianxia.itheimaspringtransaction.dao.User2Dao;
import com.brianxia.itheimaspringtransaction.domain.User;
import com.brianxia.itheimaspringtransaction.mapper.User1MapperAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MybatisController {

    @Autowired
    private User1MapperAnno userMapper;
    @Autowired
    private User2Dao user2;

    /**
     *  REQUIRED,
     * 支持事务。如果业务方法执行时已经在一个事务中，则加入当前事务，否则重新开启一个事务。
     * 外层事务提交了，内层才会提交。
     * 内/外只要有报错，他俩会一起回滚。
     * 只要内层方法报错抛出异常，即使外层有try-catch，该事务也会回滚！
     * 因为内外层方法在同一个事务中，内层只要抛出了异常，这个事务就会被设置成rollback-only，即使外层try-catch内层的异常，该事务也会回滚。
     */
    @RequestMapping("/add1")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser1() {
        User user = new User();
        user.setName("1");
        user.setPassword("1");
        user.setUsername("1");

        userMapper.insert(user);

        try {
            user2.addUser1();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        user2.addUser1();

    }


    /**
     *  REQUIRES_NEW,
     * 支持事务。如果业务方法执行时已经在一个事务中，则加入当前事务，否则重新开启一个事务。
     * 外层事务提交了，内层才会提交。
     * 内/外只要有报错，他俩会一起回滚。
     * 只要内层方法报错抛出异常，即使外层有try-catch，该事务也会回滚！
     * 因为内外层方法在同一个事务中，内层只要抛出了异常，这个事务就会被设置成rollback-only，即使外层try-catch内层的异常，该事务也会回滚。
     */
    @RequestMapping("/add2")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser2() {
        User user = new User();
        user.setName("1");
        user.setPassword("1");
        user.setUsername("1");

        user2.addUser2();

        userMapper.insert(user);
        int j =  10/ 0;  // 外层报错抛出异常，但是内层的事务已经提交了

    }


    /**
     NESTED
     该传播行为解释：支持事务。如果当前已经在一个事务中了，则嵌套在已有的事务中作为一个子事务。如果当前没在事务中则开启一个事务。
     内层事务结束，要等着外层一起提交。
     外层回滚，内层也回滚。
     如果只是内层回滚，不影响外层。
     这个内层回滚不影响外层的特性是有前提的，否则内外都回滚。
     */
    @RequestMapping("/add3")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser3() {
        User user = new User();
        user.setName("1");
        user.setPassword("1");
        user.setUsername("1");

        try {
            user2.addUser3();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userMapper.insert(user);

    }

    /**
     SUPPORTS
     该传播行为解释：支持事务。当前有事务就支持。当前没有事务就算了，不会开启一个事物。
     */
    @RequestMapping("/add4")
    @ResponseBody
    public void addUser4() {
        user2.addUser4();  //因为没有事务，所以在异常前就提交了数据
    }

    /**
     5.MANDATORY
     该传播行为解释：支持事务，如果业务方法执行时已经在一个事务中，则加入当前事务。否则抛出异常。
     */
    @RequestMapping("/add5")
    @ResponseBody
    public void addUser5() {
        user2.addUser5();  //因为没有事务，直接抛出异常
    }

//    6.NOT_SUPPORTED
//    不支持事务，如果业务方法执行时已经在一个事务中，则挂起当前事务，等方法执行完毕后，事务恢复进行。

//    7.NEVER
//    不支持事务。如果当前已经在一个事务中了，抛出异常。
        @RequestMapping("/add7")
        @ResponseBody
        @Transactional
        public void addUser7() {
            user2.addUser7();  //因为有事务，直接抛出异常
        }

}
