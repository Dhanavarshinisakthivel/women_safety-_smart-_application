package com.womensafety.service;

import com.womensafety.dto.ContactRequest;
import com.womensafety.model.EmergencyContact;
import com.womensafety.model.User;
import com.womensafety.repository.EmergencyContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final EmergencyContactRepository contactRepository;

    public ContactService(EmergencyContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<EmergencyContact> getContacts(User user) {
        return contactRepository.findByUserOrderByPriorityAsc(user);
    }

    public EmergencyContact addContact(User user, ContactRequest request) {
        EmergencyContact contact = new EmergencyContact();
        contact.setUser(user);
        contact.setName(request.getName());
        contact.setPhone(request.getPhone());
        contact.setEmail(request.getEmail());
        contact.setRelationship(request.getRelationship());
        contact.setPriority(request.getPriority() != null ? request.getPriority() : 1);
        return contactRepository.save(contact);
    }

    public EmergencyContact updateContact(User user, Long contactId, ContactRequest request) {
        EmergencyContact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found."));

        if (!contact.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You do not have permission to edit this contact.");
        }

        contact.setName(request.getName());
        contact.setPhone(request.getPhone());
        contact.setEmail(request.getEmail());
        contact.setRelationship(request.getRelationship());
        if (request.getPriority() != null) {
            contact.setPriority(request.getPriority());
        }
        return contactRepository.save(contact);
    }

    public void deleteContact(User user, Long contactId) {
        EmergencyContact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found."));

        if (!contact.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You do not have permission to delete this contact.");
        }
        contactRepository.delete(contact);
    }
}
