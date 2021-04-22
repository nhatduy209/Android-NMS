package com.example.notemanagement.ui.friority;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.DaoClass.FriorityDaoClass;
import com.example.notemanagement.DB.DaoClass.StatusDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.FriorityModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.R;
import com.example.notemanagement.ui.status.StatusAdapter;
import com.example.notemanagement.ui.status.StatusViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FriorityFragment extends Fragment {
    private RecyclerView recyclerFriorityView;
    FriorityAdapter friorityAdapter;
    List<FriorityModel> listFriority;
    Database database;
    FriorityDaoClass friorityDao;
    EditText name;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friority, container, false);
        recyclerFriorityView = view.findViewById(R.id.recyclerFriorityView);
        registerForContextMenu(recyclerFriorityView);

        FloatingActionButton floating = view.findViewById(R.id.friority_fab);


        floating.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View view) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());//khởi tạo alert
                View v = inflater.inflate(R.layout.dialog_add_friority,null);
                alert.setView(v);
                alert.setCancelable(true);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String txtName = name.getText().toString().trim();
                        String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                        if(txtName != null){
                            FriorityModel friorityModel = new FriorityModel();
                            friorityModel.setIdAccount("1");
                            friorityModel.setName(txtName);
                            friorityModel.setFrCrD(createdDate);
                            friorityDao.insertData(friorityModel);

                            Toast.makeText(getContext(),"data successfully added",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                name = v.findViewById(R.id.txtAddFriority);
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
        database = Database.getInstance(getActivity().getApplicationContext());

        friorityDao  = database.friorityDaoClass();

        listFriority = friorityDao.getAllData();
        if(listFriority.size() == 0){
            FriorityModel friorityModel = new FriorityModel();
            friorityModel.setIdAccount("1");
            friorityModel.setName("0");
            friorityModel.setFrCrD("High");
            friorityDao.insertData(friorityModel);

            Toast.makeText(getContext(),"data successfully added",Toast.LENGTH_SHORT).show();
        }


        friorityAdapter = new FriorityAdapter(getActivity().getApplicationContext(),listFriority);

//        createStatusList();
        recyclerFriorityView.setHasFixedSize(true);
        recyclerFriorityView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerFriorityView.setAdapter(friorityAdapter);
        return view;
    }
}
