/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_7.dao;

import com.example.project_7.dao.exceptions.NonexistentEntityException;
import com.example.project_7.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.example.project_7.entity.ExamAttendance;
import com.example.project_7.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student
 */
public class StudentJpaController implements Serializable {

    public StudentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Student student) throws PreexistingEntityException, Exception {
        if (student.getExamAttendanceList() == null) {
            student.setExamAttendanceList(new ArrayList<ExamAttendance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ExamAttendance> attachedExamAttendanceList = new ArrayList<ExamAttendance>();
            for (ExamAttendance examAttendanceListExamAttendanceToAttach : student.getExamAttendanceList()) {
                examAttendanceListExamAttendanceToAttach = em.getReference(examAttendanceListExamAttendanceToAttach.getClass(), examAttendanceListExamAttendanceToAttach.getId());
                attachedExamAttendanceList.add(examAttendanceListExamAttendanceToAttach);
            }
            student.setExamAttendanceList(attachedExamAttendanceList);
            em.persist(student);
            for (ExamAttendance examAttendanceListExamAttendance : student.getExamAttendanceList()) {
                Student oldStudentRollNoOfExamAttendanceListExamAttendance = examAttendanceListExamAttendance.getStudentRollNo();
                examAttendanceListExamAttendance.setStudentRollNo(student);
                examAttendanceListExamAttendance = em.merge(examAttendanceListExamAttendance);
                if (oldStudentRollNoOfExamAttendanceListExamAttendance != null) {
                    oldStudentRollNoOfExamAttendanceListExamAttendance.getExamAttendanceList().remove(examAttendanceListExamAttendance);
                    oldStudentRollNoOfExamAttendanceListExamAttendance = em.merge(oldStudentRollNoOfExamAttendanceListExamAttendance);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStudent(student.getRollNo()) != null) {
                throw new PreexistingEntityException("Student " + student + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Student student) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Student persistentStudent = em.find(Student.class, student.getRollNo());
            List<ExamAttendance> examAttendanceListOld = persistentStudent.getExamAttendanceList();
            List<ExamAttendance> examAttendanceListNew = student.getExamAttendanceList();
            List<ExamAttendance> attachedExamAttendanceListNew = new ArrayList<ExamAttendance>();
            for (ExamAttendance examAttendanceListNewExamAttendanceToAttach : examAttendanceListNew) {
                examAttendanceListNewExamAttendanceToAttach = em.getReference(examAttendanceListNewExamAttendanceToAttach.getClass(), examAttendanceListNewExamAttendanceToAttach.getId());
                attachedExamAttendanceListNew.add(examAttendanceListNewExamAttendanceToAttach);
            }
            examAttendanceListNew = attachedExamAttendanceListNew;
            student.setExamAttendanceList(examAttendanceListNew);
            student = em.merge(student);
            for (ExamAttendance examAttendanceListOldExamAttendance : examAttendanceListOld) {
                if (!examAttendanceListNew.contains(examAttendanceListOldExamAttendance)) {
                    examAttendanceListOldExamAttendance.setStudentRollNo(null);
                    examAttendanceListOldExamAttendance = em.merge(examAttendanceListOldExamAttendance);
                }
            }
            for (ExamAttendance examAttendanceListNewExamAttendance : examAttendanceListNew) {
                if (!examAttendanceListOld.contains(examAttendanceListNewExamAttendance)) {
                    Student oldStudentRollNoOfExamAttendanceListNewExamAttendance = examAttendanceListNewExamAttendance.getStudentRollNo();
                    examAttendanceListNewExamAttendance.setStudentRollNo(student);
                    examAttendanceListNewExamAttendance = em.merge(examAttendanceListNewExamAttendance);
                    if (oldStudentRollNoOfExamAttendanceListNewExamAttendance != null && !oldStudentRollNoOfExamAttendanceListNewExamAttendance.equals(student)) {
                        oldStudentRollNoOfExamAttendanceListNewExamAttendance.getExamAttendanceList().remove(examAttendanceListNewExamAttendance);
                        oldStudentRollNoOfExamAttendanceListNewExamAttendance = em.merge(oldStudentRollNoOfExamAttendanceListNewExamAttendance);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = student.getRollNo();
                if (findStudent(id) == null) {
                    throw new NonexistentEntityException("The student with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Student student;
            try {
                student = em.getReference(Student.class, id);
                student.getRollNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The student with id " + id + " no longer exists.", enfe);
            }
            List<ExamAttendance> examAttendanceList = student.getExamAttendanceList();
            for (ExamAttendance examAttendanceListExamAttendance : examAttendanceList) {
                examAttendanceListExamAttendance.setStudentRollNo(null);
                examAttendanceListExamAttendance = em.merge(examAttendanceListExamAttendance);
            }
            em.remove(student);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Student> findStudentEntities() {
        return findStudentEntities(true, -1, -1);
    }

    public List<Student> findStudentEntities(int maxResults, int firstResult) {
        return findStudentEntities(false, maxResults, firstResult);
    }

    private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Student.class));
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

    public Student findStudent(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Student> rt = cq.from(Student.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
