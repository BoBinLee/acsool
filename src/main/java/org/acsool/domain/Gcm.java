package org.acsool.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@Entity
public class Gcm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="gc_id")
	public long gcId;
	@Column(name="u_id")
	public long uId;
	@Column(name="push_token")
	public String pushToken;
	@Column(name="push_yn")
	public String pushYn;
	public Timestamp created;
	
	@PrePersist
	public void onCreate() {
		created = new Timestamp((new Date()).getTime());
	}
}
