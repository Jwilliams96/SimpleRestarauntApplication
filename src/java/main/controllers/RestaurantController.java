/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.models.MenuItem;

/**
 *
 * @author Jonathon
 */
@WebServlet(name = "RestaurantController", urlPatterns = {"/RestaurantController"})
public class RestaurantController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the writer
        PrintWriter out = response.getWriter();
        try {

            //get the link to the right place (needs to be validated)
            String linkAction = request.getParameter("link");

            //if the user is trying to link to the menu page
            if (linkAction.equalsIgnoreCase("menu")) {

                //send the user to the menu page
                RequestDispatcher view = request.getRequestDispatcher("/menu.jsp");
                view.forward(request, response);

                //if the user is trying to link to the order page
            } else if (linkAction.equalsIgnoreCase("order")) {

                //tell the user the page isn't created yet
                out.write("This feature is not yet implemented");
            }
            //if the user didn't select anywhere to go
        } catch (NullPointerException e) {

            //tell the user the page isn't created yet
            out.write("This feature is not yet implemented");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the parameter for the menu item passed (if the user got here from
        //the menu page)
        String item = request.getParameter("menuItem");

        //if there is not a stored value then it's a link
        if (item == null || item.isEmpty()) {

            //run the normal method
            processRequest(request, response);

            //else they selected a menu item
        } else {

            //get the manager creator
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory(
                    "SimpleRestarauntApplicationPU");

            //create the manager
            EntityManager em = emf.createEntityManager();

            //get all of the Menu Items
            List<MenuItem> items = em.createNamedQuery("getAllItems")
                    .getResultList();

            //create an object for storage
            MenuItem foodItem = null;

            //for each value grabbed from the items
            for (MenuItem stuff : items) {

                //if the current name is the same as the searched for item
                if (stuff.getName().equalsIgnoreCase(item)) {

                    //set it to the object and end the loop
                    foodItem = stuff;
                    break;
                }
            }

            //set the item for the page to get
            request.setAttribute("foodItem", foodItem);

            //close the entity stuff
            em.close();
            emf.close();

            //redirect the user
            RequestDispatcher view = request.getRequestDispatcher("/menu.jsp");
            view.forward(request, response);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
