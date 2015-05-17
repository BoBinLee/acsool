package org.acsool.controller;

import org.acsool.dto.APICode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@RequestMapping(value = "/skhu")
	public APICode mappingCode(@RequestBody APICode reqCode) {
		APICode resCode = new APICode();

		System.out.println("----------------- " + reqCode.tranCd + " --------------------");
		APICode.Code code = APICode.Code.valueOf(resCode.tranCd);

		switch (code) {
		case SL0001:

			break;
		case SL0002:

			break;
		case SL0003:

			break;
		case SL0004:

			break;
		case SL0006:

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
