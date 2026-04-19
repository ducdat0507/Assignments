package com.example.project_10;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "attendees")
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @NotNull
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    // Constructors
    public Attendee() {}

    public Attendee(Event event, String name, String email) {
        this.event = event;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}