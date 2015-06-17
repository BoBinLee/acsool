package org.acsool.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * @JsonProperty("_usr_pw") get 에 했을 경우 _usr_pw json 형태로 주고 set을 했을경우 _usr_pw 로 인식
 * //		@JsonProperty("_usr_pw")
 //		@JsonProperty("_usr_id")
 //		@JsonIgnore
 */

public class APICode<T> implements Serializable {
	@JsonProperty("_tran_cd")
	public String tranCd;
	@JsonProperty("_tran_data")
	public T tranData;
	@JsonProperty("_error_cd")
	public String errorCd;
	@JsonProperty("_error_msg")
	public String errorMsg;
	/*
	 * ʺ1000ʺ; 페이지 유지 ʺ1001ʺ; 거래 첫화면으로 분기 ʺ1002ʺ; 홈으로 분기(자동으로 로그아웃 됨) ʺ9999ʺ;
	 * 프로그램 종료
	 */
	@JsonProperty("_error_action")
	public int errorAction;

	public static enum Code {
		SL0001, SL0002, SL0003, SL0004, SL0005, SL0006, SL0007, PS0001, PS0002, SL0008
	}

	public static enum Status {
		/** User API Status Code */
		SUCCESS("000", "SUCCESS"), ERROR("999", "ERROR"), USER_NOT_FOUND("001", "USER_NOT_FOUND"), PASSWORD_ERROR(
				"002", "PASSWORD_ERROR"), USER_ALREADY_EXISTS("003", "USER_ALREADY_EXISTS"), WAIT_TO_AUTH("004",
				"WAIT_TO_AUTH"), ALREADY_AUTH("005", "ALREADY_AUTH"), NOT_HAVE_RECORD("999", "NOT_HAVE_RECORD"), ALREADY_FEEDBACK(
				"101", "ALREADY_FEEDBACK"), DOESNOT_REALNAMEAUTH("102", "DOESNOT_REALNAMEAUTH"), TOO_MANY_VALID_CODE(
				"004", "TOO_MANY_VALID_CODE"), VALID_CODE_NOT_VALID("005", "VALID_CODE_NOT_VALID"), SEND_SMS_ERROR(
				"006", "SEND_SMS_ERROR");

		private String status;
		private String message;

		private Status(String status, String message) {
			this.message = message;
			this.status = status;
		}
	}

	public static APICode getError(String errorCd, Status status) {
		APICode errorCode = new APICode();
		errorCode.errorCd = errorCd;
		errorCode.errorAction = Integer.parseInt(status.status);
		errorCode.errorMsg = status.message;
		return errorCode;
	}
}
