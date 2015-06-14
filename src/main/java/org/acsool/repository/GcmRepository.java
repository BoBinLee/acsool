package org.acsool.repository;

import org.acsool.domain.Gcm;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GcmRepository  extends JpaRepository<Gcm, Long> {
	Gcm findByUId(long uid);
}
