package org.acsool.repository;

import org.acsool.AcsoolApplication;
import org.acsool.repository.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AcsoolApplication.class)
public class RepositoryTests {
	@Autowired
	TestRepository testRepository;
	@Autowired
	ArticleRepository articleRepository;
	
	@Test
	public void sl0006(){
		articleRepository.SumByUId(1);
	}
}
