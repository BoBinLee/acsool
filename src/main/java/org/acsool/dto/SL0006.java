package org.acsool.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SL0006 {
	@JsonProperty("_sl_no")
	public String slNo;
	
	@JsonProperty("_name")
	public String name;
	@JsonProperty("_profile_img")
	public String profileImg;
	@JsonProperty("_max_zan")
	public String maxZan;
	@JsonProperty("_max_all_zan")
	public String maxAllZan;
}
