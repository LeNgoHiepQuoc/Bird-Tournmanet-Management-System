package controller;

import bird.BirdDAO;
import bird.BirdDTO;
import blog.BlogDAO;
import blog.BlogDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tournament.TournamentDAO;
import tournament.TournamentDTO;

@WebServlet(name = "LoadHomePageController", urlPatterns = {"/LoadHomePageController"})
public class LoadHomePageController extends HttpServlet {

    private final static String SUCCESS = "homePage.jsp";
    private final static String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = null;
            HttpSession s = request.getSession(false);
            TournamentDAO tour = new TournamentDAO();
            BirdDAO bird = new BirdDAO();
            BlogDAO blog = new BlogDAO();
            try {
                List<TournamentDTO> t = tour.viewTournament();
                List<BirdDTO> b = bird.getAllBird();
                List<BlogDTO> bl = blog.viewBlog();
                if (tour != null && bird != null) {
                    s.setAttribute("GET_TOURNAMENT", t);
                    s.setAttribute("GET_BIRD", b);
                    s.setAttribute("GET_BLOG", bl);
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
