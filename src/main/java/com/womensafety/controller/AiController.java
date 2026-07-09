package com.womensafety.controller;

import com.womensafety.dto.ChatRequest;
import com.womensafety.service.AiFeatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiFeatureService aiFeatureService;

    public AiController(AiFeatureService aiFeatureService) {
        this.aiFeatureService = aiFeatureService;
    }

    /** AI FEATURE 1: Safety chatbot */
    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody ChatRequest request) {
        String reply = aiFeatureService.chatWithSafetyAssistant(request.getMessage());
        return ResponseEntity.ok(Map.of("reply", reply));
    }
}
