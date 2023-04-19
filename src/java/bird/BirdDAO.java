package bird;

import account.AccountDTO;
import achievement.AchievementDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;

public class BirdDAO implements Serializable {

    private static final String GET_INFORMATION_BIRD = "SELECT b.birdName, b.height, b.weight,b.color,b.dentification,b.birdStatus, b.birdPhoto\n"
            + "FROM Bird b\n"
            + "WHERE b.birdID = ?";

    private final static String LIST_BIRD = "SELECT TOP 3 b.birdName, b.height, b.weight, b.color, b.birdStatus,b.birdPhoto, a.totalScore\n"
            + "FROM Bird b JOIN Achievement a ON b.birdID = a.birdID\n"
            + "ORDER BY a.rank ASC";

    private static final String SEARCH_BIRD_ID = "SELECT b.birdID\n"
            + "FROM Bird b\n"
            + "WHERE b.accountID = ? AND b.birdName = ?";

    private final static String GET_BIRD_BY_ACCOUNT = "SELECT  b.birdID, b.accountID, b.birdName, b.birdPhoto, b.height, b.weight, b.color,b.birdStatus ,b.dentification\n"
            + "FROM Bird b \n"
            + "WHERE accountID= ?";

    private final static String GET_BIRD_ACHIEVEMENT_BY_ACCOUNT = "SELECT Bird.birdID,\n"
            + " Bird.accountID,\n"
            + "   Bird.birdName,\n"
            + "   Bird.birdPhoto,\n"
            + "   Bird.height,\n"
            + "   Bird.weight,\n"
            + "   Bird.color,\n"
            + "   Bird.birdStatus,\n"
            + "   Bird.dentification,\n"
            + "   Achievement.rank,\n"
            + "   Achievement.description,\n"
            + "   Achievement.medals,\n"
            + "   Achievement.totalScore\n"
            + "FROM  Bird\n"
            + "  INNER JOIN  Achievement ON  Bird.birdID =\n"
            + "     Achievement.birdID\n"
            + "WHERE Bird.accountID = ?";
    private final static String GET_BIRD_ACHIEVEMENT_BY_ID = "SELECT Bird.birdID,\n"
            + " Bird.accountID,\n"
            + "   Bird.birdName,\n"
            + "   Bird.birdPhoto,\n"
            + "   Bird.height,\n"
            + "   Bird.weight,\n"
            + "   Bird.color,\n"
            + "   Bird.birdStatus,\n"
            + "   Bird.dentification,\n"
            + "   Achievement.rank,\n"
            + "   Achievement.description,\n"
            + "   Achievement.medals,\n"
            + "   Achievement.totalScore\n"
            + "FROM  Bird\n"
            + "  INNER JOIN  Achievement ON  Bird.birdID =\n"
            + "     Achievement.birdID\n"
            + "WHERE Bird.birdID = ?";
    private static final String GET_ALL_BIRD = "SELECT b.birdID, b.accountID, b.birdName, b.birdPhoto, b.height, b.weight, b.color, b.categoriesID, b.dentification, b.birdStatus\n"
            + "FROM Bird b ";
    private static final String GET_BY_ID = "SELECT birdID, accountID, birdName, birdPhoto, height, weight, color, birdStatus, dentification\n"
            + "FROM Bird \n"
            + "WHERE birdID = ? ";
    private static final String UPDATE_BIRD = "UPDATE Bird \n"
            + "SET accountID = ? , birdName = ? , birdPhoto = ? , height = ? , weight = ? , color = ? , birdStatus = ? , dentification = ?\n"
            + "WHERE birdID = ? ";
    private static final String DELETE_BIRD = "DElETE FROM Bird WHERE birdID = ? ";
    private static final String ADD_BIRD = "INSERT INTO Bird(accountID,categoriesID,birdName,birdPhoto,height,weight,color,dentification,birdStatus)\n"
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_BIRD_BY_ACCOUNT_ID = "UPDATE Bird\n"
            + "SET categoriesID = ?, birdName=?, birdPhoto=?,height = ?, weight = ?, color = ?, dentification =?, birdStatus =?\n"
            + "WHERE birdID = ? AND accountID = ?;";
    private static final String GET_BIRD_BY_ACCOUNT_DENDIFICATION = "SELECT  b.birdID, b.accountID, b.birdName, b.birdPhoto, b.height, b.weight, b.color,b.birdStatus ,b.dentification\n"
            + "FROM Bird b \n"
            + "WHERE accountID= ?";
    private static final String DASHBOARD = "SELECT COUNT(BirdID) as BirdID\n"
            + "FROM Bird";
    private static final String TOTAL_RANKING
            = "SELECT Bird.birdName,\n"
            + "  Account.name,\n"
            + "  Achievement.totalScore,\n"
            + "  Achievement.rank\n"
            + "FROM Bird\n"
            + "  INNER JOIN Account ON Account.accountID\n"
            + "    = Bird.accountID\n"
            + "  INNER JOIN Achievement ON Bird.birdID =\n"
            + "    Achievement.birdID\n"
            + "	where totalScore != 0 and totalScore is not null\n"
            + "order by rank asc";

    public List<BirdDTO> getTotalRanking() throws SQLException {
        List<BirdDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(TOTAL_RANKING);
                rs = stm.executeQuery();
                while (rs.next()) {
                    BirdDTO bird = new BirdDTO();
                    bird.setBirdName(rs.getString(1));
                    AccountDTO acc = new AccountDTO();
                    acc.setName(rs.getString(2));
                    bird.setAccount(acc);
                    AchievementDTO ach = new AchievementDTO();
                    ach.setTotalScore(rs.getInt(3));
                    ach.setTop(rs.getInt(4));
                    bird.setAchivement(ach);
                    list.add(bird);
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

    public int countBird() throws Exception {
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
                    count = rs.getInt("BirdID");
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

    public BirdDTO getBirdAchievement(int birdID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BIRD_ACHIEVEMENT_BY_ID);
                stm.setInt(1, birdID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    BirdDTO bird = new BirdDTO();
                    bird.setBirdID(rs.getInt(1));
                    bird.setAccountID(rs.getInt(2));
                    bird.setBirdName(rs.getString(3));
                    bird.setBirdPhoto(rs.getString(4));
                    bird.setHeight(rs.getString(5));
                    bird.setWeight(rs.getString(6));
                    bird.setColor(rs.getString(7));
                    bird.setBirdStatus(rs.getInt(8));
                    bird.setDentification(rs.getString(9));
                    AchievementDTO ach = new AchievementDTO();
                    ach.setTop(rs.getInt(10));
                    ach.setDescription(rs.getString(11));
                    ach.setMedals(rs.getString(12));
                    ach.setTotalScore(rs.getInt(13));
                    bird.setAchivement(ach);
                    return bird;
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

    public List<BirdDTO> getAllBirdAchievement(int accountID) throws SQLException {
        List<BirdDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BIRD_ACHIEVEMENT_BY_ACCOUNT);
                stm.setInt(1, accountID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    BirdDTO bird = new BirdDTO();
                    bird.setBirdID(rs.getInt(1));
                    bird.setAccountID(rs.getInt(2));
                    bird.setBirdName(rs.getString(3));
                    bird.setBirdPhoto(rs.getString(4));
                    bird.setHeight(rs.getString(5));
                    bird.setWeight(rs.getString(6));
                    bird.setColor(rs.getString(7));
                    bird.setBirdStatus(rs.getInt(8));
                    bird.setDentification(rs.getString(9));
                    AchievementDTO ach = new AchievementDTO();
                    ach.setTop(rs.getInt(10));
                    ach.setDescription(rs.getString(11));
                    ach.setMedals(rs.getString(12));
                    ach.setTotalScore(rs.getInt(13));
                    bird.setAchivement(ach);
                    list.add(bird);
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

    public boolean updateBirdByAccountID(int categoriesID, String birdName, String birdPhoto, int height, int weight, String color, String dentification, int birdStatus, int birdID, int accountID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_BIRD_BY_ACCOUNT_ID);
                stm.setInt(1, categoriesID);
                stm.setString(2, birdName);
                stm.setString(3, birdPhoto);
                stm.setInt(4, height);
                stm.setInt(5, weight);
                stm.setString(6, color);
                stm.setString(7, dentification);
                stm.setInt(8, birdStatus);
                stm.setInt(9, birdID);
                stm.setInt(10, accountID);
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

    public static int addBird(int accountID, String name, int height, int weight, String color, int categoriesID, String denfitication, String image, int birdStatus) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int result = 0;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(ADD_BIRD);
                stm.setInt(1, accountID);
                stm.setInt(2, categoriesID);
                stm.setString(3, name);
                stm.setString(4, image);
                stm.setInt(5, height);
                stm.setInt(6, weight);
                stm.setString(7, color);
                stm.setString(8, denfitication);
                stm.setInt(9, birdStatus);
                result = stm.executeUpdate();
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
        return result;
    }

    public BirdDTO getBirdFormByID(int birdID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_INFORMATION_BIRD);
                stm.setInt(1, birdID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String birdName = rs.getString("birdName");
                    String height = rs.getString("height");
                    String weight = rs.getString("weight");
                    String color = rs.getString("color");
                    String dentification = rs.getString("dentification");
                    int birdStatus = rs.getInt("birdStatus");
                    String birdPhoto = rs.getString("birdPhoto");
                    BirdDTO bird = new BirdDTO(birdName, height, weight, color, dentification, birdStatus,birdPhoto);
                    return bird;
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

    public List<BirdDTO> getAllBird() throws Exception {
        List<BirdDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(LIST_BIRD);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String birdName = rs.getString("birdName");
                    String height = rs.getString("height");
                    String weight = rs.getString("weight");
                    String color = rs.getString("color");
                    int birdStatus = rs.getInt("birdStatus");
                    String birdPhoto = rs.getString("birdPhoto");
                    int totalScore = rs.getInt("totalScore");
                    AchievementDTO a = new AchievementDTO(totalScore);
                    BirdDTO b = new BirdDTO(birdName, height, weight, color, birdStatus, birdPhoto, a);
                    list.add(b);
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

    public BirdDTO FindBird(int accountID, String birdName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(SEARCH_BIRD_ID);
                stm.setInt(1, accountID);
                stm.setString(2, birdName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int birdID = rs.getInt("birdID");
                    BirdDTO bird = new BirdDTO(birdID);
                    return bird;
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

    public List<BirdDTO> getAllBirdByAccountID(int accountID) throws SQLException {
        List<BirdDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BIRD_BY_ACCOUNT);
                stm.setInt(1, accountID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    BirdDTO bird = new BirdDTO();
                    bird.setBirdID(rs.getInt(1));
                    bird.setAccountID(rs.getInt(2));
                    bird.setBirdName(rs.getString(3));
                    bird.setBirdPhoto(rs.getString(4));
                    bird.setHeight(rs.getString(5));
                    bird.setWeight(rs.getString(6));
                    bird.setColor(rs.getString(7));
                    bird.setBirdStatus(rs.getInt(8));
                    bird.setDentification(rs.getString(9));
                    list.add(bird);
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

    public BirdDTO getBirdByAccountID(int accountID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BIRD_BY_ACCOUNT);
                stm.setInt(1, accountID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    BirdDTO bird = new BirdDTO();
                    bird.setBirdID(rs.getInt(1));
                    bird.setAccountID(rs.getInt(2));
                    bird.setBirdName(rs.getString(3));
                    bird.setBirdPhoto(rs.getString(4));
                    bird.setHeight(rs.getString(5));
                    bird.setWeight(rs.getString(6));
                    bird.setColor(rs.getString(7));
                    bird.setBirdStatus(rs.getInt(8));
                    bird.setDentification(rs.getString(9));
                    return bird;
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

    public List<BirdDTO> manageBird() throws SQLException {
        List<BirdDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ALL_BIRD);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int birdID = rs.getInt("birdID");
                    int accountID = rs.getInt("accountID");
                    String birdName = rs.getString("birdName");
                    String birdPhoto = rs.getString("birdPhoto");
                    String height = rs.getString("height");
                    String weight = rs.getString("weight");
                    String color = rs.getString("color");
                    int categoriesID = rs.getInt("categoriesID");
                    String dentification = rs.getString("dentification");
                    int birdStatus = rs.getInt("birdStatus");
                    BirdDTO bird = new BirdDTO(birdID, accountID, birdName, birdPhoto, height, weight, color, categoriesID, dentification, birdStatus);
                    list.add(bird);
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

    public BirdDTO getByID(int birdID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BY_ID);
                stm.setInt(1, birdID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    BirdDTO bird = new BirdDTO();
                    bird.setBirdID(rs.getInt(1));
                    bird.setAccountID(rs.getInt(2));
                    bird.setBirdName(rs.getString(3));
                    bird.setBirdPhoto(rs.getString(4));
                    bird.setHeight(rs.getString(5));
                    bird.setWeight(rs.getString(6));
                    bird.setColor(rs.getString(7));
                    bird.setBirdStatus(rs.getInt(8));
                    bird.setDentification(rs.getString(9));
                    return bird;
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

    public boolean updateBird(BirdDTO bird) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(UPDATE_BIRD);
                stm.setInt(1, bird.getAccountID());
                stm.setString(2, bird.getBirdName());
                stm.setString(3, bird.getBirdPhoto());
                stm.setString(4, bird.getHeight());
                stm.setString(5, bird.getWeight());
                stm.setString(6, bird.getColor());
                stm.setInt(7, bird.getBirdStatus());
                stm.setString(8, bird.getDentification());
                stm.setInt(9, bird.getBirdID());
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

    public boolean deleteBird(int birdID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(DELETE_BIRD);
                stm.setInt(1, birdID);
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
