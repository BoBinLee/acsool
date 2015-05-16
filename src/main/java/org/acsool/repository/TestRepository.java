package org.acsool.repository;

import org.acsool.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestRepository  extends JpaRepository<Test, Long> {
}
