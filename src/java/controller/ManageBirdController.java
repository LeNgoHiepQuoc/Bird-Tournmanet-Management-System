package controller;

import bird.BirdDAO;
import bird.BirdDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageBirdController", urlPatterns = {"/ManageBirdController"})
public class ManageBirdController extends HttpServlet {

    private final String ERROR = "error.jsp";
    private final String SUCCESS = "LoadBirdController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = ERROR;
            BirdDTO bird = new BirdDTO();
            try {
                String birdID = request.getParameter("birdID");
                String action = request.getParameter("action");
                if (action.equals("Block")) {
                    BirdDAO dao = new BirdDAO();
                    bird = dao.getByID(Integer.parseInt(birdID));
                    if (bird != null) {
                        bird.setBirdStatus(0);
                        if (dao.updateBird(bird)) {
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    } else {
                        url = ERROR;
                    }
                }
                if (action.equals("Unblock")) {
                    BirdDAO dao = new BirdDAO();
                    bird = dao.getByID(Integer.parseInt(birdID));
                    if (bird != null) {
                        bird.setBirdStatus(1);
                        if (dao.updateBird(bird)) {
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    } else {
                        url = ERROR;
                    }
                }
                if (action.equals("Remove")) {
                    BirdDAO dao = new BirdDAO();
                    if (dao.deleteBird(Integer.parseInt(birdID))) {
                        url = SUCCESS;
                    } else {
                        url = ERROR;
                    }
                }
                if (action.equals("Detail")) {
                    BirdDAO dao = new BirdDAO();
                    bird = dao.getBirdAchievement(Integer.parseInt(birdID));
                    if (bird != null) {
                        request.setAttribute("bird", bird);
                        url = "manageBirdDetail.jsp";
                    } else {
                        url = ERROR;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
