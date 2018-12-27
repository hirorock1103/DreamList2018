package com.example.user.dreamlist2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DreamManager manager = new DreamManager(this);

        Dream dream = new Dream();
        dream.setTitle("aaa");
        dream.setDescription("dream is ...");

        String deadline = "2018-12-05";
        dream.setDeadline(deadline);
        dream.setCreatedate(Common.formatDate(new Date(), Common.DB_DATE_FORMAT));

        manager.addDream(dream);

        List<Dream> list = manager.getDreamList();

        for (Dream d : list){
            Common.log("id:" + d.getId());
            Common.log("title:" + d.getTitle());
            Common.log("description:" + d.getDescription());
            Common.log("deadline:" + d.getDeadline());
            Common.log("createdate:" + d.getCreatedate());
        }


    }
}
