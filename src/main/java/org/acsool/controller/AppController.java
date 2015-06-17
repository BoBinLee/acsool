package org.acsool.controller;

import org.acsool.dto.APICode;
import org.acsool.service.PSService;
import org.acsool.service.SLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	private SLService sLService;
	@Autowired
	private PSService pSService;

	@RequestMapping(value = "/api")
	public APICode mappingCode(@RequestBody APICode reqCode) {
		APICode resCode = null;

		System.out.println("----------------- " + reqCode.tranCd + " --------------------");
		APICode.Code code = APICode.Code.valueOf(reqCode.tranCd);

		try {
			switch (code) {
			case SL0001:
				resCode = sLService.resSL0001(reqCode);
				break;
			case SL0002:
				resCode = sLService.resSL0002(reqCode);
				break;
			case SL0003:
				resCode = sLService.resSL0003(reqCode);
				break;
			case SL0004:
				resCode = sLService.resSL0004(reqCode);
				break;
			case SL0005:
				resCode = sLService.resSL0005(reqCode);
				break;
			case SL0006:
				resCode = sLService.resSL0006(reqCode);
				break;
			case SL0007:
				resCode = sLService.resSL0007(reqCode);
				break;
			case SL0008:
				resCode = sLService.resSL0008(reqCode);
				break;
			case PS0001:
				resCode = pSService.resPS0001(reqCode);
				break;
			case PS0002:
				resCode = pSService.resPS0002(reqCode);
				break;
			}
		} catch (Exception e) {
			resCode = new APICode();
			resCode.errorCd = "" + APICode.Status.ERROR.ordinal();
			resCode.errorMsg = APICode.Status.ERROR.name();
		}
		return resCode;
	}
}
