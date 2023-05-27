package com.patagonialabs.juntadevecinos.controllers;

import com.patagonialabs.juntadevecinos.exceptions.ResourceNotFoundException;
import com.patagonialabs.juntadevecinos.models.Record;
import com.patagonialabs.juntadevecinos.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class RecordController {

    @Autowired
    private RecordRepository repository;

    @GetMapping("/records")
    public List<Record> listAllRecords(){
        return repository.findAll();
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id){
        Record record = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("El acta no existe")
        );

        return ResponseEntity.ok(record);
    }

    @PostMapping("/records")
    public Record saveRecord(@RequestBody Record record){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        record.setCreation_date(date);

        return repository.save(record);
    }

    @PutMapping("/records/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable Long id, @RequestBody Record recordEdit){
        Record record = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El acta no existe")
        );

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        record.setAuthor(recordEdit.getAuthor());
        record.setTitle(recordEdit.getTitle());
        record.setDescription(recordEdit.getDescription());
        record.setUpdate_date(date);

        return ResponseEntity.ok(repository.save(record));
    }

    @DeleteMapping("records/{id}")
    public ResponseEntity <Map<String,Boolean>> deleteRecord(@PathVariable Long id){
        Record record = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El acta no existe")
        );

        repository.delete(record);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
