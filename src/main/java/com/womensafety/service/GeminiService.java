package com.womensafety.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

/**
 * ONE central place that knows how to "ask Gemini a question".
 * Every AI feature (chatbot, message generator, risk prediction, etc.)
 * calls the askGemini() method below with a different prompt (instruction).
 *
 * Why centralize this? If Google changes their API tomorrow, we only
 * fix code in ONE file instead of ten.
 */
@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final WebClient webClient = WebClient.builder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Sends a prompt (a piece of text instruction) to Gemini and returns
     * its plain-text reply.
     *
     * @param prompt the instruction/question we want the AI to answer
     * @return the AI's text response, or a friendly fallback message if
     *         something goes wrong (e.g. no internet, bad API key, quota hit)
     */
    public String askGemini(String prompt) {
        if (apiKey == null || apiKey.isBlank()) {
            return "AI features are not configured yet. Please set the GEMINI_API_KEY environment variable.";
        }

        try {
            Map<String, Object> requestBody = Map.of(
                    "contents", List.of(
                            Map.of("parts", List.of(Map.of("text", prompt)))
                    )
            );

            String response = webClient.post()
                    .uri(apiUrl + "?key=" + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // .block() = wait for the answer before moving on
                              // (fine for our beginner-friendly, low-traffic app)

            return extractText(response);

        } catch (Exception e) {
            // Never let an AI hiccup crash the whole request - degrade gracefully
            return "Sorry, the AI assistant is temporarily unavailable. (" + e.getMessage() + ")";
        }
    }

    /** Pulls the actual generated text out of Gemini's JSON response shape. */
    private String extractText(String rawJson) {
        try {
            JsonNode root = objectMapper.readTree(rawJson);
            return root.path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();
        } catch (Exception e) {
            return "The AI returned an unexpected response format.";
        }
    }
}
