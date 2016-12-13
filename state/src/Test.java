/**
 * Created by iminright on 2016/12/13.
 */
public class Test {

    public static void main(String args[]) throws Exception{
        //服务 A begin
        //创建了一个订单
        Order order = new Order() ;
        //此时订单处于init状态
        order.setState(new OrderInitState());
        System.out.println(order);
        //插入数据库
        //do insert

        //服务 A end

        //服务 B begin  客户发起支付请求
        //query order from db
        //do query
        //支付状态流转
        Context<Order> orderContext = new Context<Order>(order, "支付") ;
        order.getState().next(orderContext);
        System.out.println(order);
        //订单状态已经被改变
        //支付操作完成，入库
        //do insert order

        //服务 B end

        //服务 C begin  客户取消订单，发起退款请求
        //query order from db
        //do query
        //取消状态流转
        Context<Order> orderContext2 = new Context<Order>(order, "取消") ;
        order.getState().next(orderContext2);
        System.out.println(order);
        //订单状态已经被改变
        //取消操作完成，入库
        //do insert order

        //服务 C end

    }

}
