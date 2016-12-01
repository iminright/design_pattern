/**
 * Created by iminright on 2016/12/1.
 * 利用jvm的特性，static变量和类绑定，保证只有一个实例
 */
public class StaticSingleton {

    private static final StaticSingleton singleton ;

    static {
        singleton = new StaticSingleton() ;
    }

    public static StaticSingleton getSingleton(){
        return singleton ;
    }

    private StaticSingleton(){} ;

}
