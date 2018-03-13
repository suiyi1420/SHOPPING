package org.pussinboots.morning.os.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pussinboots.morning.common.base.BaseController;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.common.constant.CategoryConstantEnum;
import org.pussinboots.morning.product.common.enums.CommentTypeEnum;
import org.pussinboots.morning.product.common.enums.ProductRecommendTypeEnum;
import org.pussinboots.morning.product.pojo.vo.CategoryVO;
import org.pussinboots.morning.product.pojo.vo.ProductVO;
import org.pussinboots.morning.product.service.ICategoryService;
import org.pussinboots.morning.product.service.IProductRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* 项目名称：morning-os-web Maven Webapp   
* 类名称：ProductRecommendController   
* 类描述：商品推荐表示层控制器   
* 创建人：黎业德
* 创建时间：2017年4月11日 下午3:37:59   
*
 */
@Controller
@RequestMapping(value = "/recommend")
@Api(value = "商品推荐", description = "商品推荐")
public class ProductRecommendController extends BaseController {
	
	@Autowired
	private IProductRecommendService productRecommendService;
	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * GET 明星商品
	 * @return
	 */
	@ApiOperation(value = "明星商品", notes = "明星商品")  
	@GetMapping(value = "/star")
	@ResponseBody
	public Object listStar(Model model,PageInfo pageInfo) {
		Map<String,Object> map=new HashMap<>();
		List<ProductVO> products = productRecommendService.listByRecommendId(ProductRecommendTypeEnum.STAR.getType(),pageInfo);
		map.put("products", products);
		return map;
	}
	
	/**
	 * GET 为你推荐
	 * @return
	 */
	@ApiOperation(value = "为你推荐", notes = "为你推荐")  
	@GetMapping(value = "/popular")
	@ResponseBody
	public Object listPopular(Model model,PageInfo pageInfo) {
		Map<String,Object> map=new HashMap<>();
		List<ProductVO> products = productRecommendService.listByRecommendId(ProductRecommendTypeEnum.POPULAR.getType(),pageInfo);
		map.put("products", products);
		return map;
	}
	
	/**
	 * GET 热评商品
	 * @return
	 */
	@ApiOperation(value = "热评商品", notes = "热评商品")  
	@GetMapping(value = "/comment")
	@ResponseBody
	public Object listComment(Model model,PageInfo pageInfo) {
		Map<String,Object> map=new HashMap<>();
		List<ProductVO> products = productRecommendService.listComment(ProductRecommendTypeEnum.COMMENT.getType(),
				CommentTypeEnum.HIGH_GUALITY.getType(),pageInfo);
		map.put("products", products);
		return map;
	}
	
	/**
	 * GET 新品推荐
	 * @return
	 */
	@ApiOperation(value = "新品推荐", notes = "新品推荐")  
	@GetMapping(value = "/new")
	@ResponseBody
	public Object listNew(Model model,PageInfo pageInfo) {
		Map<String,Object> map=new HashMap<>();
		List<ProductVO> products = productRecommendService.listByRecommendId(ProductRecommendTypeEnum.NEW.getType(),pageInfo);
		map.put("products", products);
		return map;
	}

	@ApiOperation(value = "分类商品区热门分类", notes = "分类商品区热门分类")
	@GetMapping(value = "/hottype")
	@ResponseBody
	public Object listParentHot(Model model) {
		Map<String,Object> map=new HashMap<>();
		List<CategoryVO> categorys = categoryService.listParentHot(CategoryConstantEnum.CATEGORY_RECOMMEND_PRODUCT.getValue(),
				CategoryConstantEnum.CATEGORY_RECOMMEND_ADVERT.getValue(), CommentTypeEnum.HIGH_GUALITY.getType());
		model.addAttribute("categorys", categorys);
		map.put("categorys", categorys);
		return map;
	}

}
