package com.example.user.dreamlist2018;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class FgDreamList extends Fragment {

    RecyclerView recyclerView;
    DreamManager manager;
    DreamListAdapter adapter;

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

        public DreamListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_dreamtitle);
            pieView = itemView.findViewById(R.id.pieView);
            deadline = itemView.findViewById(R.id.deadline);
            btDetail = itemView.findViewById(R.id.bt_detail);
        }

    }

    public class DreamListAdapter extends RecyclerView.Adapter<DreamListViewHolder>{

        List<Dream> list;

        DreamListAdapter(List<Dream> list



        ){
            this.list = list;
        }

        public void setList(List<Dream> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public DreamListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.list_view_item2, viewGroup, false);
            //View view = inflater.inflate(R.layout.list_view_item2, null);

            DreamListViewHolder holder = new DreamListViewHolder(view);
            return holder;

        }

        @Override
        public void onBindViewHolder(@NonNull DreamListViewHolder holder, int i) {

            holder.title.setText(list.get(i).getTitle());
            holder.deadline.setText(list.get(i).getDeadline());

            holder.pieView.setPercentageTextSize((float)10);
            holder.pieView.setPercentage(40);
            PieAngleAnimation animation = new PieAngleAnimation(holder.pieView);
            animation.setDuration(1000);
            holder.pieView.startAnimation(animation);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

}
