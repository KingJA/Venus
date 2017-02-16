package kingja.kpopupwindow;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Description：TODO
 * Create Time：2017/2/1622:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ListPop extends BasePopupwindow {
    public ListPop(Activity activity) {
        super(activity);
    }

    @Override
    protected View getPopView() {
        return View.inflate(activity,R.layout.pop_list,null);
    }

    @Override
    protected void initView() {
        ListView lv_pop = (ListView) popView.findViewById(R.id.lv_pop);
        PopAdapter popAdapter = new PopAdapter(activity, Arrays.asList("Android", "Java", "MySQL", "HTML5"));
        lv_pop.setAdapter(popAdapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getPopWidth() {
        return 0;
    }
}
