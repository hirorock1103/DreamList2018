package com.example.user.dreamlist2018;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DashBoard {

    public static class Topics{

        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class TopicCreater{

        private Context context;

        TopicCreater(Context context){
            this.context = context;
        }

        public List<Topics> getTopics(){

            List<Topics> list = new ArrayList<>();
            DreamManager manager = new DreamManager(context);
            List<Dream> dreamList = manager.getDreamList();

            for (Dream dream : dreamList){
                Topics topics = new Topics();
                topics.setTitle( dream.getCreatedate() + "/" + dream.getTitle().toString() + "が登録されたよ");
                list.add(topics);
            }

            return list;

        }

    }

}
