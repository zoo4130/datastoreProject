package com.gcp.crud;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcp.crud.CRUDService;
import com.gcp.crud.DatastoreService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private CRUDService crudService;
	
	@Autowired
	private DatastoreService datatoreService;
	
	/**
	 * home.jsp VIEW
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		System.out.println("===============================");
		System.out.println("Java CRUD test");
		
		
		return "home";
	}
	
	/**
	 * java Select ($.ajax @RequestBody)
	 */ 
	@ResponseBody 
	@RequestMapping(value = "/select",produces="application/json", method = RequestMethod.POST)
	public List<Map<String, Object>> javaSelect(@RequestBody  Map<String,Object> map) throws Exception{
		
		System.out.println("===============================");
		System.out.println("Java CRUD Select");
		
		//crudService.select(param);
		List<Map<String, Object>> result = datatoreService.select();
		
		return result;
	}
	
	/**
	 * java Insert
	 */ 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void javaInsert(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("===============================");
		System.out.println("Java CRUD Insert");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("no", "004");
		param.put("name", "김철수");
		param.put("age", "15");
		
		datatoreService.insert();
		//crudService.insert(param);
		
		//재조회
		//crudService.select(null);
	}
	
	/**
	 * java Update
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void javaUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("===============================");
		System.out.println("Java CRUD Update");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("no", "002");
		param.put("age", "100");
		
		datatoreService.update();
		//crudService.update(param);
		
		//재조회
		//crudService.select(null);
	}
	
	/**
	 * java Delete
	 */ 
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void javaDelete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("===============================");
		System.out.println("Java CRUD Delete");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("no", "004");
		
		datatoreService.delete();
		//crudService.delete(param);
		
		//재조회
		//crudService.select(null);
	}	
}
