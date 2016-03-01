package service;

import dao.IJdbcDao;
import model.Distance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-Alyaksei on 29.02.2016.
 */
public class DistanceJdbcService implements IService<Distance, DistancePK> {

    private IJdbcDao dao;

    public DistanceJdbcService (IJdbcDao dao) {
        this.dao = dao;
    }


    @Deprecated
    @Override
    public Distance create() throws ServiceException {
        throw new ServiceException("This method in this class is unnormal!");
    }


    @Override
    public Distance persist(Distance obj) throws ServiceException {

        try(Connection conn = dao.getConnection()) {

            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO railway.distance (st_id_from, st_id_to, d_distance) VALUES (?, ?, ?)");

            int i = 0;
            st.setInt(i++, obj.getIdFrom());
            st.setInt(i++, obj.getIdTo());
            st.setInt(i++, obj.getDistance());

            int rowsAmount = st.executeUpdate();
            if(rowsAmount != 1) {
                throw new ServiceException("Strange amount of rows during insert " + rowsAmount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return obj;
    }


    @Override
    public Distance getByPK(DistancePK key) throws ServiceException {

        try(Connection conn = dao.getConnection()) {

            PreparedStatement st = conn.prepareStatement(
                    "SELECT * FROM railway.distance WHERE st_id_from=? and st_id_to=?");
            st.setInt(1, key.getIdFrom());
            st.setInt(2, key.getIdTo());
            ResultSet rs = st.executeQuery();
            List<Distance> list = parse(rs);

            if (list.size() == 1) {
                return list.get(0);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }


    @Override
    public void update(Distance obj) throws ServiceException {

        try(Connection conn = dao.getConnection()) {

            PreparedStatement st = conn.prepareStatement(
                    "UPDATE railway.distance SET d_distance = ?" +
                            " WHERE st_id_from = ? and st_id_to = ?");

            int i = 0;
            st.setInt(i++, obj.getDistance());
            st.setInt(i++, obj.getIdFrom());
            st.setInt(i++, obj.getIdTo());

            int rowsAmount = st.executeUpdate();
            if(rowsAmount != 1) {
                throw new ServiceException("Strange amount of rows during insert " + rowsAmount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }


    @Override
    public void delete(Distance obj) throws ServiceException {

        try(Connection conn = dao.getConnection()) {

            PreparedStatement st = conn.prepareStatement(
                    "DELETE FROM railway.distance WHERE st_id_from = ? and st_id_to = ?");

            int i = 0;
            st.setInt(i++, obj.getIdFrom());
            st.setInt(i++, obj.getIdTo());

            int rowsAmount = st.executeUpdate();
            if(rowsAmount != 1) {
                throw new ServiceException("Strange amount of rows during delete " + rowsAmount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }


    @Override
    public List<Distance> getAll() throws ServiceException {

        try(Connection conn = dao.getConnection()) {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM railway.distance;");
            ResultSet rs = st.executeQuery();
            return parse(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }


    public List<Distance> parse (ResultSet rs) {

        List<Distance> list = new ArrayList<>();

        try {
            while (rs.next()) {
                Distance d = new Distance(
                        rs.getInt("st_id_from"),
                        rs.getInt("st_id_to"),
                        rs.getInt("d_distance"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
