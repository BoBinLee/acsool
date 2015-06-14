package org.acsool.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SL0007 {
	@JsonProperty("_art_no")
	public String artNo;
	@JsonProperty("_art_no")
	public String slNo;
	@JsonProperty("_file_nm")
	public String name;
	@JsonProperty("_file_content")
	public byte[] content;
	@JsonProperty("_file_type")
	public String type;
	@JsonProperty("_file_size")
	public long size;
	
	@JsonProperty("_rslt_yn")
	public String rsltYn;
}
