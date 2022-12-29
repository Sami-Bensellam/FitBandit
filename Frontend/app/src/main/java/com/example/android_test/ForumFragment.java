package com.example.android_test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;

import tech.gusavila92.websocketclient.WebSocketClient;

public class ForumFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WebSocketClient client;

    public ForumFragment() {}

    public static ForumFragment newInstance(String param1, String param2) {
        ForumFragment fragment = new ForumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        TextView forumOption1 = (TextView) getView().findViewById(R.id.forumOption1);
        TextView forumOption2 = (TextView) getView().findViewById(R.id.forumOption2);
        TextView forumOption3 = (TextView) getView().findViewById(R.id.forumOption3);
        EditText forumInput = (EditText) getView().findViewById(R.id.forumInput);
        Button forumPost = (Button) getView().findViewById(R.id.forumPost);

        forumOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    forumOption1.setVisibility(View.INVISIBLE);
                    forumOption2.setVisibility(View.INVISIBLE);
                    forumOption3.setVisibility(View.INVISIBLE);
                    webSocket(client, MainActivity.userProfile.getInt("id"), 0);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });

        forumOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    forumOption1.setVisibility(View.INVISIBLE);
                    forumOption2.setVisibility(View.INVISIBLE);
                    forumOption3.setVisibility(View.INVISIBLE);
                    webSocket(client, MainActivity.userProfile.getInt("id"), 1);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });

        forumOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    forumOption1.setVisibility(View.INVISIBLE);
                    forumOption2.setVisibility(View.INVISIBLE);
                    forumOption3.setVisibility(View.INVISIBLE);
                    webSocket(client, MainActivity.userProfile.getInt("id"), 2);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });

        forumPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = forumInput.getText().toString();
                try{
                    client.send(inputText);
                }
                catch(Exception e){
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });
    }

    public void webSocket(WebSocketClient client, int userID, int threadID){
        TextView chatHistory = (TextView) getView().findViewById(R.id.forum);
        EditText forumInput = (EditText) getView().findViewById(R.id.forumInput);
        Button forumPost = (Button) getView().findViewById(R.id.forumPost);
        chatHistory.setVisibility(View.VISIBLE);
        forumInput.setVisibility(View.VISIBLE);
        forumPost.setVisibility(View.VISIBLE);
        String w = "ws://coms-309-052.class.las.iastate.edu:8080/chat/" + userID + "/" + threadID;

        try {
            Log.d("Socket:", "Trying socket");
            client = new WebSocketClient(new URI(w)) {
                public void onMessage(String message) {
                    forumPost(message);
                }

                @Override
                public void onTextReceived(String text){
                    forumPost(text);
                }
                public void onBinaryReceived(byte[] b){}
                public void onPingReceived(byte[] b){}
                public void onPongReceived(byte[] b){}
                public void onException(Exception e){}
                public void onCloseReceived(){}

                @Override
                public void onOpen() {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
            client.connect();
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
    }

    public void forumPost(String input){
        TextView chatHistory = (TextView) getView().findViewById(R.id.forum);
        String history = chatHistory.getText().toString();
        String post = "";
        while(input.contains("USERNAME")){
            String[] parse = input.split(" ", 4);
            String username = parse[1];
            String text = parse[3];
            if(text.contains("USERNAME")) text = text.substring(0, text.indexOf("USERNAME"));
            post += username + "\n" + text +
                    "\n--------------------------------------------------\n";
            input = input.substring(username.length() + text.length() + 15);
        }
        chatHistory.setText(history + post);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum, container, false);
    }
}