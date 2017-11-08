package com.my.service;

import com.my.StudyConstants;
import com.my.jedis.CommonJedisDao;
import com.my.jedis.ProductJedisDao;
import com.my.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductService {

    @Autowired
    private ProductJedisDao productJedisDao;

    @Autowired
    private CommonJedisDao commonJedisDao;

    public String createOneProduct(ProductVO productVO) {
        productVO.setId(commonJedisDao.incr(StudyConstants.INDEX_PRODUCT));
        productJedisDao.set(productVO, StudyConstants.PREFIX_PRODUCT + productVO.getId(), StudyConstants.SEQ_ALL_PRODUCT);
        return StudyConstants.PREFIX_PRODUCT + productVO.getId();
    }

    public ProductVO findProduct(String identityId) {
        return productJedisDao.find(StudyConstants.PREFIX_PRODUCT + identityId);
    }

    public boolean isProductExist(String productId){
        return this.commonJedisDao.isExist(StudyConstants.PREFIX_PRODUCT+productId);
    }


    public List<ProductVO> findAllProduct() {
        return productJedisDao.findAllFromSeq(StudyConstants.SEQ_ALL_PRODUCT);
    }

}
