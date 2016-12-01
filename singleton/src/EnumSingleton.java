/**
 * Created by iminright on 2016/12/1.
 *
 * 利用枚举的语言特性，保证只有一个实例
 * 并且枚举类还能当作管理单例子的集合类使用
 * 但是这样做带来了一些轻微的耦合
 * 挺优雅的单例实现，但是用的并不多
 */
public enum EnumSingleton {

    SINGLETON_1 ;

    private Singleton singleton ;

    private EnumSingleton(){
        this.singleton = new Singleton() ;
    }

    public Singleton getSingleton(){
        return this.singleton ;
    }

}

class Singleton{
    protected Singleton(){} ;
}

class TestSingleton{

    public static void main(String args[]){
        System.out.println(EnumSingleton.SINGLETON_1.getSingleton());
    }

}
