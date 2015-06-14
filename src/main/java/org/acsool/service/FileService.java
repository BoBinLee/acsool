package org.acsool.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;

	@Autowired
	Cloudinary cloudinary;
	
	public TagFile saveTagFile(TagFile file){
		Map<String, String> uploadResult = null;
		if(file.getFileId() == 0){
			uploadResult = upload(file);
			file.setPublicId(uploadResult.get("public_id"));
			file.setUrl(uploadResult.get("url"));
		}
		return fileRepository.save(file);
	}
	
	public Map<String, String> upload(TagFile file){
		Map<String, String> uploadResult = null;
		try {
			uploadResult = cloudinary.uploader().upload(file.getBytes(),  ObjectUtils.asMap("resource_type", "auto"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uploadResult;
	}
	
	public void delete(long id){
		TagFile file = findById(id);
		deleteResource(file.getPublicId());
		fileRepository.delete(id);
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
	
	public TagFile findById(long fileId){
		return fileRepository.findOne(fileId);
	}
}
