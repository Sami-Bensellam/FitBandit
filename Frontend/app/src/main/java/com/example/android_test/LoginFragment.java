package com.example.android_test;

import static android.view.View.VISIBLE;

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
 * Login screen
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {}

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        // Login button
        Button b = (Button) getView().findViewById(R.id.loginButton);

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // Get username
                EditText username = (EditText)getView().findViewById(R.id.username);
                String userN = username.getText().toString();

                // Get password
                EditText password = (EditText)getView().findViewById(R.id.password);
                String passW = password.getText().toString();

                // Make JSON request for all users
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                // String url = "https://1242a859-4636-4467-97df-07920b22bad3.mock.pstmn.io/users";
                String url = "http://coms-309-052.class.las.iastate.edu:8080/users";

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                try{
                                    for(int it = 0; it < response.length(); it++){
                                        JSONObject profile = response.getJSONObject(it);
                                        if(userN.equals(profile.getString("userName"))){ // Check username
                                            if(passW.equals(profile.getString("password"))){ // Check password
                                                MainActivity.userProfile = profile;
                                                globalVariables.bottomNav.setVisibility(View.VISIBLE);
                                                getFragmentManager().beginTransaction().replace(
                                                        R.id.fragment_container,
                                                        new WorkoutFragment()).commit(); // Transition to workout
                                            }
                                        }
                                    }
                                }catch (JSONException error){
                                    error.printStackTrace(); // Replace with "invalid user/pass error"
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {}
                        });
                queue.add(jsonArrayRequest);
            }
        });

        //Create Account button
        TextView cAccount = (TextView) getView().findViewById(R.id.createAccount);

        cAccount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CreateAccount()).commit(); // Transition to Create Account
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}