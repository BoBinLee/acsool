package org.acsool.service;

import java.util.HashMap;

import org.acsool.domain.Gcm;
import org.acsool.dto.APICode;
import org.acsool.dto.PS0001;
import org.acsool.dto.PS0002;
import org.acsool.utils.JacksonUtils;
import org.springframework.stereotype.Service;

@Service("psService")
public class PSService {
	
	public APICode resPS0001(APICode reqCode){
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		PS0001 ps = JacksonUtils.<PS0001>hashMapToObject(hashMap, PS0001.class);
		
		Gcm gcm = new Gcm();
		gcm.mac = ps.mac;
		gcm.pushYn = ps.pushYn;
		gcm.tokenId = ps.pushTokenId;
		
		try {
//			ps.resultYn = (1 == gcmMapper.insertGcm(gcm))? "Y" : "N";
		} catch(Exception e){
			ps.resultYn = "N";
		}
		APICode resCode = this.<PS0001>processCommonResponse(reqCode, ps);
		return resCode;
	}
	
	public APICode resPS0002(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		PS0002 ps = JacksonUtils.<PS0002>hashMapToObject(hashMap, PS0002.class);
		
		String mac = ps.mac;
		Gcm gcm = null;
//		Gcm gcm = gcmMapper.getGcm(mac);
		ps.pushTokenId = gcm.tokenId;
		ps.pushYn = gcm.pushYn;
		
		APICode resCode = this.<PS0002>processCommonResponse(reqCode, ps);
		return resCode;
	}
	
	public <T> APICode<T> processCommonResponse(APICode reqCode, T ps){
		// post Response
		APICode<T> resCode = new APICode<T>();
		resCode.tranCd = reqCode.tranCd;
		resCode.tranData = ps;
		return resCode;
	}
}
