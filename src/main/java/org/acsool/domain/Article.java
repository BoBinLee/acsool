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
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="art_id")
	public long artId;
	@Column(name="u_id")
	public long uId;
	public String content;
	@Column(name="zan_cnt")
	public int zanCount;
	@Column(name="zan_max_cnt")
	public int zanMaxCount;
	public Timestamp created;
	public Timestamp updated;
	
	@PrePersist
	public void onCreate() {
		created = updated = new Timestamp((new Date()).getTime());
	}

	@PreUpdate
	public void onUpdate() {
		updated = new Timestamp((new Date()).getTime());
	}
}
