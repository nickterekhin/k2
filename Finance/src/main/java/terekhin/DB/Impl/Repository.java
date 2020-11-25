package terekhin.DB.Impl;

import terekhin.DB.Adapter.DBAdapter;
import terekhin.DB.IRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class Repository<T> extends DBAdapter implements IRepository<T> {

    protected String INSERT_LINE;

    @Override
    public void create(T obj) throws Exception {

        if (obj == null) return;

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(INSERT_LINE, java.sql.Statement.RETURN_GENERATED_KEYS);
            setInsertArguments(statement, obj);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
                setId(obj, rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Exception while execute DAOImpl.create()");
            e.printStackTrace();
            throw new Exception(e);
        }
        finally {
            this.disconnect();
        }
    }

    @Override
    public void update(T obj) {

    }

    @Override
    public T findById(long Id) {
        return null;
    }

    @Override
    public void delete(T obj) {

    }

    @Override
    public void delete(long Id) {

    }

    @Override
    public List<T> findAll() throws SQLException {
        return null;
    }

    protected int getInsertId() throws SQLException {
        Statement s = connection.createStatement();
        ResultSet insertedIdRes = s.executeQuery("SELECT LAST_INSERT_ID() AS n");
        insertedIdRes.next();
        return insertedIdRes.getInt(1);
    }
    protected abstract void setId(T type, int id);
    protected abstract void setInsertArguments(PreparedStatement statement, T type) throws SQLException;
}
