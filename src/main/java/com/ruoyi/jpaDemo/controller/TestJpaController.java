package com.ruoyi.jpaDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.jpaDemo.pojo.Test;
import com.ruoyi.jpaDemo.repository.TestRepository;

@RestController
@RequestMapping("testJpa")
public class TestJpaController {
	
	@Autowired
	TestRepository testRepository;
	
	@GetMapping("test")
	public List<Test> find (){
		return testRepository.findAll();
	}
	
	@GetMapping("save")
	public void save() {
		Test test = new Test();
		test.setName("测试");
		test.setSex(0);
		testRepository.save(test);
	}
	
	@GetMapping("del")
	public void del(Long id) {
		testRepository.deleteById(id);
	}

}
