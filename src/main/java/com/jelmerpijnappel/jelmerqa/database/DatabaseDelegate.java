package com.jelmerpijnappel.jelmerqa.database;

import com.jelmerpijnappel.jelmerqa.database.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A delegate that handles all communication with the database
 */
@Repository
@Slf4j
public class DatabaseDelegate {

    private final QuestionDAO questionDAO;

    public DatabaseDelegate(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    /**
     * Persist a question to the database
     * @param question the question to persist to the database
     */
    public void persistQuestion(Question question) {
        log.debug(String.format("Persisting question: '%s' with email: '%s' to database", question.getText(), question.getEmail()));
        questionDAO.save(question);
    }

    /**
     * Get all questions from the database
     */
    public List<Question> readAllQuestions() {
        return questionDAO.findAll();
    }

    /**
     * Persist an answer to a question to the database
     * @param answer the answer to persist to the database
     * @param questionId the ID of the question to answer
     */
    public void persistAnswerToQuestion(Long questionId, String answer) {
        Question question = questionDAO.getById(questionId);
        log.debug(String.format("Persisting answer: '%s' to question: '%s' to database", answer, question.getText()));

        question.setAnswer(answer);
        questionDAO.save(question);
    }

    /**
     * Delete the stored email address from a question in the database
     * @param questionId the ID of the question to remove the email from
     */
    public void deleteEmailFromQuestion(Long questionId) {
        Question question = questionDAO.getById(questionId);
        log.debug(String.format("Removing stored email address from question: '%s' in the database", question.getText()));

        question.setEmail("");
        questionDAO.save(question);
    }

    /**
     * Delete a question in the database by id
     * @param questionId the ID of the question to remove
     */
    public void deleteQuestion(Long questionId) {
        Question question = questionDAO.getById(questionId);
        log.debug(String.format("Removing stored question: '%s' from the database", question.getText()));

        questionDAO.delete(question);
    }
}

interface QuestionDAO extends JpaRepository<Question, Long> {
}