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

import com.rest.dao.first.First;
import com.rest.util.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestApplication.class)
@ActiveProfiles(value = "local")
@WebAppConfiguration
@Rollback
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	protected MockMvc mockMvc;
	
	private First first = null;
	
	@Before
	public void before() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		
		String firstUrl = "/first";
		ResultActions firstResultActions = mockMvc.perform(put(firstUrl).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		MvcResult firstMvcResult =  firstResultActions.andExpect(status().is2xxSuccessful())
													  .andDo(print())
													  .andReturn();
		
		first = JsonUtils.fromJson(firstMvcResult.getResponse().getContentAsString(), First.class);
	}
	
	@Test
	public void firstAll() throws Exception {
		String url = "/first";
		ResultActions resultActions = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		resultActions.andExpect(status().is2xxSuccessful())
					 .andExpect(jsonPath("$[0].firstNo", Matchers.equalTo(first.getFirstNo())))
					 .andExpect(jsonPath("$[0].message").value(first.getMessage()))
					 .andDo(print());
	}
	
	@Test
	public void firstMessages() throws Exception {
		String url = "/first/messages";
		ResultActions resultActions = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		resultActions.andExpect(status().is2xxSuccessful())
					 .andExpect(jsonPath("$.[1]").value(first.getMessage()))
					 .andDo(print());
	}
	
}