# 🤖 ENSET Bot — ChatBot Spring AI + MCP + Telegram

Un chatbot intelligent intégrant **Spring AI**, le protocole **MCP (Model Context Protocol)** et **Telegram**, permettant d'interroger des données RH via une interface conversationnelle.

---

## 🏗️ Architecture

```
┌─────────────────┐        ┌──────────────────┐        ┌─────────────────┐
│  Telegram Bot   │──────▶│   ENSET Bot      │ ──────▶│   MCP Server   │
│  (utilisateur)  │        │  (Spring AI)     │        │  (données RH)  │
└─────────────────┘        └──────────────────┘        └─────────────────┘
                                    │
                                    ▼
                           ┌──────────────────┐
                           │   OpenAI GPT-4o  │
                           └──────────────────┘
```

Le projet est composé de **deux modules** :

- **`enset-bot`** — Le chatbot principal (port `8087`) qui expose une API REST et un bot Telegram
- **`mcp-server`** — Le serveur MCP (port `8989`) qui fournit les outils/données RH

---

## ✨ Fonctionnalités

- 💬 Interaction via **Telegram** ou via l'API REST (`/chat`)
- 🧠 Mémoire conversationnelle avec `MessageChatMemoryAdvisor`
- 🔧 Intégration **MCP** pour appeler des outils externes (données employés)
- 🤖 Modèle **GPT-4o** via Spring AI

---

## 📁 Structure du projet

```
enset-bot/
├── src/main/java/ma/saifdine/ensetbot/
│   ├── EnsetBotApplication.java       # Point d'entrée Spring Boot
│   ├── agents/
│   │   └── AIAgent.java               # Agent IA (ChatClient + MCP tools + mémoire)
│   ├── telegram/
│   │   └── TelegramBot.java           # Bot Telegram (LongPolling)
│   └── web/
│       └── ChatController.java        # API REST /chat
├── src/main/resources/
│   └── application.properties         # Configuration (sans secrets)
└── .env                               # Variables d'environnement (NON commité)

mcp-server/
├── src/main/java/ma/saifdine/hd/mcpserver/
│   ├── McpServerApplication.java      # Point d'entrée MCP Server
│   └── tools/
│       └── McpTools.java              # Outils MCP (getEmployee, getAllEmployees)
└── src/main/resources/
    └── application.properties
```

---

## ⚙️ Configuration

### `application.properties` (enset-bot)

```properties
spring.application.name=enset-bot
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o
server.port=8087
spring.ai.mcp.client.streamable-http.connections.mcprh.url=http://localhost:8989/mcp
telegram.api.key=${TELEGRAM_BOT_TOKEN}
```

### `application.properties` (mcp-server)

```properties
spring.application.name=mcp-server
spring.ai.mcp.server.protocol=streamable
spring.ai.mcp.server.name=mcp-rh
server.port=8989
```

---

## 🔐 Variables d'environnement

Créez un fichier **`.env`** à la racine du projet `enset-bot` (jamais commité sur Git) :

```env
OPENAI_API_KEY=votre_clé_openai
TELEGRAM_BOT_TOKEN=votre_token_telegram
```

> ⚠️ Le fichier `.env` est dans `.gitignore`. Ne partagez jamais vos clés API.

---

## 🚀 Lancement

### 1. Démarrer le serveur MCP

```bash
cd mcp-server
mvn spring-boot:run
```

Le serveur démarre sur `http://localhost:8989`

### 2. Démarrer le bot

```bash
cd enset-bot
mvn spring-boot:run
```

Le bot démarre sur `http://localhost:8087`

### 3. Tester via l'API REST

```bash
curl "http://localhost:8087/chat?query=Qui+sont+les+employés"
```

### 4. Tester via Telegram

Ouvrez votre bot Telegram et envoyez un message, par exemple :
- `Liste tous les employés`
- `Quel est le salaire de Hassan ?`

---

## 🛠️ Outils MCP disponibles

| Outil | Description |
|-------|-------------|
| `getAllEmployees` | Retourne la liste de tous les employés |
| `getEmployee` | Retourne les infos d'un employé par son nom |

---

## 🧰 Technologies

| Technologie | Version |
|-------------|---------|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Spring AI | 1.1.0-M4 |
| MCP SDK | 0.15.0 |
| TelegramBots | 6.9.7.1 |
| OpenAI Model | GPT-4o |
| Lombok | 1.18.32 |

---

## 📄 Licence

Projet académique — ENSET Mohammedia
