package com.jelmerpijnappel.jelmerqa;

import com.jelmerpijnappel.jelmerqa.database.DatabaseDelegate;
import com.jelmerpijnappel.jelmerqa.database.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for all endpoints related to questions
 */
@RestController
@CrossOrigin(origins = "https://jelmerpijnappel.com/")
public class QuestionController {

    private final DatabaseDelegate databaseDelegate;

    @Autowired
    public QuestionController(DatabaseDelegate databaseDelegate) {
        this.databaseDelegate = databaseDelegate;
    }

    /**
     * Registers a question in the database.
     * @param question The question data to register.
     */
    @PostMapping("/register-question")
    public void registerQuestion(@RequestBody Question question) {
        databaseDelegate.persistQuestion(question);
    }

    /**
     * Returns all questions in the database.
     */
    @GetMapping("/get-questions")
    public List<Question> readAllQuestions() {
        return databaseDelegate.readAllQuestions();
    }

    /**
     * Answer an existing question
     */
    @PutMapping("/answer-question")
    public void answerQuestion(@RequestParam Long questionId, @RequestParam String answer) {
        databaseDelegate.persistAnswerToQuestion(questionId, answer);

        // TODO: Send answer update email

        databaseDelegate.deleteEmailFromQuestion(questionId);
    }
}
