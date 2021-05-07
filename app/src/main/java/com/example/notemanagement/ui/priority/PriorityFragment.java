package com.example.notemanagement.ui.priority;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.DaoClass.PriorityDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.PriorityModel;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class PriorityFragment extends Fragment {
    private RecyclerView recyclerPriorityView;
    private Session session;
    PriorityAdapter priorityAdapter;
    List<PriorityModel> listPriority;
    Database database;
    PriorityDaoClass friorityDao;
    EditText name;
    Button add,cancel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_priority, container, false);
        recyclerPriorityView = view.findViewById(R.id.recyclerPriorityView);
        session = new Session(getActivity());
        registerForContextMenu(recyclerPriorityView);

        FloatingActionButton floating = view.findViewById(R.id.priority_fab);


        floating.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View view) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());//khởi tạo alert
                final View v = inflater.inflate(R.layout.dialog_add_priority,null);
                name = v.findViewById(R.id.txtAddPriority);
                add = v.findViewById(R.id.btnFriAdd);
                cancel = v.findViewById(R.id.btnFriCancel);
                alert.setView(v);
                alert.setCancelable(true);

                final AlertDialog dialog = alert.create();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        session = new Session(getActivity());
                        String txtName = name.getText().toString().trim();
                        String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                        if(txtName != null){
                            PriorityModel priorityModel = new PriorityModel();
                            priorityModel.setIdAccount(session.getIdAccount());
                            priorityModel.setName(txtName);
                            priorityModel.setFrCrD(createdDate);
                            friorityDao.insertData(priorityModel);

                            Toast.makeText(getContext(),"data successfully added",Toast.LENGTH_SHORT).show();
                            listPriority = friorityDao.getAllData(session.getIdAccount());
                            dialog.dismiss();
                            reload(listPriority,v);
                        }
                        else{
                            Toast.makeText(getContext(),"The input is empty!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        database = Database.getInstance(getActivity().getApplicationContext());
        friorityDao  = database.priorityDaoClass();
        listPriority = friorityDao.getAllData(session.getIdAccount());
        reload(listPriority,view);
        return view;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = priorityAdapter.getPosition();
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.MenuEditPriority:
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());//khởi tạo alert
                final View v = View.inflate(getContext(),R.layout.dialog_edit_priority,null);
                Button edit = v.findViewById(R.id.btnEditPriority);
                Button cancel = v.findViewById(R.id.btnCancelEditCategory);
                final EditText editText = v.findViewById(R.id.txtEditCategory);
                String txt = listPriority.get(position).getName();

                editText.append(txt);
                alert.setView(v);
                alert.setCancelable(true);
                final AlertDialog dialog = alert.create();
                final int finalPosition = position;
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text = editText.getText().toString().trim();
                        if(!text.isEmpty())
                        {
                            PriorityModel priorityModel = listPriority.get(finalPosition);
                            updatePriority(text,priorityModel.getName());
                            priorityModel.setName(text);
                            friorityDao.updateData(priorityModel);
                            Toast.makeText(getContext(),"Update!",Toast.LENGTH_SHORT).show();
                            listPriority = friorityDao.getAllData(session.getIdAccount());
                            dialog.dismiss();
                            reload(listPriority,view);
                        }
                        else
                        {
                            Toast.makeText(getContext(),"Name can't be null!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
//                Toast.makeText(getContext(),"The input is empty!",Toast.LENGTH_SHORT).show();
                // do your stuff
                break;
            case R.id.MenuDeletePriority:
                // do your stuff
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void updatePriority(String priority, String oldPriority){
        List<Note> list = null;
        NoteDao noteDao;
        noteDao = database.noteDao();
        list = noteDao.getNote(oldPriority);
        for(int i =0; i< list.size();i++){
            Note note;
            note = list.get(i);
            note.setPriority(priority);
            noteDao.updateNote(note);
        }
    }

    public void reload(List<PriorityModel> listPriority, View view){
        priorityAdapter = new PriorityAdapter(getActivity().getApplicationContext(),listPriority);
        recyclerPriorityView.setHasFixedSize(true);
        recyclerPriorityView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerPriorityView.setAdapter(priorityAdapter);
    }
}
