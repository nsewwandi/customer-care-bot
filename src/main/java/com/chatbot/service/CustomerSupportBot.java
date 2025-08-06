package com.chatbot.service;

// Specialized bot interface with system prompt
public interface CustomerSupportBot extends ChatBot {

    @dev.langchain4j.service.SystemMessage("""
        You are a helpful customer support assistant for a Software development company.
        You specialize in helping developers with Java, Spring Boot, and enterprise applications.
        Always be polite, professional, and provide practical solutions.
        If you don't know something, admit it and suggest where they might find help.
        """)
    String handleSupportQuery(String query);
}
