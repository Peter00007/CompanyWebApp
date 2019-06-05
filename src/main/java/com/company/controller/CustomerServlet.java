package com.company.controller;

import com.company.model.Customer;
import com.company.service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    CustomerService customerService = new CustomerService();
    Customer customerToSave = new Customer();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        listCustomer(request, response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        String button = request.getParameter("button");
        System.out.println("button = " + button);

        switch (button) {
            case "add":
                addCustomer(request, response);
                break;
            case "edit":
                editCustomerForm(request, response);
                break;
            case "update":
                updateCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> CustomerList = customerService.getAll();
        request.setAttribute("listOfCustomer", CustomerList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/customer.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfCustomer");

        customerToSave.setName(name);
        customerService.save(customerToSave);
        response.sendRedirect("customer");
    }

    private void editCustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/editCustomerForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedCustomer"));
        String nameOfUpdatedCustomer = request.getParameter("nameOfUpdatedCustomer");
        customerToSave.setId(id);
        customerToSave.setName(nameOfUpdatedCustomer);
        customerService.update(customerToSave);
        response.sendRedirect("customer");
    }


    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("customerId");
        int id = Integer.parseInt(strId);
        customerService.delete(id);
        response.sendRedirect("customer");
    }
}