package com.jelmerpijnappel.jelmerqa;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for all endpoints related to questions
 */
@RestController
public class QuestionController {

    /**
     * Registers a question in the database.
     * @param questionData The question data to register.
     */
    @PostMapping("/register-question")
    public void registerQuestion(@RequestBody QuestionData questionData) {
        System.out.println(questionData.getQuestion());
        System.out.println(questionData.getEmail());
    }
}
