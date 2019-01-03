package com.example.user.dreamlist2018;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FgTopPage.FgNoticeListener, FgDreamList.FgNoticeListener {


    FragmentManager fragmentManager;
    ImageButton bt_header_back;
    String mode = "TESTS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_header_back = findViewById(R.id.bt_header_back);
        setListener();

        fragmentManager = getSupportFragmentManager();
        List<Fragment> fList = fragmentManager.getFragments();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(fList.size() == 0){

            Bundle bundle = new Bundle();
            bundle.putString("mode", mode);

            Fragment fragment = new FgTopPage();
            fragment.setArguments(bundle);

            //Default Fragment
            transaction.add(R.id.fragment_area, fragment);
            //transaction.add()

            transaction.commit();

        }

    }

    private void setListener(){

        bt_header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                List<Fragment> list = fragmentManager.getFragments();
                for (Fragment fragment : list){
                    transaction.remove(fragment);
                }

                Bundle bundle = new Bundle();
                bundle.putString("mode", mode);

                Fragment fragment = new FgTopPage();
                fragment.setArguments(bundle);

                //Default Fragment
                transaction.add(R.id.fragment_area, fragment);
                //transaction.add()

                transaction.commit();

            }
        });

    }

    //notice from fragment
    @Override
    public void notice() {

        Common.log("notice from Fragment!");
        Fragment newFragment = new FgDreamList();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> list = fragmentManager.getFragments();
        for (Fragment fragment : list){
            transaction.remove(fragment);
        }

        Fragment fragment = new FgTopPage();
        //Default Fragment
        transaction.add(R.id.fragment_area, newFragment);
        //transaction.add()

        transaction.commit();

    }

    @Override
    public void notice2(int dreamId) {
        Common.log("notice2:" + dreamId);

        Fragment newFragment = new FgDreamDetail();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> list = fragmentManager.getFragments();
        for (Fragment fragment : list){
            transaction.remove(fragment);
        }

        Bundle bundle = new Bundle();
        bundle.putInt("dreamId", dreamId);

        newFragment.setArguments(bundle);

        //Default Fragment
        transaction.add(R.id.fragment_area, newFragment);
        //transaction.add()
        transaction.commit();

    }
}
