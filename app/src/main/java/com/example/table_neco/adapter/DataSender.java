package com.example.table_neco.adapter;

import com.example.table_neco.NewPost;

import java.util.List;

public interface DataSender {
    public void onDataRecived(List<NewPost> listData);
}
