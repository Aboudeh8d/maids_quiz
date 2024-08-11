package com.top.spring.security.login.controllers;

import com.top.spring.security.login.models.Borrow;
import com.top.spring.security.login.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import com.top.spring.security.login.payload.request.CreateBorrowRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/borrows")

public class BorrowController {
    @Autowired
private  BorrowService borrowService;

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        Optional<Borrow> borrow = borrowService.findById(id);
        return borrow.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/patron/{patronId}")
    public List<Borrow> getBorrowsByPatronId(@PathVariable Long patronId) {
        return borrowService.findByPatronId(patronId);
    }

    @GetMapping("/book/{bookId}")
    public List<Borrow> getBorrowsByBookId(@PathVariable Long bookId) {
        return borrowService.findByBookId(bookId);
    }

//    @PostMapping
//    public Borrow createBorrow(@RequestBody Borrow borrow) {
//        System.out.println(borrow);
//        return borrowService.save(borrow);
//    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CreateBorrowRequest borrow, BindingResult result) {
        if (result.hasErrors()) {
            // Handling validation errors
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
         Borrow res_borrow = borrowService.save(borrow);
        return new ResponseEntity<>(res_borrow, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public Object updateBorrow(@PathVariable Long id, @RequestBody Borrow borrow) {
//        if (!borrowService.findById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        borrow.setId(id);
        return borrowService.update(id ,borrow);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id) {
        if (!borrowService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        borrowService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
