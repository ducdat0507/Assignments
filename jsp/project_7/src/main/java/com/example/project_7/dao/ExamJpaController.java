/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_7.dao;

import com.example.project_7.dao.exceptions.NonexistentEntityException;
import com.example.project_7.entity.Exam;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.example.project_7.entity.ExamAttendance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student
 */
public class ExamJpaController implements Serializable {

    public ExamJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exam exam) {
        if (exam.getExamAttendanceList() == null) {
            exam.setExamAttendanceList(new ArrayList<ExamAttendance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ExamAttendance> attachedExamAttendanceList = new ArrayList<ExamAttendance>();
            for (ExamAttendance examAttendanceListExamAttendanceToAttach : exam.getExamAttendanceList()) {
                examAttendanceListExamAttendanceToAttach = em.getReference(examAttendanceListExamAttendanceToAttach.getClass(), examAttendanceListExamAttendanceToAttach.getId());
                attachedExamAttendanceList.add(examAttendanceListExamAttendanceToAttach);
            }
            exam.setExamAttendanceList(attachedExamAttendanceList);
            em.persist(exam);
            for (ExamAttendance examAttendanceListExamAttendance : exam.getExamAttendanceList()) {
                Exam oldExamIdOfExamAttendanceListExamAttendance = examAttendanceListExamAttendance.getExamId();
                examAttendanceListExamAttendance.setExamId(exam);
                examAttendanceListExamAttendance = em.merge(examAttendanceListExamAttendance);
                if (oldExamIdOfExamAttendanceListExamAttendance != null) {
                    oldExamIdOfExamAttendanceListExamAttendance.getExamAttendanceList().remove(examAttendanceListExamAttendance);
                    oldExamIdOfExamAttendanceListExamAttendance = em.merge(oldExamIdOfExamAttendanceListExamAttendance);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exam exam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exam persistentExam = em.find(Exam.class, exam.getId());
            List<ExamAttendance> examAttendanceListOld = persistentExam.getExamAttendanceList();
            List<ExamAttendance> examAttendanceListNew = exam.getExamAttendanceList();
            List<ExamAttendance> attachedExamAttendanceListNew = new ArrayList<ExamAttendance>();
            for (ExamAttendance examAttendanceListNewExamAttendanceToAttach : examAttendanceListNew) {
                examAttendanceListNewExamAttendanceToAttach = em.getReference(examAttendanceListNewExamAttendanceToAttach.getClass(), examAttendanceListNewExamAttendanceToAttach.getId());
                attachedExamAttendanceListNew.add(examAttendanceListNewExamAttendanceToAttach);
            }
            examAttendanceListNew = attachedExamAttendanceListNew;
            exam.setExamAttendanceList(examAttendanceListNew);
            exam = em.merge(exam);
            for (ExamAttendance examAttendanceListOldExamAttendance : examAttendanceListOld) {
                if (!examAttendanceListNew.contains(examAttendanceListOldExamAttendance)) {
                    examAttendanceListOldExamAttendance.setExamId(null);
                    examAttendanceListOldExamAttendance = em.merge(examAttendanceListOldExamAttendance);
                }
            }
            for (ExamAttendance examAttendanceListNewExamAttendance : examAttendanceListNew) {
                if (!examAttendanceListOld.contains(examAttendanceListNewExamAttendance)) {
                    Exam oldExamIdOfExamAttendanceListNewExamAttendance = examAttendanceListNewExamAttendance.getExamId();
                    examAttendanceListNewExamAttendance.setExamId(exam);
                    examAttendanceListNewExamAttendance = em.merge(examAttendanceListNewExamAttendance);
                    if (oldExamIdOfExamAttendanceListNewExamAttendance != null && !oldExamIdOfExamAttendanceListNewExamAttendance.equals(exam)) {
                        oldExamIdOfExamAttendanceListNewExamAttendance.getExamAttendanceList().remove(examAttendanceListNewExamAttendance);
                        oldExamIdOfExamAttendanceListNewExamAttendance = em.merge(oldExamIdOfExamAttendanceListNewExamAttendance);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exam.getId();
                if (findExam(id) == null) {
                    throw new NonexistentEntityException("The exam with id " + id + " no longer exists.");
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
            Exam exam;
            try {
                exam = em.getReference(Exam.class, id);
                exam.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exam with id " + id + " no longer exists.", enfe);
            }
            List<ExamAttendance> examAttendanceList = exam.getExamAttendanceList();
            for (ExamAttendance examAttendanceListExamAttendance : examAttendanceList) {
                examAttendanceListExamAttendance.setExamId(null);
                examAttendanceListExamAttendance = em.merge(examAttendanceListExamAttendance);
            }
            em.remove(exam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Exam> findExamEntities() {
        return findExamEntities(true, -1, -1);
    }

    public List<Exam> findExamEntities(int maxResults, int firstResult) {
        return findExamEntities(false, maxResults, firstResult);
    }

    private List<Exam> findExamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exam.class));
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

    public Exam findExam(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exam.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exam> rt = cq.from(Exam.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
