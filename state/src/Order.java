/**
 * Created by iminright on 2016/12/13.
 */

public class Order {

    private State state ;


    public State getState(){
        return this.state ;
    }

    public void setState(State state){
        this.state = state ;
    }

    public String toString(){
        return this.getState().show();
    }

}
