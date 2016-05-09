package com.android.galleryapp.flows;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.galleryapp.R;
import com.android.galleryapp.models.Album;
import com.android.galleryapp.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder> {

    private final AlbumListAdapterListener listener;
    private List<Album> mList;

    public AlbumListAdapter(List<Album> albumList, AlbumListAdapterListener listener) {
        this.mList = albumList;
        this.listener = listener;
    }

    @Override
    public AlbumListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Album album = mList.get(position);
        holder.itemText.setText(album.getTitle());
        holder.itemImage.setImageURI(Uri.parse(album.getThumbnailUrl()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = Constants.AllScreens.DETAIL_LIST_SCREEN;
                Bundle argsBundle = new Bundle();
                argsBundle.putString(Constants.DETAIL_URL, album.getUrl());
                message.setData(argsBundle);
                listener.openImageWithUrl(message);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setAlbumList(List<Album> albumList) {
        this.mList = albumList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;
        SimpleDraweeView itemImage;

        public ViewHolder(View view) {
            super(view);
            itemImage = (SimpleDraweeView) view.findViewById(R.id.item_image);
            itemText = (TextView) view.findViewById(R.id.item_text);
        }
    }

    public interface AlbumListAdapterListener {
        void openImageWithUrl(Message message);
    }
}

