package com.restful.service.impl;

import com.restful.bo.User;
import com.restful.dao.UserDao;
import com.restful.service.UserService;
import com.restful.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: restfulproject
 * @description: UserServiceImpl
 * @author: lw
 * @create: 2018-11-21 14:36
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(LoginVo loginVo) {
        return this.userDao.login(loginVo);
    }
}
