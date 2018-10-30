package com.lh.product.repository;

import com.lh.product.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 具体如何编写查询方法见官方文档：Supported keywords inside method names
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
