package com.womensafety.service;

import com.womensafety.model.User;
import org.springframework.stereotype.Service;

/**
 * This service turns our app's data (user info, incident notes, location)
 * into good "prompts" (instructions) for Gemini, for each specific AI
 * feature. GeminiService just knows how to call the AI - this class knows
 * WHAT to ask it for each feature.
 *
 * PHASE 1 includes 3 AI features. More will be added in Phase 2:
 *   1. AI Safety Assistant Chatbot         <-- included
 *   2. AI Emergency Message Generator      <-- included
 *   5. AI Incident Report Generator        <-- included
 *   10. AI Risk Level Prediction           <-- included
 *   (others: voice distress, route planner, contact recommendation,
 *    translation, fake-alert detection -> Phase 2)
 */
@Service
public class AiFeatureService {

    private final GeminiService geminiService;

    public AiFeatureService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    /** FEATURE 1: AI Safety Assistant Chatbot */
    public String chatWithSafetyAssistant(String userMessage) {
        String prompt = """
                You are a calm, supportive women's safety assistant inside a
                safety app. Answer the user's question with clear, practical,
                actionable safety advice. Keep the reply under 150 words.
                If the user describes an ongoing emergency, your FIRST line
                must tell them to trigger the in-app SOS button and/or call
                local emergency services immediately.

                User question: %s
                """.formatted(userMessage);
        return geminiService.askGemini(prompt);
    }

    /** FEATURE 2: AI Emergency Message Generator (used automatically during SOS) */
    public String generateSosMessage(User user, Double latitude, Double longitude) {
        String locationText = (latitude != null && longitude != null)
                ? "at coordinates latitude " + latitude + ", longitude " + longitude
                : "at an unknown location (location not available)";

        String prompt = """
                Write a short, urgent, clear emergency SOS text message
                (under 40 words) to send to trusted contacts. The message
                is from %s, who has just triggered an SOS alert %s.
                It must ask the reader to call them immediately and/or
                contact local authorities. Do not add hashtags or emojis.
                """.formatted(user.getFullName(), locationText);

        return geminiService.askGemini(prompt);
    }

    /** FEATURE 5: AI Incident Report Generator - turns short notes into a structured report */
    public String generateIncidentReport(String rawNotes) {
        String prompt = """
                Rewrite the following short, informal incident notes into a
                clear, structured incident report suitable for police or
                administrative review. Use this format exactly:

                SUMMARY: (one sentence)
                WHAT HAPPENED: (2-4 sentences, factual, neutral tone)
                LOCATION DETAILS: (if mentioned, otherwise say "Not specified")
                RECOMMENDED NEXT STEPS: (1-2 bullet points)

                Raw notes from the user: %s
                """.formatted(rawNotes);

        return geminiService.askGemini(prompt);
    }

    /** FEATURE 10: AI Risk Level Prediction - scores urgency of a reported incident */
    public String predictRiskLevel(String incidentDescription) {
        String prompt = """
                Read the following incident description from a women's safety
                app. Reply with ONLY one word - LOW, MEDIUM, or HIGH - to
                indicate the urgency/risk level. No explanation, no extra text.

                Incident description: %s
                """.formatted(incidentDescription);

        String result = geminiService.askGemini(prompt).trim().toUpperCase();

        // Safety net: if the AI replies with something unexpected, default to MEDIUM
        // so an incident is never silently ignored.
        if (result.contains("HIGH")) return "HIGH";
        if (result.contains("LOW")) return "LOW";
        return "MEDIUM";
    }
}
