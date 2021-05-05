
package com.example.notemanagement.ui.status;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.notemanagement.DB.DaoClass.StatusDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class StatusFragment extends Fragment {

    // Add RecyclerView member
    private RecyclerView recyclerStatusView;
    private Session session;
    StatusAdapter statusAdapter;
    List<StatusModel> listStatus;
    Database database;
    StatusDaoClass statusDao;
    EditText name;
    Button add,cancel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        recyclerStatusView = view.findViewById(R.id.recyclerStatusView);
        session = new Session(getActivity());
        registerForContextMenu(recyclerStatusView);

        FloatingActionButton floating = view.findViewById(R.id.status_fab);


        floating.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View view) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());//khởi tạo alert
                View v = inflater.inflate(R.layout.dialog_add_status,null);
                name = v.findViewById(R.id.txtAddStatus);
                add = v.findViewById(R.id.btnStatusAdd);
                cancel = v.findViewById(R.id.btnStatusCancel);
                alert.setView(v);

                final AlertDialog dialog = alert.create();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        session = new Session(getActivity());
                        String txtName = name.getText().toString().trim();
                        String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                        if(!txtName.isEmpty()){
                            StatusModel statusModel = new StatusModel();
                            statusModel.setIdAccount(session.getIdAccount());
                            statusModel.setName(txtName);
                            statusModel.setStCrD(createdDate);
                            statusDao.insertData(statusModel);
                            Toast.makeText(getContext(),"data successfully added",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            listStatus = statusDao.getAllData(session.getIdAccount());
                            statusAdapter = new StatusAdapter(getActivity().getApplicationContext(),listStatus);
                            recyclerStatusView.setAdapter(statusAdapter);
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

        statusDao  = database.statusDaoClass();

        listStatus = statusDao.getAllData(session.getIdAccount());



        statusAdapter = new StatusAdapter(getActivity().getApplicationContext(),listStatus);

//        createStatusList();
        recyclerStatusView.setHasFixedSize(true);
        recyclerStatusView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerStatusView.setAdapter(statusAdapter);
        return view;
    }
    private void AddStatus(){

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position =  statusAdapter.getPosition();
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.MenuEditStatus:
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());//khởi tạo alert
                View v = View.inflate(getContext(),R.layout.dialog_edit_status,null);
                final Button edit = v.findViewById(R.id.btnEditStatus);
                Button cancel = v.findViewById(R.id.btnCancelEditStatus);
                final EditText editText = v.findViewById(R.id.txtEditStatus);
                String txt = listStatus.get(position).getName();

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
                            StatusModel statusModel = listStatus.get(finalPosition);
                            statusModel.setName(text);
                            statusDao.updateData(statusModel);
                            Toast.makeText(getContext(),"Update!",Toast.LENGTH_SHORT).show();
                            listStatus = statusDao.getAllData(session.getIdAccount());
                            dialog.dismiss();
                            statusAdapter = new StatusAdapter(getActivity().getApplicationContext(),listStatus);
                            recyclerStatusView.setHasFixedSize(true);
                            recyclerStatusView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            recyclerStatusView.setAdapter(statusAdapter);
                        }
                        else{
                            Toast.makeText(getContext(),"Name can't be null",Toast.LENGTH_SHORT).show();
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
            case R.id.MenuDeleteCategory:

                // do your stuff
                break;
        }
        return super.onContextItemSelected(item);
    }
}