package com.tal.testapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.squareup.picasso.Picasso;
import com.tal.testapp.Activites.VideoActivity;
import com.tal.testapp.Modul.PlayList;
import com.tal.testapp.Modul.PlayListItem;
import com.tal.testapp.R;

import java.util.List;

/**
 * Created by tal on 02/01/17.
 */
public class MyRecyclerAdapter extends ExpandableRecyclerAdapter<MyRecyclerAdapter.MyParentViewHolder, MyRecyclerAdapter.MyChildViewHolder> {
    private LayoutInflater mInflater;
    private Context context;

    public MyRecyclerAdapter(Context context, List<ParentListItem> itemList) {
        super(itemList);
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MyParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.parent_row, viewGroup, false);
        return new MyParentViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.child_row, viewGroup, false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(MyParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        PlayList playList = (PlayList) parentListItem;
        parentViewHolder.parenTitle.setText(playList.getTitle());

    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder childViewHolder, int position, Object childListItem) {
        PlayListItem playListItem = (PlayListItem) childListItem;
        childViewHolder.childTitle.setText(playListItem.getTitle());
        Picasso.with(context).load(playListItem.getImage()).fit().into(childViewHolder.image);
        childViewHolder.itemView.setOnClickListener(new OnChildClickListener(playListItem));
    }

    public class MyParentViewHolder extends ParentViewHolder {

        public TextView parenTitle;

        public MyParentViewHolder(View itemView) {
            super(itemView);

            parenTitle = (TextView) itemView.findViewById(R.id.playlist_name);
        }
    }


    public class MyChildViewHolder extends ChildViewHolder {

        public TextView childTitle;
        public ImageView image;

        public MyChildViewHolder(View itemView) {
            super(itemView);
            childTitle = (TextView) itemView.findViewById(R.id.playlist_item_name);
            image = (ImageView) itemView.findViewById(R.id.photo);

        }


    }

    class OnChildClickListener implements View.OnClickListener {

        PlayListItem playListItem;

        public OnChildClickListener(PlayListItem playListItem) {
            this.playListItem = playListItem;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, VideoActivity.class);
            intent.putExtra("videoId", playListItem.getVideo());
            context.startActivity(intent);
        }
    }
}
