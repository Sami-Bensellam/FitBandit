package com.example.android_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
 * Workout fragment containing a generated workout plan for users based on their profile.
 *
 * Workouts may be modified per user request.
 */
public class WorkoutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WorkoutFragment() {}

    public static WorkoutFragment newInstance(String param1, String param2) {
        WorkoutFragment fragment = new WorkoutFragment();
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
        // Get profile information
        JSONObject profile = MainActivity.userProfile;

        // Make JSON request for all workouts
        RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());
        String url = "http://coms-309-052.class.las.iastate.edu:8080/workouts";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        MainActivity.workouts = response; // Store all currently available workouts in Main
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });
        queue.add(jsonArrayRequest);
        try{
            Thread.sleep(1000); // Allow GET to complete before continuing
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        Button workout1 = (Button) getView().findViewById(R.id.workout1);
        Button workout2 = (Button) getView().findViewById(R.id.workout2);
        Button workout3 = (Button) getView().findViewById(R.id.workout3);

        try{
            JSONArray profileWorkouts = profile.getJSONArray("workouts");

            workout1.setText(profileWorkouts.getJSONObject(0).getString("wName"));
            workout2.setText(profileWorkouts.getJSONObject(1).getString("wName"));
            workout3.setText(profileWorkouts.getJSONObject(2).getString("wName"));
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        workout1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                hideSelections(workout1, workout2, workout3);
                workoutMenu(profile, 0);
            }
        });
        workout2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                hideSelections(workout1, workout2, workout3);
                workoutMenu(profile, 1);
            }
        });
        workout3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                hideSelections(workout1, workout2, workout3);
                workoutMenu(profile, 2);
            }
        });
    }

    void hideSelections(Button b1, Button b2, Button b3){
        TextView title = (TextView) getView().findViewById(R.id.workout);
        title.setVisibility(View.INVISIBLE);

        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
    }

    void workoutMenu(JSONObject profile, int workoutNum){
        TextView workoutInfo = (TextView) getView().findViewById(R.id.workoutInfoType);
        TextView workoutInfoText = (TextView) getView().findViewById(R.id.workoutInfo);
        TextView weightInfo = (TextView) getView().findViewById(R.id.weightInfo);
        TextView weightInput = (TextView) getView().findViewById(R.id.weightInput);
        TextView calorieInfo = (TextView) getView().findViewById(R.id.calorieInfo);
        TextView calorieInput = (TextView) getView().findViewById(R.id.calorieInput);
        Button closeButton = (Button) getView().findViewById(R.id.closeButton);
        TextView workoutName = (TextView) getView().findViewById(R.id.workout);
        Spinner dropdown = (Spinner) getView().findViewById(R.id.workoutModifier);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.workout_options, android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        workoutInfo.setVisibility(View.VISIBLE);
        workoutInfoText.setVisibility(View.VISIBLE);
        weightInfo.setVisibility(View.VISIBLE);
        weightInput.setVisibility(View.VISIBLE);
        calorieInfo.setVisibility(View.VISIBLE);
        calorieInput.setVisibility(View.VISIBLE);
        closeButton.setVisibility(View.VISIBLE);
        dropdown.setVisibility(View.VISIBLE);

        // Populate workout info
        try{
            String[] workoutOptions = getResources().getStringArray(R.array.workout_options);

            for(int it = 0; it < workoutOptions.length; it++){
                if(workoutOptions[it].equals(profile.getJSONArray("workouts")
                        .getJSONObject(workoutNum).getString("wName"))){
                    dropdown.setSelection(it);
                }
            }
            populateWorkouts(profile.getJSONArray("workouts"), workoutInfoText, weightInput,
                    calorieInput, workoutNum);
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        JSONArray workouts = MainActivity.workouts;
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id){
                String item = adapterView.getItemAtPosition(position).toString();
                try{
                    String comparator = profile.getJSONArray("workouts")
                            .getJSONObject(workoutNum).getString("wName");
                    if(!item.equals(comparator)){
                        int workoutID = 0;
                        for(int it = 0; it < workouts.length(); it++){
                            if(workouts.getJSONObject(it).getString("wName").equals(item)){
                                populateWorkouts(workouts, workoutInfoText, weightInput, calorieInput, it);
                                workoutID = it;
                            }
                        }

                        // JSON PUT
                        String url = "http://coms-309-052.class.las.iastate.edu:8080/workouts/"+workoutID;
                        String workout = workouts.getJSONObject(workoutID).toString();
                        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                        MainActivity.jsonPUT(url, workout, queue);
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        closeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                workoutInfo.setVisibility(View.INVISIBLE);
                workoutInfoText.setVisibility(View.INVISIBLE);
                weightInfo.setVisibility(View.INVISIBLE);
                weightInput.setVisibility(View.INVISIBLE);
                calorieInfo.setVisibility(View.INVISIBLE);
                calorieInput.setVisibility(View.INVISIBLE);
                closeButton.setVisibility(View.INVISIBLE);
                dropdown.setVisibility(View.INVISIBLE);

                Button workout1 = (Button) getView().findViewById(R.id.workout1);
                Button workout2 = (Button) getView().findViewById(R.id.workout2);
                Button workout3 = (Button) getView().findViewById(R.id.workout3);

                workoutName.setVisibility(View.VISIBLE);
                workout1.setVisibility(View.VISIBLE);
                workout2.setVisibility(View.VISIBLE);
                workout3.setVisibility(View.VISIBLE);
            }
        });
    }

    // Populate workout info
    public void populateWorkouts(JSONArray profile, TextView workout, TextView weight,
                                 TextView calories, int workoutNum){
        try{
            workout.setText(profile.getJSONObject(workoutNum).getString("wType"));
            weight.setText(profile.getJSONObject(workoutNum).getString("weight"));
            calories.setText(profile.getJSONObject(workoutNum).getString("caloriesB"));
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false);

    }
}
