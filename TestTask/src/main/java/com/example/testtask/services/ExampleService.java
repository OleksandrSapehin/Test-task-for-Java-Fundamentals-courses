package com.example.testtask.services;

import com.example.testtask.models.Example;
import com.example.testtask.rep.ExampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }
    public void save(Example example){
        exampleRepository.save(example);
    }
    public List<Example> findAllByRoots(Double roots){
        return exampleRepository.findAllByRoots(roots);
    }
    public List<Example> findUniqueRootsAndExamples() {
        return exampleRepository.findUniqueRootsAndExamples();
    }
    public List<Example> findAll(){
        return exampleRepository.findAll();
    }

}



