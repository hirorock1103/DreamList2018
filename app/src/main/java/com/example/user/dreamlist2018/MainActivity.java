package com.example.user.dreamlist2018;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FragmentManager fragmentManager;

    String mode = "TEST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
