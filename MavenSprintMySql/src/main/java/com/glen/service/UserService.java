package com.glen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glen.dao.LoginLogDao;
import com.glen.dao.UserDao;
import com.glen.domain.LoginLog;
import com.glen.domain.User;
 
 @Service
 public class UserService {
     @Autowired
     private UserDao userdao;
     @Autowired
     private LoginLogDao loginLogDao;
 
     public boolean hasMatchUser(String name, String password) {
         int matchCount = userdao.getMatchCount(name, password);
         return matchCount > 0;
     }
 
     public User findUserByUsername(String username) {
         return userdao.findUserByName(username);
     }
 
     public void loginSuccess(User user) {
         user.setCredits(user.getCredits() + 5);
         LoginLog loginLog = new LoginLog();
         loginLog.setUserId(user.getUserId());
         loginLog.setIp(user.getLastIp());
         loginLog.setLoginDate(user.getLastVisit());
         userdao.updateLoginInfo(user);
         loginLogDao.insertLoginLog(loginLog);
     }
 }