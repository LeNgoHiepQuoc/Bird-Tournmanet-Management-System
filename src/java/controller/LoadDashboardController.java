package controller;

import account.AccountDAO;
import account.AccountDTO;
import bird.BirdDAO;
import blog.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tournament.TournamentDAO;
import tournament.TournamentDTO;

@WebServlet(name = "LoadDashboardController", urlPatterns = {"/LoadDashboardController"})
public class LoadDashboardController extends HttpServlet {

    private static final String SUCCESS = "dashboadAdmin.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = null;
            BirdDAO birddao = new BirdDAO();
            AccountDAO accdao = new AccountDAO();
            TournamentDAO tourdao = new TournamentDAO();
            BlogDAO blogdao = new BlogDAO();
            try {
                int count_bird = birddao.countBird();
                int count_account = accdao.countAccount();
                int count_tournament = tourdao.countTournament();
                int count_tournament_onGoing = tourdao.countTournamentOnGoing();
                int count_tournament_finised = tourdao.countTournamentFinised();
                int count_tournament_delay = tourdao.countTournamentDelay();
                int count_blog = blogdao.countBlog();
                ArrayList<AccountDTO> a_list = (ArrayList) accdao.getAllAccount();
                ArrayList<TournamentDTO> t_list = (ArrayList) tourdao.listTournament();
                HttpSession s = request.getSession();
                if (count_account != 0 && count_bird != 0 && count_tournament != 0) {
                    request.setAttribute("count_bird", count_bird);
                    request.setAttribute("count_account", count_account);
                    request.setAttribute("count_tournament", count_tournament);
                    request.setAttribute("count_blog", count_blog);
                    request.setAttribute("count_tournament_onGoing", count_tournament_onGoing);
                    request.setAttribute("count_tournament_finised", count_tournament_finised);
                    request.setAttribute("count_tournament_delay", count_tournament_delay);
                    s.setAttribute("t_list", t_list);
                    s.setAttribute("a_list", a_list);
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            } catch (Exception e) {
                e.printStackTrace();
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
