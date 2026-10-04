package com.mrcoffeebean.journalapp.controller;
import java.util.*;
import com.mrcoffeebean.journalapp.entity.JournalEntity;
import com.mrcoffeebean.journalapp.services.JournalEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor 
@RequestMapping("/api/journal/{userName}")
public class JournalEntryController {

    private final JournalEntityService journalEntityService;

    //all Journal by user
    @GetMapping
    public ResponseEntity<?> getAll(@PathVariable String userName){
        return journalEntityService.findAll(userName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAll(@PathVariable String userName, @PathVariable ObjectId id){
        return journalEntityService.findById(userName,id);
    }

    //add one journal by user
    @PostMapping
    public ResponseEntity<?> addJournal(@PathVariable String userName,@RequestBody JournalEntity entity) {
        return journalEntityService.save(userName,entity);
        
    }

    //add some journal by user
    @PostMapping("/addall")
    public ResponseEntity<?> addAllJournal(@PathVariable String userName,@RequestBody List<JournalEntity>entries) {
        return journalEntityService.saveAll(userName,entries);
    }
    
    //delete one journal by user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String userName,@PathVariable ObjectId id){
        return journalEntityService.deleteById(userName,id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> ChangeJournal(@PathVariable String userName,@PathVariable ObjectId id, @RequestBody JournalEntity entity) {
        return journalEntityService.putById(userName,id, entity);
    }

}
