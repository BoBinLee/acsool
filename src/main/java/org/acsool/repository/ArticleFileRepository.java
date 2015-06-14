package org.acsool.repository;

import org.acsool.domain.ArticleFile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleFileRepository  extends JpaRepository<ArticleFile, Long> {
	ArticleFile findByArtId(long artId);
}
