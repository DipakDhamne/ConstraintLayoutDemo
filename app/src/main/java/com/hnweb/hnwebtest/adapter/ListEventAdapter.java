package com.hnweb.hnwebtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hnweb.hnwebtest.R;
import com.hnweb.hnwebtest.databinding.LayoutListItemBinding;
import com.hnweb.hnwebtest.model.Response;
import com.hnweb.hnwebtest.view.MapsActivity;

import java.util.List;

public class ListEventAdapter extends RecyclerView.Adapter<ListEventAdapter.ListItemViewHolder> {

    private Context context;
    private List<Response> list;

    public ListEventAdapter(Context context, List<Response> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_list_item, parent, false);


        return new ListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder listItemViewHolder, int position) {
        final Response item = list.get(position);
        listItemViewHolder.bind(item);
        //Glide.with(context).load(item.getEventFlyer()).placeholder(R.mipmap.ic_launcher).into((ImageView) listItemViewHolder.itemView.findViewById(R.id.ivImg));
        // Glide.with(context).load(item.getColor()).placeholder(R.mipmap.ic_launcher).into((ImageView) listItemViewHolder.itemView.findViewById(R.id.ivStar));

        listItemViewHolder.itemView.findViewById(R.id.parentLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("lat", item.getLat());
                intent.putExtra("long", item.getLong1());
                intent.putExtra("venu", item.getEventVenue());
                intent.putExtra("title", item.getEventName());
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {

        public LayoutListItemBinding bindingList;

        public ListItemViewHolder(@NonNull LayoutListItemBinding itemView) {
            super(itemView.getRoot());
            this.bindingList = itemView;
        }

        public void bind(Response item) {
            bindingList.setModel(item);
            bindingList.executePendingBindings();
        }
    }


}
