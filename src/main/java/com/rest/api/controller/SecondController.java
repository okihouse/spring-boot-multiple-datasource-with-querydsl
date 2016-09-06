package com.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.service.CheckpointService;
import com.rest.dao.second.Second;

@RestController
@RequestMapping(value = "/second")
public class SecondController {
	
	@Autowired
	private CheckpointService checkpointService;

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Second save(){
		return checkpointService.secondSave();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Second> seconds(){
		return checkpointService.seconds();
	}
	
	@RequestMapping(value = {"/messages"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<String> messages(){
		return checkpointService.secondMessages();
	}
	
}
