package com.top.spring.security.login.controllers;


import com.top.spring.security.login.models.Patron;
import com.top.spring.security.login.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService PatronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return PatronService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patron = PatronService.findById(id);
        return patron.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patron createPatron(@RequestBody Patron patron) {
        return PatronService.save(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody Patron patron) {
        if (!PatronService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        patron.setId(id);
        return ResponseEntity.ok(PatronService.save(patron));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        if (!PatronService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        PatronService.delete(id);
        return ResponseEntity.noContent().build();
    }
}