package com.restful.controller;

import com.restful.utils.RSADecrypter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date
 *
 */
@RestController
@RequestMapping(value = "/cry")
public class CrypterController {
    @PostMapping("/crypter")
    public void decrypter(String pwd) {
        pwd = pwd.replace(" ", "+");
        String str = RSADecrypter.decryptByPrivateKey(pwd, RSADecrypter.PRIVATEKEY_NEW, 6);
        System.out.println("RSA解密后的字符串：" + str);
    }
}
