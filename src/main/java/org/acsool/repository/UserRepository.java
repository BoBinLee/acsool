package org.acsool.repository;

import java.util.List;

import org.acsool.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository  extends JpaRepository<User, Long> {
	User findBySlId(String slNo);

	@Query("SELECT u FROM User u WHERE u.uId NOT IN :ids")
	List<User> findByNotUIds(@Param("ids") List<Long> ids);
}
