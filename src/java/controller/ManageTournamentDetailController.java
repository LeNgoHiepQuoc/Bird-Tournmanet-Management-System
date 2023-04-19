/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registrationform.RegistrationFormDAO;
import round.RoundDAO;
import round.RoundDTO;
import tournament.TournamentDAO;
import tournament.TournamentDTO;

/**
 *
 * @author thang
 */
@WebServlet(name = "ManageTournamentDetailController", urlPatterns = {"/ManageTournamentDetailController"})
public class ManageTournamentDetailController extends HttpServlet {

    private final String ERROR = "error.jsp";
    private final String SUCCESS = "LoadTournamentController";
    private final String TOURNAMENT_DETAIL = "manageTournamentDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        TournamentDTO tour = null;
        String rid = request.getParameter("roundID");
        String rname = request.getParameter("roundName");
        String rstatus = request.getParameter("roundStatus");
        String tournamentID = request.getParameter("tournamentID");
        try {
            TournamentDAO tdao = new TournamentDAO();
            tour = tdao.getDetail(Integer.parseInt(tournamentID));
            RoundDAO roudao = new RoundDAO();
            if (tour != null) {
                request.setAttribute("tour", tour);
                RegistrationFormDAO rdao = new RegistrationFormDAO();
                request.setAttribute("confirmedForm", rdao.getNumberRegistered(2, Integer.parseInt(tournamentID)));
                if (tour.getTournamentStatus() == 0) {
                    url = TOURNAMENT_DETAIL;
                } else if (tour.getTournamentStatus() == 1) {
                    request.setAttribute("numberPlayer", rdao.getNumberRegistered(1, Integer.parseInt(tournamentID)) + rdao.getNumberRegistered(2, Integer.parseInt(tournamentID)));
                    url = TOURNAMENT_DETAIL;
                } else if (tour.getTournamentStatus() == 2) {
                    request.setAttribute("numberPlayer", rdao.getNumberRegistered(2, Integer.parseInt(tournamentID)));
                    url = TOURNAMENT_DETAIL;
                } else if (tour.getTournamentStatus() == 6) {
                    request.setAttribute("numberPlayer", rdao.getNumberRegistered(2, Integer.parseInt(tournamentID)));
                    url = TOURNAMENT_DETAIL;
                } else if (tour.getTournamentStatus() == 5) {
                    List<RoundDTO> rounds = roudao.getAllByTID(Integer.parseInt(tournamentID));
                    request.setAttribute("numberPlayer", rdao.getNumberRegistered(2, Integer.parseInt(tournamentID)));
                    request.setAttribute("rounds", rounds);
                    url = TOURNAMENT_DETAIL;
                } else if (tour.getTournamentStatus() == 4) {
                    request.setAttribute("numberPlayer", rdao.getNumberRegistered(2, Integer.parseInt(tournamentID)));
                    List<RoundDTO> rounds = roudao.getAllByTID(Integer.parseInt(tournamentID));
                    request.setAttribute("rounds", rounds);
                    url = TOURNAMENT_DETAIL;
                } else if (tour.getTournamentStatus() == 3) {
                    request.setAttribute("numberPlayer", rdao.getNumberRegistered(2, Integer.parseInt(tournamentID)));
                    List<RoundDTO> rounds = roudao.getAllByTID(Integer.parseInt(tournamentID));
                    if (rounds.isEmpty()) {
                        RoundDTO roud = null;
                        int numberOfPlayer = rdao.getNumberRegistered(2, Integer.parseInt(tournamentID));
                        if (numberOfPlayer >= 35) {
                            String[] names = {"Qualified", "Top30", "Top20", "Top10", "Top4"};
                            for (String name : names) {
                                roud = new RoundDTO();
                                tour = new TournamentDTO();
                                tour.setTournamentID(Integer.parseInt(tournamentID));
                                roud.setTournament(tour);
                                roud.setRoundName(name);
                                roud.setRoundStatus(0);
                                roudao.insertRound(roud);
                            }
                        } else if (numberOfPlayer >= 25) {
                            String[] names = {"Qualified", "Top20", "Top10", "Top4"};
                            for (String name : names) {
                                roud = new RoundDTO();
                                tour = new TournamentDTO();
                                tour.setTournamentID(Integer.parseInt(tournamentID));
                                roud.setTournament(tour);
                                roud.setRoundName(name);
                                roud.setRoundStatus(0);
                                roudao.insertRound(roud);
                            }
                        } else if (numberOfPlayer >= 15) {
                            String[] names = {"Qualified", "Top10", "Top4"};
                            for (String name : names) {
                                roud = new RoundDTO();
                                tour = new TournamentDTO();
                                tour.setTournamentID(Integer.parseInt(tournamentID));
                                roud.setTournament(tour);
                                roud.setRoundName(name);
                                roud.setRoundStatus(0);
                                roudao.insertRound(roud);
                            }
                        } else if (numberOfPlayer >= 10) {
                            String[] names = {"Qualified", "Top4"};
                            for (String name : names) {
                                roud = new RoundDTO();
                                tour = new TournamentDTO();
                                tour.setTournamentID(Integer.parseInt(tournamentID));
                                roud.setTournament(tour);
                                roud.setRoundName(name);
                                roud.setRoundStatus(0);
                                roudao.insertRound(roud);
                            }
                        } else {
                            url = TOURNAMENT_DETAIL;
                        }
                        rounds = roudao.getAllByTID(Integer.parseInt(tournamentID));
                        request.setAttribute("rounds", rounds);
                        url = TOURNAMENT_DETAIL;
                    } else {
                        request.setAttribute("rounds", rounds);
                        url = TOURNAMENT_DETAIL;
                    }
                }

            } else {
                url = ERROR;
            }
            if (roudao.getRoundStatusbyName(Integer.parseInt(tournamentID), "Top4") == 2) {
                request.setAttribute("finishTournament", "true");
            } else {
                request.setAttribute("finishTournament", "false");
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
