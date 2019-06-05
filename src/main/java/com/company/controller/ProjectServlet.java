package com.company.controller;


import com.company.model.Project;
import com.company.service.CustomerService;
import com.company.service.ProjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProjectServlet extends HttpServlet {
    ProjectService projectService = new ProjectService();
    Project projectToSave = new Project();
    CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        listProject(request, response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        String button = request.getParameter("button");
        System.out.println("button = " + button);

        switch (button) {
            case "add":
                addProject(request, response);
                break;
            case "edit":
                editProjectForm(request, response);
                break;
            case "update":
                updateProject(request, response);
                break;
            case "delete":
                deleteProject(request, response);
                break;
        }
    }

    private void listProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Project> ProjectList = projectService.getAll();
        request.setAttribute("listOfProject", ProjectList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/project.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfProject");
        int budget = Integer.parseInt(request.getParameter("budget"));
        int customer = Integer.parseInt(request.getParameter("names"));

        projectToSave.setName(name);
        projectToSave.setBudget(budget);
        projectToSave.setCustomer(customerService.getById(customer));
        projectService.save(projectToSave);
        response.sendRedirect("project");
    }

    private void editProjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/editProjectForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedProject"));
        String nameOfUpdatedProject = request.getParameter("nameOfUpdatedProject");
        int budget = Integer.parseInt(request.getParameter("budget"));
        int customer = Integer.parseInt(request.getParameter("names"));

        projectToSave.setId(id);
        projectToSave.setName(nameOfUpdatedProject);
        projectToSave.setBudget(budget);
        projectToSave.setCustomer(customerService.getById(customer));
        projectService.update(projectToSave);
        response.sendRedirect("project");
    }


    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("projectId");
        int id = Integer.parseInt(strId);
        projectService.delete(id);
        response.sendRedirect("project");
    }
}
