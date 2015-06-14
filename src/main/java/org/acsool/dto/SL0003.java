package org.acsool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SL0003 {
	@JsonProperty("_sl_no")
	public String slNo;
	@JsonProperty("_art_no")
	public String artNo;
	
	@JsonProperty("_rslt_yn")
	public String rsltYn;
}
