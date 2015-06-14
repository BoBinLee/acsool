package org.acsool.repository;

import org.acsool.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<User, Long> {
	User findBySlId(String slNo);
}
