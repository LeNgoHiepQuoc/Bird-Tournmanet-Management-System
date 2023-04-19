package controller;

import achievement.AchievementDAO;
import achievement.AchievementDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageBirdDetailController", urlPatterns = {"/ManageBirdDetailController"})
public class ManageBirdDetailController extends HttpServlet {

    private final String ERROR = "error.jsp";
    private final String SUCCES = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String action = request.getParameter("action");
        String birdID = request.getParameter("birdID");
        String desc = request.getParameter("description");
        String medals = request.getParameter("medals");
        String totalScore = request.getParameter("totalScore");
        String top = request.getParameter("top");
        try {
            if (action.equals("Update Achievement")) {
                AchievementDAO adao = new AchievementDAO();
                AchievementDTO adto = new AchievementDTO();
                adto.setBirdID(Integer.parseInt(birdID));
                adto.setDescription(desc);
                adto.setMedals(medals);
                adto.setTop(Integer.parseInt(top));
                adto.setTotalScore(Integer.parseInt(totalScore));
                if (adao.updateAchievementByBID(adto)) {
                    url = "ManageBirdController?action=Detail&birdID=" + Integer.parseInt(birdID);
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
