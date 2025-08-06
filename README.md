# customer-care-bot
Customer Support Chatbot with Java, LangChain4j &amp; Ollama

This project demonstrates how to build a lightweight customer support chatbot using:

- **Java** (Spring Boot)
- **LangChain4j** – a Java framework for LLM integration
- **Ollama** – to run open-source language models like LLaMA 3 locally

It’s perfect for developers looking to add AI-powered conversational features to their Java applications.

---

## 🚀 Features

- 🤖 Local chatbot with LangChain4j + Ollama
- 🧩 Uses LLaMA 3.2 as the model
- 💬 System message scope for specialized support
- 📦 Simple REST API to chat with the bot
- 🧪 Easily extendable and customizable

---

## 🔧 Prerequisites

- Java 21+
- Maven
- Spring Boot
- Ollama (for local LLM)

---

## 📥 Setup Instructions

### 1. Install Ollama

Go to [https://ollama.com](https://ollama.com) and download Ollama for your OS.

In your terminal, pull and run the model:

```bash
ollama run llama3.2
You can change the model in ChatController.java.

2. Clone the Repo
bash
Copy
Edit
git clone https://github.com/your-username/customer-support-bot-java.git
cd customer-support-bot-java
3. Run the App
bash
Copy
Edit
mvn spring-boot:run
The backend will be available at:
http://localhost:8080/support

🛠 API
POST /support
Send user message to the bot.

Request Body:

json
Copy
Edit
{
  "message": "How do I create a REST API with Spring Boot?"
}
Response:

json
Copy
Edit
{
  "response": "To create a REST API with Spring Boot..."
}
✨ Tech Stack
Java 21

Spring Boot

LangChain4j

Ollama

LLaMA 3.2 (default)

📌 Customization
Update your SystemMessage in CustomerSupportBot.java to change the bot's personality or domain.

Use any frontend (React, Angular, etc.) to connect with the /support endpoint.
