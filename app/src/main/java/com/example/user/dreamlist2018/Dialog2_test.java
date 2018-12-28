package com.example.user.dreamlist2018;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Dialog2_test extends AppCompatDialogFragment {

    DreamManager manager;
    TextView textViewList;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view  = inflater.inflate(R.layout.f_testpage, null);

        textViewList = view.findViewById(R.id.tv_list);
        manager = new DreamManager(getContext());
        List<Dream> list = manager.getDreamList();

        StringBuilder sbuilder = new StringBuilder();
        for(Dream dream : list){
            sbuilder.append("ID:" + dream.getId()+"\n");
            sbuilder.append("title:" + dream.getTitle()+"\n");
            sbuilder.append("detail:" + dream.getDescription()+"\n");
            sbuilder.append("deadline:" + dream.getDeadline()+"\n");
            sbuilder.append("create:" + dream.getCreatedate()+"\n\n");
        }

        textViewList.setText(sbuilder.toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view)
                .setTitle("TEST")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        Dialog dialog = builder.create();

        return dialog;
    }
}
