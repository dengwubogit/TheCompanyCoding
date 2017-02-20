package cn.smarttoo.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    //实现密码比较  第一个参数:代表用户在界面上输入的用户名和密码  第二个参数:代表用户在数据库保存的密码
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //向下转型得到用户的输入信息
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //1.将用户在界面上输入的密码获取出来---->撒盐
        String afterPwd=Encrypt.md5(new String (upToken.getPassword()), upToken.getUsername());
        System.out.println("new String="+new String (upToken.getPassword())+"|||||||||  toString="+upToken.getPassword().toString());
        //2. 得到数据库加密数据
        Object dbPwd = info.getCredentials();
               //如果返回值为false就会抛出异常
        return super.equals(afterPwd, dbPwd);
    }    
}  
