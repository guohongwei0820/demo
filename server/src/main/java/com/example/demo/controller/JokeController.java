package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.excepion.JokeNotFoundException;
import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;
import com.example.demo.service.JokeService;

@RestController
@RequestMapping("/api")
public class JokeController {
    @Autowired
    private JokeRepository jokeRepository;
    
    @Autowired
    private JokeService jokeService ;

    @GetMapping("/jokes")
    public List<Joke> getAllJokes(){
        return jokeService.findAll();
    }

    @PostMapping("/jokes")
    public List<Joke> createJoke(@RequestBody Joke joke){
    	jokeService.add(joke);
        return jokeService.findAll();
    }

    @GetMapping("/jokes/{id}")
    public Joke getJokeById(@PathVariable(value = "id") Integer jokeId)  {
    	try {
    		Joke joke = jokeService.findById(jokeId);
    		return joke;
    	}catch (JokeNotFoundException e){
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"joke not found",e);
    		
    	}
        
    }

    @PutMapping("/jokes/{id}")
    public Joke updateJoke(@PathVariable(value = "id") Integer jokeId, @Valid @RequestBody Joke newJoke){
        Joke joke = jokeService.findById(jokeId);
        joke.setTitle(newJoke.getTitle());
        joke.setContent(newJoke.getContent());

        Joke updatedJoke = jokeService.updateJoke(joke);
        return updatedJoke;
    }


    @DeleteMapping("/jokes/{id}")
    public List<Joke> deleteJoke(@PathVariable(value = "id") Integer jokeId){
    	
        jokeService.deleteJoke(jokeId);
        return jokeRepository.findAll();
    }
    @GetMapping("/")
    public String testException() {
     throw new EntityNotFoundException("cvicse");
    }
    
}