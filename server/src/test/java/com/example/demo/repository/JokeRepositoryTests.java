package com.example.demo.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Joke;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeRepositoryTests {
	@Autowired
	JokeRepository jokeRepository;
	Joke joke1,joke2;
	@Before
	public  void setUp() {
		this.jokeRepository.deleteAll();
		joke1 = new Joke();
		joke1.setTitle("title1");
		joke1.setContent("content1");
		joke1.setCreatedAt(new Date());
		joke1.setUpdatedAt(new Date());
		jokeRepository.save(joke1);
		joke2 = new Joke();
		joke2.setTitle("title2");
		joke2.setContent("content2");
		joke2.setCreatedAt(new Date());
		joke2.setUpdatedAt(new Date());
		jokeRepository.save(joke2);
	}
	
	@Test
	public void test() {
		List<Joke> list = this.jokeRepository.findAll();
		assertThat(list.size()).isEqualTo(3);
	}
	@After
	public void clean() {
		this.jokeRepository.deleteAll();
	}
}
