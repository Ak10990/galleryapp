package com.android.galleryapp.flows;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.galleryapp.R;
import com.android.galleryapp.common.BaseFragment;
import com.android.galleryapp.models.Album;
import com.android.galleryapp.presenter.AlbumListPresenter;
import com.android.galleryapp.presenter.AlbumListPresenterImpl;
import com.android.galleryapp.utils.NetworkUtils;
import com.android.galleryapp.views.AlbumListView;

import java.util.ArrayList;
import java.util.List;

public class AlbumListFragment extends BaseFragment implements AlbumListView, AlbumListAdapter.AlbumListAdapterListener {

    private AlbumListPresenter albumListPresenter;
    private AlbumListAdapter adapter;
    private TextView headerTextView;
    private List<Album> albumList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private TextView noAlbumsPlaceholder;

    public static AlbumListFragment newInstance() {
        return new AlbumListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkUtils.isInternetConnected(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_list, container, false);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movie_list);
        if (recyclerView != null) {
            adapter = new AlbumListAdapter(albumList, this);
            recyclerView.setAdapter(adapter);
            layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(scrollListener);
        }
        headerTextView = (TextView) view.findViewById(R.id.header_textview);
        noAlbumsPlaceholder = (TextView) view.findViewById(R.id.no_albums_placeholder);

        headerTextView.setVisibility(View.INVISIBLE);

    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int position = layoutManager.findFirstVisibleItemPosition();
            String header = "Album ID: " + albumList.get(position).getAlbumId();
            headerTextView.setText(header);
        }
    };

    @Override
    protected void initPresenter() {
        albumListPresenter = new AlbumListPresenterImpl(this);
        albumListPresenter.loadData();
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
        adapter.setAlbumList(albumList);
    }

    @Override
    public void onListPopulated() {
        headerTextView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        noAlbumsPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void setNoDataView(String message) {
        noAlbumsPlaceholder.setVisibility(View.VISIBLE);
        noAlbumsPlaceholder.setText(message);
    }


    @Override
    public void openImageWithUrl(Message message) {
        if (!isAdded()) return;
        getCurrActivity().handleMessage(message);
    }
}
