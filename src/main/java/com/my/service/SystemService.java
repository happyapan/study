package com.my.service;

import com.my.StudyConstants;
import com.my.jedis.CommonJedisDao;
import com.my.jedis.ProductJedisDao;
import com.my.jedis.UserJedisDao;
import com.my.vo.ProductVO;
import com.my.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemService")
public class SystemService {

    @Autowired
    private UserJedisDao userJedisDao;

    @Autowired
    private CommonJedisDao commonJedisDao;

    public String createNewUser(UserVO user){
        if(commonJedisDao.isExist(StudyConstants.PREFIX_USER + user.getUserName())){
            return null;
        }else{
            user.setUserId(commonJedisDao.incr(StudyConstants.INDEX_USER));
            userJedisDao.set(user,StudyConstants.PREFIX_USER+user.getUserName(),StudyConstants.SEQ_ALL_USER);
            return StudyConstants.PREFIX_USER+user.getUserId();
        }
    }

    public UserVO searchUserByAccount(String account){
        return userJedisDao.find(StudyConstants.PREFIX_USER+account);
    }


}
