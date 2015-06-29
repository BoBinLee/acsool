package org.acsool.service;

import java.util.HashMap;

import org.acsool.AcsoolApplication;
import org.acsool.dto.APICode;
import org.acsool.dto.PS0001;
import org.acsool.dto.PS0002;
import org.acsool.dto.SL0001;
import org.acsool.dto.SL0002;
import org.acsool.dto.SL0003;
import org.acsool.dto.SL0004;
import org.acsool.dto.SL0005;
import org.acsool.dto.SL0006;
import org.acsool.dto.SL0007;
import org.acsool.dto.SL0008;
import org.acsool.utils.JacksonUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AcsoolApplication.class)
public class ServiceTests {
	@Autowired
	public SLService slService;
	@Autowired
	public PSService psService;
	
	@Test
	@Ignore
	public void sl0001() {
		APICode reqCode = new APICode<SL0001>();
		HashMap<String, String> sl = new HashMap<String, String>();

		sl.put("_sl_no", "3");
		sl.put("_art_content", "test3");
		sl.put("_art_zan_max_cnt", "10");
		reqCode.tranCd = "SL0001";
		reqCode.tranData = sl;

		APICode resCode = slService.resSL0001(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}

	@Test
	@Ignore
	public void sl0002() {
		APICode reqCode = new APICode<SL0002>();
		HashMap<String, String> sl = new HashMap<String, String>();

		sl.put("_sl_no", "3");
		sl.put("_req_po_cnt", "10");
		sl.put("_req_po_no", "14");
		reqCode.tranCd = "SL0002";
		reqCode.tranData = sl;

		APICode resCode = slService.resSL0002(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}

	@Test
	@Ignore
	public void sl0003() {
		APICode reqCode = new APICode<SL0003>();
		HashMap<String, String> sl = new HashMap<String, String>();

		sl.put("_sl_no", "1");
		sl.put("_art_no", "2");
		reqCode.tranCd = "SL0003";
		reqCode.tranData = sl;

		APICode resCode = slService.resSL0003(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}
	
	@Test
	@Ignore
	public void sl0004() {
		APICode reqCode = new APICode<SL0004>();
		HashMap<String, String> sl = new HashMap<String, String>();

		sl.put("_sl_no", "1");
		sl.put("_art_no", "15");
		sl.put("_reply_subject", "test");
		sl.put("_reply_content", "testtest");
		sl.put("_reply_emotion", "123");
		
		reqCode.tranCd = "SL0003";
		reqCode.tranData = sl;

		APICode resCode = slService.resSL0004(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}
	
	@Test
	@Ignore
	public void sl0005() {
		APICode reqCode = new APICode<SL0005>();
		HashMap<String, String> sl = new HashMap<String, String>();
	
		sl.put("_sl_no", "1");
		sl.put("_art_no", "15");
		sl.put("_msg_yn", "Y");
		
		reqCode.tranCd = "SL0005";
		reqCode.tranData = sl;

		APICode resCode = slService.resSL0005(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}
	
	@Test
//	@Ignore
	public void sl0006() {
		APICode reqCode = new APICode<SL0006>();
		HashMap<String, String> sl = new HashMap<String, String>();

		sl.put("_sl_no", "3");
		reqCode.tranCd = "SL0006";
		reqCode.tranData = sl;

		APICode resCode = 	slService.resSL0006(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}
	
	@Test
	@Ignore
	public void sl0007() {
		APICode reqCode = new APICode<SL0007>();
		HashMap<String, String> sl = new HashMap<String, String>();

		sl.put("_sl_no", "1");
		reqCode.tranCd = "SL0007";
		reqCode.tranData = sl;

		APICode resCode = 	slService.resSL0007(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}
	
	@Test
	@Ignore
	public void sl0008() {
		APICode reqCode = new APICode<SL0008>();
		HashMap<String, String> sl = new HashMap<String, String>();
		
		sl.put("_art_no", "15");
		sl.put("_req_po_cnt", "10");
		sl.put("_req_po_no", "0");
		
		reqCode.tranCd = "SL0008";
		reqCode.tranData = sl;

		APICode resCode = slService.resSL0008(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}
	
	@Test
	@Ignore
	public void ps0001() {
		APICode reqCode = new APICode<PS0001>();
		HashMap<String, String> sl = new HashMap<String, String>();
		
		sl.put("_sl_no", "1");
		sl.put("_push_token_id", "abcdef");
		sl.put("_push_yn", "Y");
		reqCode.tranCd = "PS0001";
		reqCode.tranData = sl;

		APICode resCode = psService.resPS0001(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}
	
	@Test
	@Ignore
	public void ps0002() {
		APICode reqCode = new APICode<PS0002>();
		HashMap<String, String> sl = new HashMap<String, String>();
		
		sl.put("_sl_no", "1");
		reqCode.tranCd = "PS0002";
		reqCode.tranData = sl;

		APICode resCode = psService.resPS0002(reqCode);
		String json = JacksonUtils.objectToJson(resCode);
		System.out.println(json);
	}
}
