package com.example.androidhomework8;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NoteFragment extends Fragment {
    private boolean isLandscape;
    NotesRepositoryImpl notesRepository = new NotesRepositoryImpl();

    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList(view);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape){
            showDescripotionNote(0);
        }
    }

    private void initList(View view) {

        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < notesRepository.getNotes().size(); i++) {
            Context context = getContext();
            if (context != null) {
                String title = notesRepository.getNotes().get(i).getTitle();
                String date = notesRepository.getNotes().get(i).getDate();
                TextView textViewTitle = new TextView(getContext());
                TextView textViewDate = new TextView(getContext());
                textViewTitle.setText(title);
                textViewTitle.setTextSize(25);
                textViewDate.setTextSize(15);
                textViewDate.setText(date);
                layoutView.addView(textViewTitle);
                layoutView.addView(textViewDate);
                final int fi = i;
                textViewTitle.setOnClickListener(v -> { showDescripotionNote(fi);});
            }
        }
    }

    private void showDescripotionNote(int index) {
        if (isLandscape){
            showLandNote(index);
        }
        else {
            showPortNote(index);
        }
    }

    private void showPortNote(int index) {
        Context context = getContext();
        if (context != null){
            Intent intent = new Intent(context, NoteActivity.class);
            intent.putExtra(DescriptionsFragment.ARG_INDEX, index);
            startActivity(intent);
        }
    }

    private void showLandNote(int index) {
        DescriptionsFragment detail = DescriptionsFragment.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.descriptionLand, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}