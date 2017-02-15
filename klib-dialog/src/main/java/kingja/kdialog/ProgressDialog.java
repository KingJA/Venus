package kingja.kdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Description：TODO
 * Create Time：2017/2/1520:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProgressDialog extends BaseDialog {
    private String title;
    private Orientation orientation;
    private TextView tv_progress_title;

    public ProgressDialog(Context context, String title, Orientation orientation) {
        super(context);
        this.title = title;
        this.orientation = orientation;
    }

    @Override
    protected int getContentView() {
        return orientation==Orientation.vertical?R.layout.dialog_progress_vertical:R.layout.dialog_progress_horizontal;
    }

    @Override
    public void initView() {
        tv_progress_title = (TextView) findViewById(R.id.tv_progress_title);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        tv_progress_title.setText(title);
    }

    @Override
    public void childClick(View v) {

    }
    public static class Builder{
        private Context context;
        private String title;
        private Orientation orientation;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setOrientation(Orientation orientation) {
            this.orientation = orientation;
            return  this;
        }

        public AlertDialog create() {
            AlertDialog dialog=new ProgressDialog(context,title,orientation);
            return  dialog;
        }
    }

    public enum Orientation {
        horizontal, vertical
    }
}
