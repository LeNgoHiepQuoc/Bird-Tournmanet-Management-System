package registrationform;

import account.AccountDTO;
import bird.BirdDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tournament.TournamentDTO;
import utils.DBContext;

public class RegistrationFormDAO implements Serializable {

    private static final String INSERT_FORM = "INSERT INTO RegistrationForm([tournamentID],[accountID],[birdID],[formStatus])\n"
            + "VALUES(?,?,?,?)";

    private final static String GET_BIRD_BY_ID = "SELECT b.birdID, b.birdName, b.birdPhoto\n"
            + "FROM Bird b\n"
            + "WHERE b.accountID = ? AND b.birdStatus = ?\n"
            + "ORDER BY b.birdID ASC";

    private final static String GET_TOURNAMENT_DETAIL = "SELECT DISTINCT t.tournamentID,t.image, t.tournamentName, t.tournamentStatus, FORMAT(CAST(t.dateTime AS datetime),'dd/MM/yyyy HH:mm:ss') AS dateTime, t.location, t.fee, t.prize, t.minParticipant, t.sponsor\n"
            + " FROM  Tournament t\n"
            + "WHERE t.tournamentID =  ?";

    private final static String MY_TOURNAMENT = "SELECT r.formID, r.formStatus,t.tournamentName,r.tournamentID, t.location, t.fee, t.dateTime, t.minParticipant,t.tournamentStatus, b.birdPhoto, b.birdName, b.height, b.weight, b.color, a.accountID ,a.phone, a.email, a.name\n"
            + "FROM Tournament t\n"
            + "JOIN RegistrationForm r ON t.tournamentID = r.tournamentID\n"
            + "JOIN Bird b ON b.birdID = r.birdID\n"
            + "JOIN Account a ON r.accountID = a.accountID\n"
            + " WHERE a.accountID = ?"
            + " ORDER BY t.dateTime DESC";

    private final static String COUNT_TOURNAMENT = "SELECT COUNT(*) AS count, formStatus \n"
            + "FROM RegistrationForm WHERE accountID = ?\n"
            + "GROUP BY formStatus";

    public static String NUMBER_CURRENT_REGISTERED = "SELECT COUNT(formID) as numberOfPlayer\n"
            + "FROM RegistrationForm\n"
            + "WHERE formStatus = ? AND tournamentID = ?";
    private final static String GET_FORM_BY_TOURNAMENTID = "SELECT r.formID, r.tournamentID, a.name, b.birdName, r.formStatus\n"
            + "FROM RegistrationForm r \n"
            + "JOIN Account a ON r.accountID = a.accountID \n"
            + "JOIN Bird b ON r.birdID = b.birdID\n"
            + "WHERE r.tournamentID = ?";
    private final static String MANAGE_FORM_BY_ID = "UPDATE RegistrationForm \n"
            + "SET formStatus = ?\n"
            + "WHERE formID = ?";
    private final static String FORM_DETAIL_BY_ID = "SELECT r.accountID, r.formID, r.formStatus,t.tournamentName,r.tournamentID, t.location, t.fee, t.dateTime, t.minParticipant,t.tournamentStatus, b.birdPhoto, b.birdName, b.height, b.weight, b.color, a.accountID ,a.phone, a.email, a.name\n"
            + "FROM Tournament t\n"
            + "JOIN RegistrationForm r ON t.tournamentID = r.tournamentID\n"
            + "JOIN Bird b ON b.birdID = r.birdID\n"
            + "JOIN Account a ON r.accountID = a.accountID\n"
            + "WHERE r.formID = ?\n"
            + "AND t.tournamentID = ?";

    public boolean manageForm(int formStatus, int formID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(MANAGE_FORM_BY_ID);
                stm.setInt(1, formStatus);
                stm.setInt(2, formID);
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

    public boolean insertForm(int tournamentID, int accountID, int birdID, int formStatus) throws SQLException {
        RegistrationFormDTO r = null;
        boolean check = true;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(INSERT_FORM);
                stm.setInt(1, tournamentID);
                stm.setInt(2, accountID);
                stm.setInt(3, birdID);
                stm.setInt(4, formStatus);
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

    public List<RegistrationFormDTO> listBirdByAccountID(int accountID, int birdStatus) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<RegistrationFormDTO> list = new ArrayList<>();
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BIRD_BY_ID);
                stm.setInt(1, accountID);
                stm.setInt(2, birdStatus);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String birdName = rs.getString("birdName");
                    String birdPhoto = rs.getString("birdPhoto");
                    BirdDTO b = new BirdDTO(birdName, birdPhoto);
                    RegistrationFormDTO r = new RegistrationFormDTO(b);
                    list.add(r);
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

    public RegistrationFormDTO getDetailTournament(int ID) throws Exception {
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
                    RegistrationFormDTO r = new RegistrationFormDTO(tournamentID, t);
                    return r;
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

    public List<RegistrationFormDTO> MyTournament(int accountID) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<RegistrationFormDTO> list = new ArrayList<>();
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(MY_TOURNAMENT);
                stm.setInt(1, accountID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int formID = rs.getInt("formID");
                    int formStatus = rs.getInt("formStatus");
                    String tournamentName = rs.getString("tournamentName");
                    int tournamentID = rs.getInt("tournamentID");
                    String location = rs.getString("location");
                    String fee = rs.getString("fee");
                    String dateTime = rs.getString("dateTime");
                    int minParticipant = rs.getInt("minParticipant");
                    int tournamentStatus = rs.getInt("tournamentStatus");
                    String birdPhoto = rs.getString("birdPhoto");
                    String birdName = rs.getString("birdName");
                    String height = rs.getString("height");
                    String weight = rs.getString("weight");
                    String color = rs.getString("color");
                    int phone = rs.getInt("phone");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    TournamentDTO t = new TournamentDTO(tournamentID, tournamentName, location, fee, dateTime, minParticipant, tournamentStatus);
                    BirdDTO b = new BirdDTO(birdPhoto, birdName, height, weight, color);
                    AccountDTO a = new AccountDTO(accountID, phone, email, name);
                    RegistrationFormDTO r = new RegistrationFormDTO(formID, formStatus, t, b, a);
                    list.add(r);
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

    public int countTournament(int accountID) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(COUNT_TOURNAMENT);
                stm.setInt(1, accountID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
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

    public int getNumberRegistered(int formStatus, int tournamentID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int num = 0;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(NUMBER_CURRENT_REGISTERED);
                stm.setInt(1, formStatus);
                stm.setInt(2, tournamentID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    num = rs.getInt(1);
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
        return num;
    }

    public List<RegistrationFormDTO> loadFormByTournamentID(int tournamentID) throws SQLException {
        List<RegistrationFormDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_FORM_BY_TOURNAMENTID);
                stm.setInt(1, tournamentID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int formID = rs.getInt("formID");
                    int ntournamentID = rs.getInt("tournamentID");
                    String accName = rs.getString("name");
                    String birdName = rs.getString("birdName");
                    int formStatus = rs.getInt("formStatus");
                    AccountDTO acc = new AccountDTO(accName);
                    BirdDTO bird = new BirdDTO(birdName);
                    RegistrationFormDTO form = new RegistrationFormDTO(formID, tournamentID, acc, bird, formStatus);
                    list.add(form);
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

    public RegistrationFormDTO getFromDetailByID(int formID, int tournamentID) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(FORM_DETAIL_BY_ID);
                stm.setInt(1, formID);
                stm.setInt(2, tournamentID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    formID = rs.getInt("formID");
                    int formStatus = rs.getInt("formStatus");
                    String tournamentName = rs.getString("tournamentName");
                    tournamentID = rs.getInt("tournamentID");
                    String location = rs.getString("location");
                    String fee = rs.getString("fee");
                    String dateTime = rs.getString("dateTime");
                    int minParticipant = rs.getInt("minParticipant");
                    int tournamentStatus = rs.getInt("tournamentStatus");
                    String birdPhoto = rs.getString("birdPhoto");
                    String birdName = rs.getString("birdName");
                    String height = rs.getString("height");
                    String weight = rs.getString("weight");
                    String color = rs.getString("color");
                    int accountID = rs.getInt("accountID");
                    int phone = rs.getInt("phone");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    TournamentDTO t = new TournamentDTO(tournamentID, tournamentName, location, fee, dateTime, minParticipant, tournamentStatus);
                    BirdDTO b = new BirdDTO(birdPhoto, birdName, height, weight, color);
                    AccountDTO a = new AccountDTO(accountID, phone, email, name);
                    RegistrationFormDTO r = new RegistrationFormDTO(formID, formStatus, t, b, a);
                    return r;
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
