package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    AccountService accountService;
    @Autowired
    ObjectMapper objectMapper;
     @Autowired
    MessageService messageService; 
     @PostMapping("/register")
    public ResponseEntity<Object> registerAccount(@RequestBody Account account){
        

        
    //     if (account.getUsername() == null || userRequest.getUsername().isEmpty()
    //     || userRequest.getPassword() == null || userRequest.getPassword().length() < 4) {
    // return ResponseEntity.badRequest().body("Invalid username or password");

    if(account.getUsername()==null||account.getUsername().isEmpty()||account.getPassword()==null||account.getPassword().length()<4){
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
        

         if (accountService.findUsername(account.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
         }
        
        /// Create a new user and save it to the database
        // UserEntiy newUser = new UserEnity(userRequest.getUsername(), userRequest.getPassword());
        // userRepository.save(newUser);


     Account ac= accountService.saveAccount(account);
        return ResponseEntity.ok(ac);

        }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
    
        Account authenticatedAccount = accountService.logIn(account);

        if (authenticatedAccount != null) {
            return ResponseEntity.ok(authenticatedAccount);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        try {
            Message createdMessage = messageService.createMessage(message);
            return ResponseEntity.ok(createdMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
     @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();

        // Return an empty list if no messages are present
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }
     @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer message_id) {
        Message message = messageService.getMessageById(message_id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer message_id) {
         Integer rowsUpdated = messageService.deleteByMessageId(message_id);

        // Return appropriate response based on the number of rows updated
        if (rowsUpdated > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(rowsUpdated);
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
     //return ResponseEntity.status(HttpStatus.OK).body(1);
    }
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Object> updateMessage(@PathVariable Integer message_id,@RequestBody Message message){
        
        if(message.getMessage_text() == null ||message.getMessage_text().length()>255||message.getMessage_text().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Integer rowUpdated = messageService.updateMessage(message_id, message.getMessage_text());

        // Return appropriate response based on the number of rows updated
        if (rowUpdated> 0) {
            return ResponseEntity.status(HttpStatus.OK).body(rowUpdated);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @RequestMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable("account_id") Integer accountId) {
        List<Message> messages = messageService.findMessageByUser(accountId);
        return ResponseEntity.ok(messages);
    }

}
