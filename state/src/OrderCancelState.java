/**
 * Created by iminright on 2016/12/13.
 */
public class OrderCancelState implements State {

    public void prev(Context context) throws Exception {
        Order order = (Order)context.getT() ;
        order.setState(new OrderInitState());
    }

    public void next(Context context) {

    }

    public String show() {
        return "取消状态";
    }
}
