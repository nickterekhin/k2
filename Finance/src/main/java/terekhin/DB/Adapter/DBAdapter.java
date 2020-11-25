package terekhin.DB.Adapter;

import terekhin.Services.Variables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBAdapter {
    protected Connection connection;
    protected String tableName;

    protected void connect()
    {
        try{
            connection = DriverManager.getConnection(Variables.DBUrl,Variables.DBUser,Variables.DBPassword);
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }

    protected void disconnect()
    {
        try{
            if(connection!=null)
            {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
