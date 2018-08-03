package com.lh.product.controller;

import com.lh.product.dataobject.ProductCategory;
import com.lh.product.dataobject.ProductInfo;
import com.lh.product.service.CategoryService;
import com.lh.product.service.ProductService;
import com.lh.product.utils.ResultVoUtil;
import com.lh.product.vo.ProductInfoVo;
import com.lh.product.vo.ProductVo;
import com.lh.product.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
    public ResultVo<ProductVo> list() {
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 获取类目type列表
        List<Integer> integerList = productInfoList.stream().map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(integerList);

        //4. 构造数据
        List<ProductVo> ProductVo = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                ProductInfoVo productInfoVo = new ProductInfoVo();
                BeanUtils.copyProperties(productInfo,productInfoVo);
                productInfoVos.add(productInfoVo);
            }
            productVo.setProductInfoVOList(productInfoVos);
            ProductVo.add(productVo);
        }

        return ResultVoUtil.success(ProductVo);
    }
}
