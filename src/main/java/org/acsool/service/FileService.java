package org.acsool.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.acsool.domain.ArticleFile;
import org.acsool.repository.ArticleFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Service
public class FileService {
	@Autowired
	private ArticleFileRepository articleFileRepository;

	@Autowired
	Cloudinary cloudinary;
	
	public ArticleFile saveArticleFile(ArticleFile file, byte[] contents){
		Map<String, String> uploadResult = null;
		if(file.getFId() == 0){
			uploadResult = upload(contents);
			file.setPublicId(uploadResult.get("public_id"));
			file.setUrl(uploadResult.get("url"));
		}
		return articleFileRepository.save(file);
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
	
	public void delete(long id){
		ArticleFile file = articleFileRepository.findOne(id);
		deleteResource(file.getPublicId());
		articleFileRepository.delete(id);
	}
	
	public void deleteResource(String publicId){
		Api api = cloudinary.api();
		try {
			api.deleteResources(Arrays.asList(publicId),
				    ObjectUtils.emptyMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
