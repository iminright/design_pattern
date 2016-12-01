import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by iminright on 2016/12/1.
 * 线程不安全的单例模式
 */
public class ThreadNotSafeSingleton {

    public static ThreadNotSafeSingleton singleton ;

    /**
     * 禁止对外提供无参构造方法
     */
    private ThreadNotSafeSingleton(){} ;

    /**
     * 在单线程的情况下可以提供可靠的单例
     * @return
     * 改进方法是可以对此方法做同步操作或者使用double check，不过考虑jvm的指令重排，其实也不是特别靠谱，而且代码太复杂，垃圾
     */
    public static ThreadNotSafeSingleton getSingleton(){
        if(singleton == null){
            singleton = new ThreadNotSafeSingleton() ;
        }
        return singleton ;
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
        final Set<String> singletons = Collections.synchronizedSet(new HashSet<String>()) ;
        final Lock lock = Lock.getInstance() ;
        lock.setLock(true);
        ExecutorService service = Executors.newCachedThreadPool() ;
        for(int i = 0; i < 100 ; i++){
            service.execute(new Runnable() {
                public void run(){
                    while(true){
                        if(!lock.getLock()){
                            String singleton = ThreadNotSafeSingleton.getSingleton().toString() ;
                            singletons.add(singleton) ;
                        }
                    }
                }
            });
        }
        Thread.sleep(1000);
        lock.setLock(false);
        Thread.sleep(20000);
        System.out.println(singletons) ;
        service.shutdown();
        System.exit(0);
    }

}

class Lock{

    public static Lock getInstance(){
        return new Lock() ;
    }

    private boolean lock ;

    public void setLock(boolean lock){
        this.lock = lock ;
    }

    public boolean getLock(){
        return this.lock ;
    }
}
