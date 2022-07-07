package com.lsy;

import com.MybatisPlusApplication;
import com.lsy.model.User;
import com.lsy.service.MybatisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisPlusApplication.class})
public class MpTest {

    @Autowired
    private MybatisService mybatisService;

    @Test
    public void update(){
        User user = new User();
        user.setId(1L);
//        user.setEmail(null);
        user.setName("null");
        user.setAge(1);
        mybatisService.updateBySomething(user);
    }
}
