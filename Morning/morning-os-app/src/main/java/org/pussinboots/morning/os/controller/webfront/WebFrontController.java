package org.pussinboots.morning.os.controller.webfront;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pussinboots.morning.common.base.BaseController;
import org.pussinboots.morning.online.common.enums.AdvertTypeEnum;
import org.pussinboots.morning.online.common.enums.NavigationBarTypeEnum;
import org.pussinboots.morning.online.entity.AdvertDetail;
import org.pussinboots.morning.online.entity.NavigationBar;
import org.pussinboots.morning.online.service.IAdvertDetailService;
import org.pussinboots.morning.online.service.INavigationBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* 项目名称：morning-os-web Maven Webapp   
* 类名称：WebFrontController   
* 类描述：商城首页表示层控制器       
* 创建人：黎业德
* 创建时间：2017年4月9日 下午4:59:20   
*
 */
@Controller
@Api(value = "商城首页", description = "商城首页")
public class WebFrontController extends BaseController {
	
	@Autowired
	private IAdvertDetailService advertDetailService;
	@Autowired
	private INavigationBarService navigationBarService;
	
	/**
	 * GET 商城首页
	 * @return
	 */
	@ApiOperation(value = "商城首页2", notes = "商城首页展示页面2")
	@GetMapping(value = "/index")
	@ResponseBody
	public Map<String,Object> index() {
		Map<String,Object> map=new HashMap<>();
		// 首页-广告栏-左部导航栏
		List<NavigationBar> indexAdvertLeft = navigationBarService
				.listByNavigationId(NavigationBarTypeEnum.INDEX_ADVERT_LEFT.getType());
		map.put("indexAdvertLeft", indexAdvertLeft);

		// 首页轮播广告列表
		List<AdvertDetail> indexCarouselImgs = advertDetailService
				.listByAdvertId(AdvertTypeEnum.INDEX_CAROUSEL.getType());
		map.put("indexCarouselImgs", indexCarouselImgs);

		// 首页热点广告列表
		List<AdvertDetail> indexHotAdvertImgs = advertDetailService
				.listByAdvertId(AdvertTypeEnum.INDEX_HOT_ADVERT.getType());
		map.put("indexHotAdvertImgs", indexHotAdvertImgs);
		
		return map;
	}
}
