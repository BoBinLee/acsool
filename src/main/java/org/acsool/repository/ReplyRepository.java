package org.acsool.repository;

import java.util.List;

import org.acsool.domain.Gcm;
import org.acsool.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReplyRepository  extends JpaRepository<Reply, Long> {
	List<Reply> findByUId(long uId);
	List<Reply> findByUIdAndVertified(long uId, int vertified);
	Page<Reply> findByUIdAndVertified(long uId, int vertified, Pageable pageable);
}
