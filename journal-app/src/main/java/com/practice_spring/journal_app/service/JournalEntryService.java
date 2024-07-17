package com.practice_spring.journal_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.practice_spring.journal_app.entity.JournalEntry;
import com.practice_spring.journal_app.entity.User;
import com.practice_spring.journal_app.repository.JournalEntryRepo;


@Component
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException("Ann Error Occured");
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    
    public void deleteById(ObjectId id, String userName) {
       User user = userService.findByUserName(userName);
       user.getJournalEntries().removeIf(x-> x.getId().equals(id));
       userService.saveEntry(user);
       journalEntryRepo.deleteById(id);
    }
}
