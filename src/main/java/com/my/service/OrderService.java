package com.my.service;

import com.my.StudyConstants;
import com.my.jedis.CommonJedisDao;
import com.my.jedis.OrderJedisDao;
import com.my.jedis.ProductJedisDao;
import com.my.tools.DateUtil;
import com.my.vo.OrderInfoVO;
import com.my.vo.OrderVO;
import com.my.vo.ProductVO;
import com.my.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private CommonJedisDao commonJedisDao;

    @Autowired
    private ProductJedisDao productJedisDao;

    @Autowired
    private OrderJedisDao orderJedisDao;

    /**
     * 加入购物车，返回购物车中所有数据
     *
     * @param userAccount 用户账号
     * @param products    N个商品ID
     * @return
     */
    public void addToShoppingCart(String userAccount, String... products) {
        commonJedisDao.lpush(StudyConstants.PROFIX_SHOPPING_CART + userAccount, products);
//        return this.fetchShoppingCartItems(userAccount);
    }

    /**
     * 加入购物车，返回购物车中所有数据
     *
     * @param userAccount 用户账号
     * @param product     商品ID
     * @return
     */
    public void delItemFromShoppingCart(String userAccount, String product) {
        commonJedisDao.lrem(StudyConstants.PROFIX_SHOPPING_CART + userAccount, product);
//        return productJedisDao.findAllFromSeqWithProfix(StudyConstants.PROFIX_SHOPPING_CART+userAccount,StudyConstants.PROFIX_PRODUCT);
    }

    public List<ProductVO> fetchShoppingCartItems(String userAccount) {
        return productJedisDao.findAllFromSeqWithProfix(StudyConstants.PROFIX_SHOPPING_CART + userAccount, StudyConstants.PREFIX_PRODUCT);
    }

    /**
     * place order
     * @param userAccount 用户账号
     * @return
     */
    public String placeOrder(String userAccount) {
        String orderId = "";
        //获得购物车数据
        List<ProductVO> shoppingList = this.fetchShoppingCartItems(userAccount);
        float totalPrice = 0.0F;
        String items = "";

        //build 订单信息
        if (shoppingList != null && shoppingList.size() > 0) {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(commonJedisDao.incr(StudyConstants.INDEX_ORDER));
            orderVO.setCreateDate(DateUtil.GET_CURRENT_TIME());
            for (ProductVO item : shoppingList) {
                if (!items.equals("")) {
                    items += ",";
                }
                items += item.getId();
                totalPrice += Float.parseFloat(item.getPrice());
            }
            orderVO.setPrice("" + totalPrice);
            orderVO.setItems(items);
            orderVO.setAccount(userAccount);

            //存储订单信息,存储该订单ID到用户订单列表中
            orderJedisDao.set(orderVO, StudyConstants.PREFIX_ORDER + orderVO.getOrderId(), StudyConstants.PROFIX_ORDER_LIST+userAccount);

            //存储该订单ID到列表中，用于后续TASK JOB 处理
            commonJedisDao.lpush(StudyConstants.SEQ_TASK_ORDER, StudyConstants.PREFIX_ORDER + orderVO.getOrderId());

            //删除用户购物车
            commonJedisDao.del(StudyConstants.PROFIX_SHOPPING_CART+ userAccount);

            orderId = orderVO.getOrderId();
        }
        return orderId;


    }


    /**
     * 查找用户所有订单
     * @param user
     * @return
     */
    public List<OrderInfoVO> findUserAllOrder(UserVO user){
        List<OrderInfoVO> orderInfos=null;
        List<OrderVO> orders=orderJedisDao.findAllFromSeq(StudyConstants.PROFIX_ORDER_LIST+ user.getUserName());
        if(orders!=null && orders.size()>0){
            orderInfos=new ArrayList<OrderInfoVO>();
            for(OrderVO oneOrder:orders){
                OrderInfoVO oneOrderInfo=new OrderInfoVO();

                try {
                    BeanUtils.copyProperties(oneOrder,oneOrderInfo);
                } catch (Exception e) {
                   log.error(e.getMessage());
                }
                String []productIds=oneOrder.getItems().split(",");
                List<ProductVO> orderProducts=new ArrayList<ProductVO>();
                for(String oneProductId:productIds){
                    if(commonJedisDao.isExist(StudyConstants.PREFIX_PRODUCT+oneProductId)){
                        orderProducts.add(this.productJedisDao.find(StudyConstants.PREFIX_PRODUCT+oneProductId));
                    }
                }
                oneOrderInfo.setProducts(orderProducts);
                orderInfos.add(oneOrderInfo);
            }
        }

        return orderInfos;
    }

}
