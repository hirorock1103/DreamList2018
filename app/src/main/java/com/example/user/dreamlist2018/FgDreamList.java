package com.example.user.dreamlist2018;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class FgDreamList extends Fragment {

    RecyclerView recyclerView;
    DreamManager manager;
    DreamListAdapter adapter;
    FgNoticeListener listener;

    //
    int selectedDreamId;

    public interface FgNoticeListener{
        public void notice2(int dreamId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (FgNoticeListener)context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.f_dream_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        manager = new DreamManager(getContext());
        List<Dream> list = manager.getDreamList();

        adapter = new DreamListAdapter(list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;

    }

    public class DreamListViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        PieView pieView;
        TextView deadline;
        ImageButton btDetail;
        ImageView image;
        ConstraintLayout layout;

        public DreamListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_dreamtitle);
            pieView = itemView.findViewById(R.id.pieView);
            deadline = itemView.findViewById(R.id.deadline);
            btDetail = itemView.findViewById(R.id.bt_detail);
            image = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.layout);
        }

    }

    public class DreamListAdapter extends RecyclerView.Adapter<DreamListViewHolder>{

        List<Dream> list;

        DreamListAdapter(List<Dream> list){
            this.list = list;
        }

        public void setList(List<Dream> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public DreamListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.list_view_item3, viewGroup, false);
            //View view = inflater.inflate(R.layout.list_view_item2, null);

            DreamListViewHolder holder = new DreamListViewHolder(view);
            return holder;

        }

        @Override
        public void onBindViewHolder(@NonNull DreamListViewHolder holder, int i) {

            //SET LISTENER TO LAYOUT
            setListener(holder.layout, i);

            holder.title.setText(list.get(i).getTitle());
            holder.deadline.setText(list.get(i).getDeadline());
            try{
                Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(i).getImage(), 0, list.get(i).getImage().length);
                holder.image.setImageBitmap(bitmap);
            }catch(Exception e){
                Common.log(e.getMessage());
            }
            DreamManager manager = new DreamManager(getContext());
            List<Todo> todoList = manager.getTodoByDreamId(list.get(i).getId());
            List<Todo> finishedTodoList = manager.getFinishedTodoByDreamId(list.get(i).getId());

            float percentage = 0;
            if(todoList.size() > 0){
                percentage = ((float)finishedTodoList.size() / (float)todoList.size()) * 100;
            }

            holder.pieView.setPercentageTextSize((float)10);
            holder.pieView.setPercentage(percentage);
            PieAngleAnimation animation = new PieAngleAnimation(holder.pieView);
            animation.setDuration(1000);
            holder.pieView.startAnimation(animation);

            holder.layout.setTag(String.valueOf(list.get(i).getId()) );
            registerForContextMenu(holder.layout);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private void setListener(ConstraintLayout layout, int i){

            final int position = i;

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Common.log("click:" + position);
                    Common.log(list.get(position).getTitle() + "("+list.get(position).getId()+")");
                    listener.notice2(list.get(position).getId());
                }
            });


        }

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        selectedDreamId = Integer.parseInt(v.getTag().toString());
        getActivity().getMenuInflater().inflate(R.menu.menu_option_3, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.option1:
                //action
                openDialog(selectedDreamId);
                //reloadView
                //reloadView();

                break;
            case R.id.option2:
                //action
                manager.deleteDream(selectedDreamId);
                //reloadView
                reloadView();

                break;

            default:
                return super.onContextItemSelected(item);

        }

        return true;
    }

    public void reloadView(){
        List<Dream> list = manager.getDreamList();
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    private void openDialog(int dreamId){

        Dialog1 dialog = new Dialog1();
        dialog.setTargetFragment(FgDreamList.this, 40);
        Bundle bundle = new Bundle();
        bundle.putInt("dreamId", dreamId);
        dialog.setArguments(bundle);
        dialog.show(getActivity().getSupportFragmentManager(), "fragment");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Common.log("request:" + requestCode);
        if(requestCode == 40){
            if(resultCode == Activity.RESULT_OK){
                reloadView();
            }
        }

    }
}
