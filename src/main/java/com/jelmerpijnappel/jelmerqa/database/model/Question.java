package com.jelmerpijnappel.jelmerqa.database.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A POJO for the question data that can be stored to, or retrieved from the database
 */
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String email;
    private String answer;

    /**
     * Creates an empty question object
     */
    protected Question() {

    }

    /**
     * Creates a question object containing question text and an email address to send question state updates to
     * @param text A string representing a question
     * @param email A string representing an email address to send question state updates to
     */
    public Question(String text, String email) {
        this.text = text;
        this.email = email;
    }

    /**
     * Gets the question id
     * @return A string containing the stored question id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Gets the question text
     * @return A string containing the stored question text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gets the email address
     * @return A string containing the stored email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email address
     * @param email A string containing email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the answer text
     * @return A string containing the stored answer text
     */
    public String getAnswer() {
        return this.answer;
    }

    /**
     * Sets the answer text
     * @param answer A string containing the answer text to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
