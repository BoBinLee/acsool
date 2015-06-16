package org.acsool.repository;

import java.util.List;

import org.acsool.domain.ArticleStatus;
import org.acsool.domain.Gcm;
import org.acsool.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleStatusRepository  extends JpaRepository<ArticleStatus, Long> {
	ArticleStatus findByUId(long uId);

	ArticleStatus findByUIdAndArtId(long uId, long artId);

	List<ArticleStatus> findByArtId(long artId);
}
