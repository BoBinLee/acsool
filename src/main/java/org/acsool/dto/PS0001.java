package org.acsool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PS0001 {
	@JsonProperty("_sl_no")
	public String slNo;
	@JsonProperty("_push_token_id")
	public String pushTokenId;
	@JsonProperty("_push_yn")
	public String pushYn;
	
	@JsonProperty("_rslt_yn")
	public String resultYn;
}
