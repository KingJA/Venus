package kingja.kdialog;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DoubleDialog extends BaseDialog {
    private String message;
    private String leftBtnStr;
    private String rightBtnStr;
    private TextView tv_message;
    private RelativeLayout rl_btn_left;
    private RelativeLayout rl_btn_right;
    private TextView tv_btn_left;
    private TextView tv_btn_right;
    private float widthRatio;
    private OnDoubleClickListener onDoubleClickListener;
    private LinearLayout ll_root;

    public DoubleDialog(Context context, String message, String leftBtnStr, String rightBtnStr,float widthRatio, OnDoubleClickListener onDoubleClickListener) {
        super(context);
        this.message = message;
        this.leftBtnStr = leftBtnStr;
        this.rightBtnStr = rightBtnStr;
        this.widthRatio = widthRatio;
        this.onDoubleClickListener = onDoubleClickListener;
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_double;
    }

    @Override
    public void initView() {
        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_btn_left = (TextView) findViewById(R.id.tv_btn_left);
        tv_btn_right = (TextView) findViewById(R.id.tv_btn_right);
        rl_btn_left = (RelativeLayout) findViewById(R.id.rl_btn_left);
        rl_btn_right = (RelativeLayout) findViewById(R.id.rl_btn_right);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);
    }

    @Override
    public void initEvent() {
        rl_btn_left.setOnClickListener(this);
        rl_btn_right.setOnClickListener(this);
    }

    @Override
    public void initData() {
        widthRatio=widthRatio==0?DEFAULT_WIDTH_RATIO:widthRatio;
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams((int) (widthRatio*Util.getScreenWidth(context)), ViewGroup.LayoutParams.WRAP_CONTENT);
        ll_root.setLayoutParams(layoutParams);
        tv_message.setText(message);
        tv_btn_left.setText(leftBtnStr);
        tv_btn_right.setText(rightBtnStr);
    }


    @Override
    public void childClick(View v) {
        if (onDoubleClickListener != null) {
            if (v.getId() == R.id.rl_btn_left) {
                onDoubleClickListener.onLeftClick();
            }
            if (v.getId() == R.id.rl_btn_right) {
                onDoubleClickListener.onRightClick();
            }
        }
    }

    public interface OnDoubleClickListener {
        void onLeftClick();

        void onRightClick();
    }

    public static class Builder {
        private float widthRatio;
        private String message;
        private String leftBtnStr;
        private String rightBtnStr;
        private OnDoubleClickListener onDoubleClickListener;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setLeftBtnStr(String leftBtnStr) {
            this.leftBtnStr = leftBtnStr;
            return this;
        }

        public Builder setRightBtnStr(String rightBtnStr) {
            this.rightBtnStr = rightBtnStr;
            return this;
        }

        public Builder setWidthRatio(float widthRatio) {
            this.widthRatio = widthRatio;
            return this;
        }

        public Builder setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener) {
            this.onDoubleClickListener = onDoubleClickListener;
            return this;
        }

        public DoubleDialog create() {
            DoubleDialog doubleDialog = new DoubleDialog(context, message, leftBtnStr, rightBtnStr,widthRatio, onDoubleClickListener);
            return doubleDialog;
        }
    }
}
