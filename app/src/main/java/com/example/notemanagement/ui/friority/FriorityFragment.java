package com.example.notemanagement.ui.friority;

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

import com.example.notemanagement.DB.DaoClass.FriorityDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.FriorityModel;
import com.example.notemanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FriorityFragment extends Fragment {
    private RecyclerView recyclerFriorityView;
    FriorityAdapter friorityAdapter;
    List<FriorityModel> listFriority;
    Database database;
    FriorityDaoClass friorityDao;
    EditText name;
    Button add,cancel;

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
                name = v.findViewById(R.id.txtAddFriority);
                add = v.findViewById(R.id.btnFriAdd);
                cancel = v.findViewById(R.id.btnFriCancel);
                alert.setView(v);
                alert.setCancelable(true);

                final AlertDialog dialog = alert.create();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                        else{
                            Toast.makeText(getContext(),"The input is empty!",Toast.LENGTH_SHORT).show();
                        }
                        listFriority = friorityDao.getAllData();
                        dialog.dismiss();
                        friorityAdapter = new FriorityAdapter(getActivity().getApplicationContext(),listFriority);

//        createStatusList();
                        recyclerFriorityView.setHasFixedSize(true);
                        recyclerFriorityView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        recyclerFriorityView.setAdapter(friorityAdapter);
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

        friorityDao  = database.friorityDaoClass();

        listFriority = friorityDao.getAllData();


        friorityAdapter = new FriorityAdapter(getActivity().getApplicationContext(),listFriority);

//        createStatusList();
        recyclerFriorityView.setHasFixedSize(true);
        recyclerFriorityView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerFriorityView.setAdapter(friorityAdapter);
        return view;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = friorityAdapter.getPosition();
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.MenuEditFriority:
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());//khởi tạo alert
                View v = View.inflate(getContext(),R.layout.dialog_edit_category,null);
                Button edit = v.findViewById(R.id.btnEditFriority);
                Button cancel = v.findViewById(R.id.btnCancelEditFrioritry);
                final EditText editText = v.findViewById(R.id.txtEditFriority);
                String txt = listFriority.get(position).getName();

                editText.append(txt);
                alert.setView(v);
                alert.setCancelable(true);
                final AlertDialog dialog = alert.create();
                final int finalPosition = position;
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text = editText.getText().toString().trim();
                        FriorityModel friorityModel = listFriority.get(finalPosition);
                        friorityModel.setName(text);
                        friorityDao.updateData(friorityModel);
                        listFriority = friorityDao.getAllData();
                        Toast.makeText(getContext(),"Update!",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        friorityAdapter = new FriorityAdapter(getActivity().getApplicationContext(),listFriority);

//        createStatusList();
                        recyclerFriorityView.setHasFixedSize(true);
                        recyclerFriorityView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        recyclerFriorityView.setAdapter(friorityAdapter);
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
            case R.id.MenuDeleteFriority:

                // do your stuff
                break;
        }
        return super.onContextItemSelected(item);
    }
}
