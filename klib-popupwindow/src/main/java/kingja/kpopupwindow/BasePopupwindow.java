package kingja.kpopupwindow;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;


public abstract class BasePopupwindow extends PopupWindow implements OnDismissListener {

    protected Activity activity;
    protected View popView;

    public BasePopupwindow(Activity activity) {
        this.activity = activity;
        // 设置PopupWindow布局
        popView = getPopView();
        this.setContentView(popView);
        //初始化布局
        initView();
        initEvent();
        initData();
        // 设置PopupWindow弹出窗体的宽
        this.setWidth(getPopWidth() == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : getPopWidth());
        // 设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 点击PopupWindow以外区域隐藏
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        // 监听PopupWindow关闭
        this.setOnDismissListener(this);
        // 可以PopupWindow点击
        this.setFocusable(true);
        // 设置PopupWindow出现和消失动画
        // this.setAnimationStyle(R.style.PopupAnimation);
    }


    protected abstract View getPopView();

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract int getPopWidth();


    public void showPopupWindow(View parent, int gravity, int x, int y) {
        if (!this.isShowing()) {
            setAlpha(activity, 0.7f);
            this.showAtLocation(parent, gravity, x, y);

        }

    }

    public void showPopAsDropDown(View anchor) {
        showAsDropDownAnimate(anchor, 0, 0);
    }

    public void showPopAsDropDown(View anchor, int xoff, int yoff) {
        showAsDropDownAnimate(anchor, xoff, yoff);
    }

    private void showAsDropDownAnimate(View anchor, int xoff, int yoff) {
        if (!this.isShowing()) {
            setAlpha(activity, 0.7f);
            this.showAsDropDown(anchor, xoff, yoff);
        }
    }


    private void setAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {
        setAlpha(activity, 1f);
    }


}