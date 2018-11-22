package com.restful.controller;

import com.restful.bo.User;
import com.restful.service.UserService;
import com.restful.utils.RSADecrypter;
import com.restful.vo.LoginVo;
import com.restful.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date
 *
 */
@RestController
@RequestMapping(value = "/loginCtrl")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResultVo login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        String pwd = loginVo.getPwd().replace(" ", "+");
        String str = RSADecrypter.decryptByPrivateKey(pwd, RSADecrypter.PRIVATEKEY_NEW, loginVo.getLength());
        loginVo.setPwd(str);
        User user = this.userService.login(loginVo);
        ResultVo resultVo = new ResultVo();
        if (user != null) {
            resultVo.setCode("Y");
            resultVo.setMessage("登录成功");
            request.getSession().setAttribute("loginUser", loginVo);
        } else {
            resultVo.setCode("N");
            resultVo.setMessage("账号密码错误，请重新登录");
        }

        return resultVo;
    }
}
