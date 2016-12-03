package com.ch.wchhuangya.dzah.android.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.android.BR;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.enums.AlbumSong;
import com.ch.wchhuangya.dzah.android.model.Album;
import com.ch.wchhuangya.dzah.android.model.Song;
import com.ch.wchhuangya.dzah.android.util.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wchya on 2016-12-02 10:38
 */

public class RefreshAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Map<String, Object>> mData = new ArrayList<>();
    private static final int ITEM_VIEW_TYPE_ALBUM = 1;
    private static final int ITEM_VIEW_TYPE_SONG = 2;

    public void setData(List<Map<String, Object>> data, int start, int count) {
        mData.addAll(data);
        notifyItemRangeInserted(start, count);
    }

    public void setRefreshData(List<Map<String, Object>> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_VIEW_TYPE_ALBUM : ITEM_VIEW_TYPE_SONG;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BindingViewHolder viewHolder;
        if (viewType == ITEM_VIEW_TYPE_ALBUM) {
            Album album = new Album();
            viewHolder =  new BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_album_info, parent, false), album);
            viewHolder.getBinding().setVariable(BR.album, album);
        } else {
            Song song = new Song();
            viewHolder = new BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_song_info, parent, false), song);
            viewHolder.getBinding().setVariable(BR.song, song);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Map<String, Object> map = mData.get(position);
        if (position == 0) {
            Album album = (Album) holder.getBinding().getRoot().getTag();
            album.name.set(map.get(AlbumSong.ALBUM_NAME.getKey()).toString());
            album.time.set(map.get(AlbumSong.ALBUM_PUBLISH_TIME.getKey()).toString());
            album.count.set(Integer.parseInt(map.get(AlbumSong.ALBUM_SONGS_COUNT.getKey()).toString()));
            album.imgResId.set(Integer.parseInt(map.get(AlbumSong.ALBUM_PIC.getKey()).toString()));
        } else {
            Song song = (Song) holder.getBinding().getRoot().getTag();
            song.order.set(StringHelper.getOrderOfSong(position));
            song.name.set(map.get(AlbumSong.SONG_NAME.getKey()).toString());
            song.lyrics.set(map.get(AlbumSong.SONG_LYRICS.getKey()).toString());
            song.tune.set(map.get(AlbumSong.SONG_TUNE.getKey()).toString());
            song.arrangement.set(map.get(AlbumSong.SONG_ARRANGEMENT.getKey()).toString());
            song.state.set(StringHelper.getStateOfSong(Integer.parseInt(map.get(AlbumSong.SONG_STATE.getKey()).toString())));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
