package com.example.notemanagement.ui.note;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.DaoClass.CategoryDaoClass;
import com.example.notemanagement.DB.DaoClass.PriorityDaoClass;
import com.example.notemanagement.DB.DaoClass.StatusDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.PriorityModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static android.content.ContentValues.TAG;

public class NoteFragment extends Fragment{
    RecyclerView recyclerView;
    List<Note> listNote;
    NoteAdapter noteAdapter;
    Session session;
    //Text  and button of Add Note Dialog
    Button btnClose,btnAdd, btnDate;
    TextView txtSelectCategory,txtSelectPriority,txtSelectStatus,txtselectDate;
 ;  EditText txtNoteName;
    //Text and button of Edit Note Dialog
    TextView txtEditselectDate,txtEditSelectCategory,txtEditSelectPriority,txtEditSelectStatus;
    Button btnEditClose,btnUpdate,btnDateEdit;
    EditText txtEditNoteName;
    //Database
    Database database ;
    NoteDao noteDao;
    CategoryDaoClass categoryDao;
    PriorityDaoClass priorityDao;
    StatusDaoClass statusDao;
    Note selectedNote = new Note();


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        database = Database.getInstance(getActivity().getApplicationContext());
        recyclerView = view.findViewById(R.id.recyclerview);
        session= new Session(getActivity());
        registerForContextMenu(recyclerView);

        noteDao = database.noteDao();
        categoryDao = database.categoryDaoClass();
        statusDao = database.statusDaoClass();
        priorityDao = database.priorityDaoClass();

        //Get floating button
        FloatingActionButton fabBtn = view.findViewById(R.id.fabAddNote);

        //Show Add Dialog
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                final View v = inflater.inflate(R.layout.add_note_dialog, null);

                //Button Text
                txtNoteName = v.findViewById(R.id.txtNoteName);
                txtSelectCategory = v.findViewById(R.id.txtSelectCategory);
                txtSelectPriority = v.findViewById(R.id.txtSelectPriority);
                txtSelectStatus = v.findViewById(R.id.txtSelectStatus);
                txtselectDate = v.findViewById(R.id.txtSelectPlanDate);

                //button Choose Date
                btnDate = v.findViewById(R.id.btnSelectPlanDate);

                //button Add
                btnAdd = v.findViewById(R.id.btnAdd);

                //button Close
                btnClose = v.findViewById(R.id.btnClose);

                //Get list category
                final List<CategoryModel> ListCategory  =  categoryDao.getAllData(session.getIdAccount());
                final String[] lstCategory = new String[ListCategory.size()];
                int countCategory = 0;
                for (CategoryModel i : ListCategory) {
                    lstCategory[countCategory] = i.getName();
                    countCategory++;
                }

                txtSelectCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Choose category ")
                                .setItems(lstCategory, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                        txtSelectCategory.setText(lstCategory[which]);
                                    }
                                })
                                .setNegativeButton("No", null).show();
                    }
                });


                // Get list priority
                final List<PriorityModel> ListPriority = priorityDao.getAllData(session.getIdAccount());
                final String[] lstPriority = new String[ListPriority.size()];
                int countPriority = 0;
                for (PriorityModel i : ListPriority) {
                    lstPriority[countPriority] = i.getName();
                    countPriority++;
                }

                txtSelectPriority.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Choose Priority ")
                                .setItems(lstPriority, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                        txtSelectPriority.setText(lstPriority[which]);
                                    }
                                })
                                .setNegativeButton("No", null).show();
                    }
                });


                // Get list status
                final List<StatusModel> ListStatus  =  statusDao.getAllData(session.getIdAccount());
                final String[] lstStatus = new String[ListStatus.size()];
                int count = 0;
                for (StatusModel i : ListStatus) {
                    lstStatus[count] = i.getName();
                    count++;
                }

                txtSelectStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Choose status ")
                                .setItems(lstStatus, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                        txtSelectStatus.setText(lstStatus[which]);
                                    }
                                })
                                .setNegativeButton("No", null).show();
                    }
                });

                // Set Plan date
                final DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        Date curentTime = java.util.Calendar.getInstance().getTime();
                        boolean check = false;
                        if(year >= curentTime.getYear())
                            if( monthOfYear+1 >= curentTime.getMonth())
                                if(dayOfMonth >= curentTime.getDate()){
                                    check = true;
                                }
                        if(!check)
                        {
                            Toast.makeText(getContext(),"The plan date must be after the current date!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            txtselectDate.setText( String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear +1)
                                    + "/" +String.valueOf(year) );
                        }
                    }
                };

                //button Date call DatePickerFragment
                btnDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerFragment dpf = new DatePickerFragment().newInstance();
                        dpf.setCallBack(onDate);
                        dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
                    }
                });


                alert.setView(v);
                alert.setCancelable(true);
                final AlertDialog dialog = alert.create();

                //Button Add to insert note to Database
                btnAdd.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        Session session = new Session(getActivity());

                        String Name = txtNoteName.getText().toString().trim();
                        String Category = txtSelectCategory.getText().toString().trim();
                        String Priority = txtSelectPriority.getText().toString().trim();
                        String Status = txtSelectStatus.getText().toString().trim();
                        String PlanDate = txtselectDate.getText().toString().trim();
                        String CreateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ).format(Calendar.getInstance().getTime());

                        //Check text not null
                        if(!Name.isEmpty() && !Category.isEmpty() && !Priority.isEmpty() && !Status.isEmpty() && !PlanDate.isEmpty())
                        {
                            Note note = new Note();
                            note.setName(Name);
                            note.setCategory(Category);
                            note.setPriority(Priority);
                            note.setStatus(Status);
                            note.setPlanDate(PlanDate);
                            note.setCreateDate(CreateDate);
                            note.setIdAccount(session.getIdAccount());
                            noteDao.insertNotes(note);

                            Toast.makeText(getContext(),"Add Successfully",Toast.LENGTH_SHORT).show();

                            dialog.dismiss();

                            listNote = noteDao.getAll(session.getIdAccount());
                            noteAdapter = new NoteAdapter(getActivity().getApplicationContext(), listNote);
                            recyclerView.setAdapter(noteAdapter);


                        }
                        else
                        {
                            String message = "";
                            if(Name.isEmpty())
                            {
                                message += "Name, ";
                            }
                            if(Category.isEmpty())
                            {
                                message += "Category, ";
                            }
                            if(Priority.isEmpty())
                            {
                                message += "Priority, ";
                            }
                            if(Status.isEmpty())
                            {
                                message += "Status, ";
                            }
                            if(PlanDate.isEmpty())
                            {
                                message += "Plan date, ";
                            }
                            Toast.makeText(getContext(),"Please select " + message.substring(0,message.length()-2) +"! They can't be empty!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                //Button Close to dismiss dialog
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();






            }

        });

        //Create Note Adapter
       database = Database.getInstance(getActivity().getApplicationContext());
       listNote = noteDao.getAll(session.getIdAccount());
       noteAdapter = new NoteAdapter(getActivity().getApplicationContext(),listNote);
       //Set adapter to RecyclerView
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
       recyclerView.setAdapter(noteAdapter);

        return view;
    }


    //Menu context Edit
    @Override
    public  boolean onContextItemSelected(MenuItem item)
    {
        int position = -1;
        try{
            position = noteAdapter.getPosition();
        }
        catch (Exception e){
            Log.d(TAG, e.getLocalizedMessage(),e);
            return  super.onContextItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.MenuEditNote:
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                final View v = View.inflate(getContext(), R.layout.edit_note_dialog,null);


                //get selected Note
                selectedNote = listNote.get(position);
                //btn Update
                btnUpdate = v.findViewById(R.id.btnUpdate);
                //btn Close
                btnEditClose = v.findViewById(R.id.btnEditClose);
                //btn Choose Date in Update
                btnDateEdit = v.findViewById(R.id.btnEditSelectPlanDate);
                //button text
                txtEditNoteName = v.findViewById(R.id.txtEditNoteName);
                txtEditSelectCategory = v.findViewById(R.id.txtEditSelectCategory);
                txtEditSelectPriority = v.findViewById(R.id.txtEditSelectPriority);
                txtEditSelectStatus = v.findViewById(R.id.txtEditSelectStatus);
                txtEditselectDate = v.findViewById(R.id.txtEditSelectPlanDate);
                //biding data
                txtEditNoteName.setText(selectedNote.name);
                txtEditSelectCategory.setText(selectedNote.category);
                txtEditSelectPriority.setText(selectedNote.priority);
                txtEditSelectStatus.setText(selectedNote.status);
                txtEditselectDate.setText(selectedNote.planDate);

                // Get list status
                final List<StatusModel> ListStatus  =  statusDao.getAllData(session.getIdAccount());
                final String[] lstStatus = new String[ListStatus.size()];
                int count = 0;
                for (StatusModel i : ListStatus) {
                    lstStatus[count] = i.getName();
                    count++;
                }
                txtEditSelectStatus.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Choose status ")
                                .setItems(lstStatus, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                        txtEditSelectStatus.setText(lstStatus[which]);
                                    }
                                })
                                .setNegativeButton("No", null).show();
                    }
                });
                // Get list category
                final List<CategoryModel> ListCategory  =  categoryDao.getAllData(session.getIdAccount());
                final String[] lstCategory = new String[ListCategory.size()];
                int countCategory = 0;
                for (CategoryModel i : ListCategory) {
                    lstCategory[countCategory] = i.getName();
                    countCategory++;
                }
                txtEditSelectCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Choose category ")
                                .setItems(lstCategory, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                        txtEditSelectCategory.setText(lstCategory[which]);
                                    }
                                })
                                .setNegativeButton("No", null).show();
                    }
                });
                // Get list priority
                final List<PriorityModel> ListPriority = priorityDao.getAllData(session.getIdAccount());
                final String[] lstPriority = new String[ListPriority.size()];
                int countPriority = 0;
                for (PriorityModel i : ListPriority) {
                    lstPriority[countPriority] = i.getName();
                    countPriority++;
                }
                txtEditSelectPriority.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Choose Priority ")
                                .setItems(lstPriority, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                        txtEditSelectPriority.setText(lstPriority[which]);
                                    }
                                })
                                .setNegativeButton("No", null).show();
                    }
                });

                // Set Date
                final DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        Date curentTime = java.util.Calendar.getInstance().getTime();
                        boolean check = false;
                        if(year >= curentTime.getYear())
                            if( monthOfYear+1 >= curentTime.getMonth())
                                if(dayOfMonth >= curentTime.getDate()){
                                    check = true;
                                }
                        if(!check)
                        {
                            Toast.makeText(getContext(),"The plan date must be after the current date!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            txtEditselectDate.setText( String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear +1)
                                    + "/" +String.valueOf(year));

                        }
                    }
                };

                //Button choose Date in Edit
                btnDateEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerFragment dpf = new DatePickerFragment().newInstance();
                        dpf.setCallBack(onDate);
                        dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
                    }
                });

                //Create dialog
                alert.setView(v);
                alert.setCancelable(true);
                final AlertDialog dialog = alert.create();

                //Button Update to update note into database
                btnUpdate.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String Name = txtEditNoteName.getText().toString().trim();
                        String Category = txtEditSelectCategory.getText().toString().trim();
                        String Priority = txtEditSelectPriority.getText().toString().trim();
                        String Status = txtEditSelectStatus.getText().toString().trim();
                        String PlanDate = txtEditselectDate.getText().toString().trim();
                        String CreateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ).format(Calendar.getInstance().getTime());
                        //Check text not null

                        if(!Name.isEmpty() && !Category.isEmpty() && !Priority.isEmpty() && !Status.isEmpty() && !PlanDate.isEmpty())
                        {
                            Note note = selectedNote;
                            note.setName(Name);
                            note.setCategory(Category);
                            note.setPriority(Priority);
                            note.setStatus(Status);
                            note.setPlanDate(PlanDate);
                            note.setCreateDate(CreateDate);
                            note.setIdAccount(session.getIdAccount());
                            noteDao.updateNote(note);

                            Toast.makeText(getContext(),"Update Successfully",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                            //Refresh RecyclerView
                            listNote = noteDao.getAll(session.getIdAccount());
                            noteAdapter = new NoteAdapter(getActivity().getApplicationContext(),listNote);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            recyclerView.setAdapter(noteAdapter);

                        }
                        else
                        {
                            String message = "";
                            if(Name.isEmpty())
                            {
                                message += "Name, ";
                            }
                            if(Category.isEmpty())
                            {
                                message += "Category, ";
                            }
                            if(Priority.isEmpty())
                            {
                                message += "Priority, ";
                            }
                            if(Status.isEmpty())
                            {
                                message += "Status, ";
                            }
                            if(PlanDate.isEmpty())
                            {
                                message += "Plan date, ";
                            }
                            Toast.makeText(getContext(),"Please select " + message.substring(0,message.length()-2) +"! They can't be empty!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                //Button Close to dismiss Edit Dialog
                btnEditClose.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.MenuDeleteNote:
                break;
        }
        return  super.onContextItemSelected(item);
    }
}