package com.example.notemanagement.ui.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;

import java.util.ArrayList;

public class NoteFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<NoteModel> listNote;
    NoteAdapter noteAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);

        listNote = new ArrayList<>();
        listNote.add(new NoteModel("volleyball","relax","high","processing","12-3-2021","10-3-2021"));
        listNote.add(new NoteModel("football","relax","high","processing","12-3-2021","10-3-2021"));
        noteAdapter = new NoteAdapter(getActivity().getApplicationContext(), listNote);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;









    }

}
