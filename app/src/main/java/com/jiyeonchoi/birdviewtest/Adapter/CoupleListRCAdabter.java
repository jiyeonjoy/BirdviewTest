package com.jiyeonchoi.birdviewtest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jiyeonchoi.birdviewtest.Data_VO.CoupleListItem;
import com.jiyeonchoi.birdviewtest.R;
import java.util.ArrayList;
// test
public class CoupleListRCAdabter extends RecyclerView.Adapter<CoupleListRCAdabter.ViewHolder> {
    Context context;
    ArrayList<CoupleListItem> items;

    public CoupleListRCAdabter(Context context, ArrayList<CoupleListItem> items) {
        this.context = context;
        this.items = items;
    }

    public void itemclear() {
        items.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.couple_list_item,parent,false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CoupleListItem item = items.get(position);

        holder.coupleNumber.setText(item.getCoupleNumber());
        holder.firstCouple.setText(item.getFirstCouple());
        holder.secondCouple.setText(item.getSecondCouple());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView coupleNumber;
        TextView firstCouple;
        TextView secondCouple;

        public ViewHolder(View itemView) {
            super(itemView);

            coupleNumber = itemView.findViewById(R.id.coupleNumber);
            firstCouple = itemView.findViewById(R.id.firstCouple);
            secondCouple = itemView.findViewById(R.id.secondCouple);

        }
    }
}
