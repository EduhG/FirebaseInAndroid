package com.eduhg.firebaseinandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    Firebase mRootRef;
    ArrayList<String> mMessages = new ArrayList<>();
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRootRef = new Firebase("https://fir-inandroid.firebaseio.com");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        Firebase messagesRef = mRootRef.child("messages");

        FirebaseRecyclerAdapter<String, MessageViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<String, MessageViewHolder>(
                        String.class,
                        android.R.layout.two_line_list_item,
                        MessageViewHolder.class,
                        messagesRef
                ) {
            @Override
            protected void populateViewHolder(MessageViewHolder messageViewHolder, String s, int i) {
                messageViewHolder.mTextView.setText(s);
            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MessageViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
        }
    }
}
