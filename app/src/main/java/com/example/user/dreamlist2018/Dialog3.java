package com.example.user.dreamlist2018;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Dialog3 extends AppCompatDialogFragment {

    Button btnAdd;
    TextView todoTitle;
    DreamManager manager;
    String mode = "insert";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        Bundle bundle = getArguments();
        final int dreamId =bundle.getInt("dreamId");
        final int todoId =bundle.getInt("todoId");

        if(todoId > 0){
            //編集モード
            mode = "edit";
        }

        //共通
        AlertDialog dialog;
        manager = new DreamManager(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.f_reg_3, null);
        todoTitle = view.findViewById(R.id.input_todo);
        btnAdd = view.findViewById(R.id.bt_add);

        setBtnListener(mode,dreamId,todoId);

        //共通
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(view)
                .setTitle("ToDo登録")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        dialog = builder.create();

        return dialog;

    }


    private void setBtnListener(String mode, final int dreamId, final int todoId){

        if(mode == "insert"){

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(todoTitle.getText().toString().isEmpty()){
                        Common.toast(getContext(), "タイトルは必須です！");
                    }else{

                        Todo todo = new Todo();
                        todo.setTitle(todoTitle.getText().toString());
                        todo.setDreamId(dreamId);

                        DreamManager manager = new DreamManager(getContext());

                        long insertId;
                        insertId = manager.addTodo(todo);

                        if(insertId > 0){
                            Common.toast(getContext(), "登録しました。");
                            Intent result = new Intent();
                            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,result );
                        }
                    }
                }
            });

        }else{

            // edit mode
            final Todo todo =manager.getTodoById(todoId);
            todoTitle.setText(todo.getTitle());

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(todoTitle.getText().toString().isEmpty()){
                        Common.toast(getContext(), "タイトルは必須です！");
                    }else{

                        todo.setTitle(todoTitle.getText().toString());

                        DreamManager manager = new DreamManager(getContext());

                        long insertId;
                        insertId = manager.updateTodo(todo);

                        if(insertId > 0){
                            Common.toast(getContext(), "更新しました。");
                            Intent result = new Intent();
                            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,result );
                        }

                    }

                }
            });


        }

    }
}
