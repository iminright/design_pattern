/**
 * Created by iminright on 2016/12/13.
 */
public class OrderPaidState implements State {

    public void prev(Context context) throws Exception {
        throw new Exception("该状态不可回退") ;
    }

    public void next(Context context) throws Exception{
        if("取消".equals(context.getOperation())){
            Order order = (Order)context.getT() ;
            order.setState(new OrderCancelState());
        }else{
            throw new Exception("不支持的操作") ;
        }
    }

    public String show() {
        return "已支付状态";
    }
}
