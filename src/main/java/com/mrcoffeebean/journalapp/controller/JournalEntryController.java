package com.mrcoffeebean.journalapp.controller;
import java.util.*;
import com.mrcoffeebean.journalapp.entity.JournalEntity;
import com.mrcoffeebean.journalapp.services.JournalEntityService;
import org.springframework.web.bind.annotation.RestController;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired 
    private JournalEntityService journalEntityService;

    //all Journal by user
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAll(@PathVariable String userName){
        return journalEntityService.findAll(userName);
    }

    @GetMapping("/{userName}/{id}")
    public ResponseEntity<?> getAll(@PathVariable String userName, @PathVariable ObjectId id){
        return journalEntityService.findById(userName,id);
    }

    //add one journal by user
    @PostMapping("/{userName}")
    public ResponseEntity<?> addJournal(@PathVariable String userName,@RequestBody JournalEntity entity) {
        return journalEntityService.save(userName,entity);
        
    }

    //add some journal by user
    @PostMapping("/{userName}/addall")
    public ResponseEntity<?> addAllJournal(@PathVariable String userName,@RequestBody List<JournalEntity>entries) {
        return journalEntityService.saveAll(userName,entries);
    }
    
    //delete one journal by user
    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String userName,@PathVariable ObjectId id){
        return journalEntityService.deleteById(userName,id);
    }
    @PutMapping("/{userName}/{id}")
    public ResponseEntity<?> ChangeJournal(@PathVariable String userName,@PathVariable ObjectId id, @RequestBody JournalEntity entity) {
        return journalEntityService.putById(userName,id, entity);
    }

}
