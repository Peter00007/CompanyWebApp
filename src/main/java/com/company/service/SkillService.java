package com.company.service;

import com.company.model.Skill;
import com.company.repository.SkillRepository;
import com.company.repository.hibernate.HibernateSkillRepositoryImpl;

import java.util.List;

public class SkillService {
    SkillRepository skillRepository;

    public SkillService() {
        skillRepository = new HibernateSkillRepositoryImpl();
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill getById(int id) {
        return skillRepository.getById(id);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill update(Skill skill) {
        return skillRepository.update(skill);
    }

    public void delete(int id) {
        skillRepository.delete(id);
    }
}
