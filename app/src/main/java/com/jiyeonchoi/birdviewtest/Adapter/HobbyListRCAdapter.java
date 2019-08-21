package com.jiyeonchoi.birdviewtest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jiyeonchoi.birdviewtest.ListItem.HobbyListItem;
import com.jiyeonchoi.birdviewtest.R;
import java.util.ArrayList;

public class HobbyListRCAdapter extends RecyclerView.Adapter<HobbyListRCAdapter.ViewHolder> {
    Context context;
    ArrayList<HobbyListItem> items;
    int item_layout;

    public HobbyListRCAdapter(Context context, ArrayList<HobbyListItem> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout=item_layout;
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

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hobby;

        public ViewHolder(View itemView) {
            super(itemView);

            hobby = itemView.findViewById(R.id.hobby);

        }
    }
}
