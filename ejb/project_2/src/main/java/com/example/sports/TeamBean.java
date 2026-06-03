package com.example.sports;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TeamBean implements TeamBeanRemote {

    @PersistenceContext(unitName = "SportsPU")
    private EntityManager em;

    @Override
    public void addTeam(String name, String city) {
        Team t = new Team(name, city);
        em.persist(t);
    }

    @Override
    public List<Team> getAllTeams() {
        TypedQuery<Team> q = em.createQuery("SELECT t FROM Team t", Team.class);
        return q.getResultList();
    }

    @Override
    public Team getTeamById(int id) {
        return em.find(Team.class, id);
    }

    @Override
    public void updateTeam(int id, String name, String city) {
        Team t = em.find(Team.class, id);
        if (t != null) {
            t.setName(name);
            t.setCity(city);
            em.merge(t);
        }
    }

    @Override
    public void deleteTeam(int id) {
        Team t = em.find(Team.class, id);
        if (t != null) em.remove(t);
    }
}
