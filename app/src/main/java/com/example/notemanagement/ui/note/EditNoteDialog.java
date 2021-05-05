package com.example.notemanagement.ui.note;

import android.app.DatePickerDialog;
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

import com.example.notemanagement.DB.Database;
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
    List<Note> listNote;


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


        Session session = new Session(getContext());
        int id = session.getIdNote();

        //connect database
        database = Database.getInstance(getActivity().getApplicationContext());

        final NoteDao noteDao = database.noteDao();
        final Note selectedNote = noteDao.getNote(id);


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
                    noteDao.updateNote(note);


                    Toast.makeText(getContext(),"Update Successfully",Toast.LENGTH_SHORT).show();
                    dismiss();


                }
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
