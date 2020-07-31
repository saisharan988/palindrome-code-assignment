package com.odx.test.controller;

import com.odx.test.service.PalindromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/palindrome")
public class PalindromeController {
    PalindromeService palindromeService;

    @Autowired
    public PalindromeController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    @PostMapping("/saveLongestPalindrome")
    @ResponseBody
    public ResponseEntity storeLongestPalindrome(@RequestBody String inputString) {
        try {
            if (inputString != null) {
                palindromeService.storeLongestPalindrome(inputString);
            }
            return ResponseEntity.ok().body("successfully saved longest palindrome");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving longest Palindrome");
        }
    }

    @GetMapping("/getLongestPalindrome")
    @ResponseBody
    public ResponseEntity getLongestPalindrome() {
        /* retrieving last stored palindrome value*/
        return ResponseEntity.ok().body(
                palindromeService.getLongestPalindrome());
    }
}
