package com.my.jedis;


import com.my.vo.UserVO;
import org.springframework.stereotype.Component;

@Component("userJedisDao")
public class UserJedisDao extends  BaseJedis<UserVO> {

}
