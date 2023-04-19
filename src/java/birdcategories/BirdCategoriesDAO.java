package birdcategories;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;

public class BirdCategoriesDAO implements Serializable {

    private static final String LOAD_BIRD_CATEGORY = "SELECT b.categoriesID , b.categoriesName\n"
            + "FROM BirdCategories b";

    public List<BirdCategoriesDTO> LoadBirdCate() throws Exception {
        List<BirdCategoriesDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            if (con != null) {
                stm = con.prepareStatement(LOAD_BIRD_CATEGORY);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int categoriesID = rs.getInt("categoriesID");
                    String categoriesName = rs.getString("categoriesName");
                    BirdCategoriesDTO b = new BirdCategoriesDTO(categoriesID, categoriesName);
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

}
