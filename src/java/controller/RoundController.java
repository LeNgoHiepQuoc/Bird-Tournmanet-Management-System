/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import candidates.CandidatesDAO;
import candidates.CandidatesDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import round.RoundDAO;
import round.RoundDTO;

@WebServlet(name = "RoundController", urlPatterns = {"/RoundController"})
public class RoundController extends HttpServlet {

    private final String ERROR = "error.jsp";
    private final String TOURNAMENT_DETAIL = "TournamentDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int tournamentID = Integer.parseInt(request.getParameter("ID"));
            String rname = request.getParameter("roundName");
            int rid = Integer.parseInt(request.getParameter("roundID"));
            RoundDTO round = null;
            RoundDAO rdao = new RoundDAO();
            int rstatus = rdao.getRoundStatusbyName(tournamentID, rname);
            if (rstatus == 0) {
                round = rdao.getRoundByID(rid);
                if (round == null) {
                    url = ERROR;
                }
                request.setAttribute("uround", round);
                url = TOURNAMENT_DETAIL;
            } else if (rstatus == 1) {
                CandidatesDAO cdao = new CandidatesDAO();
                round = rdao.getRoundByID(rid);
                if (round == null) {
                    url = ERROR;
                } else {
                    List<CandidatesDTO> Rcands = cdao.getCandidatesByRID(1, rid);
                    if (Rcands.isEmpty()) {
                        request.setAttribute("error", "there is not any bird in this round");
                    }
                    request.setAttribute("uround", round);
                    request.setAttribute("ucands", Rcands);
                    url = TOURNAMENT_DETAIL;
                }
            } else if (rstatus == 2) {
                CandidatesDAO cdao = new CandidatesDAO();
                round = rdao.getRoundByID(rid);
                if (round == null) {
                    url = ERROR;
                } else {
                    List<CandidatesDTO> Fcands = cdao.getFinishCandidates(rid);
                    if (Fcands.isEmpty()) {
                        request.setAttribute("error", "result have not set");
                    } else {
                        if (rdao.getNextRStatus(tournamentID, rid) == 1 || rdao.getNextRStatus(tournamentID, rid) == 2) {
                            List<CandidatesDTO> fpcands = cdao.getFinishPassedCandidates(rid);
                            Fcands.addAll(0, cdao.getFinishPassedCandidates(rid));
                            request.setAttribute("unexton", "true");

                        } else if (rdao.getNextRStatus(tournamentID, rid) == 0) {
                            request.setAttribute("unexton", "false");
                        }
                        request.setAttribute("ucands", Fcands);
                    }
                    request.setAttribute("uround", round);
                    url = TOURNAMENT_DETAIL;
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
