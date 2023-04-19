package controller;

import candidates.CandidatesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registrationform.RegistrationFormDAO;

@WebServlet(name = "ManageParticipantController", urlPatterns = {"/ManageParticipantController"})
public class ManageParticipantController extends HttpServlet {

    private final String ERROR = "error.jsp";
    private final String SUCCESS = "LoadParticipantController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = ERROR;
            RegistrationFormDAO dao = new RegistrationFormDAO();
            CandidatesDAO cdao = new CandidatesDAO();
            try {
                int formID = Integer.valueOf(request.getParameter("participantID"));
                int tournamentID = Integer.valueOf(request.getParameter("tournamentID"));
                String action = request.getParameter("action");
                if (action.equals("APPROVE_PARTICIPANT")) {
                    boolean form = dao.manageForm(2, formID);
                    if (form == true) {
                        boolean form_candidates = cdao.insertCandidates(formID, tournamentID);
                        url = SUCCESS;
                    } else {
                        url = ERROR;
                    }
                }
                if (action.equals("DENY_PARTICIPANT")) {
                    boolean form = dao.manageForm(3, formID);
                    if (form == true) {
                        url = SUCCESS;
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
