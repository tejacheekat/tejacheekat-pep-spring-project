package com.example.controller;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
//package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social-media")
public class SocialMediaController {

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getUserProfile(@PathVariable Long userId) {
        // TODO: Implement logic to retrieve user profile by userId
        return new ResponseEntity<>("User profile for userId " + userId, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUserProfile(@RequestBody String userProfile) {
        // TODO: Implement logic to create a new user profile
        return new ResponseEntity<>("User profile created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<String> updateUserProfile(@PathVariable Long userId, @RequestBody String updatedProfile) {
        // TODO: Implement logic to update user profile by userId
        return new ResponseEntity<>("User profile updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable Long userId) {
        // TODO: Implement logic to delete user profile by userId
        return new ResponseEntity<>("User profile deleted successfully", HttpStatus.OK);
    }

    // Additional endpoints can be added as needed for your application

}
