package org.acsool.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@Entity
public class Reply {
	@Id
	@Column(name="rep_id")
	public long repId;
	@Column(name="art_id")
	public long artId;
	@Column(name="u_id")
	public long uId;
	public String subject;
	public String content;
	public String emotion;
	public int vertified;
	public Timestamp created;
	
	@PrePersist
	public void onCreate() {
		created = new Timestamp((new Date()).getTime());
	}
}
