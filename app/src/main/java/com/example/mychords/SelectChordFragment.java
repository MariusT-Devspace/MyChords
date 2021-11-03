package com.example.mychords;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.mychords.databinding.FragmentSelectChordBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectChordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectChordFragment extends Fragment {
    private FragmentSelectChordBinding binding;
    List notes = new ArrayList();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BottomSheetBehavior sheetBehavior;
    public SelectChordFragment() {
        super(R.layout.fragment_select_chord);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectChordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectChordFragment newInstance(String param1, String param2) {
        SelectChordFragment fragment = new SelectChordFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentSelectChordBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Context context = getContext();
        if(context != null){
            setEnterTransition(context.getString(R.string.hello_blank_fragment));
        }
        LinearLayout contentLayout = binding.contentLayout;
        sheetBehavior = BottomSheetBehavior.from(contentLayout);
        sheetBehavior.setFitToContents(false);
        sheetBehavior.setHideable(false);//prevents the boottom sheet from completely hiding off the screen
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//initially state to fully expanded
        binding.addChordBtn.setOnClickListener((View c) -> toggleFilters());

        notes.add("C");
        notes.add("C#");
        notes.add("D");
        notes.add("D#");
        notes.add("E");
        notes.add("F");
        notes.add("F#");
        notes.add("G");
        notes.add("G#");
        notes.add("A");



        return view;

    }

    private void toggleFilters(){
        if(sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            sheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        }
        else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
}