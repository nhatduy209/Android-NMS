package com.example.notemanagement.ui.note;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.notemanagement.DB.DaoClass.CategoryDaoClass;
import com.example.notemanagement.DB.DaoClass.FriorityDaoClass;
import com.example.notemanagement.DB.DaoClass.StatusDaoClass;
import com.example.notemanagement.DB.Database;

import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.FriorityModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class EditNoteDialog extends DialogFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    TextView txtEditselectDate;
    Button btnEditClose;
    Button btnUpdate;
    EditText txtEditNoteName;
    TextView txtEditSelectCategory;
    TextView txtEditSelectPriority;
    TextView txtEditSelectStatus;
    Database database ;
    NoteAdapter noteAdapter;
    Note selectedNote = new Note();


    public EditNoteDialog() {
        // Empty constructor required for DialogFragment
    }

    public static EditNoteDialog newInstance() {
        EditNoteDialog frag = new EditNoteDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create view
        View view = inflater.inflate(R.layout.edit_note_dialog, container, false);


        final Session session = new Session(getContext());
        int id = session.getIdNote();

        //connect database
        database = Database.getInstance(getActivity().getApplicationContext());

        final NoteDao noteDao = database.noteDao();
        final CategoryDaoClass categoryDao = database.categoryDaoClass();
        final StatusDaoClass statusDao = database.statusDaoClass();
        final FriorityDaoClass priorityDao = database.friorityDaoClass();

        selectedNote = noteDao.getNote(id);





        //button choose Date
        Button btnDate = view.findViewById(R.id.btnEditSelectPlanDate);
        txtEditselectDate = view.findViewById(R.id.txtEditSelectPlanDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(EditNoteDialog.this, 0);
                datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });




        //button Close
        btnEditClose = view.findViewById(R.id.btnEditClose);
        btnEditClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view)
            {
                dismiss();
            }

        });

        //button text
        txtEditNoteName = view.findViewById(R.id.txtEditNoteName);
        txtEditSelectCategory = view.findViewById(R.id.txtEditSelectCategory);
        txtEditSelectPriority = view.findViewById(R.id.txtEditSelectPriority);
        txtEditSelectStatus = view.findViewById(R.id.txtEditSelectStatus);

        txtEditNoteName.setText(selectedNote.name);
        txtEditSelectCategory.setText(selectedNote.category);
        txtEditSelectPriority.setText(selectedNote.priority);
        txtEditSelectStatus.setText(selectedNote.status);
        txtEditselectDate.setText(selectedNote.planDate);

        //button update
        btnUpdate = view.findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view)
            {
                String Name = txtEditNoteName.getText().toString().trim();
                String Category = txtEditSelectCategory.getText().toString().trim();
                String Priority = txtEditSelectPriority.getText().toString().trim();
                String Status = txtEditSelectStatus.getText().toString().trim();
                String PlanDate = txtEditselectDate.getText().toString().trim();
                String CreateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ).format(Calendar.getInstance().getTime());

                if(Name != null)
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
                    dismiss();


                }
            }
        });
        // text view status
        txtEditSelectStatus =txtEditSelectStatus.findViewById(R.id.txtEditSelectStatus);
        final List<StatusModel> ListStatus  =  statusDao.getAllData();
        final String[] lstStatus = new String[ListStatus.size()];
        int count = 0;
        for (StatusModel i : ListStatus) {
            lstStatus[count] = i.getName();
            count++;
        }
        txtEditSelectStatus.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
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
                return false;
            }
        });
        // text view category
        txtEditSelectCategory =txtEditSelectCategory.findViewById(R.id.txtEditSelectCategory);
        final List<CategoryModel> ListCategory  =  categoryDao.getAllData();
        final String[] lstCategory = new String[ListCategory.size()];
        int countCategory = 0;
        for (CategoryModel i : ListCategory) {
            lstCategory[countCategory] = i.getName();
            countCategory++;
        }
        txtEditSelectCategory.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
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
                return false;
            }
        });
        // text view priority
        txtEditSelectPriority =txtEditSelectPriority.findViewById(R.id.txtEditSelectPriority);
        final List<FriorityModel> ListPriority = priorityDao.getAllData();
        final String[] lstPriority = new String[ListPriority.size()];
        int countPriority = 0;
        for (FriorityModel i : ListPriority) {
            lstPriority[countPriority] = i.getName();
            countPriority++;
        }
        txtEditSelectPriority.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
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
                return false;
            }
        });






        return view;
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        showSetDate(year,monthOfYear,dayOfMonth);
    }

    private void showSetDate(int year, int monthOfYear, int dayOfMonth) {
        txtEditselectDate.setText(dayOfMonth + "/"+ monthOfYear + "/" + year);
    }


}