package com.womensafety.service;

import com.womensafety.dto.FeedbackRequest;
import com.womensafety.model.Feedback;
import com.womensafety.model.User;
import com.womensafety.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback submitFeedback(User userOrNull, FeedbackRequest request) {
        Feedback feedback = new Feedback();
        feedback.setUser(userOrNull);
        feedback.setName(request.getName());
        feedback.setEmail(request.getEmail());
        feedback.setMessage(request.getMessage());
        feedback.setRating(request.getRating());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}
