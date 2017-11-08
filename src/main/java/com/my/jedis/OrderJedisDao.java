package com.my.jedis;


import com.my.vo.OrderVO;
import org.springframework.stereotype.Component;

@Component("orderJedisDao")
public class OrderJedisDao extends  BaseJedis<OrderVO> {
}
