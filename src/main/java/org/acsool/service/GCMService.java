package org.acsool.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.acsool.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/* PROJECT ID = spry-district-693
 * PROJECT NUMBER = 191825913558 191825913558
 * API KEY = AIzaSyBUIZrcluzPp_N0_6QrOSRRUGf_hUsTdkM
 */

@Service
public class GCMService {
	// 누군가 짠을 해주었을 때 알람
	public void sendReplyMessage(String[] tokenIds, String repId) {
		Message message = new Message.Builder().addData("repId", repId).addData("type", "reply")
				.build();
		sendMessage(tokenIds, message);
	}
	
	// 누군가가 짠을 보내서 내가 받았을 때 알람
	public void sendArticleMessage(String[] tokenIds, String artId){
		Message message = new Message.Builder().addData("artId", artId).addData("type", "article")
				.build();
		sendMessage(tokenIds, message);
	}
	
	protected void sendMessage(String[] tokenIds, Message message){
		Sender sender = new Sender("AIzaSyDtc1Lroot8QtYdgBA3yTfqiYRPdlQJxeY");
//		String regId = "APA91bFPO7UzexoyF6a4Vo00RNtlWo0AcjKR9RC_rgjXAgq4K04XOzDXYsal5XrmEawh2wfrJZhi1-CBnmEyuPvFvfleQOmedBbtXdJ6uUsukGmW5Ip9fVpBAmG4ism8O0iJ99YNVDb5M_INKREUTHwxazeGV2TYHOLsWjR_FiQKMLfnnyb4gPI";

		MulticastResult multiResult = null;
//		System.out.println("tokenId : " + tokenIds[0]);
		try {
			multiResult = sender.send(message, Arrays.asList(tokenIds), 5);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (multiResult != null) {
			List<Result> resultList = multiResult.getResults();
			for (Result result : resultList) {
				System.out.println(result.getMessageId());
			}
		}
	}
}