/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_12.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ducdat0507
 */
@Entity
@Table(name = "schedulers", catalog = "exam_db_2", schema = "")
@NamedQueries({
    @NamedQuery(name = "Scheduler.findAll", query = "SELECT s FROM Scheduler s"),
    @NamedQuery(name = "Scheduler.findById", query = "SELECT s FROM Scheduler s WHERE s.id = :id"),
    @NamedQuery(name = "Scheduler.findByTimeSlot", query = "SELECT s FROM Scheduler s WHERE s.timeSlot = :timeSlot"),
    @NamedQuery(name = "Scheduler.findByLocation", query = "SELECT s FROM Scheduler s WHERE s.location = :location"),
    @NamedQuery(name = "Scheduler.findByExamSitter", query = "SELECT s FROM Scheduler s WHERE s.examSitter = :examSitter"),
    @NamedQuery(name = "Scheduler.findByStartAt", query = "SELECT s FROM Scheduler s WHERE s.startAt = :startAt"),
    @NamedQuery(name = "Scheduler.findByEndAt", query = "SELECT s FROM Scheduler s WHERE s.endAt = :endAt")})
public class Scheduler implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 20)
    @Column(name = "time_slot", length = 20)
    private String timeSlot;
    @Size(max = 30)
    @Column(length = 30)
    private String location;
    @Size(max = 50)
    @Column(name = "exam_sitter", length = 50)
    private String examSitter;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endAt;
    @OneToMany(mappedBy = "schedulerId")
    private List<ExamAttendance> examAttendanceList;

    public Scheduler() {
    }

    public Scheduler(Integer id) {
        this.id = id;
    }

    public Scheduler(Integer id, Date startAt, Date endAt) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExamSitter() {
        return examSitter;
    }

    public void setExamSitter(String examSitter) {
        this.examSitter = examSitter;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scheduler)) {
            return false;
        }
        Scheduler other = (Scheduler) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.project_12.model.Scheduler[ id=" + id + " ]";
    }
    
}
