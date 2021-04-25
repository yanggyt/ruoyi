package com.ruoyi.content.utils;

import com.ruoyi.content.domain.CmsSysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "MD5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(CmsSysUser operator) {
        operator.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithmName, operator.getPwd(),
                ByteSource.Util.bytes(operator.getEmail() + operator.getSalt()), hashIterations).toHex();
        operator.setPwd(newPassword);
    }

    public void encryptCkeckPassword(CmsSysUser operator) {
//		operator.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithmName, operator.getPwd(),
                ByteSource.Util.bytes(operator.getEmail() + operator.getSalt()), hashIterations).toHex();
        operator.setPwd(newPassword);
    }

    public static void main(String[] args) {
        // TblWmsOperator operator = new TblWmsOperator();
        // operator.setPwd("123456");
        // operator.setEmail("lwadsy@aliyun.com");
        // operator.setSalt("75f4749e3648284edb4ea94a5618f71e");
        // new PasswordHelper().encryptPassword(operator);

        String salt = new PasswordHelper().randomNumberGenerator.nextBytes().toHex();
        System.out.println(salt);
        String newPassword = new SimpleHash("MD5", "123456", ByteSource.Util.bytes("lwadsy@aliyun.com" + salt), 2)
                .toHex();
        System.out.println("11111111111111111111111111111============" + newPassword + "---" + salt);

        // System.out.println(ByteSource.Util.bytes(operator.getEmail() +
        // operator.getSalt()));
    }
}
