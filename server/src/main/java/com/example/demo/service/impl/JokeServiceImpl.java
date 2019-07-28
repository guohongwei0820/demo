package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.excepion.JokeNotFoundException;
import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;
import com.example.demo.service.JokeService;

@Service
public class JokeServiceImpl implements JokeService {

@Autowired
private     JokeRepository jokeReposity;

public void  add(Joke joke){
    jokeReposity.save(joke);
}

public void deleteJoke(int  id){
    jokeReposity.deleteById(id);
}

public Joke updateJoke(Joke joke){
   return jokeReposity.save(joke);
}

public List<Joke> findAll(){
    List<Joke> list =  jokeReposity.findAll();
    return list;
}

@Override
public Joke findById(int id)  throws JokeNotFoundException {
	return jokeReposity.findById(id).orElseThrow(()->new JokeNotFoundException("joke cant be found exception"));
	
}


	


}