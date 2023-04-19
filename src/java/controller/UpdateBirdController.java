package controller;

import bird.BirdDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateBirdController", urlPatterns = {"/UpdateBirdController"})
public class UpdateBirdController extends HttpServlet {

    private final String SUCCESS = "LoadBirdByAccountID";
    private final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession s = request.getSession();
            String url = null;
            int accID = Integer.valueOf(request.getParameter("accID"));
            int birdID = Integer.valueOf(request.getParameter("birdID"));
            try {
                String name = request.getParameter("bName");
                String bCate = request.getParameter("bCate");
                String height = request.getParameter("bHeight");
                String weight = request.getParameter("bWeight");
                String color = request.getParameter("bColor");
                String denfitication = request.getParameter("denfitication");
                String image = "image/" + request.getParameter("image");
                String bStatus = request.getParameter("bStatus");
                BirdDAO bDAO = new BirdDAO();
                boolean result = bDAO.updateBirdByAccountID(Integer.valueOf(bCate), name, image, Integer.valueOf(height), Integer.valueOf(weight), color, denfitication, Integer.valueOf(bStatus), birdID, accID);
                if (result == true) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
