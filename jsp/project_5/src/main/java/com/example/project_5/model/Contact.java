package com.example.project_5.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "first_name", length = 50)
    protected String firstName;

    @Column(name = "last_name", length = 50)
    protected String lastName;

    @Column(name = "created_date")
    protected LocalDate createdDate;

    @Column(name = "phone_number", length = 20)
    protected String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friend_group")
    @JoinColumn(name = "group_id")
    protected FriendGroup friendGroup;

}
