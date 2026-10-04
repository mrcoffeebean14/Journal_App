package com.mrcoffeebean.journalapp.services;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import com.mrcoffeebean.journalapp.Repository.JournalEntityRepo;
import com.mrcoffeebean.journalapp.Repository.UserEntityRepo;
import com.mrcoffeebean.journalapp.entity.JournalEntity;
import com.mrcoffeebean.journalapp.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class JournalEntityService {

    private final JournalEntityRepo journalEntityRepo;
    private final UserEntityRepo userEntityRepo;


    // show all Journal published by username
    public ResponseEntity<?> findAll(String userName){
        UserEntity user = userEntityRepo.findByUserName(userName);
        if(user!=null){
            return new ResponseEntity<>(user.getJournalEntities() , HttpStatus.OK);
        }
        return new ResponseEntity<>("USER NOT FOUND",HttpStatus.NOT_FOUND);
        

    }

    // show one Journal published by username
    public ResponseEntity<?> findById(String userName, ObjectId id){
        UserEntity user = userEntityRepo.findByUserName(userName);
        if(user!=null){
            for (JournalEntity journal : user.getJournalEntities()) {
                if (journal.getId().equals(id)) {return new ResponseEntity<>(journal,HttpStatus.OK);}
            }
            return new ResponseEntity<>("JOURNAL NOT FOUND",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("USER NOT FOUND",HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> save(String userName,JournalEntity entity){
        try {
            UserEntity user = userEntityRepo.findByUserName(userName);
            if(user!=null){
                JournalEntity entity1 = journalEntityRepo.save(entity);
                user.getJournalEntities().add(entity1);
                userEntityRepo.save(user);
                return new ResponseEntity<>("Journal Created Successfully",HttpStatus.CREATED);
            }
            return new ResponseEntity<>("User NOT FOUND",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> saveAll(String userName,List<JournalEntity> entity){
        try {
            UserEntity user = userEntityRepo.findByUserName(userName);
            if(user!=null){
                List <JournalEntity>entity1 = journalEntityRepo.saveAll(entity);
                user.getJournalEntities().addAll(entity1);
                userEntityRepo.save(user);
                return new ResponseEntity<>("Journal Created Successfully",HttpStatus.CREATED);
            }
            return new ResponseEntity<>("User NOT FOUND",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteById(String userName ,ObjectId id){
        try {
            UserEntity user = userEntityRepo.findByUserName(userName);
            if(user == null) return new ResponseEntity<>("User NOT FOUND",HttpStatus.NOT_FOUND);
            for (JournalEntity journal : user.getJournalEntities()) {
                if (journal.getId().equals(id)){
                    journalEntityRepo.deleteById(id);
                    user.getJournalEntities().remove(journal);
                    userEntityRepo.save(user);
                    return new ResponseEntity<>("Delete Successfully", HttpStatus.NO_CONTENT);
                }
            }
            return new ResponseEntity<>("Journal NOT FOUND" , HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error" , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> putById(String userName,ObjectId id , JournalEntity entity){
        try {
            UserEntity user = userEntityRepo.findByUserName(userName);
            if(user == null) return new ResponseEntity<>("User NOT FOUND",HttpStatus.NOT_FOUND);
            for (JournalEntity journal : user.getJournalEntities()) {
                if (journal.getId().equals(id)){
                    journal.setTitle(entity.getTitle());
                    journal.setContent(entity.getContent());
                    journalEntityRepo.save(journal);
                    return new ResponseEntity<>("Change Successfully", HttpStatus.CREATED);
                }
            }
            return new ResponseEntity<>("Journal NOT FOUND" , HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error" , HttpStatus.BAD_REQUEST);
        }
    }
}
