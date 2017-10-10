package com.example.dao;

import com.example.dao.admin.IUserDao;
import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author liufeng
 * @version [1.0 , 2017/10/10]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class IUserDaoTest {

    @Resource
    private IUserDao userDao;

    @Test
    public void findById() throws Exception {
        String name=userDao.findById(3);
        System.out.println(name);
    }

}