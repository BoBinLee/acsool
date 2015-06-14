package org.acsool.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SL0004 {
	@JsonProperty("_sl_no")
	public String slNo;
	@JsonProperty("_art_no")
	public String artNo;
	@JsonProperty("_reply_subject")
	public String subject;
	@JsonProperty("_reply_content")
	public String content;
	@JsonProperty("_reply_emotion")
	public String emotion;
	@JsonProperty("_art_zan_yn")
	public String artZanYn;
	
	@JsonProperty("_rslt_yn")
	public String rsltYn;
}
