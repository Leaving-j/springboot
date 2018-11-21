package com.restful.controller;

import com.restful.utils.RSADecrypter;
import com.restful.vo.LoginVo;
import com.restful.vo.ResultVo;
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

    @PostMapping(value = "/login")
    public ResultVo login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        String pwd = loginVo.getPwd().replace(" ", "+");
        String str = RSADecrypter.decryptByPrivateKey(pwd, RSADecrypter.PRIVATEKEY_NEW, loginVo.getLength());

        request.getSession().setAttribute("loginUser", loginVo);

        ResultVo resultVo = new ResultVo();
        resultVo.setCode("Y");
        resultVo.setMessage("登录成功");
        return resultVo;
    }
}
