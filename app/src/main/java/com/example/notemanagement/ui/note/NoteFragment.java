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

import com.example.notemanagement.DB.DaoClass.NoteDao;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.Note;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {
    RecyclerView recyclerView;
    List<Note> listNote;
    NoteAdapter noteAdapter;
    Session session;

    private Database database ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        session= new Session(getActivity());
        database = Database.getInstance(getActivity().getApplicationContext());

        NoteDao noteDao = database.noteDao();


        listNote = noteDao.getAll(session.getIdAccount());

        noteAdapter = new NoteAdapter(getActivity().getApplicationContext(), listNote);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(noteAdapter);

        return view;
    }

}
