/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import candidates.CandidatesDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thang
 */
@WebServlet(name = "UpdateCandidatesController", urlPatterns = {"/UpdateCandidatesController"})
public class UpdateCandidatesController extends HttpServlet {

    private final String ERROR = "error.jsp";
    private final String SUCCESS = "ManageRoundController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String tid = request.getParameter("tournamentID");
            String rid = request.getParameter("roundID");
            String cid = request.getParameter("candidatesID");
            String rname = request.getParameter("roundName");
            String rstatus = request.getParameter("roundStatus");
            String score = request.getParameter("score");
            String action = request.getParameter("action");
            String result = request.getParameter("result");
            int cstatus = 0;
            if (result.equals("fail")) {
                cstatus = 2;
            } else if (result.equals("")) {
                cstatus = 1;
                result = null;
            } else {
                cstatus = 1;
            }

            if (action.equals("Update")) {
                CandidatesDAO cdao = new CandidatesDAO();
                if (score == null) {
                    score = "0";
                }
                if (score.equals("0")) {
                    if (cdao.updateScoreResult(Integer.parseInt(score), result, cstatus, Integer.parseInt(cid))) {
                        url = SUCCESS;
                    } else {
                        url = ERROR;
                    }
                } else {
                    if (rname.equals("Top4")) {
                        if (cdao.checkDuplicateScore(Integer.parseInt(score), Integer.parseInt(rid))) {
                            request.setAttribute("duplicateScore", "true");
                            request.setAttribute("cid", cid);
                            url = SUCCESS;
                        } else {
                            if (cdao.updateScoreResult(Integer.parseInt(score), result, cstatus, Integer.parseInt(cid))) {
                                request.setAttribute("duplicateScore", "fase");
                                url = SUCCESS;
                            } else {
                                url = ERROR;
                            }
                        }
                    } else {
                        if (cdao.updateScoreResult(Integer.parseInt(score), result, cstatus, Integer.parseInt(cid))) {
                            request.setAttribute("duplicateScore", "fase");
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    }
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
