package com.example.joyho.basiccomponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.joyho.basiccomponents.AlarmDataManager.INDEX_FIELD;
import static com.example.joyho.basiccomponents.AlarmDataManager.TIME;
import static com.example.joyho.basiccomponents.AlarmDataManager.VALUE;

public class AfterLogin extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_login);

        Button btnRefresh = findViewById(R.id.refresh);

        ListView listView  = findViewById(R.id.listView);
        AlarmDataManager db = new AlarmDataManager(this);
        Cursor cursor = db.getAlarmDataCursor();
        List<Map<String,String>> alarmDataList = new ArrayList<>();
        while(cursor.moveToNext()){
            Map<String, String> map = new HashMap<>();
            map.put("date", cursor.getString(cursor.getColumnIndex(TIME)));
            map.put("index", cursor.getString(cursor.getColumnIndex(INDEX_FIELD)));
            map.put("value", cursor.getString(cursor.getColumnIndex(VALUE)));
            alarmDataList.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, alarmDataList, R.layout.item, new String[]{"date", "index", "value"}, new int[]{R.id.date, R.id.index, R.id.value});
        listView.setAdapter(adapter);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // 按下的如果是BACK，同时没有重复
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void refresh(){
        finish();
        Intent intent = new Intent(AfterLogin.this, AfterLogin.class);
        startActivity(intent);
    }
}
