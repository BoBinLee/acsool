package org.acsool.service;

import java.util.HashMap;

import org.acsool.domain.Gcm;
import org.acsool.dto.APICode;
import org.acsool.dto.PS0001;
import org.acsool.dto.PS0002;
import org.acsool.repository.GcmRepository;
import org.acsool.repository.UserRepository;
import org.acsool.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PSService {
	@Autowired
	public GcmRepository gcmRepository;
	@Autowired
	public UserRepository userRepository;

	public APICode resPS0001(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		PS0001 ps = JacksonUtils.<PS0001> hashMapToObject(hashMap, PS0001.class);

		Gcm gcm = new Gcm();
		gcm.uId = userRepository.findBySlId(ps.slNo).uId;
		gcm.pushYn = ps.pushYn;
		gcm.pushToken = ps.pushTokenId;

		try {
			gcmRepository.save(gcm);
		} catch (Exception e) {
			ps.resultYn = "N";
		}
		APICode resCode = this.<PS0001> processCommonResponse(reqCode, ps);
		return resCode;
	}

	public APICode resPS0002(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		PS0002 ps = JacksonUtils.<PS0002> hashMapToObject(hashMap, PS0002.class);

		long uid = userRepository.findBySlId(ps.slNo).uId;
		Gcm gcm = gcmRepository.findByUId(uid);

		if (gcm != null) {
			ps.pushTokenId = gcm.pushToken;
			ps.pushYn = gcm.pushYn;
		}

		APICode resCode = this.<PS0002> processCommonResponse(reqCode, ps);
		return resCode;
	}

	public <T> APICode<T> processCommonResponse(APICode reqCode, T ps) {
		// post Response
		APICode<T> resCode = new APICode<T>();
		resCode.tranCd = reqCode.tranCd;
		resCode.tranData = ps;
		return resCode;
	}
}
