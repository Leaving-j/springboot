package com.restful.service;

import com.restful.bo.User;
import com.restful.vo.LoginVo;

/**
 * @program: restfulproject
 * @description: UserService
 * @author: lw
 * @create: 2018-11-21 14:35
 **/
public interface UserService {
    User login(LoginVo loginVo);
}
