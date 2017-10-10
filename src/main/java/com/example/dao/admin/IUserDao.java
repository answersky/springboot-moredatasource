package com.example.dao.admin;

import org.springframework.stereotype.Repository;

/**
 * @author liufeng
 * @version [1.0 , 2017/10/10]
 */
@Repository(value = "sqlSessionFactoryBean1")
public interface IUserDao {
    String findById(Integer id);
}
