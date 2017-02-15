package kingja.kdialog;

import android.content.Context;
import android.view.View;

/**
 * Description：TODO
 * Create Time：2017/2/1520:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProgressDialog extends BaseDialog {
    public ProgressDialog(Context context) {
        super(context);
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_progress;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void childClick(View v) {

    }
}
