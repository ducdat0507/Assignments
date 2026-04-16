/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_7.dao;

import com.example.project_7.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.example.project_7.entity.Exam;
import com.example.project_7.entity.ExamAttendance;
import com.example.project_7.entity.Scheduler;
import com.example.project_7.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

/**
 *
 * @author student
 */
public class ExamAttendanceJpaController implements Serializable {

    public ExamAttendanceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamAttendance examAttendance) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exam examId = examAttendance.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getId());
                examAttendance.setExamId(examId);
            }
            Scheduler schedulerId = examAttendance.getSchedulerId();
            if (schedulerId != null) {
                schedulerId = em.getReference(schedulerId.getClass(), schedulerId.getId());
                examAttendance.setSchedulerId(schedulerId);
            }
            Student studentRollNo = examAttendance.getStudentRollNo();
            if (studentRollNo != null) {
                studentRollNo = em.getReference(studentRollNo.getClass(), studentRollNo.getRollNo());
                examAttendance.setStudentRollNo(studentRollNo);
            }
            em.persist(examAttendance);
            if (examId != null) {
                examId.getExamAttendanceList().add(examAttendance);
                examId = em.merge(examId);
            }
            if (schedulerId != null) {
                schedulerId.getExamAttendanceList().add(examAttendance);
                schedulerId = em.merge(schedulerId);
            }
            if (studentRollNo != null) {
                studentRollNo.getExamAttendanceList().add(examAttendance);
                studentRollNo = em.merge(studentRollNo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExamAttendance examAttendance) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamAttendance persistentExamAttendance = em.find(ExamAttendance.class, examAttendance.getId());
            Exam examIdOld = persistentExamAttendance.getExamId();
            Exam examIdNew = examAttendance.getExamId();
            Scheduler schedulerIdOld = persistentExamAttendance.getSchedulerId();
            Scheduler schedulerIdNew = examAttendance.getSchedulerId();
            Student studentRollNoOld = persistentExamAttendance.getStudentRollNo();
            Student studentRollNoNew = examAttendance.getStudentRollNo();
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getId());
                examAttendance.setExamId(examIdNew);
            }
            if (schedulerIdNew != null) {
                schedulerIdNew = em.getReference(schedulerIdNew.getClass(), schedulerIdNew.getId());
                examAttendance.setSchedulerId(schedulerIdNew);
            }
            if (studentRollNoNew != null) {
                studentRollNoNew = em.getReference(studentRollNoNew.getClass(), studentRollNoNew.getRollNo());
                examAttendance.setStudentRollNo(studentRollNoNew);
            }
            examAttendance = em.merge(examAttendance);
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getExamAttendanceList().remove(examAttendance);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getExamAttendanceList().add(examAttendance);
                examIdNew = em.merge(examIdNew);
            }
            if (schedulerIdOld != null && !schedulerIdOld.equals(schedulerIdNew)) {
                schedulerIdOld.getExamAttendanceList().remove(examAttendance);
                schedulerIdOld = em.merge(schedulerIdOld);
            }
            if (schedulerIdNew != null && !schedulerIdNew.equals(schedulerIdOld)) {
                schedulerIdNew.getExamAttendanceList().add(examAttendance);
                schedulerIdNew = em.merge(schedulerIdNew);
            }
            if (studentRollNoOld != null && !studentRollNoOld.equals(studentRollNoNew)) {
                studentRollNoOld.getExamAttendanceList().remove(examAttendance);
                studentRollNoOld = em.merge(studentRollNoOld);
            }
            if (studentRollNoNew != null && !studentRollNoNew.equals(studentRollNoOld)) {
                studentRollNoNew.getExamAttendanceList().add(examAttendance);
                studentRollNoNew = em.merge(studentRollNoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = examAttendance.getId();
                if (findExamAttendance(id) == null) {
                    throw new NonexistentEntityException("The examAttendance with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamAttendance examAttendance;
            try {
                examAttendance = em.getReference(ExamAttendance.class, id);
                examAttendance.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examAttendance with id " + id + " no longer exists.", enfe);
            }
            Exam examId = examAttendance.getExamId();
            if (examId != null) {
                examId.getExamAttendanceList().remove(examAttendance);
                examId = em.merge(examId);
            }
            Scheduler schedulerId = examAttendance.getSchedulerId();
            if (schedulerId != null) {
                schedulerId.getExamAttendanceList().remove(examAttendance);
                schedulerId = em.merge(schedulerId);
            }
            Student studentRollNo = examAttendance.getStudentRollNo();
            if (studentRollNo != null) {
                studentRollNo.getExamAttendanceList().remove(examAttendance);
                studentRollNo = em.merge(studentRollNo);
            }
            em.remove(examAttendance);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExamAttendance> findExamAttendanceEntities() {
        return findExamAttendanceEntities(true, -1, -1);
    }

    public List<ExamAttendance> findExamAttendanceEntities(int maxResults, int firstResult) {
        return findExamAttendanceEntities(false, maxResults, firstResult);
    }

    private List<ExamAttendance> findExamAttendanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ExamAttendance.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ExamAttendance findExamAttendance(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamAttendance.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamAttendanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExamAttendance> rt = cq.from(ExamAttendance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
