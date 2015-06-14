package org.acsool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SL0001 {
	@JsonProperty("_sl_no")
	public String slNo;
	@JsonProperty("_art_content")
	public String artContent;
	@JsonProperty("_art_zan_max_cnt")
	public int artZanMaxCnt;
	
	@JsonProperty("_art_no")
	public String artNo;
	@JsonProperty("_rslt_yn")
	public String rsltYn;
	
}
