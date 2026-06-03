package com.example.sports;

import jakarta.ejb.Remote;
import java.util.List;

@Remote
public interface TeamBeanRemote {
    void addTeam(String name, String city);
    List<Team> getAllTeams();
    Team getTeamById(int id);
    void updateTeam(int id, String name, String city);
    void deleteTeam(int id);
}
