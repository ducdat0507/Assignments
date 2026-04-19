package com.example.project_11.entity;

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 5, message = "Username must have at least 5 characters")
    private String username;

    @NotNull
    @Size(min = 5, message = "Password must have at least 5 characters")
    private String password;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
