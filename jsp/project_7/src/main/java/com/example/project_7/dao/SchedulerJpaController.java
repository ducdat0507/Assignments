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
import com.example.project_7.entity.ExamAttendance;
import com.example.project_7.entity.Scheduler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student
 */
public class SchedulerJpaController implements Serializable {

    public SchedulerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Scheduler scheduler) {
        if (scheduler.getExamAttendanceList() == null) {
            scheduler.setExamAttendanceList(new ArrayList<ExamAttendance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ExamAttendance> attachedExamAttendanceList = new ArrayList<ExamAttendance>();
            for (ExamAttendance examAttendanceListExamAttendanceToAttach : scheduler.getExamAttendanceList()) {
                examAttendanceListExamAttendanceToAttach = em.getReference(examAttendanceListExamAttendanceToAttach.getClass(), examAttendanceListExamAttendanceToAttach.getId());
                attachedExamAttendanceList.add(examAttendanceListExamAttendanceToAttach);
            }
            scheduler.setExamAttendanceList(attachedExamAttendanceList);
            em.persist(scheduler);
            for (ExamAttendance examAttendanceListExamAttendance : scheduler.getExamAttendanceList()) {
                Scheduler oldSchedulerIdOfExamAttendanceListExamAttendance = examAttendanceListExamAttendance.getSchedulerId();
                examAttendanceListExamAttendance.setSchedulerId(scheduler);
                examAttendanceListExamAttendance = em.merge(examAttendanceListExamAttendance);
                if (oldSchedulerIdOfExamAttendanceListExamAttendance != null) {
                    oldSchedulerIdOfExamAttendanceListExamAttendance.getExamAttendanceList().remove(examAttendanceListExamAttendance);
                    oldSchedulerIdOfExamAttendanceListExamAttendance = em.merge(oldSchedulerIdOfExamAttendanceListExamAttendance);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Scheduler scheduler) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Scheduler persistentScheduler = em.find(Scheduler.class, scheduler.getId());
            List<ExamAttendance> examAttendanceListOld = persistentScheduler.getExamAttendanceList();
            List<ExamAttendance> examAttendanceListNew = scheduler.getExamAttendanceList();
            List<ExamAttendance> attachedExamAttendanceListNew = new ArrayList<ExamAttendance>();
            for (ExamAttendance examAttendanceListNewExamAttendanceToAttach : examAttendanceListNew) {
                examAttendanceListNewExamAttendanceToAttach = em.getReference(examAttendanceListNewExamAttendanceToAttach.getClass(), examAttendanceListNewExamAttendanceToAttach.getId());
                attachedExamAttendanceListNew.add(examAttendanceListNewExamAttendanceToAttach);
            }
            examAttendanceListNew = attachedExamAttendanceListNew;
            scheduler.setExamAttendanceList(examAttendanceListNew);
            scheduler = em.merge(scheduler);
            for (ExamAttendance examAttendanceListOldExamAttendance : examAttendanceListOld) {
                if (!examAttendanceListNew.contains(examAttendanceListOldExamAttendance)) {
                    examAttendanceListOldExamAttendance.setSchedulerId(null);
                    examAttendanceListOldExamAttendance = em.merge(examAttendanceListOldExamAttendance);
                }
            }
            for (ExamAttendance examAttendanceListNewExamAttendance : examAttendanceListNew) {
                if (!examAttendanceListOld.contains(examAttendanceListNewExamAttendance)) {
                    Scheduler oldSchedulerIdOfExamAttendanceListNewExamAttendance = examAttendanceListNewExamAttendance.getSchedulerId();
                    examAttendanceListNewExamAttendance.setSchedulerId(scheduler);
                    examAttendanceListNewExamAttendance = em.merge(examAttendanceListNewExamAttendance);
                    if (oldSchedulerIdOfExamAttendanceListNewExamAttendance != null && !oldSchedulerIdOfExamAttendanceListNewExamAttendance.equals(scheduler)) {
                        oldSchedulerIdOfExamAttendanceListNewExamAttendance.getExamAttendanceList().remove(examAttendanceListNewExamAttendance);
                        oldSchedulerIdOfExamAttendanceListNewExamAttendance = em.merge(oldSchedulerIdOfExamAttendanceListNewExamAttendance);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = scheduler.getId();
                if (findScheduler(id) == null) {
                    throw new NonexistentEntityException("The scheduler with id " + id + " no longer exists.");
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
            Scheduler scheduler;
            try {
                scheduler = em.getReference(Scheduler.class, id);
                scheduler.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scheduler with id " + id + " no longer exists.", enfe);
            }
            List<ExamAttendance> examAttendanceList = scheduler.getExamAttendanceList();
            for (ExamAttendance examAttendanceListExamAttendance : examAttendanceList) {
                examAttendanceListExamAttendance.setSchedulerId(null);
                examAttendanceListExamAttendance = em.merge(examAttendanceListExamAttendance);
            }
            em.remove(scheduler);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Scheduler> findSchedulerEntities() {
        return findSchedulerEntities(true, -1, -1);
    }

    public List<Scheduler> findSchedulerEntities(int maxResults, int firstResult) {
        return findSchedulerEntities(false, maxResults, firstResult);
    }

    private List<Scheduler> findSchedulerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Scheduler.class));
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

    public Scheduler findScheduler(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Scheduler.class, id);
        } finally {
            em.close();
        }
    }

    public int getSchedulerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Scheduler> rt = cq.from(Scheduler.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
