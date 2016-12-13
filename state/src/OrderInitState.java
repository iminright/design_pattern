/**
 * Created by iminright on 2016/12/13.
 */
public class OrderInitState implements State{

    public void prev(Context context) throws Exception{
        throw new Exception("已经是初始状态") ;
    }

    public void next(Context context) {
        Order order = (Order)context.getT() ;
        if("支付".equals(context.getOperation())){
            order.setState(new OrderPaidState());
        }else if("取消".equals(context.getOperation())){
            order.setState(new OrderCancelState());
        }
    }

    public String show() {
        return "初始化状态";
    }

}
