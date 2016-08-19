package com.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.service.TestService;
import com.rest.dao.first.First;
import com.rest.dao.second.Second;

@RestController
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping(value = {"/first"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public First first(){
		return testService.first();
	}
	
	@RequestMapping(value = {"/firstAll"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<First> firsts(){
		return testService.firsts();
	}
	
	@RequestMapping(value = {"/firstMessages"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<String> firstMessages(){
		return testService.firstMessages();
	}
	
	@RequestMapping(value = {"/second"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Second second(){
		return testService.second();
	}
	
	@RequestMapping(value = {"/secondAll"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Second> seconds(){
		return testService.seconds();
	}
}
