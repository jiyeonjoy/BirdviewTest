package com.jiyeonchoi.birdviewtest.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyeonchoi.birdviewtest.CoupleListActivity;
import com.jiyeonchoi.birdviewtest.Data_VO.HobbyListItem;
import com.jiyeonchoi.birdviewtest.HobbyListActivity;
import com.jiyeonchoi.birdviewtest.R;
import java.util.ArrayList;

public class HobbyListRCAdapter extends RecyclerView.Adapter<HobbyListRCAdapter.ViewHolder> {
    Context context;
    ArrayList<HobbyListItem> items;

    public HobbyListRCAdapter(Context context, ArrayList<HobbyListItem> items) {
        this.context = context;
        this.items = items;
    }

    public void itemclear() {
        items.clear();
    }

    @Override
    public HobbyListRCAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.hobby_list_item,parent,false);

        return new HobbyListRCAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final HobbyListRCAdapter.ViewHolder holder, final int position) {
        final HobbyListItem item = items.get(position);

        holder.hobby.setText(item.getHobby());

        holder.itemView.setOnClickListener( v ->
        {
            Intent go_couple_list = new Intent(context.getApplicationContext(), CoupleListActivity.class);
            go_couple_list.putExtra("hobby", item.getHobby());
            context.startActivity(go_couple_list);
        });
    }

    @Override
    public int getItemCount() {
        if(items!=null){
            return items.size();
        } else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hobby;

        public ViewHolder(View itemView) {
            super(itemView);

            hobby = itemView.findViewById(R.id.hobby);

        }
    }
}

