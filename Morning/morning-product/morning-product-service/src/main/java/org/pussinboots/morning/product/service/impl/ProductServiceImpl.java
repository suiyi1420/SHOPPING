package org.pussinboots.morning.product.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.entity.Category;
import org.pussinboots.morning.product.entity.Product;
import org.pussinboots.morning.product.mapper.ProductMapper;
import org.pussinboots.morning.product.pojo.vo.ProductVO;
import org.pussinboots.morning.product.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
* 项目名称：morning-product-service   
* 类名称：ProductServiceImpl   
* 类描述：Product / 商品表 业务逻辑层接口实现        
* 创建人：黎业德
* 创建时间：2017年4月11日 下午3:17:31   
*
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public ProductVO getByNumber(Long productNumber, Integer status) {
		return productMapper.getByNumber(productNumber, status);
	}

	public BasePageDTO<Product> getList(PageInfo pageInfo,String search,Long category_id){
		Page<Category> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
		List<Product> productList=productMapper.getList(category_id,pageInfo,search,page);
		pageInfo.setTotal(page.getTotal());
		return new BasePageDTO<Product>(pageInfo,productList);
	}
	
}
