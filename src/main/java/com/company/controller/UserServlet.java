package com.company.controller;


import com.company.model.Skill;
import com.company.model.User;
import com.company.repository.SkillRepository;
import com.company.repository.hibernate.HibernateSkillRepositoryImpl;
import com.company.service.SkillService;
import com.company.service.TeamService;
import com.company.service.UserService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();
    private User userToSave = new User();
    private TeamService teamService = new TeamService();
    private SkillService skillService = new SkillService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listUser(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String button = request.getParameter("button");

        switch (button) {
            case "add":
                addUser(request, response);
                break;
            case "edit":
                editUserForm(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
        }

    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.getAll();
        request.setAttribute("listOfUser", userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/user.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SkillRepository sr = new HibernateSkillRepositoryImpl();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialty = request.getParameter("specialty");
        // ---Adding skills---:
        String[] arrayOfId = request.getParameterValues("names");
        Set<String> idSetString = new HashSet<>(Arrays.asList(arrayOfId));

        Set<Integer> idSetInteger = new HashSet<>();
        for (String sId : idSetString) {
            idSetInteger.add(Integer.parseInt(sId));
        }

        Set<Skill> skillSet = new HashSet<>();
        for (Integer id : idSetInteger) {
            Skill skillToSave = sr.getById(id);
            skillSet.add(skillToSave);
        }
        // ------------------------;

        int team = Integer.parseInt(request.getParameter("team"));

        userToSave.setFirstName(firstName);
        userToSave.setLastName(lastName);
        userToSave.setSpecialty(specialty);
        userToSave.setSkill(skillSet);
        userToSave.setTeam(teamService.getById(team));
        userService.save(userToSave);
        response.sendRedirect("user");
    }


    private void editUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/editUserForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedUser"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialty = request.getParameter("specialty");

        int team = Integer.parseInt(request.getParameter("team"));

        String[] arrayOfId = request.getParameterValues("names");
        Set<String> idSetString = new HashSet<>(Arrays.asList(arrayOfId));

        Set<Integer> idSetInteger = new HashSet<>();
        for (String sId : idSetString) {
            idSetInteger.add(Integer.parseInt(sId));
        }

        Set<Skill> skillSet = new HashSet<>();
        for (Integer skillId : idSetInteger) {
            Skill skillToSave = skillService.getById(skillId);
            skillSet.add(skillToSave);
        }

        userToSave.setId(id);
        userToSave.setFirstName(firstName);
        userToSave.setLastName(lastName);
        userToSave.setSpecialty(specialty);
        userToSave.setSkill(skillSet);
        userToSave.setTeam(teamService.getById(team));
        userService.update(userToSave);
        response.sendRedirect("user");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("userId");
        int id = Integer.parseInt(strId);
        userService.delete(id);
        response.sendRedirect("user");
    }

}