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
import android.widget.EditText;

import com.celalkorucu.navigationjava.FirstFragmentDirections;


public class FirstFragment extends Fragment {

    EditText nameEditText ;
    EditText surnameEditText ;
    EditText ageEditText ;

    int age ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button);

        nameEditText = view.findViewById(R.id.nameEditText);
        surnameEditText = view.findViewById(R.id.SurnameEditText);
        ageEditText = view.findViewById(R.id.AgeEditText);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToSecond(view);
            }
        });
    }

    public void goToSecond(View view){

       // NavDirections action = FirstFragmentDirections.actionFirstFragmentToSecondFragment();
        age = Integer.parseInt(ageEditText.getText().toString());
        FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(nameEditText.getText().toString() , surnameEditText.getText().toString() , age);
        Navigation.findNavController(view).navigate(action);

    }
}