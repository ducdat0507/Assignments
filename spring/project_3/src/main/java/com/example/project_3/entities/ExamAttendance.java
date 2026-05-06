package com.example.project_3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exam_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "scheduler_id")
    private Scheduler scheduler;

    @ManyToOne
    @JoinColumn(name = "student_roll_no")
    private Student student;
}