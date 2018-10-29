/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Note;
import Services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 707114
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService noteservice = new UserService();
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            int selectednotetoview = Integer.parseInt(request.getParameter("selectednotetoview"));
            try {
                Note notetoview = noteservice.get(selectednotetoview);
                request.setAttribute("selectednotetoedit", notetoview);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        updateTable(request);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String content = request.getParameter("content");

        UserService noteservice = new UserService();
        if (action == null) {
            return;
        }

        try {
            switch (action) {
                case "add":
                    if (!(content == null || content.equals(""))) {
                        noteservice.insert(content);
                    } else {
                        request.setAttribute("message", "Content can't be empty");
                    }
                    updateTable(request);
                    break;
                case "delete":
                    int selectednotetodelete = Integer.parseInt(request.getParameter("selectednotetodelete"));
                    noteservice.delete(selectednotetodelete);
                    updateTable(request);
                    break;
                case "edit":
                    if (!(content == null || content.equals(""))){
                    noteservice.update(Integer.parseInt(request.getParameter("hiddennote")), content);
                    } else {
                        request.setAttribute("message", "Content can't be null");
                    }
                    updateTable(request);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    private void updateTable(HttpServletRequest request) {
        try {
            UserService noteservice = new UserService();
            List<Note> notelist = noteservice.getAll();
            request.setAttribute("notes", notelist);
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
