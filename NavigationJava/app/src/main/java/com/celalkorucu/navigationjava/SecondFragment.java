package com.celalkorucu.navigationjava;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SecondFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.button2);

        TextView textView = view.findViewById(R.id.textView);
        TextView surnameTextView = view.findViewById(R.id.SurnameEditText);
        TextView ageTextView = view.findViewById(R.id.AgeEditText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFirst(view);
            }
        });

        if(getArguments() != null){


            /*
            String name = SecondFragmentArgs.fromBundle(getArguments()).getName();
            String surname = SecondFragmentArgs.fromBundle(getArguments()).getSurname();
            */

            int age = SecondFragmentArgs.fromBundle(getArguments()).getAge();
            int a = SecondFragmentArgs.fromBundle(getArguments()).getAge();

/*
            textView.setText(name);
            surnameTextView.setText(surname);

 */
            ageTextView.setText(a);

        }
    }

    public void goToFirst(View view){

        NavDirections action = SecondFragmentDirections.actionSecondFragmentToFirstFragment();
        Navigation.findNavController(view).navigate(action);
    }
}