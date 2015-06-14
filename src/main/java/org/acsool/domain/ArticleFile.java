package org.acsool.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class ArticleFile {
	@Id
	@Column(name="f_id")
	public long fId;
	@Column(name="art_id")
	public long artId;
	public String type;
	public String url;
	public Timestamp created;
	
	@PrePersist
	public void onCreate() {
		created = new Timestamp((new Date()).getTime());
	}
}
