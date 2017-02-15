package com.kingja.venus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kingja.kdialog.DoubleDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final String TAG = MainActivity.this.getClass().getSimpleName();
    private DoubleDialog doubleDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_double_dialog = (Button) findViewById(R.id.btn_double_dialog);
        btn_double_dialog.setOnClickListener(this);

        doubleDialog = new DoubleDialog.Builder(this)
                .setMessage("你好，请问有什么需要")
                .setLeftBtnStr("取消")
                .setRightBtnStr("确定")
                .setWidthRatio(0.9f)
                .setOnDoubleClickListener(new DoubleDialog.OnDoubleClickListener() {
                    @Override
                    public void onLeftClick() {
                        Log.e(TAG, "onLeftClick: ");
                    }

                    @Override
                    public void onRightClick() {
                        Log.e(TAG, "onRightClick: ");
                    }
                })
                .create();


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.btn_double_dialog:
               doubleDialog.show();
                break;
           default:
               break;
        }
    }
}
