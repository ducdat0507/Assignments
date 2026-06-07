package com.example.service;

import com.example.model.Exam;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ExamService {

    @PersistenceContext(unitName = "examPU")
    private EntityManager em;

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/topic/exam_topic")
    private Topic examTopic;

    public List<Exam> findAllExams() {
        return em.createQuery("SELECT e FROM Exam e ORDER BY e.id", Exam.class).getResultList();
    }

    public void saveExam(String name, int duration, String description) {
        Connection conn = null;
        Session session = null;
        try {
            conn = connectionFactory.createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(examTopic);
            MapMessage msg = session.createMapMessage();
            msg.setString("name", name);
            msg.setInt("duration", duration);
            msg.setString("description", description);
            producer.send(msg);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (session != null) session.close();
                if (conn != null) conn.close();
            } catch (JMSException ignored) {}
        }
    }
}
