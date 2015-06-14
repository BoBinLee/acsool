package org.acsool.controller;

import org.acsool.dto.APICode;
import org.acsool.service.SLService;
import org.apache.xml.resolver.apps.resolver;
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
	
	@RequestMapping(value = "/api")
	public APICode mappingCode(@RequestBody APICode reqCode) {
		APICode resCode = null;

		System.out.println("----------------- " + reqCode.tranCd + " --------------------");
		APICode.Code code = APICode.Code.valueOf(reqCode.tranCd);

		switch (code) {
		case SL0001:
			logger.info("SL0001 : ");
			resCode = sLService.resSL0001(reqCode);
			break;
		case SL0002:

			break;
		case SL0003:

			break;
		case SL0004:

			break;
		case SL0006:
			resCode = sLService.resSL0006(reqCode);
			break;
		case SL0007:

			break;
		case PS0001:

			break;
		case PS0002:

			break;
		}
		return resCode;
	}
}
