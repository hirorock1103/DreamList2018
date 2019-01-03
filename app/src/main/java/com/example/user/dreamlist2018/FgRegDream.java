package com.example.user.dreamlist2018;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class FgRegDream extends Fragment {

    EditText inputTitle;
    //EditText inputDescription;
    EditText inputDeadline;

    InputMethodManager inputMethodManager;

    ConstraintLayout layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.f_reg_1, container, false);

        inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        layout = view.findViewById(R.id.layout);

        //set touch event to view
        view = setTouchEventToView(view);

        inputTitle = view.findViewById(R.id.input_dream);
        //inputDescription = view.findViewById(R.id.input_description);
        inputDeadline = view.findViewById(R.id.input_deadline);

        return view;

    }

    private View setTouchEventToView(View view){

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Common.toast(getContext(),"toch");


                inputMethodManager.hideSoftInputFromWindow(layout.getWindowToken(), inputMethodManager.HIDE_NOT_ALWAYS);
                layout.requestFocus();

                return true;
            }
        });

        return view;

    }

}


