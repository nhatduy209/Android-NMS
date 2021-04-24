package com.example.notemanagement.ui.note;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.DaoClass.FriorityDaoClass;
import com.example.notemanagement.DB.DaoClass.StatusDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.FriorityModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddNoteDialog extends DialogFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    TextView txtselectDate;
    Button btnClose;
    Button btnAdd;
    EditText txtNoteName;
    TextView txtSelectCategory;
    TextView txtSelectPriority;
    TextView txtSelectStatus;
    Database database ;
    NoteAdapter noteAdapter;
    RecyclerView recyclerView;
    Session session ;

    public AddNoteDialog() {
        // Empty constructor required for DialogFragment
    }

    public static AddNoteDialog newInstance() {
        AddNoteDialog frag = new AddNoteDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create view
        View view = inflater.inflate(R.layout.add_note_dialog, container, false);

        //connect database
        database = Database.getInstance(getActivity().getApplicationContext());
        session= new Session(getActivity());

        final NoteDao noteDao = database.noteDao();
        List<Note> listNotes = noteDao.getAll(session.getIdAccount());
        final NoteAdapter noteAdapter = new NoteAdapter(getActivity().getApplicationContext(), listNotes);

        final StatusDaoClass statusDao = database.statusDaoClass();
        final FriorityDaoClass priorityDao = database.friorityDaoClass();
        //button choose Date
        Button btnDate = view.findViewById(R.id.btnSelectPlanDate);
        txtselectDate = view.findViewById(R.id.txtSelectPlanDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(AddNoteDialog.this, 0);
                datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });

        //button Close
        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view)
            {
                dismiss();
            }

        });

        //button text
        txtNoteName = view.findViewById(R.id.txtNoteName);
        txtSelectCategory = view.findViewById(R.id.txtSelectCategory);
        txtSelectPriority = view.findViewById(R.id.txtSelectPriority);
        txtSelectStatus = view.findViewById(R.id.txtSelectStatus);

        //button Add
        btnAdd = view.findViewById(R.id.btnAdd);

            btnAdd.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View view)
                {
                    Session session = new Session(getActivity());
                    String Name = txtNoteName.getText().toString().trim();
                    String Category = txtSelectCategory.getText().toString().trim();
                    String Priority = txtSelectPriority.getText().toString().trim();
                    String Status = txtSelectStatus.getText().toString().trim();
                    String PlanDate = txtselectDate.getText().toString().trim();
                    String CreateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ).format(Calendar.getInstance().getTime());
                    int IdAccount=session.getIdAccount();
                    if(Name != null)
                    {
                        Note note = new Note();
                        note.setId(session.getIdAccount());
                        note.setName(Name);
                        note.setCategory(Category);
                        note.setPriority(Priority);
                        note.setStatus(Status);
                        note.setPlanDate(PlanDate);
                        note.setCreateDate(CreateDate);
                        note.setIdAccount(session.getIdAccount());
                        noteDao.insertNotes(note);


                       /* //reset recycler view
                        recyclerView = view.findViewById(R.id.recyclerview);
                        List<Note> listNotes = noteDao.getAll();

                        noteAdapter.notifyDataSetChanged();
*/


                        Toast.makeText(getContext(),"Add Successfully",Toast.LENGTH_SHORT).show();

                        dismiss();


                        dismiss();
                        Toast.makeText(getContext(),"Add Successfully",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // text view status
        txtSelectStatus =txtSelectStatus.findViewById(R.id.txtSelectStatus);
        final List<StatusModel> ListStatus  =  statusDao.getAllData();
        final String[] lstStatus = new String[ListStatus.size()];
        int count = 0;
        for (StatusModel i : ListStatus) {
            lstStatus[count] = i.getName();
            count++;
        }
        txtSelectStatus.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
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
                return false;
            }
        });
        // text view priority
        txtSelectPriority =txtSelectPriority.findViewById(R.id.txtSelectPriority);
        final List<FriorityModel> ListPriority = priorityDao.getAllData();
        final String[] lstPriority = new String[ListPriority.size()];
        int countPriority = 0;
        for (FriorityModel i : ListPriority) {
            lstPriority[countPriority] = i.getName();
            countPriority++;
        }
        txtSelectPriority.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
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
       txtselectDate.setText(dayOfMonth + "/"+ monthOfYear + "/" + year);
    }


}
