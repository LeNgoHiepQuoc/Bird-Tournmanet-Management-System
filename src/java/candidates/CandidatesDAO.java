package candidates;

import account.AccountDTO;
import bird.BirdDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import round.RoundDTO;
import utils.DBContext;

public class CandidatesDAO implements Serializable {

    public static String GET_APPROVED_CANDIDATES
            = "SELECT Candidates.candidatesID,\n"
            + "  Bird.birdName,\n"
            + "  Account.name,\n"
            + "  Candidates.candidatesStatus,\n"
            + "  Candidates.score,\n"
            + "  Candidates.result,\n"
            + "  Candidates.roundID\n"
            + "FROM BirdTournament.dbo.Candidates\n"
            + "  INNER JOIN BirdTournament.dbo.RegistrationForm\n"
            + "    ON BirdTournament.dbo.RegistrationForm.formID =\n"
            + "    BirdTournament.dbo.Candidates.formID\n"
            + "  INNER JOIN BirdTournament.dbo.Bird ON BirdTournament.dbo.Bird.birdID =\n"
            + "    BirdTournament.dbo.RegistrationForm.birdID\n"
            + "  INNER JOIN BirdTournament.dbo.Account ON BirdTournament.dbo.Account.accountID\n"
            + "    = BirdTournament.dbo.RegistrationForm.accountID AND\n"
            + "    BirdTournament.dbo.Account.accountID = BirdTournament.dbo.Bird.accountID\n"
            + "WHERE Candidates.candidatesStatus = ? AND Candidates.tournamentID = ? \n"
            + "ORDER BY score desc";
    public static String GET_CANDIDATES_BY_RID
            = "SELECT Candidates.candidatesID,\n"
            + "  Bird.birdName,\n"
            + "  Account.name,\n"
            + "  Candidates.candidatesStatus,\n"
            + "  Candidates.score,\n"
            + "  Candidates.result,\n"
            + "  Candidates.roundID\n"
            + "FROM BirdTournament.dbo.Candidates\n"
            + "  INNER JOIN BirdTournament.dbo.RegistrationForm\n"
            + "    ON BirdTournament.dbo.RegistrationForm.formID =\n"
            + "    BirdTournament.dbo.Candidates.formID\n"
            + "  INNER JOIN BirdTournament.dbo.Bird ON BirdTournament.dbo.Bird.birdID =\n"
            + "    BirdTournament.dbo.RegistrationForm.birdID\n"
            + "  INNER JOIN BirdTournament.dbo.Account ON BirdTournament.dbo.Account.accountID\n"
            + "    = BirdTournament.dbo.RegistrationForm.accountID AND\n"
            + "    BirdTournament.dbo.Account.accountID = BirdTournament.dbo.Bird.accountID\n"
            + "WHERE Candidates.candidatesStatus = ? AND Candidates.roundID = ? \n"
            + "ORDER BY score desc";

    public static String UPDATE_ROUND_CANDIDATES
            = "UPDATE  Candidates \n"
            + "SET roundID = ? \n"
            + "WHERE tournamentID = ? AND candidatesStatus = ?";

    public static String UPDATE_RESULT_BY_TOP
            = "WITH C AS(\n"
            + "SELECT TOP (?)*\n"
            + "FROM Candidates\n"
            + "WHERE roundID = ? AND score IS NOT NULL AND score>0 \n"
            + "ORDER BY score desc\n"
            + ")\n"
            + "UPDATE C\n"
            + "SET result = ?";

    public static String NUMBER_CANDIDATES_ATTEND
            = "SELECT COUNT(candidatesID) as birdAttend\n"
            + "FROM Candidates\n"
            + "WHERE roundID = ?";
    public static String NUMBER_CANDIDATES_PASS
            = "SELECT COUNT(candidatesID) as birdPass\n"
            + "FROM Candidates\n"
            + "WHERE roundID = ? AND result != 'fail' AND result IS NOT NULL";

    public static String GET_FINISH_CANDIDATES
            = "SELECT Candidates.candidatesID,\n"
            + "  Bird.birdName,\n"
            + "  Account.name,\n"
            + "  Candidates.candidatesStatus,\n"
            + "  Candidates.score,\n"
            + "  Candidates.result,\n"
            + "  Candidates.roundID\n"
            + "FROM BirdTournament.dbo.Candidates\n"
            + "  INNER JOIN BirdTournament.dbo.RegistrationForm\n"
            + "    ON BirdTournament.dbo.RegistrationForm.formID =\n"
            + "    BirdTournament.dbo.Candidates.formID\n"
            + "  INNER JOIN BirdTournament.dbo.Bird ON BirdTournament.dbo.Bird.birdID =\n"
            + "    BirdTournament.dbo.RegistrationForm.birdID\n"
            + "  INNER JOIN BirdTournament.dbo.Account ON BirdTournament.dbo.Account.accountID\n"
            + "    = BirdTournament.dbo.RegistrationForm.accountID AND\n"
            + "    BirdTournament.dbo.Account.accountID = BirdTournament.dbo.Bird.accountID\n"
            + "WHERE Candidates.result IS NOT NULL AND Candidates.roundID = ? \n"
            + "ORDER BY score desc";

    public static String UPDATE_FAILED_CANDIDATES
            = "UPDATE Candidates \n"
            + "SET     result = ?, candidatesStatus = ?\n"
            + "WHERE roundID = ? AND result IS NULL";

    public static String RESET_RESULT_CANDIDATES
            = "UPDATE Candidates\n"
            + "SET     result = NULL, score = NULL\n"
            + "WHERE  roundID = ? AND tournamentID = ? AND candidatesStatus = ?";

    public static String GET_ID_LIST_BY_TOP
            = "SELECT TOP(?) candidatesID\n"
            + "FROM Candidates\n"
            + "WHERE roundID = ?\n"
            + "ORDER BY score desc";

    public static String UPDATE_RESULT_BY_ID
            = "UPDATE Candidates \n"
            + "SET     result = ? \n"
            + "WHERE  candidatesID = ? ";
    public static String UPDATE_SCORE_RESULT
            = "UPDATE Candidates \n"
            + "SET     score = ?, result = ?, candidatesStatus = ? \n"
            + "WHERE  candidatesID = ? ";

    public static final String INSERT_CANDIDATES = "INSERT INTO Candidates(formID, tournamentID,candidatesStatus)\n"
            + "VALUES(?,?,1)";

    public static String GET_NUMBER_SCORED
            = "SELECT COUNT(candidatesID) as birdScored\n"
            + "FROM Candidates\n"
            + "WHERE roundID = ? AND score IS NOT NULL AND score != 0 ";
    public static String GET_NUMBER_FAILED
            = "SELECT COUNT(candidatesID) as failed\n"
            + "FROM Candidates\n"
            + "WHERE roundID = ? AND result = 'fail'";
    public static String GET_BIRD_ID
            = "SELECT RegistrationForm.birdID\n"
            + "FROM Candidates\n"
            + "  INNER JOIN RegistrationForm\n"
            + "    ON RegistrationForm.formID =\n"
            + "    Candidates.formID\n"
            + "WHERE candidatesID = ?";
    private static final String CHECK_DUPLICATE_SCORE
            = "SELECT score FROM Candidates WHERE score = ? AND roundID = ?";
    public static String GET_FINISH_PASSED_CANDIDATES
            = "SELECT Candidates.candidatesID,\n"
            + "  Bird.birdName,\n"
            + "  Account.name,\n"
            + "  Candidates.candidatesStatus,\n"
            + "  Candidates.score,\n"
            + "  Candidates.result,\n"
            + "  Candidates.roundID\n"
            + "FROM BirdTournament.dbo.Candidates\n"
            + "  INNER JOIN BirdTournament.dbo.RegistrationForm\n"
            + "    ON BirdTournament.dbo.RegistrationForm.formID =\n"
            + "    BirdTournament.dbo.Candidates.formID\n"
            + "  INNER JOIN BirdTournament.dbo.Bird ON BirdTournament.dbo.Bird.birdID =\n"
            + "    BirdTournament.dbo.RegistrationForm.birdID\n"
            + "  INNER JOIN BirdTournament.dbo.Account ON BirdTournament.dbo.Account.accountID\n"
            + "    = BirdTournament.dbo.RegistrationForm.accountID AND\n"
            + "    BirdTournament.dbo.Account.accountID = BirdTournament.dbo.Bird.accountID\n"
            + "WHERE Candidates.roundID > ? \n"
            + "ORDER BY score desc";
    public static String GET_LIST_TOP_CANDIDATES
            = "SELECT Candidates.candidatesID,\n"
            + "  Bird.birdName,\n"
            + "  Account.name\n"
            + "FROM Candidates\n"
            + "  INNER JOIN RegistrationForm\n"
            + "    ON RegistrationForm.formID =\n"
            + "   Candidates.formID\n"
            + "  INNER JOIN .Bird ON Bird.birdID =\n"
            + "    RegistrationForm.birdID\n"
            + "  INNER JOIN Account ON Account.accountID\n"
            + "    = RegistrationForm.accountID AND\n"
            + "    Account.accountID = Bird.accountID\n"
            + "	where Candidates.tournamentID =?\n"
            + "order by roundID desc, score desc";

    public List<CandidatesDTO> getListTopCandidates(int TID) throws SQLException {
        List<CandidatesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_LIST_TOP_CANDIDATES);
                stm.setInt(1, TID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    CandidatesDTO cands = new CandidatesDTO();
                    BirdDTO bird = new BirdDTO();
                    AccountDTO acc = new AccountDTO();
                    cands.setCandidatesID(rs.getInt(1));
                    bird.setBirdName(rs.getString(2));
                    cands.setBird(bird);
                    acc.setName(rs.getString(3));
                    bird.setAccount(acc);
                    cands.setBird(bird);
                    list.add(cands);
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

    public List<CandidatesDTO> getFinishPassedCandidates(int RID) throws SQLException {
        List<CandidatesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_FINISH_PASSED_CANDIDATES);
                stm.setInt(1, RID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    CandidatesDTO cands = new CandidatesDTO();
                    BirdDTO bird = new BirdDTO();
                    AccountDTO acc = new AccountDTO();
                    RoundDTO rou = new RoundDTO();
                    cands.setCandidatesID(rs.getInt(1));
                    bird.setBirdName(rs.getString(2));
                    cands.setBird(bird);
                    acc.setName(rs.getString(3));
                    bird.setAccount(acc);
                    cands.setBird(bird);
                    cands.setCandidatesStatus(rs.getInt(4));
                    cands.setScore(rs.getInt(5));
                    cands.setResult(rs.getString(6));
                    rou.setRoundID(rs.getInt(7));
                    cands.setRound(rou);
                    list.add(cands);
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

    public boolean checkDuplicateScore(int score, int rid) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(CHECK_DUPLICATE_SCORE);
                stm.setInt(1, score);
                stm.setInt(2, rid);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public int getBirdId(int CID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BIRD_ID);
                stm.setInt(1, CID);
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

    public int getNumberFailed(int RID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_NUMBER_FAILED);
                stm.setInt(1, RID);
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

    public int getNumberScored(int RID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_NUMBER_SCORED);
                stm.setInt(1, RID);
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

    public boolean updateScoreResult(int score, String rs, int cstatus, int CID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_SCORE_RESULT);
                stm.setInt(1, score);
                if (rs == null) {
                    stm.setNull(2, Types.NVARCHAR);
                } else {
                    stm.setString(2, rs);
                }
                stm.setInt(3, cstatus);
                stm.setInt(4, CID);
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

    public boolean updateResultByID(String result, int CID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_RESULT_BY_ID);
                stm.setString(1, result);
                stm.setInt(2, CID);
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

    public List<CandidatesDTO> getIDListByTop(int top, int RID) throws SQLException {
        List<CandidatesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ID_LIST_BY_TOP);
                stm.setInt(1, top);
                stm.setInt(2, RID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    CandidatesDTO cands = new CandidatesDTO();
                    cands.setCandidatesID(rs.getInt(1));
                    list.add(cands);
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

    public boolean resetResultCandidates(int RID, int TID, int cstatus) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(RESET_RESULT_CANDIDATES);
                stm.setInt(1, RID);
                stm.setInt(2, TID);
                stm.setInt(3, cstatus);
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

    public boolean updateFailedCandidates(String rs, int cstatus, int RID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_FAILED_CANDIDATES);
                stm.setString(1, rs);
                stm.setInt(2, cstatus);
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

    public List<CandidatesDTO> getFinishCandidates(int RID) throws SQLException {
        List<CandidatesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_FINISH_CANDIDATES);
                stm.setInt(1, RID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    CandidatesDTO cands = new CandidatesDTO();
                    BirdDTO bird = new BirdDTO();
                    AccountDTO acc = new AccountDTO();
                    RoundDTO rou = new RoundDTO();
                    cands.setCandidatesID(rs.getInt(1));
                    bird.setBirdName(rs.getString(2));
                    cands.setBird(bird);
                    acc.setName(rs.getString(3));
                    bird.setAccount(acc);
                    cands.setBird(bird);
                    cands.setCandidatesStatus(rs.getInt(4));
                    cands.setScore(rs.getInt(5));
                    cands.setResult(rs.getString(6));
                    rou.setRoundID(rs.getInt(7));
                    cands.setRound(rou);
                    list.add(cands);
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

    public boolean updateResultByTop(int top, int RID, String result) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_RESULT_BY_TOP);
                stm.setInt(1, top);
                stm.setInt(2, RID);
                stm.setString(3, result);
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

    public int numberCAttend(int RID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(NUMBER_CANDIDATES_ATTEND);
                stm.setInt(1, RID);
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

    public int numberCPass(int RID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(NUMBER_CANDIDATES_PASS);
                stm.setInt(1, RID);
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

    public List<CandidatesDTO> getCandidatesByRID(int candidatesStatus, int RID) throws SQLException {
        List<CandidatesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_CANDIDATES_BY_RID);
                stm.setInt(1, candidatesStatus);
                stm.setInt(2, RID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    CandidatesDTO cands = new CandidatesDTO();
                    BirdDTO bird = new BirdDTO();
                    AccountDTO acc = new AccountDTO();
                    RoundDTO rou = new RoundDTO();
                    cands.setCandidatesID(rs.getInt(1));
                    bird.setBirdName(rs.getString(2));
                    cands.setBird(bird);
                    acc.setName(rs.getString(3));
                    bird.setAccount(acc);
                    cands.setBird(bird);
                    cands.setCandidatesStatus(rs.getInt(4));
                    cands.setScore(rs.getInt(5));
                    cands.setResult(rs.getString(6));
                    rou.setRoundID(rs.getInt(7));
                    cands.setRound(rou);
                    list.add(cands);
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

    public boolean updateRoundCandidates(int RID, int TID, int cstatus) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_ROUND_CANDIDATES);
                stm.setInt(1, RID);
                stm.setInt(2, TID);
                stm.setInt(3, cstatus);
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

    public List<CandidatesDTO> getApprovedCandidates(int candidatesStatus, int tournamentID) throws SQLException {
        List<CandidatesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_APPROVED_CANDIDATES);
                stm.setInt(1, candidatesStatus);
                stm.setInt(2, tournamentID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    CandidatesDTO cands = new CandidatesDTO();
                    BirdDTO bird = new BirdDTO();
                    AccountDTO acc = new AccountDTO();
                    RoundDTO rou = new RoundDTO();
                    cands.setCandidatesID(rs.getInt(1));
                    bird.setBirdName(rs.getString(2));
                    cands.setBird(bird);
                    acc.setName(rs.getString(3));
                    bird.setAccount(acc);
                    cands.setBird(bird);
                    cands.setCandidatesStatus(rs.getInt(4));
                    cands.setScore(rs.getInt(5));
                    cands.setResult(rs.getString(6));
                    rou.setRoundID(rs.getInt(7));
                    cands.setRound(rou);
                    list.add(cands);
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

    public boolean insertCandidates(int formID, int tournamentID) throws SQLException {
        CandidatesDTO c = null;
        boolean check = true;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(INSERT_CANDIDATES);
                stm.setInt(1, formID);
                stm.setInt(2, tournamentID);
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
