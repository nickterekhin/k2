package terekhin.DB;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    void create(T obj) throws Exception;
    void update(T obj);
    T findById(long Id);
    void delete(T obj);
    void delete(long Id);
    public List<T> findAll() throws SQLException;
}
