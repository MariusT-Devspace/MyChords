package com.example.mychords;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mychords.databinding.FragmentSelectChordBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectChordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectChordFragment extends Fragment {
    private FragmentSelectChordBinding binding;
    List notes = new ArrayList();
    final String notesArray [] = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    final String qualityArray[] = {"Major", "Minor", "Suspended", "Augmented", "Diminished", "Half diminished"};
    final String qualityAbbrArray[] = {"", "m", "sus", "aug", "dim", "m7b5"};
    Map<String, String> qualityDictionary;
    int noteSelection;
    List<Chord> chords = new ArrayList<Chord>();
    private static final int SPAN_COUNT = 2;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BottomSheetBehavior sheetBehavior;
    ;


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
        binding.createChordBtn.setOnClickListener((View c) -> toggleFilters());
        qualityDictionary = new HashMap<String, String>();

        for(int i = 0; i < notesArray.length; i++){
            notes.add(notesArray[i]);
        }

        final ArrayAdapter adapter = new ArrayAdapter(this.getContext(), R.layout.list_item, notes);
        AutoCompleteTextView textInputLayout = (AutoCompleteTextView) binding.menu.getEditText();
        textInputLayout.setAdapter(adapter);

        final ArrayAdapter adapter2 = new ArrayAdapter(this.getContext(), R.layout.list_item, qualityArray);
        AutoCompleteTextView textInputLayout2 = (AutoCompleteTextView) binding.menu2.getEditText();
        textInputLayout2.setAdapter(adapter2);
        textInputLayout2.setText(qualityArray[0], false);
        for (int i = 0; i < qualityArray.length; i++){
            qualityDictionary.put(qualityArray[i], qualityAbbrArray[i]);
        }
        customAdapter = new CustomAdapter(chords);
        recyclerView = (RecyclerView)  binding.recyclerView;
        recyclerView.setAdapter(customAdapter);
        layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);

        textInputLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                noteSelection = position;
            }
        });

        binding.addChordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFilters();
                String quality = qualityDictionary.get(binding.menu2.getEditText().getText().toString());
                Chord newChord = new Chord(noteSelection, notesArray[noteSelection], quality);
                chords.add(newChord);
                customAdapter.notifyDataSetChanged();

            }
        });
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
