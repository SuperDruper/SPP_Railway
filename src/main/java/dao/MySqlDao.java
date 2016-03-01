package dao;

import service.DistanceJdbcService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by PC-Alyaksei on 29.02.2016.
 */
public class MySqlDao implements IJdbcDao {

    private static MySqlDao instance;
    private static final Object lock = new Object();

    public static MySqlDao getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MySqlDao();
                }
            }
        }
        return instance;
    }


    private String user = "root";//Логин пользователя
    private String password = "leshik-911";//Пароль пользователя
    private String url = "jdbc:mysql://localhost:3306/railway";//URL адрес
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера


    private MySqlDao() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

    DistanceJdbcService getDistanceService() {
        return new DistanceJdbcService(this);
    }

}
