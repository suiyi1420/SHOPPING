package org.pussinboots.morning.product.service.impl;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import org.pussinboots.morning.common.enums.StatusEnum;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.entity.Product;
import org.pussinboots.morning.product.entity.ProductRecommend;
import org.pussinboots.morning.product.entity.Recommend;
import org.pussinboots.morning.product.mapper.ProductRecommendMapper;
import org.pussinboots.morning.product.mapper.RecommendMapper;
import org.pussinboots.morning.product.pojo.vo.ProductVO;
import org.pussinboots.morning.product.service.IProductRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * 
* 项目名称：morning-product-service   
* 类名称：ProductRecommendServiceImpl   
* 类描述：ProductRecommend / 商品推荐表 业务逻辑层接口实现     
* 创建人：黎业德
* 创建时间：2017年4月11日 下午3:17:08   
*
 */
@Service
public class ProductRecommendServiceImpl extends ServiceImpl<ProductRecommendMapper, ProductRecommend> implements IProductRecommendService {
	
	@Autowired
	private RecommendMapper recommendMapper;
	@Autowired
	private ProductRecommendMapper productRecommendMapper;
	
	@Override
	public List<ProductVO> listByRecommendId(Long recommendId,PageInfo pageInfo) {
		// 根据推荐位ID查找推荐位信息
		Recommend queryRecommend = new Recommend();
		queryRecommend.setStatus(StatusEnum.SHOW.getStatus());
		queryRecommend.setRecommendId(recommendId);
		Recommend recommend = recommendMapper.selectOne(queryRecommend);
		if(pageInfo.getCurrent()==0){pageInfo.setCurrent(1);}
		pageInfo.setLimit(10);
		pageInfo.offset();
		if (recommend != null) {
			return productRecommendMapper.listByRecommendId(recommendId, StatusEnum.SHOW.getStatus(),
					recommend.getShowNumber(), new Date(),pageInfo);
		}
		return null;
	}

	@Override
	public List<ProductVO> listComment(Long recommendId, Integer type,PageInfo pageInfo) {
		// 根据推荐位ID查找推荐位信息
		Recommend queryRecommend = new Recommend();
		queryRecommend.setStatus(StatusEnum.SHOW.getStatus());
		queryRecommend.setRecommendId(recommendId);
		Recommend recommend = recommendMapper.selectOne(queryRecommend);
		if(pageInfo.getCurrent()==0){pageInfo.setCurrent(1);}
		pageInfo.setLimit(10);
		pageInfo.offset();
		if (recommend != null) {
			List<ProductVO> products = productRecommendMapper.listByRecommendId(recommendId,
					StatusEnum.SHOW.getStatus(), recommend.getShowNumber(), new Date(),pageInfo);

			// 对查询出来的商品列表进行遍历,随机选取一条优质评论
			for (ProductVO product : products) {
				ProductVO vo = productRecommendMapper.getComment(product.getProductId(), type,
						StatusEnum.SHOW.getStatus());
				if (vo != null) {
					product.setUserName(vo.getUserName());
					product.setContent(vo.getContent());
					product.setCommentId(vo.getCommentId());
				}
			}
			return products;
		}
		return null;
	}
}
