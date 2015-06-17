package org.acsool.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class ArticleFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="f_id")
	public long fId;
	@Column(name="art_id")
	public long artId;
	public String type;
	public String url;
	@Column(name="public_id")
	public String publicId;
	public Timestamp created;
	
	@PrePersist
	public void onCreate() {
		created = new Timestamp((new Date()).getTime());
	}
}
