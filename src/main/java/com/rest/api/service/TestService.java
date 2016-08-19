package com.rest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.first.First;
import com.rest.dao.first.FirstRepository;
import com.rest.dao.second.Second;
import com.rest.dao.second.SecondRepository;

@Service
public class TestService {
	
	@Autowired
	private FirstRepository firstRepository;
	
	@Autowired
	private SecondRepository secondRepository;

	public First first() {
    	First first = new First();
    	first.setMessage("first");
	    return firstRepository.save(first);
	}
	
	public List<First> firsts() {
		return firstRepository.findAll();
	}

	public List<String> firstMessages() {
		return firstRepository.messages();
	}

	public Second second() {
		Second second = new Second();
		second.setMessage("second");
		return secondRepository.save(second);
	}
	
	public List<Second> seconds() {
		return secondRepository.findAll();
	}

}
