package com.example.mdb;

import com.example.model.Exam;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topic/exam_topic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic")
})
public class ExamMDB implements MessageListener {

    @PersistenceContext(unitName = "examPU")
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof MapMessage) {
                MapMessage map = (MapMessage) message;
                String name = map.getString("name");
                int duration = map.getInt("duration");
                String description = map.getString("description");

                Exam exam = new Exam();
                exam.setName(name);
                exam.setDuration(duration);
                exam.setDescription(description);
                Date now = new Date();
                exam.setCreatedAt(now);
                exam.setUpdatedAt(now);

                em.persist(exam);
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
