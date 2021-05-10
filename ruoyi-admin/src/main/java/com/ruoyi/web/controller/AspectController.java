package com.ruoyi.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.annotation.AspectTest;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * description: 测试 @AspectTest
 * @version v1.0
 * @author w
 * @date 2021年5月10日下午5:32:01
 **/
@RestController
public class AspectController {

	@AspectTest
	@RequestMapping(value = "/AspectController")
	@ResponseBody
	public AjaxResult te() {
		System.out.println("==========");
		return AjaxResult.success("AspectController");
	}
}
