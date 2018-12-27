package com.example.user.dreamlist2018;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FgRegDream extends Fragment {

    EditText inputTitle;
    EditText inputDescription;
    EditText inputDeadline;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.f_reg_1, container, false);

        inputTitle = view.findViewById(R.id.input_dream);
        inputDescription = view.findViewById(R.id.input_description);
        inputDeadline = view.findViewById(R.id.input_deadline);


        return view;

    }
}
