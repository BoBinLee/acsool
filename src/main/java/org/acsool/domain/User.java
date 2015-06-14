package org.acsool.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="u_id")
	public long uId;
	@Column(name="sl_id")
	public String slId;
	public String name;
	@Column(name="profile_img")
	public String profileImg;
}
