package cn.smarttoo.service;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smarttoo.dao.UserInfoRepository;
import cn.smarttoo.pojo.UserInfo;
 
@Service
public class UserInfoServiceImpl implements UserInfoService{
   
    @Resource
    private UserInfoRepository userInfoRepository;
   
    @Override
    public UserInfo findByUsername(String username) {
       System.out.println("UserInfoServiceImpl.findByUsername()");
       return userInfoRepository.findByUsername(username);
    }
   
}