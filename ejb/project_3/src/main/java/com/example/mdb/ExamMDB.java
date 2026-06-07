package com.example.mdb;

import com.example.model.Exam;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topic/exam_topic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
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
