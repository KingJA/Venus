package com.kingja.venus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import kingja.kpopupwindow.PopAdapter;

/**
 * Description：TODO
 * Create Time：2017/2/1620:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PopupwindowActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_select_pay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);
        tv_select_pay = (TextView) findViewById(R.id.tv_select_pay);
        tv_select_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.tv_select_pay:
               final ListPopupWindow  mListPop = new ListPopupWindow(this);//Arrays.asList("Android","Java","Android","Android")
               mListPop.setAdapter(new PopAdapter(this, Arrays.asList("Android","Java","Android","Android")));
//               mListPop.setWidth(LayoutParams.WRAP_CONTENT);
//               mListPop.setHeight(LayoutParams.WRAP_CONTENT);
               mListPop.setAnchorView(tv_select_pay);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
               mListPop.setModal(true);//设置是否是模式
               mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       mListPop.dismiss();
                       String item = (String) parent.getItemAtPosition(position);
                       Toast.makeText(PopupwindowActivity.this,item,Toast.LENGTH_SHORT).show();
                   }
               });
               mListPop.show();
               break;
           default:
               break;
        }
    }
}
