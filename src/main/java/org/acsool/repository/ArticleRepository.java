package org.acsool.repository;

import java.util.List;

import org.acsool.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
	@Query("SELECT SUM(zanCount) FROM Article a WHERE a.uId = :uId")
	Integer SumByUId(@Param("uId") long uId);

	@Query("SELECT MAX(zanCount) FROM Article a WHERE a.zanMaxCount = a.zanCount AND a.uId = :uId")
	Integer MaxByUId(@Param("uId") long uId);

	Page<Article> findByUId(long uId, Pageable pageable);

	Page<Article> findByUIdAndArtIdGreaterThan(long uId, long artId, Pageable pageable);
}
