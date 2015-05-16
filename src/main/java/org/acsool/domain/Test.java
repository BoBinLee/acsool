package org.acsool.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Test {
	@Id
	private String name;
	private int value;
}
