package org.acsool.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.acsool.domain.Article;
import org.acsool.domain.ArticleFile;
import org.acsool.domain.ArticleStatus;
import org.acsool.domain.Gcm;
import org.acsool.domain.Reply;
import org.acsool.domain.User;
import org.acsool.dto.APICode;
import org.acsool.dto.SL0001;
import org.acsool.dto.SL0002;
import org.acsool.dto.SL0003;
import org.acsool.dto.SL0004;
import org.acsool.dto.SL0005;
import org.acsool.dto.SL0006;
import org.acsool.dto.SL0007;
import org.acsool.repository.ArticleFileRepository;
import org.acsool.repository.ArticleRepository;
import org.acsool.repository.ArticleStatusRepository;
import org.acsool.repository.GcmRepository;
import org.acsool.repository.ReplyRepository;
import org.acsool.repository.UserRepository;
import org.acsool.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class SLService {
	private static final Logger logger = LoggerFactory.getLogger(SLService.class);

	@Autowired
	public UserRepository userRepository;
	@Autowired
	public ArticleRepository articleRepository;
	@Autowired
	public ReplyRepository replyRepository;
	@Autowired
	public ArticleFileRepository articleFileRepository;
	@Autowired
	public ArticleStatusRepository articleStatusRepository;
	@Autowired
	public GcmRepository gcmRepository;
	
	@Autowired
	public GCMService gcmService;
	
	@Autowired
	public Cloudinary cloudinary;
	
	// 게시글쓰기
	public APICode resSL0001(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		SL0001 sl = JacksonUtils.<SL0001> hashMapToObject(hashMap, SL0001.class);

		// 게시글 생성
		Article article = new Article();
		article.content = sl.artContent;
		article.uId = userRepository.findBySlId(sl.slNo).uId;
		article.zanMaxCount = sl.artZanMaxCnt;

		article = articleRepository.save(article);

		// 메시지 보내기 - 짠 갯수에 맞는 랜덤으로
		List<User> users = userRepository.findAll();

		if (article.zanMaxCount > users.size() - 1)
			article.zanMaxCount = users.size() - 1;
		int count = article.zanMaxCount;

		while (count >= 0 && users.size() != 0) {
			int random = (int) Math.floor(Math.random() * users.size());
			User user = users.remove(random);

			if (user.uId != article.uId) {
				ArticleStatus status = new ArticleStatus();
				status.artId = article.artId;
				status.uId = user.uId;
				status.status = 0;
				articleStatusRepository.save(status);
			}
		}
		articleRepository.save(article);

		APICode resCode = this.<SL0001> processCommonResponse(reqCode, sl);
		return resCode;
	}

	// 게시글조회
	public APICode resSL0002(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		SL0002 sl = JacksonUtils.<SL0002> hashMapToObject(hashMap, SL0002.class);

		String sid = sl.slNo;
		long reqPoNo = Long.parseLong(sl.reqPoNo);
		int reqPoCnt = Integer.parseInt(sl.reqPoCnt);
		Pageable pageable = new PageRequest(0, reqPoCnt);

		Page<Article> articles = articleRepository.findByUIdAndArtIdGreaterThan(userRepository.findBySlId(sid).uId,
				reqPoNo, pageable);
		sl.resCnt = "" + articles.getTotalPages();
		sl.resDate = (new Date()).toString();
		sl.resLastNo = "" + articles.getContent().get(articles.getTotalPages() - 1).artId;

		final List<SL0002.SL0002Data> datas = new ArrayList<SL0002.SL0002Data>();

		articles.forEach(new Consumer<Article>() {
			public void accept(Article article) {
				// System.out.println("forEach i :: " + i );
				SL0002.SL0002Data data = new SL0002.SL0002Data();
				data.artNo = String.valueOf(article.artId);
				data.artContent = article.content;
				data.artCreated = article.created.toString();
				data.artZanCnt = "" + article.zanCount;
				data.artZanMaxCnt = "" + article.zanMaxCount;

				ArticleFile file = articleFileRepository.findByArtId(article.artId);
				if (file != null)
					data.artAttach = "" + file.url;
				datas.add(data);
			}
		});
		sl.datas = datas;

		APICode resCode = this.<SL0002> processCommonResponse(reqCode, sl);
		return resCode;
	}

	// 게시글삭제
	public APICode resSL0003(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		SL0003 sl = JacksonUtils.<SL0003> hashMapToObject(hashMap, SL0003.class);

		String artId = sl.artNo;
		articleRepository.delete(Long.valueOf(artId));
		// 답글

		// 게시글 상태

		sl.rsltYn = "Y";
		APICode resCode = this.<SL0003> processCommonResponse(reqCode, sl);
		return resCode;
	}

	// 짠선택
	public APICode resSL0004(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		SL0004 sl = JacksonUtils.<SL0004> hashMapToObject(hashMap, SL0004.class);

		long artId = Long.parseLong(sl.artNo);
		long uId = userRepository.findBySlId(sl.slNo).uId;
		Article article = articleRepository.findOne(artId);

		if (sl.artZanYn.equals("Y")) {
			// 답글 추가
			Reply reply = new Reply();
			reply.subject = sl.subject;
			reply.content = sl.content;
			reply.emotion = sl.emotion;
			reply.uId = uId;
			reply.vertified = 0;

			replyRepository.save(reply);
			// 게시글 짠 추가
			article.zanCount += 1;
			articleRepository.save(article);
			// 알람 보내기 - 게시글 유저에게
			Gcm gcm = gcmRepository.findByUId(article.uId);
			
			if(gcm.pushYn.equals("Y"))
				gcmService.sendZanMessage(new String[]{gcm.pushToken}, reply);
		}
		sl.rsltYn = "Y";

		APICode resCode = this.<SL0004> processCommonResponse(reqCode, sl);
		return resCode;
	}

	// 메시지 선택
	public APICode resSL0005(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		SL0005 sl = JacksonUtils.<SL0005> hashMapToObject(hashMap, SL0005.class);

		long uId = userRepository.findBySlId(sl.slNo).uId;
		long artId = Long.parseLong(sl.artNo);

		// 게시글 - 유저 상태 업데이트
		ArticleStatus status = articleStatusRepository.findByUIdAndArtId(uId, artId);
		if (sl.msgYn.equals("Y"))
			status.status = 1;
		else
			status.status = 2;
		articleStatusRepository.save(status);

		sl.rsltYn = "Y";
		APICode resCode = this.<SL0005> processCommonResponse(reqCode, sl);
		return resCode;
	}

	// 사용자 조회
	public APICode resSL0006(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		SL0006 sl = JacksonUtils.<SL0006> hashMapToObject(hashMap, SL0006.class);

		User user = userRepository.findBySlId(sl.slNo);

		if (user == null) {
			user = new User();
			user.name = "";
			user.profileImg = null;
			user.slId = sl.slNo;
			user = userRepository.save(user);
		}
		// int zanCnt = replyRepository.findByUId(user.uId).size();
		Integer zanCnt = articleRepository.SumByUId(user.uId);
		Integer zanMaxCnt = articleRepository.MaxByUId(user.uId);

		if (zanCnt == null)
			zanCnt = 0;
		if (zanMaxCnt == null)
			zanMaxCnt = 0;

		sl.maxAllZan = String.valueOf(zanMaxCnt);
		sl.maxZan = String.valueOf(zanCnt);

		APICode resCode = this.<SL0006> processCommonResponse(reqCode, sl);
		return resCode;
	}

	// 파일 업로드
	public APICode resSL0007(APICode reqCode) {
		HashMap<String, String> hashMap = (HashMap<String, String>) reqCode.tranData;
		SL0007 sl = JacksonUtils.<SL0007> hashMapToObject(hashMap, SL0007.class);
		
		ArticleFile articleFile = new ArticleFile();
		articleFile.artId = Long.parseLong(sl.artNo);
		articleFile.type = sl.type;
		
		Map<String, String> uploadResult = null;
		uploadResult = upload(sl.content);
		articleFile.publicId = uploadResult.get("public_id");
		articleFile.url = uploadResult.get("url");

		sl.rsltYn = "Y";
		APICode resCode = this.<SL0007> processCommonResponse(reqCode, sl);
		return resCode;
	}
	
	public Map<String, String> upload(byte[] contents){
		Map<String, String> uploadResult = null;
		try {
			uploadResult = cloudinary.uploader().upload(contents,  ObjectUtils.asMap("resource_type", "auto"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uploadResult;
	}
	
	
	public <T> APICode<T> processCommonResponse(APICode reqCode, T sl) {
		// post Response
		APICode<T> resCode = new APICode<T>();
		resCode.tranCd = reqCode.tranCd;
		resCode.tranData = sl;
		return resCode;
	}
}
