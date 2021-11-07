package com.example.mychords;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final int VIEW_TYPE_FOOTER = 0;
    private final int VIEW_TYPE_CELL = 1;
    private List<Chord> dataSet;
    private MaterialButton createChordButton;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final MaterialButton createChordButton;

        public ViewHolder(View view) {
            super(view);
            // TODO: Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView);
            createChordButton = (MaterialButton) view.findViewById(R.id.create_chord_btn);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(List<Chord> dataSet) {
        this.dataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Inflate the view based on view type
        View view;
        if (viewType == VIEW_TYPE_CELL){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.text_row_item, viewGroup, false);
        }else{
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.button_item, viewGroup, false);
        }

        return new ViewHolder(view);
    }

    @NonNull


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Chord chord;
        if (position == dataSet.size()){
            viewHolder.createChordButton.setOnClickListener((View c) -> SelectChordFragment.toggleFilters());
        }else{
            chord = (Chord) dataSet.get(position);
            viewHolder.getTextView().setText(chord.getChord());
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size() + 1;
    }

    // Return the view type
    @Override
    public int getItemViewType(int position) {
        return (position == dataSet.size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }
}
