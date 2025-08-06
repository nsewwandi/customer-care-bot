package com.chatbot.controller;

import com.chatbot.dtos.ChatRequest;
import com.chatbot.dtos.ChatResponse;
import com.chatbot.service.ChatBot;
import com.chatbot.service.CustomerSupportBot;
import org.springframework.web.bind.annotation.*;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatBot supportBot;
    public ChatController() {
        // Using free Ollama model
        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3.2")
                .temperature(0.3)
                .build();

        // Create specialized customer support bot
        this.supportBot = AiServices.builder(CustomerSupportBot.class)
                .chatLanguageModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }

    @PostMapping("/support")
    public ChatResponse customerSupport(@RequestBody ChatRequest request) {
        String response = ((CustomerSupportBot) supportBot).handleSupportQuery(request.getMessage());
        return new ChatResponse(response);
    }

    // Simple web interface
    @GetMapping("/")
    public String home() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Java ChatBot</title>
                <style>
                    body { font-family: Arial; max-width: 600px; margin: 50px auto; }
                    #chat { border: 1px solid #ccc; height: 400px; overflow-y: scroll; padding: 10px; }
                    .message { margin: 10px 0; }
                    .user { color: blue; }
                    .bot { color: green; }
                    input { width: 80%; padding: 10px; }
                    button { padding: 10px 20px; }
                </style>
            </head>
            <body>
                <h1>Java LangChain4j ChatBot (FREE with Ollama)</h1>
                <p><em>Powered by Llama3.2 running locally - no API costs!</em></p>
                <div id="chat"></div>
                <input type="text" id="message" placeholder="Type your message...">
                <button onclick="sendMessage()">Send</button>
                
                <script>
                    function sendMessage() {
                        const input = document.getElementById('message');
                        const message = input.value.trim();
                        if (!message) return;
                        
                        addMessage('You: ' + message, 'user');
                        input.value = '';
                        
                        fetch('/chat/support', {
                            method: 'POST',
                            headers: {'Content-Type': 'application/json'},
                            body: JSON.stringify({message: message})
                        })
                        .then(response => response.json())
                        .then(data => addMessage('Bot: ' + data.response, 'bot'))
                        .catch(error => addMessage('Error: ' + error, 'bot'));
                    }
                    
                    function addMessage(text, className) {
                        const chat = document.getElementById('chat');
                        const div = document.createElement('div');
                        div.className = 'message ' + className;
                        div.textContent = text;
                        chat.appendChild(div);
                        chat.scrollTop = chat.scrollHeight;
                    }
                    
                    document.getElementById('message').addEventListener('keypress', function(e) {
                        if (e.key === 'Enter') sendMessage();
                    });
                </script>
            </body>
            </html>
            """;
    }
}

