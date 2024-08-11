package com.top.spring.security.login.service;
import com.top.spring.security.login.models.Patron;
import com.top.spring.security.login.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository PatronRepository;

    public List<Patron> findAll() {
        return PatronRepository.findAll();
    }

    public Optional<Patron> findById(Long id) {
        return PatronRepository.findById(id);
    }

    public Patron save(Patron Patron) {
        if (PatronRepository.existsByIdentity(Patron.getIdentity())) {
            throw new RuntimeException("Error: Identity has been taken.");
        }

        if (PatronRepository.existsByName(Patron.getName())) {
            throw new RuntimeException("Error: Identity has been taken.");
        }

        return PatronRepository.save(Patron);
    }

    @Override
    public Object update(Long id, Patron Patron) {
        Optional<Patron> optionalPatron = PatronRepository.findById(id);
        if (!optionalPatron.isPresent()) {
            throw new RuntimeException("Error: Patron is not found.");
        }
        if (PatronRepository.existsByIdentityAndIdNot(Patron.getIdentity(), id)) {
            throw new RuntimeException("Error: identity is already taken.");
        }

        if (PatronRepository.existsByNameAndIdNot(Patron.getName(), id)) {
            throw new RuntimeException("Error: name is already taken.");
        }

        // Update the Patron details
        Patron existingPatron = optionalPatron.get();
        existingPatron.setIdentity(Patron.getIdentity());
        existingPatron.setName(Patron.getName());
        existingPatron.setContactInfo(Patron.getContactInfo());
        // Update other fields as needed...

        // Save the updated patron
        return PatronRepository.save(existingPatron);
    }

    public void delete(Long id) {
        PatronRepository.deleteById(id);
    }
}

