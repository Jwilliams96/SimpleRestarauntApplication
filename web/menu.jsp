<%-- 
    Document   : menu
    Created on : Feb 14, 2013, 2:04:50 AM
    Author     : Jonathon
--%>

<%@page import="main.models.MenuItem"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Menu</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styleSheet.css">
    </head>
    <body>
        <!--The top level container-->
        <div id="headerDiv">
            <img id="headerImg" src="WebPictures/restaurantHeader.jpg" 
                 alt="Restaurant Header">
        </div>

        <!--The navigation container-->
        <div id="navigationBar">
            <a href="index.html">Home Page</a><br/>
            <a href="RestaurantController?link=Menu">Menu</a><br/>
            <a href="RestaurantController?link=Order">Order</a><br/>
            <a href="aboutUs.html">About Us</a>
        </div>

        <!--The juicy content-->
        <div id="contentDiv" style="position: relative;float:left">

            <!--when the drop down list is selected the process is ran-->
            <form method="GET" action="RestaurantController">
                <select name="menuItem" onchange="this.form.submit()">

                    <!--The Drop down list contents-->
                    <option value="">Please Select
                        <%
                            //connect to the persistence manager creator
                            EntityManagerFactory emf = Persistence
                                    .createEntityManagerFactory(
                                    "SimpleRestarauntApplicationPU");

                            //create the persistence manager
                            EntityManager em = emf.createEntityManager();

                            //get all of the menu item names
                            List<String> items = em
                                    .createNamedQuery("getAllMenuItems")
                                    .getResultList();

                            //for each name
                            for (String name : items) {

                                //create an option for each item
                                out.println("<option value=\"" + name + "\">"
                                        + name);
                            }

                            //close the manager and factory
                            em.close();
                            emf.close();
                        %>
                </select>
            </form>
                
            <!--Display the menu items-->
            <%
                //get the "food item"
                Object obj = request.getAttribute("foodItem");

                //if there is one passed here
                if (obj != null) {
                    
                    //cast it to the right type
                    MenuItem foodItem = (MenuItem) obj;
                    
                    //two spaces
                    out.write("<br/>");
                    out.write("<br/>");
                    
                    //write out the to string method (replacing line breaks
                    //with the html line break
                    out.write(foodItem.toString().replaceAll("\n", "<br/>"));
                }
            %>
        </div>
    </body>
</html>
