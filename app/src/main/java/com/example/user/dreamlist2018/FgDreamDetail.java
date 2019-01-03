package com.example.user.dreamlist2018;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class FgDreamDetail extends Fragment {

    //View
    TextView title;
    TextView deadline;
    TextView todo_done_count;
    TextView todo_count;
    PieView pieView;
    Button add_todo;
    ImageView image;

    //RecyclerView
    RecyclerView recyclerView1;
    MyAdapter1 adapter1;

    //else
    DreamManager manager;
    int dreamId;
    int selectedTodoId = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Bundle bundle = getArguments();
        dreamId = bundle.getInt("dreamId");

        View view = inflater.inflate(R.layout.f_dream_detail, container, false);

        title = view.findViewById(R.id.text_dreamtitle);
        todo_done_count = view.findViewById(R.id.todo_done_count);
        todo_count = view.findViewById(R.id.todo_count);
        deadline = view.findViewById(R.id.deadline);
        pieView = view.findViewById(R.id.pieView);
        recyclerView1 = view.findViewById(R.id.recycler_view_1);
        add_todo = view.findViewById(R.id.add_todo);
        image = view.findViewById(R.id.image);

        add_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open dialog
                Common.log("add Todo! dreamId" + dreamId);
                openDialog(dreamId,0);
            }
        });

        manager = new DreamManager(getContext());

        Dream dream = manager.getDream(dreamId);

        title.setText(dream.getTitle());
        deadline.setText(dream.getDeadline());

        try{
            Bitmap bitmap = BitmapFactory.decodeByteArray(dream.getImage(), 0, dream.getImage().length);
            image.setImageBitmap(bitmap);
        }catch(Exception e){
            Common.log(e.getMessage());
        }


        //recycler
        List<Todo> list = manager.getTodoByDreamId(dreamId);
        List<Todo> listFinished = manager.getFinishedTodoByDreamId(dreamId);
        List<Todo> listNotFinished = manager.getNotFinishedTodoByDreamId(dreamId);

        float percentage = 0;
        if(list.size() > 0){
            percentage = ((float)listFinished.size() / (float)list.size()) * 100;
        }

        pieView.setPercentage(percentage);
        pieView.setPercentageTextSize((float)20);
        PieAngleAnimation animation = new PieAngleAnimation(pieView);
        animation.setDuration(1000);
        pieView.startAnimation(animation);

        todo_count.setText(String.valueOf(listNotFinished.size()));
        todo_done_count.setText(String.valueOf(listFinished.size()));

        adapter1 = new MyAdapter1(list);

        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setAdapter(adapter1);

        return view;

    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder{

        TextView title;
        ConstraintLayout layout;
        CardView fab;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todo_title);
            layout = itemView.findViewById(R.id.layout);
            fab = itemView.findViewById(R.id.fab);
        }
    }

    public class MyAdapter1 extends RecyclerView.Adapter<MyViewHolder1>{

        List<Todo> list;

        MyAdapter1(List<Todo> list){
            this.list = list;
        }

        public void setList(List<Todo> list){
            this.list = list;
        }

        @NonNull
        @Override
        public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.simple_card,viewGroup,false);
            MyViewHolder1 holder = new MyViewHolder1(view);
            return holder;

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder1 holder, int i) {

            Todo todo = list.get(i);

            if(todo.getIsFinished() == 1){
                holder.title.setText((i+1) + "." +todo.getTitle() + "(終了)");
            }else{
                holder.title.setText((i+1) + "." +todo.getTitle());
            }

            //set tag for contextmenu
            holder.layout.setTag( String.valueOf(todo.getId()));

            //context menu when layout is clicked
            registerForContextMenu(holder.layout);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        selectedTodoId = Integer.parseInt(v.getTag().toString());
        Todo todo = manager.getTodoById(selectedTodoId);

        if(todo.getIsFinished() == 1){
            getActivity().getMenuInflater().inflate(R.menu.menu_option_2, menu);
        }else{
            getActivity().getMenuInflater().inflate(R.menu.menu_option_1, menu);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        switch(item.getItemId()){

            case R.id.option1:

                openDialog(dreamId, selectedTodoId);
                //edit
                break;

            case R.id.option2:

                //set isFinished 1
                Todo todo = manager.getTodoById(selectedTodoId);
                todo.setIsFinished(1);
                manager.updateTodo(todo);
                reloadTodoView();
                break;

            case R.id.option3:

                //delete
                manager.deleteTodo(selectedTodoId);
                reloadTodoView();
                break;

            case R.id.option4:

                //set isFinished 1
                Todo todo2 = manager.getTodoById(selectedTodoId);
                todo2.setIsFinished(0);
                manager.updateTodo(todo2);
                reloadTodoView();
                break;
            default:
        }

        return true;

    }

    private void openDialog(int dreamId, int todoId){

        Dialog3 dialog3 = new Dialog3();
        dialog3.setTargetFragment( FgDreamDetail.this, 39);
        Bundle bundle = new Bundle();
        bundle.putInt("dreamId", dreamId);
        bundle.putInt("todoId", todoId);
        dialog3.setArguments(bundle);
        dialog3.show(getActivity().getSupportFragmentManager(), "fragment");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 39){
            //dialog 1 からの結果
            Common.toast(getContext(), "result:" + resultCode);
            if(resultCode == Activity.RESULT_OK){

                Common.log("from dialog");

                reloadTodoView();


            }
        }

    }

    //reloadview
    public void reloadTodoView(){

        //recycler
        List<Todo> list = manager.getTodoByDreamId(dreamId);
        List<Todo> listFinished = manager.getFinishedTodoByDreamId(dreamId);
        List<Todo> listNotFinished = manager.getNotFinishedTodoByDreamId(dreamId);

        float percentage = 0;
        if(list.size() > 0){
            percentage = ((float)listFinished.size() / (float)list.size()) * 100;
        }

        pieView.setPercentage(percentage);
        pieView.setPercentageTextSize((float)20);
        PieAngleAnimation animation = new PieAngleAnimation(pieView);
        animation.setDuration(1000);
        pieView.startAnimation(animation);

        adapter1.setList(list);
        //adapter1.notifyDataSetChanged();
        recyclerView1.setAdapter(adapter1);
        todo_count.setText(String.valueOf(listNotFinished.size()));
        todo_done_count.setText(String.valueOf(listFinished.size()));
    }

}
