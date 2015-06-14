package org.acsool.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SL0005 {
	@JsonProperty("_sl_no")
	public String slNo;
	@JsonProperty("_art_no")
	public String artNo;
	@JsonProperty("_msg_yn")
	public String msgYn;
	
	@JsonProperty("_rslt_yn")
	public String rsltYn;
}
