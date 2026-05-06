package com.example.project_2.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long studentId;
    private String fullName;
    private String batch;
    private List<Float> grades;

    public float getAverageGrade() {
        return grades.stream().reduce(0f, Float::sum);
    }

    public String getRating() {
        float avgGrade = getAverageGrade();
        if (avgGrade > 8) return "A";
        if (avgGrade > 6) return "B";
        if (avgGrade >= 5) return "C";
        return "D";
    }
}
