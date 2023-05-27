package com.patagonialabs.juntadevecinos.controllers;

import com.patagonialabs.juntadevecinos.models.Record;
import com.patagonialabs.juntadevecinos.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RecordController {

    @Autowired
    private RecordRepository repository;

    @GetMapping("/records")
    public List<Record> listAllRecords(){
        return repository.findAll();
    }

    @PostMapping("/records")
    public Record saveRecord(@RequestBody Record record){
        return repository.save(record);
    }

}
