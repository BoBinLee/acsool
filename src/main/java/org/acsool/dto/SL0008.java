package org.acsool.dto;

import java.util.List;

import org.acsool.dto.SL0002.SL0002Data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SL0008 {
	@JsonProperty("_art_no")
	public String artNo;
	@JsonProperty("_req_po_cnt")
	public String reqPoCnt;
	@JsonProperty("_req_po_no")
	public String reqPoNo;
	
	@JsonProperty("_res_cnt")
	public String resCnt;
	@JsonProperty("_res")
	public List<SL0008Data> datas;
	@JsonProperty("_res_date")
	public String resDate;
	@JsonProperty("_res_last_no")
	public String resLastNo;
	
	public static class SL0008Data {
		@JsonProperty("_rep_no")
		public String repNo;
		@JsonProperty("_reply_subject")
		public String subject;
		@JsonProperty("_reply_content")
		public String content;
		@JsonProperty("_reply_emotion")
		public String emotion;
	}
}
