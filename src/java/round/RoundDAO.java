package round;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tournament.TournamentDTO;
import utils.DBContext;

public class RoundDAO implements Serializable {

    private static String ADD_ROUND = "INSERT INTO Round(tournamentID, roundName, typeOfRound, birdPass, birdAttend, roundStatus) VALUES(?,?,?,?,?,?)";
    private static String GET_ALL_BY_TID = "SELECT roundID, tournamentID, roundName, typeOfRound, birdPass, birdAttend, roundStatus\n"
            + "FROM Round\n"
            + "WHERE tournamentID = ? ";

    private static String GET_ROUND_BY_RID = "SELECT roundID, tournamentID, roundName, typeOfRound, birdPass, birdAttend, roundStatus FROM Round WHERE roundID = ? ";
    public static String UPDATE_ATTEND_PASS_CANDIDATES
            = "declare @birda as int\n"
            + "declare @birdp as int\n"
            + "set @birda=(SELECT COUNT(candidatesID) as birdAttend\n"
            + "            FROM Candidates\n"
            + "            WHERE roundID = ?)\n"
            + "\n"
            + "set @birdp=(SELECT COUNT(candidatesID) as birdPass\n"
            + "            FROM Candidates\n"
            + "            WHERE roundID = ? AND result != 'fail' AND result IS NOT NULL)\n"
            + "UPDATE Round \n"
            + "            SET     birdAttend = @birda , birdPass = @birdp\n"
            + "            WHERE  roundID = ?";
    private final static String UPDATE_ROUND = "UPDATE Round\n"
            + "SET  typeOfRound = ? , birdAttend = ? , birdPass = ? , roundStatus = ?\n"
            + "WHERE roundID = ? ";
    public static String GET_NUMBER_OF_ROUND = "SELECT COUNT(roundID) as numberOfRound\n"
            + "FROM Round\n"
            + "WHERE tournamentID = ? ";
    public static String DELETE_ROUND_BY_NAME = "DELETE FROM Round WHERE tournamentID = ? AND roundName = ? ";
    private final static String UPDATE_ROUND_NAME = "UPDATE Round\n"
            + "SET  roundName = ? \n"
            + "WHERE roundID = ? ";
    public static String GET_PREVIOUS_ROUND_STATUS = "SELECT TOP(1) roundStatus\n"
            + " from Round\n"
            + " where tournamentID = ? AND roundID < ? \n"
            + " order by roundID desc";
    public static String GET_NEXT_ROUND_STATUS
            = "SELECT TOP(1) roundStatus\n"
            + " from Round\n"
            + " where tournamentID = ? AND roundID > ? \n"
            + " order by roundID asc";
    public static String GET_ROUND_STATUS_BY_NAME
            = "select roundStatus\n"
            + "from Round\n"
            + "where tournamentID = ? and roundName = ? ";

    public int getRoundStatusbyName(int tid, String name) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ROUND_STATUS_BY_NAME);
                stm.setInt(1, tid);
                stm.setString(2, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int num = rs.getInt(1);
                    return num;
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
        return 0;
    }

    public int getNextRStatus(int tid, int rid) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_NEXT_ROUND_STATUS);
                stm.setInt(1, tid);
                stm.setInt(2, rid);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int num = rs.getInt(1);
                    return num;
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
        return 0;
    }

    public int getPreviousRStatus(int tid, int rid) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_PREVIOUS_ROUND_STATUS);
                stm.setInt(1, tid);
                stm.setInt(2, rid);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int num = rs.getInt(1);
                    return num;
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
        return 0;
    }

    public boolean updateRoundName(String rname, int RID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_ROUND_NAME);
                stm.setString(1, rname);
                stm.setInt(2, RID);
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

    public boolean deleteByName(int TID, String RN) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(DELETE_ROUND_BY_NAME);
                stm.setInt(1, TID);
                stm.setString(2, RN);
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

    public int getNumberOfRound(int TID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_NUMBER_OF_ROUND);
                stm.setInt(1, TID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int num = rs.getInt(1);
                    return num;
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
        return 0;
    }

    public boolean updateRound(String type, int birdA, int birdP, int rstatus, int RID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_ROUND);
                stm.setString(1, type);
                stm.setInt(2, birdA);
                stm.setInt(3, birdP);
                stm.setInt(4, rstatus);
                stm.setInt(5, RID);
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

    public boolean insertRound(RoundDTO round) throws SQLException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(ADD_ROUND);
                stm.setInt(1, round.getTournament().getTournamentID());
                stm.setString(2, round.getRoundName());
                stm.setString(3, round.getTypeOfRound());
                stm.setInt(4, round.getBirdPass());
                stm.setInt(5, round.getBirdAttend());
                stm.setInt(6, round.getRoundStatus());
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

    public List<RoundDTO> getAllByTID(int tournamentID) throws SQLException {
        List<RoundDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ALL_BY_TID);
                stm.setInt(1, tournamentID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    RoundDTO rou = new RoundDTO();
                    TournamentDTO t = new TournamentDTO();
                    rou.setRoundID(rs.getInt(1));
                    t.setTournamentID(rs.getInt(2));
                    rou.setTournament(t);
                    rou.setRoundName(rs.getString(3));
                    rou.setTypeOfRound(rs.getString(4));
                    rou.setBirdPass(rs.getInt(5));
                    rou.setBirdAttend(rs.getInt(6));
                    rou.setRoundStatus(rs.getInt(7));
                    list.add(rou);
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

    public boolean updateAttendPassCandidates(int RID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_ATTEND_PASS_CANDIDATES);
                stm.setInt(1, RID);
                stm.setInt(2, RID);
                stm.setInt(3, RID);
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

    public RoundDTO getRoundByID(int roundID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ROUND_BY_RID);
                stm.setInt(1, roundID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    RoundDTO rou = new RoundDTO();
                    TournamentDTO tour = new TournamentDTO();
                    rou.setRoundID(rs.getInt(1));
                    tour.setTournamentID(rs.getInt(2));
                    rou.setTournament(tour);
                    rou.setRoundName(rs.getString(3));
                    rou.setTypeOfRound(rs.getString(4));
                    rou.setBirdPass(rs.getInt(5));
                    rou.setBirdAttend(rs.getInt(6));
                    rou.setRoundStatus(rs.getInt(7));
                    return rou;
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
}
