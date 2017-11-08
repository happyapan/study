package com.my.action;

import com.my.service.ProductService;
import com.my.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductAction {


    @Autowired
    private ProductService productService;


    @RequestMapping(value="/detail")
    public
    String  productInfo(@RequestParam String id,ModelMap model,HttpServletRequest request,HttpServletResponse response){
        List<ProductVO> product =new ArrayList<ProductVO>();
        product.add(this.productService.findProduct(id));
        model.put("product",product ) ;
        return "productDetail";
    }
    @RequestMapping(value="/detail/all")
    public String  allProductInfo(ModelMap model){

        model.put("product",this.productService.findAllProduct() ) ;
        return "productDetail";
    }

    @RequestMapping(value="/new/{name}/{price}/{desc}",method = RequestMethod.GET)
    public
    @ResponseBody
     String createProduct(@PathVariable String name,@PathVariable String price,@PathVariable String desc){
        ProductVO oneProduct=new ProductVO();
        oneProduct.setName(name);
        oneProduct.setPrice(price);
        oneProduct.setDesc(desc);
        return this.productService.createOneProduct(oneProduct);
    }


}
