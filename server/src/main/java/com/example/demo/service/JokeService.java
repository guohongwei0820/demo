package com.example.demo.service;
import java.util.List;

import com.example.demo.model.Joke;

public  interface JokeService {

    void  add(Joke joke);

    void deleteJoke(int  id);

    Joke updateJoke(Joke joke);

    List<Joke> findAll();
    
    Joke findById(int id);


}