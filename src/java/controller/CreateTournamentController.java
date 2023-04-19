package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tournament.TournamentDAO;
import tournament.TournamentDTO;

@WebServlet(name = "CreateTournamentController", urlPatterns = {"/CreateTournamentController"})
public class CreateTournamentController extends HttpServlet {

    private static final String ERROR = "tournamentCreate.jsp";
    private static final String SUCCESS = "LoadTournamentController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            boolean checkValidation = true;
            TournamentDAO dao = new TournamentDAO();
            String url = ERROR;
            try {
                String tournamentName = request.getParameter("tournamentName");
                int minParticipant = Integer.parseInt(request.getParameter("minSlot"));
                int maxParticipant = Integer.parseInt(request.getParameter("maxSlot"));
                String dateTime = request.getParameter("dateTime");
                String location = request.getParameter("location");
                String fee = request.getParameter("fee");
                String sponsor = "image/" + request.getParameter("sponsor");
                String prize = request.getParameter("prize");
                int bCateID = Integer.valueOf(request.getParameter("bCateID"));
                String image = "image/" + request.getParameter("image");
                String description = request.getParameter("description");
                TournamentDTO t = new TournamentDTO(tournamentName, minParticipant, maxParticipant, dateTime, location, fee, sponsor, prize, bCateID, image, 0, description);
                boolean checkCreate = dao.createTournament(t);
                if (checkCreate) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Fail To Create Tournament!");
                }
            } catch (Exception e) {
                log("Error at CreateTournamentControler: " + e.toString());
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
