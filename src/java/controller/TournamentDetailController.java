/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import account.AccountDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registrationform.RegistrationFormDAO;
import round.RoundDAO;
import round.RoundDTO;
import tournament.TournamentDAO;
import tournament.TournamentDTO;

@WebServlet(name = "TournamentDetailController", urlPatterns = {"/TournamentDetailController"})
public class TournamentDetailController extends HttpServlet {

    private final static String SUCCESS = "tournamentDetail.jsp";
    private final static String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String url = ERROR;
        try {
            TournamentDTO tour = null;
            String rid = request.getParameter("roundID");
            String rname = request.getParameter("roundName");
            String rstatus = request.getParameter("roundStatus");
            int tournamentID = Integer.parseInt(request.getParameter("ID"));
            TournamentDAO tdao = new TournamentDAO();
            tour = tdao.getDetail(tournamentID);
            RoundDAO roudao = new RoundDAO();
            HttpSession s = request.getSession(false);
            AccountDTO acc = (AccountDTO)s.getAttribute("acc");
            if (tour != null) {
                request.setAttribute("utour", tour);
                RegistrationFormDAO rdao = new RegistrationFormDAO();
                if (tour.getTournamentStatus() == 1 && tour.getTournamentStatus() == 0) {
                    request.setAttribute("numberPlayer", rdao.getNumberRegistered(1, tournamentID));
                } else {
                    request.setAttribute("numberPlayer", rdao.getNumberRegistered(2, tournamentID));
                }
                if (tour.getTournamentStatus() == 0 || tour.getTournamentStatus() == 1 || tour.getTournamentStatus() == 2 || tour.getTournamentStatus() == 6) {
                    if (acc == null) {
                        acc = new AccountDTO();
                        acc.setAccountID(0);
                    }
                    if (tdao.checkUserRegistered(acc.getAccountID(), tournamentID) == null) {
                        request.setAttribute("registered", "false");
                    } else {
                        request.setAttribute("registered", "true");
                    }

                    url = SUCCESS;
                } else if (tour.getTournamentStatus() == 5 || tour.getTournamentStatus() == 4 || tour.getTournamentStatus() == 3) {
                    List<RoundDTO> rounds = roudao.getAllByTID(tournamentID);
                    request.setAttribute("urounds", rounds);
                    url = SUCCESS;
                }
            } else {
                url = ERROR;
            }
            if (roudao.getRoundStatusbyName(tournamentID, "Top4") == 2) {
                request.setAttribute("ufinishTournament", "true");
            } else {
                request.setAttribute("ufinishTournament", "false");
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
