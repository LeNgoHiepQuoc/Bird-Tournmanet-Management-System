package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    //link to jsp
    private static final String HOME_PAGE = "homePage.jsp";

    //value of action of User
    private static final String LOGIN = "Login";
    private static final String REGISTER = "Register";
    private static final String LOGOUT = "Logout";
    private static final String REGISTER_FORM = "RegisterForm";
    private static final String CONFIRM_FORM = "ConfirmForm";
    private static final String LOAD_BIRD = "BIRD";
    private static final String HOME_USER = "HOME_USER";
    private static final String TOURNAMENT = "TOURNAMENT";
    private static final String HOME_GUEST = "HOME_GUEST";
    private static final String ON_GOING_TOURNAMENT = "ON_GOING_TOURNAMENT";
    private static final String OLD_TOURNAMENT = "OLD_TOURNAMENT";
    private static final String DELAY_TOURNAMENT = "DELAY_TOURNAMENT";
    private static final String TOURNAMENT_DETAIL = "TOURNAMENT_DETAIL";
    private static final String LOAD_BIRD_BY_ID = "LoadBirdByAccountID";
    private static final String FINAL_FORM = "FinalForm";
    private static final String MY_TOURNAMENT = "MY_TOURNAMENT";
    private static final String MY_TOURNAMENT_DETAIL = "MY_TOURNAMENT_DETAIL";
    private static final String LOAD_ACCOUNT = "ProfileLoad";
    private static final String UPDATE_ACCOUNT = "Update_Account";
    private static final String SEARCH_TOURNAMENT = "SEARCH_TOURNAMENT";
    private static final String ADD_BIRD = "addBird";
    private static final String GET_BIRD_UPDATE_USER = "getBirdUpdate";
    private static final String UPDATE_BIRD = "updateBird";
    private static final String LOAD_BLOG = "BLOG";
    private static final String LOAD_BLOG_DETAIL = "BlogDetail";
    private static final String SUBMIT_FEEDBACK = "Submit Feedback";
    private static final String PASSWORD_FORGOT_SEARCH = "forgotPaasswordSearch";
    private static final String PASSWORD_RESET = "resetPasswordUpdate";
    

    //value of action of Admin
    private static final String MANAGE_ACCOUNT = "MANAGE_ACCOUNT";
    private static final String MANAGE_BIRD = "MANAGE_BIRD";
    private static final String MANAGE_TOURNAMENT = "MANAGE_TOURNAMENT";
    private static final String LOAD_BIRD_CATEGORY = "LOAD_BIRD_CATEGORY";
    private static final String CREATE_TOURNAMENT = "CREATE_TOURNAMENT";
    private static final String MANAGE_PARTICIPANT = "MANAGE_PARTICIPANT";
    private static final String REMOVE_TOURNAMENT = "REMOVE_TOURNAMENT";
    private static final String LOAD_PARTICIPANT = "LOAD_PARTICIPANT";
    private static final String DENY_PARTICIPANT = "DENY_PARTICIPANT";
    private static final String APPROVE_PARTICIPANT = "APPROVE_PARTICIPANT";
    private static final String LOAD_FORM_DETAIL = "LOAD_FORM_DETAIL";
    private static final String LOAD_BLOGS_MANGEMENT = "MANAGE_BLOG";
    private static final String DELETE_BLOGS = "DELETE_BLOGS";
    private static final String ADD_BLOGS = "AddNewBlog";
    private static final String UPDATE_BLOGS = "UPDATE_BLOGS";
    private static final String UPDATE = "UPDATE";
    private static final String LOAD_FEEDBACK = "LOAD_FEEDBACK";
    private static final String DASHBOARD = "DASHBOARD";

    //link to servlet of User
    private static final String LOAD_HOME_PAGE = "LoadHomePageController";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String REGISTER_CONTROLLER = "RegisterController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String ALL_TOURNAMENT_CONTROLLER = "AllTournamentController";
    private static final String ON_GOING_TOURNAMENT_CONTROLLER = "OnGoingTournamentController";
    private static final String OLD_TOURNAMENT_CONTROLLER = "OldTournamentController";
    private static final String DELAY_TOURNAMENT_CONTROLLER = "DelayTournamentController";
    private static final String TOURNAMENT_DETAIL_CONTROLLER = "TournamentDetailController";
    private static final String REGISTER_FORM_CONTROLLER = "RegisterFormController";
    private static final String CONFIRM_FORM_CONTROLLER = "ConfirmFormController";
    private static final String FINAL_FORM_CONTROLLER = "FinalConfirmFormController";
    private static final String MY_TOURNAMENT_CONTROLLER = "MyTournamentController";
    private static final String MY_TOURNAMENT_DETAIL_CONTROLLER = "MyTournamentDetailController";
    private static final String LOAD_ACCOUNT_DETAIL_CONTROLLER = "LoadProfileController";
    private static final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountController";
    private static final String SEARCH_TOURNAMENT_CONTROLLER = "SearchTournamentController";
    private static final String ADD_BIRD_CONTROLLER = "AddBirdController";
    private static final String GET_BIRD_UPDATE_CONTROLLER = "LoadBirdUpdateController";
    private static final String UPDATE_BIRD_CONTROLLER = "UpdateBirdController";
    private static final String LOAD_BLOG_CONTROLLER = "LoadBlogController";
    private static final String LOAD_BLOG_DETAIL_CONTROLLER = "LoadBlogDetailController";
    private static final String SUBMIT_FEEDBACK_CONTROLLER = "SubmitFeedbackController";
    private static final String LOAD_PASSWORD_FORGOT_CONTROLLER ="checkForgotController";
    private static final String RESET_FORGOT_PASSWORD ="ResetPasswordController";

    // link to servlet of Admin
    private static final String MANAGE_ACCOUNT_CONTROLLER = "ManageAccountController";
    private static final String LOAD_ACCOUNT_CONTROLLER = "LoadAccountController";
    private static final String LOAD_BIRD_CONTROLLER = "LoadBirdController";
    private static final String MANAGE_TOURNAMENT_CONTROLLER = "LoadTournamentController";
    private static final String LOAD_BIRD_CATEGORY_CONTROLLER = "LoadBirdCategoryController";
    private static final String CREATE_TOURNAMENT_CONTROLLER = "CreateTournamentController";
    private static final String MANAGE_PARTICIPANT_CONTROLLER = "ManageParticipantController";
    private static final String LOAD_FORM_DETAIL_CONTROLLER = "LoadFormDetailController";
    private static final String REMOVE_TOURNAMENT_CONTROLLER = "RemoveTournamentController";
    private static final String LOAD_PARTICIPANT_CONTROLLER = "LoadParticipantController";
    private static final String LOAD_BLOGS_MANGEMENT_CONTROLLER = "LoadBlogControllerManageController";
    private static final String DELETE_BLOGS_CONTROLLER = "DeleteBlogsController";
    private static final String ADD_BLOGS_CONTROLLER = "AddBlogsController";
    private static final String LOAD_UPDATE_BLOGS_CONTROLLER = "LoadUpdateBlogsController";
    private static final String UPDATE_BLOGS_CONTROLLER = "UpdateBlogsController";
    private static final String LOAD_FEEDBACK_CONTROLLER = "LoadFeedbackController";
    private static final String LOAD_DASHBOARD_CONTROLLER = "LoadDashboardController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOAD_HOME_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOAD_HOME_PAGE;
            } else {
                switch (action) {
                    case LOGIN:
                        url = LOGIN_CONTROLLER;
                        break;
                    case REGISTER:
                        url = REGISTER_CONTROLLER;
                        break;
                    case LOGOUT:
                        url = LOGOUT_CONTROLLER;
                        break;
                    case HOME_USER:
                        url = HOME_PAGE;
                        break;
                    case HOME_GUEST:
                        url = HOME_PAGE;
                        break;
                    case TOURNAMENT:
                        url = ALL_TOURNAMENT_CONTROLLER;
                        break;
                    case ON_GOING_TOURNAMENT:
                        url = ON_GOING_TOURNAMENT_CONTROLLER;
                        break;
                    case OLD_TOURNAMENT:
                        url = OLD_TOURNAMENT_CONTROLLER;
                        break;
                    case DELAY_TOURNAMENT:
                        url = DELAY_TOURNAMENT_CONTROLLER;
                        break;
                    case TOURNAMENT_DETAIL:
                        url = TOURNAMENT_DETAIL_CONTROLLER;
                        break;
                    case REGISTER_FORM:
                        url = REGISTER_FORM_CONTROLLER;
                        break;
                    case CONFIRM_FORM:
                        url = CONFIRM_FORM_CONTROLLER;
                        break;
                    case LOAD_BIRD:
                        url = LOAD_BIRD_BY_ID;
                        break;
                    case FINAL_FORM:
                        url = FINAL_FORM_CONTROLLER;
                        break;
                    case MANAGE_ACCOUNT:
                        url = LOAD_ACCOUNT_CONTROLLER;
                        break;
                    case MANAGE_BIRD:
                        url = LOAD_BIRD_CONTROLLER;
                        break;
                    case MANAGE_TOURNAMENT:
                        url = MANAGE_TOURNAMENT_CONTROLLER;
                        break;
                    case MY_TOURNAMENT:
                        url = MY_TOURNAMENT_CONTROLLER;
                        break;
                    case LOAD_ACCOUNT:
                        url = LOAD_ACCOUNT_DETAIL_CONTROLLER;
                        break;
                    case UPDATE_ACCOUNT:
                        url = UPDATE_ACCOUNT_CONTROLLER;
                        break;
                    case LOAD_BIRD_CATEGORY:
                        url = LOAD_BIRD_CATEGORY_CONTROLLER;
                        break;
                    case CREATE_TOURNAMENT:
                        url = CREATE_TOURNAMENT_CONTROLLER;
                        break;
                    case MANAGE_PARTICIPANT:
                        url = MANAGE_PARTICIPANT_CONTROLLER;
                        break;
                    case REMOVE_TOURNAMENT:
                        url = REMOVE_TOURNAMENT_CONTROLLER;
                        break;
                    case SEARCH_TOURNAMENT:
                        url = SEARCH_TOURNAMENT_CONTROLLER;
                        break;
                    case LOAD_PARTICIPANT:
                        url = LOAD_PARTICIPANT_CONTROLLER;
                        break;
                    case DENY_PARTICIPANT:
                        url = MANAGE_PARTICIPANT_CONTROLLER;
                        break;
                    case APPROVE_PARTICIPANT:
                        url = MANAGE_PARTICIPANT_CONTROLLER;
                        break;
                    case ADD_BIRD:
                        url = ADD_BIRD_CONTROLLER;
                        break;
                    case GET_BIRD_UPDATE_USER:
                        url = GET_BIRD_UPDATE_CONTROLLER;
                        break;
                    case UPDATE_BIRD:
                        url = UPDATE_BIRD_CONTROLLER;
                        break;
                    case LOAD_BLOG:
                        url = LOAD_BLOG_CONTROLLER;
                        break;
                    case LOAD_BLOG_DETAIL:
                        url = LOAD_BLOG_DETAIL_CONTROLLER;
                        break;
                    case LOAD_BLOGS_MANGEMENT:
                        url = LOAD_BLOGS_MANGEMENT_CONTROLLER;
                        break;
                    case DELETE_BLOGS:
                        url = DELETE_BLOGS_CONTROLLER;
                        break;
                    case ADD_BLOGS:
                        url = ADD_BLOGS_CONTROLLER;
                        break;
                    case UPDATE_BLOGS:
                        url = LOAD_UPDATE_BLOGS_CONTROLLER;
                        break;
                    case UPDATE:
                        url = UPDATE_BLOGS_CONTROLLER;
                        break;
                    case SUBMIT_FEEDBACK:
                        url = SUBMIT_FEEDBACK_CONTROLLER;
                        break;
                    case LOAD_FEEDBACK:
                        url = LOAD_FEEDBACK_CONTROLLER;
                        break;
                    case MY_TOURNAMENT_DETAIL:
                        url = MY_TOURNAMENT_DETAIL_CONTROLLER;
                        break;
                    case LOAD_FORM_DETAIL:
                        url = LOAD_FORM_DETAIL_CONTROLLER;
                        break;
                    case DASHBOARD:
                        url = LOAD_DASHBOARD_CONTROLLER;
                        break;
                    case PASSWORD_FORGOT_SEARCH:
                        url = LOAD_PASSWORD_FORGOT_CONTROLLER;
                        break;
                    case PASSWORD_RESET :
                        url = RESET_FORGOT_PASSWORD;
                        break;
                }
            }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
            request.setAttribute("mess", e.toString());
        } finally {
            request.getSession().setAttribute("urlHistory", "MainController");
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

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
