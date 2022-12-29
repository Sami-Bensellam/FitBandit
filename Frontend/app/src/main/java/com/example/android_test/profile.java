package com.example.android_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Profile fragment for displaying profile information.
 *
 * Contains additional functionality for Admin-use only.
 */
public class profile extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public profile() {
        // Required empty public constructor
    }

    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Get profile information
        JSONObject profile = MainActivity.userProfile;
        Button logoutButton = (Button) getView().findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalVariables.bottomNav.setVisibility(View.INVISIBLE);
                getFragmentManager().beginTransaction().replace(
                        R.id.fragment_container,
                new LoginFragment()).commit(); // Transition to workout
            }
        });
        try{
            // Check/Apply Admin tools
            EditText userSearch = (EditText) getView().findViewById(R.id.searchUser);
            Button profileButton = (Button) getView().findViewById(R.id.searchProfileButton);
            if(profile.getString("admin").equals("true")){
                userSearch.setVisibility(View.VISIBLE);
                profileButton.setVisibility(View.VISIBLE);
            }

            // Populate with user profile
            populateProfile(profile);

            // Admin search profile function
            profileButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    String user = userSearch.getText().toString();

                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    // String url = "https://1242a859-4636-4467-97df-07920b22bad3.mock.pstmn.io/users";
                    String url = "http://coms-309-052.class.las.iastate.edu:8080/users";

                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                            null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    String test = "";
                                    try{
                                        for(int it = 0; it < response.length(); it++){
                                            JSONObject profile = response.getJSONObject(it);
                                            if(user.equals(profile.getString("userName"))){
                                                populateProfile(profile);
                                            }
                                        }
                                    }catch (JSONException error){
                                        error.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                    queue.add(jsonArrayRequest);
                }
            });
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateProfile(JSONObject profile){
        try{
            TextView username = (TextView) getView().findViewById(R.id.username);
            username.setText(profile.getString("userName"));

            TextView DoB = (TextView) getView().findViewById(R.id.dateOfBirth);
            DoB.setText(profile.getString("age"));

            TextView Gender = (TextView) getView().findViewById(R.id.gender);
            if (profile.getString("gender") == "true") {
                Gender.setText("Female");
            }
            else Gender.setText("Male");

            TextView weightPref = (TextView) getView().findViewById(R.id.desiredWeight);
            if (profile.getString("gain") == "0") {
                weightPref.setText("Lose Weight");
            }
            else if (profile.getString("gain") == "1") {
                weightPref.setText("Maintain Weight");
            }
            else weightPref.setText("Gain Weight");

            /*TextView musclePref = (TextView) getView().findViewById(R.id.desiredMuscle);
            if (profile.getString("muscle") == "0") {
                musclePref.setText("Maintain Muscle");
            }
            else weightPref.setText("Gain Muscle");*/

            TextView userWeight = (TextView) getView().findViewById(R.id.weight);
            userWeight.setText(profile.getString("weight"));

            TextView userHeight = (TextView) getView().findViewById(R.id.height);
            userHeight.setText(profile.getString("height"));

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}