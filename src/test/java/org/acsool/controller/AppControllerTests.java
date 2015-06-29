package org.acsool.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.acsool.AcsoolApplication;
import org.acsool.dto.APICode;
import org.acsool.dto.SL0001;
import org.acsool.utils.JacksonUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AcsoolApplication.class)
public class AppControllerTests {
	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	Environment environment;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}

	@Test
	public void sl0001() throws Exception {
		APICode<SL0001> reqCode = new APICode<SL0001>();

		reqCode.tranCd = "SL0001";
		SL0001 sl = new SL0001();

		sl.slNo = "1";
		sl.artContent = "hello world";
		sl.artZanMaxCnt = 5;
		
		reqCode.tranData = sl;
		
		System.out.println(JacksonUtils.<APICode<SL0001>> objectToJson(reqCode));
		this.<SL0001> printJson(reqCode);
	}


	public <T> void printJson(APICode<T> reqCode) {
		try {
			this.mockMvc.perform(
					post("/api").contentType(MediaType.APPLICATION_JSON).content(
							JacksonUtils.<APICode<T>> objectToJson(reqCode).getBytes())).andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
