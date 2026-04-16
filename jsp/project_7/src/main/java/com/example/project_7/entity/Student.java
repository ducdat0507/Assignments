/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_7.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author student
 */
@Entity
@Table(name = "students")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByRollNo", query = "SELECT s FROM Student s WHERE s.rollNo = :rollNo"),
    @NamedQuery(name = "Student.findByFullName", query = "SELECT s FROM Student s WHERE s.fullName = :fullName"),
    @NamedQuery(name = "Student.findByClassName", query = "SELECT s FROM Student s WHERE s.className = :className")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "roll_no", nullable = false, length = 20)
    private String rollNo;
    @Size(max = 50)
    @Column(name = "full_name", length = 50)
    private String fullName;
    @Size(max = 20)
    @Column(name = "class_name", length = 20)
    private String className;
    @OneToMany(mappedBy = "studentRollNo")
    private List<ExamAttendance> examAttendanceList;

    public Student() {
    }

    public Student(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ExamAttendance> getExamAttendanceList() {
        return examAttendanceList;
    }

    public void setExamAttendanceList(List<ExamAttendance> examAttendanceList) {
        this.examAttendanceList = examAttendanceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rollNo != null ? rollNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.rollNo == null && other.rollNo != null) || (this.rollNo != null && !this.rollNo.equals(other.rollNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.project_7.entity.Student[ rollNo=" + rollNo + " ]";
    }
    
}
