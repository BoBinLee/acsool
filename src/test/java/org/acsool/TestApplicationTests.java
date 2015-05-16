package org.acsool;

import org.acsool.repository.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestApplicationTests {
	@Autowired
	TestRepository testRepository;
	
	@Test
	public void insertTest(){
//		System.out.println(testRepository.findAll());
		org.acsool.domain.Test test = new org.acsool.domain.Test();
		test.setName("test");
		test.setValue(1);
		
		testRepository.save(test);
	}
}
