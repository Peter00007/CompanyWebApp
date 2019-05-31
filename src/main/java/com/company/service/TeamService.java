package com.company.service;


import com.company.model.Team;
import com.company.repository.TeamRepository;
import com.company.repository.hibernate.HibernateTeamRepositoryImpl;

import java.util.List;

public class TeamService {
    TeamRepository teamRepository;

    public TeamService() {
        teamRepository = new HibernateTeamRepositoryImpl();
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team getById(int id) {
        return teamRepository.getById(id);
    }

    public List<Team> getAll() {
        return teamRepository.getAll();
    }

    public Team update(Team team) {
        return teamRepository.update(team);
    }

    public void delete(int id) {
        teamRepository.delete(id);
    }
}
