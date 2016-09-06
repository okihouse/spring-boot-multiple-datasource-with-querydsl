package com.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.service.CheckpointService;
import com.rest.dao.first.First;

@RestController
@RequestMapping(value = "/first")
public class FirstController {
	
	@Autowired
	private CheckpointService checkpointService;
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public First save(){
		return checkpointService.firstSave();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<First> firsts(){
		return checkpointService.firsts();
	}
	
	@RequestMapping(value = {"/messages"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<String> messages(){
		return checkpointService.firstMessages();
	}
	
}
