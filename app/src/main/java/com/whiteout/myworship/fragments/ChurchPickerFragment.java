package com.whiteout.myworship.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.whiteout.myworship.R;

/**
 * Kendrick Cline 8/2/14.
 */
public class ChurchPickerFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;

    Spinner spinner;

    // newInstance constructor for creating fragment with arguments
    public static ChurchPickerFragment newInstance(int page, String title) {
        ChurchPickerFragment fragmentChurch = new ChurchPickerFragment();
        Bundle args = new Bundle();
        args.putInt("pageInt", page);
        args.putString("pageTitle", title);
        fragmentChurch.setArguments(args);
        return fragmentChurch;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("pageInt", 0);
        title = getArguments().getString("pageTitle");


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pick_church, container, false);

        spinner = (Spinner) view.findViewById(R.id.churches_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.churches_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        return view;
    }


}
