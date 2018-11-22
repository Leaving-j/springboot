package com.restful.dao;

import com.restful.bo.User;
import com.restful.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: restfulproject
 * @description: UserDao
 * @author: lw
 * @create: 2018-11-21 14:38
 **/
@Mapper
public interface UserDao {

    @Select("select *from t_user where account=#{userName} and password=#{pwd}")
    User login(LoginVo loginVo);

}
