package com.restful.controller;

import com.restful.utils.RSADecrypter;
import com.restful.vo.LoginVo;
import com.restful.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @date
 *
 */
@RestController
@RequestMapping(value = "/loginCtrl")
public class LoginController {

    @PostMapping(value = "/login")
    public ResultVo login(@RequestBody LoginVo loginVo) {
        String pwd = loginVo.getPwd().replace(" ", "+");
        String str = RSADecrypter.decryptByPrivateKey(pwd, RSADecrypter.PRIVATEKEY_NEW, 6);
        System.out.println("RSA解密后的字符串：" + str);

        ResultVo resultVo = new ResultVo();
        resultVo.setCode("Y");
        resultVo.setMessage("登录成功");
        return resultVo;
    }
}
