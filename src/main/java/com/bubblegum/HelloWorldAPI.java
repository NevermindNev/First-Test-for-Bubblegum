package com.bubblegum;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple HelloWorld API for testing Bubblegum capabilities
 * This is a minimal Java web API using Spark Java framework
 */
public class HelloWorldAPI {
    
    private static final Gson gson = new Gson();
    
    public static void main(String[] args) {
        // Set port (default: 4567)
        port(8080);
        
        // Enable CORS
        enableCORS();
        
        System.out.println("Starting Bubblegum HelloWorld API on port 8080...");
        
        // Basic GET endpoint
        get("/", (req, res) -> {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hello from Bubblegum!");
            response.put("status", "success");
            response.put("timestamp", String.valueOf(System.currentTimeMillis()));
            res.type("application/json");
            return gson.toJson(response);
        });
        
        // Health check endpoint
        get("/health", (req, res) -> {
            Map<String, String> response = new HashMap<>();
            response.put("status", "healthy");
            response.put("service", "Bubblegum HelloWorld API");
            response.put("version", "1.0.0");
            res.type("application/json");
            return gson.toJson(response);
        });
        
        // Personalized greeting endpoint
        get("/hello/:name", (req, res) -> {
            String name = req.params(":name");
            Map<String, String> response = new HashMap<>();
            response.put("greeting", "Hello, " + name + "!");
            response.put("from", "Bubblegum 🦞");
            response.put("timestamp", String.valueOf(System.currentTimeMillis()));
            res.type("application/json");
            return gson.toJson(response);
        });
        
        // POST endpoint for echo
        post("/echo", (req, res) -> {
            try {
                String body = req.body();
                if (body == null || body.trim().isEmpty()) {
                    Map<String, Object> error = new HashMap<>();
                    error.put("error", "Request body cannot be empty");
                    error.put("status", 400);
                    res.status(400);
                    res.type("application/json");
                    return gson.toJson(error);
                }
                
                Map<String, Object> response = new HashMap<>();
                response.put("received", body);
                response.put("echoed", true);
                response.put("timestamp", System.currentTimeMillis());
                response.put("length", body.length());
                res.type("application/json");
                return gson.toJson(response);
            } catch (Exception e) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Failed to process request: " + e.getMessage());
                error.put("status", 500);
                res.status(500);
                res.type("application/json");
                return gson.toJson(error);
            }
        });
        
        // 404 handler for undefined routes
        notFound((req, res) -> {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Endpoint not found: " + req.pathInfo());
            error.put("status", 404);
            error.put("available_endpoints", new String[] {
                "GET /", "GET /health", "GET /hello/:name", 
                "POST /echo", "GET /api/info"
            });
            res.status(404);
            res.type("application/json");
            return gson.toJson(error);
        });
        
        // Exception handler
        exception(Exception.class, (e, req, res) -> {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Internal server error");
            error.put("message", e.getMessage());
            error.put("status", 500);
            error.put("path", req.pathInfo());
            res.status(500);
            res.type("application/json");
            return gson.toJson(error);
        });
        
        // API info endpoint
        get("/api/info", (req, res) -> {
            Map<String, Object> info = new HashMap<>();
            info.put("name", "Bubblegum HelloWorld API");
            info.put("description", "First test API created by LobsterBubblegum");
            info.put("version", "1.0.0");
            info.put("endpoints", new String[] {
                "GET /",
                "GET /health", 
                "GET /hello/:name",
                "POST /echo",
                "GET /api/info"
            });
            info.put("creator", "LobsterBubblegum 🦞");
            info.put("repository", "https://github.com/NevermindNev/First-Test-for-Bubblegum");
            res.type("application/json");
            return gson.toJson(info);
        });
        
        System.out.println("API started successfully!");
        System.out.println("Try: curl http://localhost:8080/");
        System.out.println("Try: curl http://localhost:8080/hello/Bubblegum");
    }
    
    /**
     * Enable CORS for all routes
     */
    private static void enableCORS() {
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
        });
    }
}