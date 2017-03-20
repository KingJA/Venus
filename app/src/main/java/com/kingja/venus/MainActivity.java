package com.kingja.venus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Description：TODO
 * Create Time：2017/2/1620:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openDialogActivity(View view) {
        startActivity(new Intent(this, DialogActivity.class));
    }

    public void openPopupwindowActivity(View view) {
        startActivity(new Intent(this, PopupwindowActivity.class));
    }
     public void openProgressActivity(View view) {
        startActivity(new Intent(this, ProgressActivity.class));
    }
     public void openMagicActivity(View view) {
        startActivity(new Intent(this, MagicActivity.class));
    }

}
