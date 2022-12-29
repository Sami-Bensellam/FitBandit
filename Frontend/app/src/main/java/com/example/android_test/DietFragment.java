package com.example.android_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Diet fragment for displaying user dietary information. Subscriber-only feature.
 */
public class DietFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DietFragment() {
        // Required empty public constructor
    }

    public static DietFragment newInstance(String param1, String param2) {
        DietFragment fragment = new DietFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // 0 == high protein, 1 == vegan, 2 == pop
        TextView highP = (TextView) getView().findViewById(R.id.HighProteinDiet);
        TextView vegan = (TextView) getView().findViewById(R.id.VeganDiet);
        TextView popular = (TextView) getView().findViewById(R.id.PopularDiet);
        ImageView forumNavigate = (ImageView) getView().findViewById(R.id.ForumNavigate);

        highP.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                globalVariables.dietChoice = 0;
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Diets()).commit(); // Transition to Diet
            }
        });

        vegan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                globalVariables.dietChoice = 1;
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Diets()).commit(); // Transition to Diet
            }
        });

        popular.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                globalVariables.dietChoice = 2;
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Diets()).commit(); // Transition to Diet
            }
        });

        forumNavigate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ForumFragment()).commit(); // Transition to Forums
            }
        });
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet, container, false);
    }
}