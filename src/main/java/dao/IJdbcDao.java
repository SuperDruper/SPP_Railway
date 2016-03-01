package dao;

import java.sql.Connection;

/**
 * Created by PC-Alyaksei on 29.02.2016.
 */
public interface IJdbcDao {
    public Connection getConnection();
}
