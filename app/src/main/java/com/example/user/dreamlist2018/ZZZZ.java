package com.example.user.dreamlist2018;

import android.content.ContentValues;
import android.content.Context;

import java.util.Date;
import java.util.List;

public class ZZZZ {


    public void Test1(Context context){

        DreamManager manager = new DreamManager(context);

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
