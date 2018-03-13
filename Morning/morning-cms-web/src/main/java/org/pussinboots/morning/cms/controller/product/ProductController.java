package org.pussinboots.morning.cms.controller.product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.pussinboots.morning.cms.common.result.CmsPageResult;
import org.pussinboots.morning.common.base.BaseController;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.entity.Category;
import org.pussinboots.morning.product.entity.Product;
import org.pussinboots.morning.product.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Killer on 2017/12/29.
 */
@Controller
@RequestMapping(value = "/product/list")
@Api(value = "商品管理", description = "商品管理")
public class ProductController extends BaseController {
    @Autowired
    private ProductServiceImpl productService;

    @ApiOperation(value = "商品管理页面", notes = "商品管理页面")
    @RequiresPermissions("product:list:view")
    @GetMapping(value = "/view")
    public String getProductPage(Model model){
        return "/modules/product/product_list";
    }

    @ApiOperation(value = "获取商品列表", notes = "根据分页信息/搜索内容/商品分类获取商品列表")
    @RequiresPermissions("product:list:view")
    @GetMapping(value = "/gid/{category_id}")
    @ResponseBody
    public Object listProduct(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search,
                              @PathVariable("category_id") Long category_id){
        BasePageDTO<Product> basePageDTO = productService.getList(pageInfo,search,category_id);
        return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
        //return productService.getList(map);
    }

    @ApiOperation(value = "商品信息修改页面", notes = "商品信息修改页面")
    @RequiresPermissions("product:list:edit")
    @GetMapping(value = "/{product_id}/edit")
    public String editProductPage(Model model, @PathVariable("product_id") Long productId){
        Product product=productService.selectById(productId);
        model.addAttribute("product",product);
        return "/modules/product/product_edit";
    }
}
