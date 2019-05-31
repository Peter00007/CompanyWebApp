package com.company.controller;


import com.company.model.Skill;
import com.company.service.SkillService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SkillServlet extends HttpServlet {
    SkillService skillService;

    public SkillServlet() {
        skillService = new SkillService();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewSkill(request, response);
                    break;
                case "/insert":
                    insertSkill(request, response);
                    break;
                case "/delete":
                    deleteSkill(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateSkill(request, response);
                    break;
                default:
                    listSkill(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listSkill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Skill> listSkill = skillService.getAll();
        request.setAttribute("listSkill", listSkill);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SkillList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewSkill(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SkillForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Skill existingSkill = skillService.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SkillForm.jsp");
        request.setAttribute("skill", existingSkill);
        dispatcher.forward(request, response);

    }

    private void insertSkill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");

        Skill newSkill = new Skill(name);
        skillService.save(newSkill);
        response.sendRedirect("list");
    }

    private void updateSkill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Skill skill = new Skill(id, name);
        skillService.update(skill);
        response.sendRedirect("list");
    }

    private void deleteSkill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        skillService.delete(id);
        response.sendRedirect("list");
    }
}
