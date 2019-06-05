package com.company.controller;

import com.company.model.Team;
import com.company.service.ProjectService;
import com.company.service.TeamService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeamServlet extends HttpServlet {
    private TeamService teamService = new TeamService();
    private Team teamToSave = new Team();
    private ProjectService projectService = new ProjectService();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        listTeam(request, response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        String button = request.getParameter("button");
        System.out.println("button = " + button);

        switch (button) {
            case "add":
                addTeam(request, response);
                break;
            case "edit":
                editTeamForm(request, response);
                break;
            case "update":
                updateTeam(request, response);
                break;
            case "delete":
                deleteTeam(request, response);
                break;
        }
    }

    private void listTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Team> TeamList = teamService.getAll();
        request.setAttribute("listOfTeam", TeamList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/team.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfTeam");
        String projectId = request.getParameter("idOfProject");

        int project = Integer.parseInt(projectId);


        teamToSave.setName(name);
        teamToSave.setProject(projectService.getById(project));
        teamService.save(teamToSave);
        response.sendRedirect("team");
    }

    private void editTeamForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/editTeamForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedTeam"));
        String nameOfUpdatedTeam = request.getParameter("nameOfUpdatedTeam");
        int project = Integer.parseInt(request.getParameter("idOfUpdatedProject"));
        teamToSave.setId(id);
        teamToSave.setName(nameOfUpdatedTeam);
        teamToSave.setProject(projectService.getById(project));
        teamService.update(teamToSave);
        response.sendRedirect("team");
    }


    private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("teamId");
        int id = Integer.parseInt(strId);
        teamService.delete(id);
        response.sendRedirect("team");
    }
}