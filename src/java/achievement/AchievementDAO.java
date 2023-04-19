package achievement;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBContext;

public class AchievementDAO implements Serializable {

    private static final String ADD_ACHIEVEMENT_BY_AID = "IF NOT EXISTS (select birdID from Achievement where birdID = (select TOP(1) birdID\n"
            + "from Bird\n"
            + "where accountID = ?\n"
            + "ORDER BY Bird.birdID desc))\n"
            + "BEGIN\n"
            + "insert into Achievement(birdID,medals)\n"
            + "select TOP(1) birdID,'Top1:0;Top2:0;Top3:0;Top4:0'\n"
            + "from Bird\n"
            + "where accountID = ? \n"
            + "ORDER BY Bird.birdID desc\n"
            + "END";
    private static final String GET_MEDALS_BY_ID = "SELECT medals from Achievement WHERE birdID = ?";
    private static final String UPDATE_MEDALS = "UPDATE Achievement SET medals = ? WHERE birdID = ?";
    private static final String UPDATE_ACHIEVEMENT_BY_BID = "UPDATE Achievement SET description = ?, medals = ?, rank = ?, totalScore = ? WHERE birdID = ?";
    private static final String UPDATE_TOTALSCORE = "UPDATE Achievement SET totalScore= 0 WHERE totalScore IS NULL\n"
            + "UPDATE Achievement SET totalScore = totalScore + ? WHERE birdID = ?";
    private static final String UPDATE_RANKING = "UPDATE Achievement\n"
            + "SET rank = (SELECT COUNT(*) FROM Achievement WHERE totalScore > s.totalScore AND totalScore IS NOT NULL AND totalScore != 0) + 1\n"
            + "FROM Achievement AS s\n"
            + "WHERE s.totalScore IS NOT NULL";
    
    public boolean updateAchievementByBID(AchievementDTO ach) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_ACHIEVEMENT_BY_BID);
                stm.setString(1, ach.getDescription());
                stm.setString(2, ach.getMedals());
                stm.setInt(3, ach.getTop());
                stm.setInt(4, ach.getTotalScore());
                stm.setInt(5, ach.getBirdID());
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

    public boolean updateRanking() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_RANKING);
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

    public boolean updateTotalScore(int top, int bid) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_TOTALSCORE);
                switch (top) {
                    case 1:
                        stm.setInt(1, 8);
                        break;
                    case 2:
                        stm.setInt(1, 3);
                        break;
                    case 3:
                        stm.setInt(1, 2);
                        break;
                    case 4:
                        stm.setInt(1, 1);
                        break;
                }
                stm.setInt(2, bid);
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

    public boolean updateMedals(String me, int bid) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_MEDALS);
                stm.setString(1, me);
                stm.setInt(2, bid);
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

    public String getMedalsByBid(int BID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_MEDALS_BY_ID);
                stm.setInt(1, BID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String medals = rs.getString(1);
                    return medals;
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
        return null;
    }

    public boolean addAchievementByAid(int aid) throws SQLException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(ADD_ACHIEVEMENT_BY_AID);
                stm.setInt(1, aid);
                stm.setInt(2, aid);
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

}
