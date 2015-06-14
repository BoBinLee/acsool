package org.acsool.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SL0002 {
	@JsonProperty("_sl_no")
	public String slNo;
	@JsonProperty("_req_po_cnt")
	public String reqPoCnt;
	@JsonProperty("_req_po_no")
	public String reqPoNo;
	
	@JsonProperty("_res_cnt")
	public String resCnt;
	@JsonProperty("_res")
	public List<SL0002Data> datas;
	@JsonProperty("_res_date")
	public String resDate;
	@JsonProperty("_res_last_no")
	public String resLastNo;
	
	public static class SL0002Data {
		@JsonProperty("_art_no")
		public String artNo;
		@JsonProperty("_art_content")
		public String artContent;
		@JsonProperty("_art_created")
		public String artCreated;
		@JsonProperty("_art_zan_cnt")
		public String artZanCnt;
		@JsonProperty("_art_zan_max_cnt")
		public String artZanMaxCnt;
		@JsonProperty("_art_attach")
		public String artAttach;
		@JsonProperty("_art_img")
		public String artImg;
	}
}
