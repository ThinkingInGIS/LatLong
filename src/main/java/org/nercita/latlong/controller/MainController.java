package org.nercita.latlong.controller;

import org.nercita.latlong.service.LatLongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class MainController { 
	
	private String viewBasePath = "/manage";
	
	@Autowired
	private LatLongService llService;
	
	@GetMapping("/") 
	public String index(ModelMap model){
		
		return viewBasePath + "/home";	
	}
	
	@RequestMapping(value = "/main")
	public String main() {
		return viewBasePath + "/main";	
	}
	
	@RequestMapping(value = "/search")
	public String search(@RequestParam String address, ModelMap model) {
		String res = llService.getLatLong(address, "北京市");
		String result = JSONObject.parseObject(res).get("result").toString();
		String location = "";
		String lat = "";
		String lng = "";
		try {
			location = JSONObject.parseObject(result).get("location").toString();
			lat = JSONObject.parseObject(location).get("lat").toString();
			lng = JSONObject.parseObject(location).get("lng").toString();
			model.addAttribute("address", address);
			model.addAttribute("lat", lat);
			model.addAttribute("lng", lng);
			return viewBasePath + "/search";
		}catch(Exception e) {
			model.addAttribute("result", "找到不输入地址的经纬度信息");
			return viewBasePath + "/not_found";
		}
		
	}
	
	//ajax 方式输出lat lng
	@RequestMapping(value = "/findLatLng/{address}")
	public @ResponseBody String findLatLng(@PathVariable String address) {
		System.out.println("address = " + address);
		String res = llService.getLatLong(address, "北京市");
		System.out.println("res = " + res);
		String result = JSONObject.parseObject(res).get("result").toString();
		System.out.println("result = " + result);
		try {
			String location = JSONObject.parseObject(result).get("location").toString();
			String lat = JSONObject.parseObject(location).get("lat").toString();
			String lng = JSONObject.parseObject(location).get("lng").toString();
			System.out.println("lat = " + lat);
			System.out.println("lng = " + lng);

			return viewBasePath + "/search";
		}catch(Exception e) {
			
			return viewBasePath + "/not_found";
		}
		
	}

}
