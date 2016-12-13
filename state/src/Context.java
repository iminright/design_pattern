/**
 * Created by iminright on 2016/12/13.
 *
 * 状态模式实际上是将状态切换的行为委托给状态自己处理，避免了零碎的ifelse代码去处理状态的转换
 */
public class Context<T> {

    private T t ;

    private String operation ;

    public Context(T t, String operation){
        this.t = t ;
        this.operation = operation ;
    }

    public T getT(){
        return this.t ;
    }

    public String getOperation(){
        return this.operation ;
    }

}
