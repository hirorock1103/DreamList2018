package com.example.user.dreamlist2018;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FgTopPage extends Fragment {

    //test用
    Button bt;
    Button bt_open_test;

    //top page
    ImageButton bt_add_dream;
    ImageButton bt_dream_list;
    FgNoticeListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Bundle bundle = getArguments();
        String mode = bundle.getString("mode");

        View view;

        if(mode == "TEST"){

            Common.toast(getContext(), "TEST MODE");

            //test用
            view = inflater.inflate(R.layout.f_reg_2, container, false);
            bt = view.findViewById(R.id.bt_opn_dialog);
            bt_open_test = view.findViewById(R.id.bt_open_test);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog();
                }
            });
            bt_open_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openTestDialog();
                }
            });

        }else{

            //top
            view = inflater.inflate(R.layout.f_top, container, false);

            bt_add_dream = view.findViewById(R.id.bt_add_dream);
            bt_add_dream.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog();
                }
            });
            bt_dream_list = view.findViewById(R.id.bt_top_dream);
            bt_dream_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Common.log("clicked");
                    //フラグメントを変更するためActivityにクリックｅｖｅｎｔを通知する
                    listener.notice();//notice後はフラグメントを差し替える
                }
            });

        }

        return view;

    }

    //For test
    private void openDialog(){

        Dialog1 dialog = new Dialog1();
        dialog.setTargetFragment(FgTopPage.this, 37);
        dialog.show(getActivity().getSupportFragmentManager(), "fragment");

    }

    //For test
    private void openTestDialog(){
        Dialog2_test dialog = new Dialog2_test();
        dialog.setTargetFragment(FgTopPage.this, 38);
        dialog.show(getActivity().getSupportFragmentManager(), "fragment");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (FgNoticeListener)context;
        }catch(ClassCastException e){
            Common.log("ActivityにFgNoticeListenerリスナをセットしてください！");
        }

    }


    //Activity側への通知
    public interface FgNoticeListener{
        public void notice();
    }


}

