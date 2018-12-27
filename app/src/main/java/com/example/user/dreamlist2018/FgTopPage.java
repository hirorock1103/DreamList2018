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

    Button bt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.f_reg_2, container, false);
        bt = view.findViewById(R.id.bt_opn_dialog);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        return view;

    }

    private void openDialog(){

        Dialog1 dialog = new Dialog1();
        dialog.show(getActivity().getSupportFragmentManager(), "fragment");

    }
}
