package com.example.user.dreamlist2018;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;

public class Dialog1 extends AppCompatDialogFragment {

    EditText inputDream;
    EditText inputDetails;
    EditText inputDeadline;
    RadioGroup radioGroup;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.f_reg_1, null);

         //setView
        inputDream = view.findViewById(R.id.input_dream);
        inputDetails = view.findViewById(R.id.input_description);
        inputDeadline = view.findViewById(R.id.input_deadline);
        radioGroup = view.findViewById(R.id.radio_group);
        setRadioListener();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle("title")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

        AlertDialog dialog = builder.create();

        return dialog;

    }

    private void setRadioListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){

                    case R.id.radio_1:
                        Common.log("radio_1");
                        inputDeadline.setText("radio1");
                        break;
                    case R.id.radio_2:
                        Common.log("radio_2");
                        inputDeadline.setText("");
                        break;
                    case R.id.radio_3:
                        Common.log("radio_3");
                        inputDeadline.setText(Common.formatDate(new Date(), Common.DATE_FORMAT_SAMPLE_1));
                        break;
                    default:
                        Common.log("default");
                }
            }
        });
    }
}
