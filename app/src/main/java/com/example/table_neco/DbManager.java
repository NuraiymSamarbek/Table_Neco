package com.example.table_neco;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.table_neco.adapter.DataSender;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private Query mQuery;
    private List<NewPost> newPostList;
    private DataSender dataSender;

    public DbManager(DataSender dataSender)
    {
        this.dataSender = dataSender;
        newPostList= new ArrayList<>();
    }

    public void getDataFromDB(String path)
    {
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        DatabaseReference dbRef= db.getReference(path);
        mQuery=dbRef.orderByChild("anuncio/time");
        readDataUpdate();


    }
    public void  readDataUpdate()
    {
        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(newPostList.size()>0) newPostList.clear();

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    NewPost newPost=ds.child("anuncio").getValue(NewPost.class);
                    newPostList.add(newPost);

                }
                dataSender.onDataRecived(newPostList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
