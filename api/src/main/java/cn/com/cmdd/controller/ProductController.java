package cn.com.cmdd.controller;



import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.dao.ProductDao;
import cn.com.cmdd.domain.Product;
import cn.com.cmdd.service.ProductService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class ProductController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductService productService;
		
	@RequestMapping(value="/product",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject saveProduct(HttpServletRequest request,
									HttpServletResponse response,
									@RequestBody Product product)
	{
											
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/											
											
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			productService.saveProduct(product);
		
		} catch (Exception e) {
				
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
				
		}
		return responseObject;
	}
	
	/*
	 * **\(1\) 获取菜品列表**

	GET /shop/:id/product/list？xxx
	
	*/
	@RequestMapping(value="/shop/{id}/product/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getProduct(
										
										HttpServletRequest request,
										HttpServletResponse response,
										@PathVariable("id")Integer shop_id
										
										){
											
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/											
																						
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			responseObject.msg = productService.getProduct(shop_id, null, null);
		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/product/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateProduct(
											
											HttpServletRequest request,
											HttpServletResponse response,
											@RequestBody Product product
											
											){
												
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/												
												
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {

			productService.updateProduct(product);

			HashMap<String,Object> resultMap = new HashMap<String,Object>();

			responseObject.msg="操作成功";

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}
	
	@RequestMapping(value="/product/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getProductById(
											
											HttpServletRequest request,
											HttpServletResponse response,		
											@PathVariable("id")Long id
											
											){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}	*/											
												
				
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {

			responseObject.msg = productDao.select(id);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}
	
	@RequestMapping(value="/product/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteProduct(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id)
	{
												
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/												
				
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			productService.deleteProduct(id);
						
		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}	
	
	@RequestMapping(value="/category/{id}/product/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getProductByCategory_id(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long category_id)
	{
														
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/														
																		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {					
			responseObject.msg = productService.getProduct(null, null, category_id);		
		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}
	
	@RequestMapping(value="/product/isUpload",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject isUpload(HttpServletRequest request,HttpServletResponse response){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			responseObject.msg=productDao.selectByIsUpload();
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/product/isUpload/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject isUpload(
			HttpServletRequest request,
			HttpServletResponse response,@PathVariable("id")Long id){
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			productDao.updateIsUpload(id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
}
