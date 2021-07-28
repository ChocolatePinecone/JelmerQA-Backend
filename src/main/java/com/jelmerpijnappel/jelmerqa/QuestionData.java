package com.jelmerpijnappel.jelmerqa;

/**
 * A POJO for the question data that can be stored to, or retrieved from the database
 */
public class QuestionData {

    private final String question;
    private final String email;

    /**
     * Creates a question object containing question text and an email address to send question state updates to
     * @param question A string representing a question
     * @param email A string representing an email address to send question state updates to
     */
    public QuestionData(String question, String email) {
        this.question = question;
        this.email = email;
    }

    /**
     * Gets the question text
     * @return A string containing the stored question text
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the email address
     * @return A string containing the stored email address
     */
    public String getEmail() {
        return email;
    }
}
