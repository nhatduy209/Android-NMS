package com.example.notemanagement.ui.note;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NoteFragment extends Fragment{
    RecyclerView recyclerView;
    List<Note> listNote;
    NoteAdapter noteAdapter;
    Session session;

    private Database database ;

    SwipeRefreshLayout swipeRefreshLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_note, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        session= new Session(getActivity());
        database = Database.getInstance(getActivity().getApplicationContext());

        final NoteDao noteDao = database.noteDao();


        listNote = noteDao.getAll(session.getIdAccount());

        noteAdapter = new NoteAdapter(getActivity().getApplicationContext(), listNote);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(noteAdapter);
        registerForContextMenu(recyclerView);




        //get floatingbutton
        FloatingActionButton fabBtn = view.findViewById(R.id.fabAddNote);


        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Interface for interacting with Fragment objects inside of an Activity
                //FragmentManager is the class responsible for performing actions on your app's
                // fragments, such as adding, removing, or replacing them, and adding them to the back stack
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                // a transaction can add or replace multiple fragments
                FragmentTransaction ft =  fragmentManager.beginTransaction();


                DialogFragment newFragment = AddNoteDialog.newInstance();
                newFragment.show(ft, "add_note_dialog");




            }
        });
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listNote = noteDao.getAll(session.getIdAccount());
                noteAdapter = new NoteAdapter(getActivity().getApplicationContext(), listNote);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setAdapter(noteAdapter);
                registerForContextMenu(recyclerView);
            }
        });

        return view;
    }



}