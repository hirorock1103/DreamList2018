package com.example.user.dreamlist2018;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FgTestPage extends Fragment {

    DreamManager manager;
    TextView textViewList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.f_testpage, container, false);
        textViewList = view.findViewById(R.id.tv_list);

        manager = new DreamManager(getContext());
        List<Dream> list = manager.getDreamList();

        StringBuilder builder = new StringBuilder();
        for(Dream dream : list){
            builder.append("ID:" + dream.getId()+"\n");
            builder.append("title:" + dream.getTitle()+"\n");
            builder.append("detail:" + dream.getDescription()+"\n");
            builder.append("deadline:" + dream.getDeadline()+"\n");
            builder.append("create:" + dream.getCreatedate()+"\n\n");
        }

        textViewList.setText(builder.toString());

        return view;

    }
}
