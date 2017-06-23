package com.vovatkach2427gmail.eyeexercises.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vovatkach2427gmail.eyeexercises.model.StatisticsModel;
import com.vovatkach2427gmail.eyeexercises.R;

import java.util.List;

/**
 * Created by vovat on 03.06.2017.
 */

public class RVAdapterStatistics extends RecyclerView.Adapter<RVAdapterStatistics.StatisticsViewHolder> {
    List<StatisticsModel> list;
    Context context;
    int index;
    public RVAdapterStatistics(List<StatisticsModel> list, Context context)
    {
        this.list=list;
        this.context=context;
        index=list.size()-1;
    }

    public static class StatisticsViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public StatisticsViewHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.tvDateStatistics);
        }
    }
//--------------------------------------------------
    @Override
    public StatisticsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_statistics, parent, false);
        StatisticsViewHolder pvh = new StatisticsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(StatisticsViewHolder holder, int position) {
                holder.tv.setText(list.get(index-position).getDate().toString());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void clear()
    {
        list.clear();
    }
}
