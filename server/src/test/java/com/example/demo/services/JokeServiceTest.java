package com.example.demo.services;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;
import com.example.demo.service.JokeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTest {
	
	@Autowired
	JokeService jokeService;
	@MockBean
	JokeRepository jokeRepository;
	@Before
	public void setup() {
		jokeRepository.deleteAll();
		Joke joke1 = new Joke("title", "content");
		Joke joke2 = new Joke("title2","content2");
		List<Joke> list = Arrays.asList(joke1, joke2);
		Mockito.when(jokeRepository.findById(111)).thenReturn(Optional.of(joke1));
		Mockito.when(jokeRepository.findAll()).thenReturn(list);
	}
	
	@Test
	public void test() {
		Joke joke1 = new Joke("title3", "content");
		Joke joke2 = new Joke("title4","content2");
		List<Joke> list = this.jokeService.findAll();
		assertThat(list).hasSize(2).extracting(Joke::getTitle).contains(joke1.getTitle(),joke2.getTitle());
	}
	
	
}
