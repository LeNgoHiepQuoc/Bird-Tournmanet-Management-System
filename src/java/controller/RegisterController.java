package controller;

import account.AccountDAO;
import account.AccountDTO;
import account.AccountError;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        AccountDTO acc = null;
        AccountError error = new AccountError();
        boolean checkValidation = true;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repass = request.getParameter("repass");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            AccountDAO dao = new AccountDAO();
            if (dao.checkDuplicate(email)) {
                error.setDuplicate("Account existed!");
                checkValidation = false;
            }
            if (password.length() < 5) {
                error.setPassword("Password must contain at least 5 characters");
                checkValidation = false;
            }
            if (!repass.equals(password)) {
                error.setRepass("Password does not match");
                checkValidation = false;
            }
            if (name.length() < 3) {
                error.setName("Name must contain at least 3 characters");
                checkValidation = false;
            }
            if (!Pattern.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b", phone)) {
                error.setPhone("Invalid phone number ");
                checkValidation = false;
            }
            if (checkValidation) {
                acc = new AccountDTO(0, email, password, name, null, 0, Integer.parseInt(phone), 1);
                boolean checkCreate = dao.insertAccount(acc);
                if (checkCreate) {
                    url = SUCCESS;
                    request.setAttribute("mess", "Account created");
                } else {
                    request.setAttribute("mess", "ERROR!");
                }
            } else {
                request.setAttribute("ERROR", error);
            }
        } catch (Exception e) {
            log("Error at CreateControler: " + e.toString());
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
