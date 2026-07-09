package com.womensafety.service;

import com.womensafety.model.EmergencyContact;
import com.womensafety.model.SosAlert;
import com.womensafety.model.User;
import com.womensafety.repository.EmergencyContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sends the SOS message to trusted contacts.
 *
 * PHASE 1: logs the notification to the console (so you can SEE it working
 * immediately, for free, with zero setup) and stores it as "sent" in-app.
 * PHASE 2: will plug in real email (Spring Mail + free Gmail SMTP) and
 * real SMS (Twilio free trial) - both require extra account setup, so
 * they are intentionally left out of Phase 1 to keep first deployment simple.
 */
@Service
public class NotificationService {

    private final EmergencyContactRepository contactRepository;

    public NotificationService(EmergencyContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void notifyContactsOfSos(User user, SosAlert alert) {
        List<EmergencyContact> contacts = contactRepository.findByUserOrderByPriorityAsc(user);

        if (contacts.isEmpty()) {
            System.out.println("[NOTIFY] " + user.getEmail() + " has no emergency contacts saved.");
            return;
        }

        for (EmergencyContact contact : contacts) {
            System.out.println("[NOTIFY] Sending SOS to " + contact.getName()
                    + " (" + contact.getPhone() + "): " + alert.getMessage()
                    + " | Location: " + alert.getLatitude() + "," + alert.getLongitude());
            // Phase 2 TODO: replace this println with real email/SMS sending
        }
    }
}
