package tournament;

import account.AccountDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;

public class TournamentDAO implements Serializable {

    private final static String VIEW_TOURNAMENT = "SELECT TOP 3 *\n"
            + "FROM (\n"
            + "SELECT DISTINCT t.tournamentID,t.tournamentName, FORMAT(CAST(t.dateTime AS datetime),'dd/MM/yyyy HH:mm:ss') AS dateTime, t.tournamentStatus, t.minParticipant, t.maxParticipant,t.fee, t.prize, t.image\n"
            + "FROM Tournament t\n"
            + "WHERE t.dateTime >= CURRENT_TIMESTAMP AND t.tournamentStatus = 1\n"
            + "       ) AS subquery\n"
            + "  ORDER BY subquery.dateTime ASC;";
    private final static String LIST_TOURNAMENT = "SELECT DISTINCT t.tournamentID, t.location, t.fee, t.tournamentStatus, t.image, t.tournamentName, FORMAT(CAST(t.dateTime AS datetime),'dd/MM/yyyy HH:mm:ss') AS dateTime\n"
            + "FROM Tournament t\n"
            + "ORDER BY t.tournamentID";
    private final static String LIST_TOURNAMENT_BY_STATUS = " SELECT DISTINCT t.tournamentID, t.location, t.fee, t.tournamentStatus, t.image, t.tournamentName, FORMAT(CAST(t.dateTime AS datetime),'dd/MM/yyyy HH:mm:ss') AS dateTime\n"
            + "FROM Tournament t\n"
            + "WHERE t.tournamentStatus = ?\n"
            + "ORDER BY t.tournamentID";
    private final static String GET_TOURNAMENT_DETAIL = "SELECT DISTINCT t.tournamentID,t.image, t.tournamentName, t.tournamentStatus, FORMAT(CAST(t.dateTime AS datetime),'dd/MM/yyyy HH:mm:ss') AS dateTime, t.location, t.fee, t.prize, t.minParticipant, t.sponsor\n"
            + " FROM  Tournament t\n"
            + "WHERE t.tournamentID =  ?";
    private final static String GET_ALL_TOURNAMENT = "SELECT tournamentID, tournamentName, sponsor, prize,minParticipant,maxParticipant, FORMAT(dateTime,'dd/MM/yyyy HH:mm') AS dateTime, tournamentStatus \n"
            + "FROM Tournament";
    private final static String CREATE_TOURNAMENT = "INSERT INTO [dbo].[Tournament]([tournamentName], [minParticipant],[maxParticipant],[dateTime],[location],[fee],[sponsor],[prize],[categoriesID],[image],[tournamentStatus],[description])\n"
            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
    private final static String REMOVE_TOURNAMENT = "DELETE FROM Tournament WHERE tournamentID = ?;";
    private final static String SEARCH_TOURNAMENT = "SELECT DISTINCT t.tournamentID, t.location, t.fee, t.tournamentStatus, t.image, t.tournamentName, FORMAT(CAST(t.dateTime AS datetime),'dd/MM/yyyy HH:mm:ss') AS dateTime\n"
            + "FROM Tournament t\n"
            + "WHERE t.tournamentName LIKE ?\n"
            + "ORDER BY t.tournamentID;";
    private final static String TOURNAMENT_DETAIL = "SELECT tournamentID, image, tournamentName, description, tournamentStatus, minParticipant ,maxParticipant, FORMAT(Tournament.dateTime,'dd/MM/yyyy HH:mm') AS dateTime, location, fee, prize, sponsor\n"
            + "FROM Tournament\n"
            + "WHERE tournamentID = ?";
    private final static String UPDATE_TOURNAMENT = "UPDATE Tournament\n"
            + "SET  tournamentStatus = ? , dateTime = TRY_CONVERT(datetime,?,103), minParticipant = ? , maxParticipant = ? , description = ?, location = ?, fee = ?, prize = ?\n"
            + "WHERE tournamentID = ?";
    private final static String DASHBOARD = "SELECT COUNT(TournamentID) as TournamentID\n"
            + "FROM Tournament";
    private final static String COUNT_ONGOING_DASHBOARD = "SELECT COUNT(tournamentID) as TournamentID FROM Tournament WHERE tournamentStatus = 1 OR  tournamentStatus = 2 OR tournamentStatus = 3";
    private final static String COUNT_FINISED_DASHBOARD = "SELECT COUNT(tournamentID) as TournamentID FROM Tournament WHERE tournamentStatus = 4";
    private final static String COUNT_DELAY_DASHBOARD = "SELECT COUNT(tournamentID) as TournamentID FROM Tournament WHERE tournamentStatus = 5";
    private final static String CHECK_IF_USER_REGISTERED
            = "SELECT Account.accountID\n"
            + "FROM Account\n"
            + "  INNER JOIN RegistrationForm\n"
            + "    ON Account.accountID =\n"
            + "    RegistrationForm.accountID\n"
            + "  INNER JOIN Tournament\n"
            + "    ON Tournament.tournamentID =\n"
            + "    RegistrationForm.tournamentID\n"
            + "where Account.accountID = ? and Tournament.tournamentID = ?";
    public AccountDTO checkUserRegistered(int aid, int tid) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(CHECK_IF_USER_REGISTERED);
                stm.setInt(1, aid);
                stm.setInt(2, tid);
                rs = stm.executeQuery();
                if (rs.next()) {
                    AccountDTO acc = new AccountDTO();
                    acc.setAccountID(rs.getInt(1));
                    return acc;
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
    public int countTournamentDelay() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(COUNT_DELAY_DASHBOARD);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("TournamentID");
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
        return count;
    }

    public int countTournamentFinised() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(COUNT_FINISED_DASHBOARD);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("TournamentID");
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
        return count;
    }

    public int countTournamentOnGoing() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(COUNT_ONGOING_DASHBOARD);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("TournamentID");
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
        return count;
    }

    public int countTournament() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(DASHBOARD);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("TournamentID");
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
        return count;
    }

    public boolean updateTournament(int tstatus, String dateTime, int minp, int maxp, String desc, String location, String fee, String prize, int TID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_TOURNAMENT);
                stm.setInt(1, tstatus);
                stm.setString(2, dateTime);
                stm.setInt(3, minp);
                stm.setInt(4, maxp);
                stm.setString(5, desc);
                stm.setString(6, location);
                stm.setString(7, fee);
                stm.setString(8, prize);
                stm.setInt(9, TID);
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

    public List<TournamentDTO> viewTournament() throws Exception {
        List<TournamentDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(VIEW_TOURNAMENT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int tournamentID = rs.getInt("tournamentID");
                    String tournamentName = rs.getString("tournamentName");
                    String dateTime = rs.getString("dateTime");
                    int tournamentStatus = rs.getInt("tournamentStatus");
                    int minParticipant = rs.getInt("minParticipant");
                    int maxParticipant = rs.getInt("maxParticipant");
                    String fee = rs.getString("fee");
                    String prize = rs.getString("prize");
                    String image = rs.getString("image");
                    TournamentDTO tour = new TournamentDTO(tournamentID, tournamentName, dateTime, tournamentStatus, minParticipant, maxParticipant, fee, prize, image);
                    list.add(tour);
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

    public List<TournamentDTO> listTournament() throws Exception {
        List<TournamentDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(LIST_TOURNAMENT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int tournamentID = rs.getInt("tournamentID");
                    String location = rs.getString("location");
                    String fee = rs.getString("fee");
                    int tournamentStatus = rs.getInt("tournamentStatus");
                    String image = rs.getString("image");
                    String tournamentName = rs.getString("tournamentName");
                    String dateTime = rs.getString("dateTime");
                    TournamentDTO t = new TournamentDTO(tournamentID, location, fee, tournamentStatus, image, tournamentName, dateTime);
                    list.add(t);
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

    public List<TournamentDTO> getTourByStatus(int status) throws Exception {
        List<TournamentDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(LIST_TOURNAMENT_BY_STATUS);
                stm.setInt(1, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int tournamentID = rs.getInt("tournamentID");
                    String location = rs.getString("location");
                    String fee = rs.getString("fee");
                    int tournamentStatus = rs.getInt("tournamentStatus");
                    String image = rs.getString("image");
                    String tournamentName = rs.getString("tournamentName");
                    String dateTime = rs.getString("dateTime");
                    TournamentDTO t = new TournamentDTO(tournamentID, location, fee, tournamentStatus, image, tournamentName, dateTime);
                    list.add(t);
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

    public TournamentDTO getDetailTournament(int ID) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_TOURNAMENT_DETAIL);
                stm.setInt(1, ID);
                rs = stm.executeQuery();

                if (rs.next()) {
                    int tournamentID = rs.getInt("tournamentID");
                    String image = rs.getString("image");
                    String tournamentName = rs.getString("tournamentName");
                    int tournamentStatus = rs.getInt("tournamentStatus");
                    String dateTime = rs.getString("dateTime");
                    String location = rs.getString("location");
                    String fee = rs.getString("fee");
                    String prize = rs.getString("prize");
                    int minParticipant = rs.getInt("minParticipant");
                    String sponsor = rs.getString("sponsor");
                    TournamentDTO t = new TournamentDTO(tournamentID, image, tournamentName, tournamentStatus, dateTime, location, fee, prize, minParticipant, sponsor);
                    return t;
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

    public List<TournamentDTO> loadTournament() throws SQLException {
        List<TournamentDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ALL_TOURNAMENT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    TournamentDTO tour = new TournamentDTO();
                    tour.setTournamentID(rs.getInt(1));
                    tour.setTournamentName(rs.getString(2));
                    tour.setSponsor(rs.getString(3));
                    tour.setPrize(rs.getString(4));
                    tour.setMinParticipant(rs.getInt(5));
                    tour.setMaxParticipant(rs.getInt(6));
                    tour.setDateTime(rs.getString(7));
                    tour.setTournamentStatus(rs.getInt(8));
                    list.add(tour);
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

    public boolean createTournament(TournamentDTO t) throws ClassNotFoundException, SQLException {
        boolean check = true;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(CREATE_TOURNAMENT);
                ptm.setString(1, t.getTournamentName());
                ptm.setInt(2, t.getMinParticipant());
                ptm.setInt(3, t.getMaxParticipant());
                ptm.setString(4, t.getDateTime());
                ptm.setString(5, t.getLocation());
                ptm.setString(6, t.getFee());
                ptm.setString(7, t.getSponsor());
                ptm.setString(8, t.getPrize());
                ptm.setInt(9, t.getCategoriesID());
                ptm.setString(10, t.getImage());
                ptm.setInt(11, t.getTournamentStatus());
                ptm.setString(12, t.getDescription());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean removeTournament(int tournamentID) throws SQLException {
        boolean checkRemove = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(REMOVE_TOURNAMENT);
                stm.setInt(1, tournamentID);
                int check = stm.executeUpdate();
                if (check > 0) {
                    checkRemove = true;
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
        return checkRemove;
    }

    public List<TournamentDTO> searchTournament(String tournamentName) throws Exception {
        List<TournamentDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(SEARCH_TOURNAMENT);
                stm.setString(1, "%" + tournamentName + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int tournamentID = rs.getInt("tournamentID");
                    String location = rs.getString("location");
                    String fee = rs.getString("fee");
                    int tournamentStatus = rs.getInt("tournamentStatus");
                    String image = rs.getString("image");
                    String mtournamentName = rs.getString("tournamentName");
                    String dateTime = rs.getString("dateTime");
                    TournamentDTO t = new TournamentDTO(tournamentID, location, fee, tournamentStatus, image, mtournamentName, dateTime);
                    list.add(t);
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

    public TournamentDTO getDetail(int tournamentID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(TOURNAMENT_DETAIL);
                stm.setInt(1, tournamentID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    TournamentDTO tour = new TournamentDTO();
                    tour.setTournamentID(rs.getInt(1));
                    tour.setImage(rs.getString(2));
                    tour.setTournamentName(rs.getString(3));
                    tour.setDescription(rs.getString(4));
                    tour.setTournamentStatus(rs.getInt(5));
                    tour.setMinParticipant(rs.getInt(6));
                    tour.setMaxParticipant(rs.getInt(7));
                    tour.setDateTime(rs.getString(8));
                    tour.setLocation(rs.getString(9));
                    tour.setFee(rs.getString(10));
                    tour.setPrize(rs.getString(11));
                    tour.setSponsor(rs.getString(12));
                    return tour;
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
