package controller;

import account.AccountDAO;
import account.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageAccountController", urlPatterns = {"/ManageAccountController"})
public class ManageAccountController extends HttpServlet {

    private final String ERROR = "error.jsp";
    private final String SUCCESS = "LoadAccountController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = ERROR;
            AccountDTO acc = new AccountDTO();
            try {
                String accountID = request.getParameter("accountID");
                String action = request.getParameter("action");
                if (action.equals("Block")) {
                    AccountDAO dao = new AccountDAO();
                    acc = dao.getByID(Integer.parseInt(accountID));
                    if (acc != null) {
                        acc.setAccountStatus(0);
                        if (dao.updateAccount(acc)) {
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    } else {
                        url = ERROR;
                    }
                }
                if (action.equals("Unblock")) {
                    AccountDAO dao = new AccountDAO();
                    acc = dao.getByID(Integer.parseInt(accountID));
                    if (acc != null) {
                        acc.setAccountStatus(1);
                        if (dao.updateAccount(acc)) {
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    } else {
                        url = ERROR;
                    }
                }
                if (action.equals("Detail")) {
                    AccountDAO dao = new AccountDAO();
                    acc = dao.getByID(Integer.parseInt(accountID));
                    if (acc != null) {
                        request.setAttribute("account_detail", acc);
                        url = "manageAccountDetail.jsp";
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
