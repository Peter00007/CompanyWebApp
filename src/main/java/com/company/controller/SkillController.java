package com.company.controller;


import com.company.model.Skill;
import com.company.service.SkillService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SkillController extends HttpServlet {
    SkillService skillService;

    public SkillController() {
        skillService = new SkillService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Skill> listSkill = skillService.getAll();

        request.setAttribute("listSkill", listSkill);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("skills.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
