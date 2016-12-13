package dynamic.proxy;

import stat.proxy.DataSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Created by iminright on 2016/12/1.
 * 动态代理的jdk方式
 */
public class DynamicConnectionProxy implements InvocationHandler {

    /**
     * 代理对象无论动态代理还是静态代理，都需要持有被代理的对象去做真正要做的事情
     */
    private Connection connection ;

    public DynamicConnectionProxy(Connection connection){
        this.connection = connection ;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Connection.class.isAssignableFrom(proxy.getClass()) && "close".equals(method.getName())){
            DataSource.getDataSource().recoveryConnection(connection);
        }
        return null;
    }

    public Connection getProxy(){
        Connection connection = (Connection) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[]{Connection.class}, this) ;
        return connection ;
    }

}
