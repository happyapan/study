package com.my.jedis;


import com.my.vo.ProductVO;
import org.springframework.stereotype.Component;

@Component("productJedisDao")
public class ProductJedisDao extends  BaseJedis<ProductVO> {


}
