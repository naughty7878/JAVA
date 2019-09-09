package com.test.swagger.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.swagger.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "用户相关接口") 
@RestController
@RequestMapping("/user")
public class UserController {

	@ApiOperation("新增用户接口")
	@PostMapping("/add")
	public boolean addUser(@RequestBody User user) {
		return false;
	}

	@GetMapping("/find/{id}")
	public User findById(@PathVariable("id") int id) {
		return new User();
	}

	@PutMapping("/update")
	public boolean update(@RequestBody User user) {
		return true;
	}

	@ApiIgnore
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable("id") int id) {
		return true;
	}
}