package com.example.demo;

import com.example.demo.mapper.AMapper;
import com.example.demo.mapper.BMapper;
import com.example.demo.myannotation.EnableScan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@EnableScan("com.example.demo.mapper")
class DemoApplicationTests {
	@Resource
	private AMapper aMapper;

	@Resource
	private BMapper bMapper;

	@Test
	void test() {

		aMapper.hello("abc");

		bMapper.hello(123);
	}

}
