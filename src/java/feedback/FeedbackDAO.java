package feedback;

import account.AccountDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tournament.TournamentDTO;
import utils.DBContext;

public class FeedbackDAO implements Serializable {

    private static final String CREATE_FEEDBACK = "INSERT Feedback([accountID], [tournamentID], [body])\n"
            + "VALUES(?,?,?);";
    private static final String LIST_ALL_FEEDBACK = "SELECT f.feedbackID, t.tournamentName, a.name, f.body\n"
            + "FROM Feedback f\n"
            + "JOIN Account a ON f.accountID = a.accountID\n"
            + "JOIN Tournament t ON f.tournamentID = t.tournamentID\n"
            + "ORDER BY t.tournamentName";

    public boolean createFeedback(FeedbackDTO feedback) throws SQLException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(CREATE_FEEDBACK);
                stm.setInt(1, feedback.getAccountID());
                stm.setInt(2, feedback.getTournamentID());
                stm.setString(3, feedback.getBody());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public List<FeedbackDTO> loadAllFeedback() throws SQLException {
        List<FeedbackDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(LIST_ALL_FEEDBACK);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int feedbackID = rs.getInt("feedbackID");
                    String tournamentName = rs.getString("tournamentName");
                    String accName = rs.getString("name");
                    String body = rs.getString("body");
                    AccountDTO acc = new AccountDTO(accName);
                    TournamentDTO tour = new TournamentDTO(tournamentName);
                    FeedbackDTO feedback = new FeedbackDTO(feedbackID, tour, acc, body);
                    list.add(feedback);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
