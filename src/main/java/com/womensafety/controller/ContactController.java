package com.womensafety.controller;

import com.womensafety.dto.ContactRequest;
import com.womensafety.model.EmergencyContact;
import com.womensafety.service.ContactService;
import com.womensafety.service.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final CurrentUserService currentUserService;

    public ContactController(ContactService contactService, CurrentUserService currentUserService) {
        this.contactService = contactService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public ResponseEntity<List<EmergencyContact>> listContacts() {
        return ResponseEntity.ok(contactService.getContacts(currentUserService.getCurrentUser()));
    }

    @PostMapping
    public ResponseEntity<EmergencyContact> addContact(@RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactService.addContact(currentUserService.getCurrentUser(), request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmergencyContact> updateContact(@PathVariable Long id, @RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactService.updateContact(currentUserService.getCurrentUser(), id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(currentUserService.getCurrentUser(), id);
        return ResponseEntity.noContent().build();
    }
}
