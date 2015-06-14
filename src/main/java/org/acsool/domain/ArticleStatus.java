package org.acsool.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ArticleStatus {
	@Id
	@Column(name="st_id")
	public long stId;
	@Column(name="art_id")
	public long artId;
	@Column(name="u_id")
	public long uId;
	// 0 : 초기상태 1 : 읽은 상태 2: 거부상태
	public int status;
}
