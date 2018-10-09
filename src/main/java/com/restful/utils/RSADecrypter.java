package com.restful.utils;

/**
 * RSA解密
 */

public class RSADecrypter {
    //后台解密key
    public static String PRIVATEKEY_NEW = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMfvGKhp2DH2Av/w"
            + "mQ62y1/HMb/ZpSub3M0HKNrFA+sUcqmcIMhN6qopnAmyFTU7JQl69SeXyPCVz9ll"
            + "tQu2dk/Q7RbPYG4lIgJf9QmC6qmEzMqfit0tOdOnMaf/P45SO70xUoZ276jpwVdy"
            + "WjEHUVKoQMNkodybyK8e8PvbngaFAgMBAAECgYA0Lqpf4mhD+fKkfFZmab1A/Ut1"
            + "yEd8z0xc0t6N4tf39jlLHW3M+dEiooOQYEN6xjoPdGEh+VK/wEokcpBDQ1czXO/J"
            + "30agt/Nhl9Gq94qoq8uXJnKZmkY5SsIegTYD7D1K/TnvLE6tDCyC0u7jPxOHz17O"
            + "iZySxVWw9/5qexz8gQJBAP4CK2ampMqPbG0k4o7xXLApoyoYg27mlpnfPLv/BL5M"
            + "qbFDw7XhgVkH3x67AFtUFFm32H1YbRv3wYeh3TES5iECQQDJgGRG0cNEz1soVDf/"
            + "IR9bHw/ICPEKXzfUO+fWg3cPhedqnKqBRVSOeJ7nQSsqLJVxSuIgfJmSN8XFLN6d"
            + "+svlAkAEuX+bKjBYgDBxG0WHiR/DOxMI3mnbbziP5iIYERdCfuSNIoiKYoZoZIgW"
            + "5z2LdVXnkU7ajgGBiqsz3aPinMphAkBp6iLLVtbdBGFWAW6tnCtvKhSRgKGyYfpm"
            + "hFaIWvHqJuhNEaxN5rNbb+uWgpq9wFGNC19w1A2k3cwGqF8biwhBAkACHm83NKCG"
            + "NUUArAI5J+TrljFs+p10bF/xtAJdO+XsJ01cguBkBPxkvChalXKFo4rE3WsjWGNj"
            + "S92I+Th7YnQ9";

    /**
     * 将base64(String)解密为byte[]
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) {
        try {
            return (new sun.misc.BASE64Decoder()).decodeBuffer(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 使用私钥进行解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) {
        try {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 取得私钥
            java.security.spec.PKCS8EncodedKeySpec pkcs8KeySpec = new java.security.spec.PKCS8EncodedKeySpec(keyBytes);
            java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");
            java.security.Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据解密
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用私钥进行解密
     *
     * @param encodedData
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encodedData, String key, int originalDataLength) {
        String result = null;
        try {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 对原始加密数据解密
            byte[] data = decryptBASE64(encodedData);

            // 取得私钥
            java.security.spec.PKCS8EncodedKeySpec pkcs8KeySpec = new java.security.spec.PKCS8EncodedKeySpec(keyBytes);
            java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");
            java.security.Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据解密
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, privateKey);

            byte[] decodedData = cipher.doFinal(data);
            String outputStr = new String(decodedData);

            //前端加密时加密数据长度变成172位，只取原始数据对应长度的字符串即可
            if (originalDataLength > 0) {
                result = outputStr.substring(outputStr.length() - originalDataLength, outputStr.length());
            } else {
                result = outputStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
