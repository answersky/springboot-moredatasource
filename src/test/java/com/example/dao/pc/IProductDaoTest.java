package com.example.dao.pc;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author liufeng
 * @version [1.0 , 2017/10/10]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class IProductDaoTest {
    @Resource
    private IProductDao productDao;

    @Test
    public void findProductById() throws Exception {
        String title=productDao.findProductById(28);
        System.out.println(title);
    }

}