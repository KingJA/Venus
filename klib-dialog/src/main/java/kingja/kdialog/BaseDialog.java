package kingja.kdialog;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;


public abstract class BaseDialog extends AlertDialog implements View.OnClickListener {
    protected Context context;
    protected final float DEFAULT_WIDTH_RATIO = 0.8f;

    protected BaseDialog(Context context) {
        super(context, R.style.CustomAlertDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        initEvent();
        initData();

    }

    protected abstract int getContentView();

    public abstract void initView();

    public abstract void initEvent();

    public abstract void initData();

    public abstract void childClick(View v);

    @Override
    public void onClick(View v) {
        this.dismiss();
        childClick(v);
    }

}
