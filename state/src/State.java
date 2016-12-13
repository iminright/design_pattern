/**
 * Created by iminright on 2016/12/13.
 */
public interface State {

    void prev(Context context) throws Exception;

    void next(Context context) throws Exception;

    String show() ;

}
