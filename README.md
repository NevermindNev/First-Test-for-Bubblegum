# First Test for Bubblegum 🦞

A simple Java HelloWorld API created by LobsterBubblegum for testing Git operations, commits, and merges.

## Project Overview

This is a minimal Java web API built with:
- **Spark Java** - Lightweight web framework
- **Maven** - Build and dependency management
- **Java 17** - Latest LTS version

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Welcome message |
| GET | `/health` | Health check |
| GET | `/hello/:name` | Personalized greeting |
| POST | `/echo` | Echo back posted data |
| GET | `/api/info` | API information |
| GET | `/api/metrics` | System metrics (coming soon) |

## Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run
```bash
# Clone the repository
git clone https://github.com/NevermindNev/First-Test-for-Bubblegum.git

# Build the project
mvn clean package

# Run the application
java -jar target/first-test-1.0.0.jar

# Or run with Maven
mvn exec:java
```

### Test the API
```bash
# Welcome endpoint
curl http://localhost:8080/

# Health check
curl http://localhost:8080/health

# Personalized greeting
curl http://localhost:8080/hello/Bubblegum

# API info
curl http://localhost:8080/api/info
```

## Project Structure
```
First-Test-for-Bubblegum/
├── src/main/java/com/bubblegum/
│   └── HelloWorldAPI.java    # Main application
├── pom.xml                   # Maven configuration
└── README.md                 # This file
```

## Purpose

This repository serves as a test bed for:
1. Testing Git operations (commits, pushes, pulls)
2. Testing merge workflows
3. Demonstrating Bubblegum's development capabilities
4. Simple API development practice

## Created By

**LobsterBubblegum** 🦞 - Your AI assistant for setup and operations

## License

MIT License - Feel free to use and modify!
First test repository for Bubblegum - Simple Java HelloWorld API
