package test;

import dao.MySqlDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySqlConnectionTest extends Assert{

    @Test
    public void selectFromDbTableDistance() {
        MySqlDao dao = MySqlDao.getInstance();
        Connection conn = dao.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM railway.distance;");
            ResultSet set = st.executeQuery();

            while (set.next()) {
                int id1 = set.getInt("st_id_from");
                int id2 = set.getInt("st_id_to");
                int d   = set.getInt("d_distance");
                System.out.println(id1 + " " + id2 + " " + d + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}