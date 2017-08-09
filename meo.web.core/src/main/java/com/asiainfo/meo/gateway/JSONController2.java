package com.asiainfo.meo.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kfc/brands")
public class JSONController2 {

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody
	Shop getShopInJSON(@PathVariable String name) {

		Shop shop = new Shop();
		shop.setName(name);
		System.out.println(shop.getName());
		shop.setStaffName(new String[] { "mkyong1", "mkyong2" });
////		 LocalCacheManager localCacheManager = ServiceLocatorFactory.getService(LocalCacheManager.class);
//	     localCacheManager.getCache("cache").put("1", "2");
//	     System.out.println(localCacheManager.getCache("cache").get("1",Object.class));
		return shop;

	}

}