package com.company.repository.hibernate;


import com.company.model.Team;
import com.company.repository.ProjectRepository;
import com.company.repository.TeamRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateTeamRepositoryImpl implements TeamRepository{
    private String getAllMessage = "SELECT * FROM teams";

    ProjectRepository projectRepository;

    public HibernateTeamRepositoryImpl() {
        projectRepository= new HibernateProjectRepositoryImpl();
    }

    public Team save(Team team) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(team);
        transaction.commit();
        session.close();
        return team;
    }

    public Team getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Team team = (Team) session.get(Team.class, id);
        session.close();
        return team;
    }

    public List<Team> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Team> list = new ArrayList<Team>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            Integer project = Integer.parseInt(String.valueOf(obj[2]));
            Team team = new Team(identifier, name, projectRepository.getById(project));
            list.add(team);
        }
        session.close();
        return list;
    }

    public Team update(Team team) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(team);
        transaction.commit();
        session.close();
        return team;
    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Team team = (Team) session.get(Team.class, id);
        session.delete(team);
        transaction.commit();
        session.close();
    }
}
