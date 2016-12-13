package stat.proxy;

import dynamic.proxy.DynamicConnectionProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by iminright on 2016/12/1.
 */
public class DataSource {

    private List<Connection> connectionList
            = Collections.synchronizedList(new ArrayList<Connection>()) ;


    private Connection createConnection(){
        Connection connection = null ;
        try {
            connection = DriverManager.getConnection("test") ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection ;
    }

    private DataSource() {
        for(int i = 0; i < 10;i ++){
            connectionList.add(createConnection()) ;
        }
    }

    public Connection getConnection(){
        if(connectionList.size() > 0){
            return new DynamicConnectionProxy(connectionList.remove(0)).getProxy() ;
            //return new ConnectionProxy(connectionList.remove(0)) ;
        }
        return null ;
    }

    public void recoveryConnection(Connection connection){
        this.connectionList.add(connection) ;
    }

    public static DataSource getDataSource(){
        return DataSourceInstance.dataSource ;
    }

    private static class DataSourceInstance{
        private static DataSource dataSource = new DataSource() ;
    }

}
