package com.company.service;


import com.company.model.Project;
import com.company.repository.ProjectRepository;
import com.company.repository.hibernate.HibernateProjectRepositoryImpl;

import java.util.List;

public class ProjectService {
    ProjectRepository projectRepository;

    public ProjectService() {
        projectRepository = new HibernateProjectRepositoryImpl();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project getById(int id) {
        return projectRepository.getById(id);
    }

    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    public Project update(Project project) {
        return projectRepository.update(project);
    }

    public void delete(int id) {
        projectRepository.delete(id);
    }
}
