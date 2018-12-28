package com.example.user.dreamlist2018;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FgTopPage extends Fragment {

    //test用
    Button bt;
    Button bt_open_test;

    //top page


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



        }



        return view;

    }

    private void openDialog(){

        Dialog1 dialog = new Dialog1();
        dialog.show(getActivity().getSupportFragmentManager(), "fragment");

    }

    private void openTestDialog(){
        Dialog2_test dialog = new Dialog2_test();
        dialog.show(getActivity().getSupportFragmentManager(), "fragment");
    }

}
