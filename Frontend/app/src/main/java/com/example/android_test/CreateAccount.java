package com.example.android_test;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.Calendar;

/**
 * Fragment for account creation.
 */
public class CreateAccount extends Fragment implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    public static TextView dateText;
    public static int userHeight;
    public static int userWeight;
    public static String userAge;
    public static int userMusclePref;
    public static int userWeightPref;

    public static Spinner weightPrefSpinner;
    public static String weightPrefChoice;

    String userN = "";
    String passW = "";

    public static int userAgeYrs = 0;

    public static Switch genderSwitch;
    public static TextView genderTV;
    public static boolean userGender;

    SeekBar heightSeekBar;
    SeekBar weightSeekBar;
    TextView heightSeekText;
    TextView weightSeekText;


    private TextView weightPrefTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateAccount() {
        // Required empty public constructor
    }

    public static CreateAccount newInstance(String param1, String param2) {
        CreateAccount fragment = new CreateAccount();
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
        // Next button
        Button next = (Button) getView().findViewById(R.id.createAccount);

        spinnerInit();
        weightSeekBarInit();
        heightSeekBarInit();
        musclePrefSwitchInit();
        genderInit();
        dobInit();


        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Get username
                EditText username = (EditText) getView().findViewById(R.id.username);
                userN = username.getText().toString();

                // Get password
                EditText password = (EditText) getView().findViewById(R.id.password);
                passW = password.getText().toString();

                if (userN == "" || passW == "" || passW == "password" || userN == "USERNAME" || userAgeYrs <= 0) {
                    Toast.makeText(getContext(), "Incomplete Fields", Toast.LENGTH_SHORT).show();
                } else {
                    String email = "test";
                    boolean gender = userGender;
                    // JSON request to check username against existing users
                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    String url = "http://coms-309-052.class.las.iastate.edu:8080/users";
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    try {
                                        for (int it = 0; it < response.length(); it++) {
                                            JSONObject profile = response.getJSONObject(it);
                                            if (userN.equals(profile.getString("userName"))) { // Check username
                                                throw new JSONException("Username taken");
                                            }
                                        }
                                        String jsonInput = "{\"userName\": \"" + userN +
                                                "\", \"password\": \"" + passW +
                                                "\", \"emailId\": \"" + email +
                                                "\", \"weight\": " + userWeight +
                                                "\", \"height\": " + userHeight +
                                                "\", \"age\": " + userAgeYrs +
                                                "\", \"gain\": " + userWeightPref +
                                                "\", \"gender\": " + userGender + "\"}";
                                        String urlPath = "http://coms-309-052.class.las.iastate.edu:8080/users/register/" + userN + "/" + passW + "/" + email + "/" + userWeight + "/" + userHeight + "/" + userAgeYrs + "/" + userWeightPref + "/" + userGender;
                                        MainActivity.jsonPOST(urlPath, jsonInput, queue);
                                        profileUpdate(userN, "http://coms-309-052.class.las.iastate.edu:8080/users", queue);
                                    } catch (JSONException error) {
                                        error.printStackTrace(); // Replace with "user/pass taken error"
                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            });
                    queue.add(jsonArrayRequest);
                }
            }
        });
    }

    void profileUpdate(String username, String url, RequestQueue queue){
        try{
            Thread.sleep(1000); // Allow POST to complete before checking username
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int it = 0; it < response.length(); it++){
                                JSONObject profile = response.getJSONObject(it);
                                if(username.equals(profile.getString("userName"))) { // Check username
                                    MainActivity.userProfile = profile;
                                    globalVariables.bottomNav.setVisibility(View.VISIBLE);
                                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                            new WorkoutFragment()).commit();
                                }
                            }
                        }catch (JSONException error){
                            error.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });
        queue.add(jsonArrayRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        userAgeYrs = 2022 - year;
        userAge = month + "/" + dayOfMonth + "/" + year;
        dateText.setText(userAge);
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        weightPrefChoice = adapterView.getItemAtPosition(i).toString();
        if (l == 0) {
            // lose weight
            userWeightPref = 0;
        }
        else if (l == 1) {
            // maintain weight
            userWeightPref = 1;
        }
        else if (l == 2) {
            // gain weight
            userWeightPref = 2;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean genderInit() {
        genderSwitch = (Switch) getView().findViewById(R.id.genderSwitch);
        genderTV = (TextView) getView().findViewById(R.id.genTextView);
        genderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    genderTV.setText("F");
                    userGender = true;
                }
                else {
                    genderTV.setText("M");
                    userGender = false;
                }
            }
        });
        return userGender;
    }
    public void spinnerInit() {
        weightPrefSpinner = (Spinner) getView().findViewById(R.id.weightPrefSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.muscle_preference, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightPrefSpinner.setAdapter(adapter);
        weightPrefSpinner.setOnItemSelectedListener(this);
    }
    public void weightSeekBarInit() {
        weightSeekBar = (SeekBar) getView().findViewById(R.id.weightSeekBar);
        weightSeekText = (TextView) getView().findViewById(R.id.weightSeekText);

        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                weightSeekText.setText("Weight: " + String.valueOf(progress) + " lbs");
                userWeight = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void heightSeekBarInit() {
        heightSeekBar = (SeekBar) getView().findViewById(R.id.heightSeekbar);
        heightSeekText = (TextView) getView().findViewById(R.id.heightSeekText);
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                heightSeekText.setText("Height: " + String.valueOf(progress) + "cm");
                userHeight = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void musclePrefSwitchInit() {
        Switch musclePrefSwitch = (Switch) getView().findViewById(R.id.muscleSwitch);
        TextView musclePrefTV = (TextView) getView().findViewById(R.id.muscleTextView);
        musclePrefSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    musclePrefTV.setText("Gain");
                    userMusclePref = 1;
                }
                else musclePrefTV.setText("Maintain");
                userMusclePref = 0;
            }
        });
    }
    public void dobInit() {
        dateText = (TextView) getView().findViewById(R.id.date_text);
        Button dobButton = (Button) getView().findViewById(R.id.dobButton);

        dobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

}

