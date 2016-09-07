package com.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.rest.dao.second.Second;
import com.rest.util.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestApplication.class)
@ActiveProfiles(value = "local")
@WebAppConfiguration
@Rollback
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecondTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	protected MockMvc mockMvc;
	
	private Second second = null;
	
	@Before
	public void before() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		
		String secondUrl = "/second";
		ResultActions secondResultActions = mockMvc.perform(put(secondUrl).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		MvcResult secondMvcResult =  secondResultActions.andExpect(status().is2xxSuccessful())
														.andDo(print())
														.andReturn();
		
		second = JsonUtils.fromJson(secondMvcResult.getResponse().getContentAsString(), Second.class);
	}
	
	@Test
	public void secondAll() throws Exception {
		String url = "/second";
		ResultActions resultActions = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		resultActions.andExpect(status().is2xxSuccessful())
					 .andExpect(jsonPath("$[0].secondNo", Matchers.equalTo(second.getSecondNo())))
					 .andExpect(jsonPath("$[0].message").value(second.getMessage()))
					 .andDo(print());
	}
	
	@Test
	public void secondMessages() throws Exception {
		String url = "/second/messages";
		ResultActions resultActions = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		resultActions.andExpect(status().is2xxSuccessful())
					 .andExpect(jsonPath("$.[1]").value(second.getMessage()))
					 .andDo(print());
	}
	
}
