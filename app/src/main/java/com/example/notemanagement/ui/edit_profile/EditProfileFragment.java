package com.example.notemanagement.ui.edit_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.notemanagement.DB.EntityClass.AccountModel;
import com.example.notemanagement.DB.DaoClass.AccountDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;

public class EditProfileFragment extends Fragment {
    Database db;
    AccountDaoClass accountLayer;
    Session session;
    Button btnEdit;
    Button btnHome;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editprofile, container, false);
        db= Room.databaseBuilder(getActivity(),Database.class,Database.Databasename).allowMainThreadQueries().build();
        accountLayer=db.accountDao();
        session= new Session(getActivity());

        btnEdit=root.findViewById(R.id.btnEditProfile);
        btnHome=root.findViewById(R.id.btnHomeinEditprofile);

        saveChanges();
        backHome();
        return root;
    }
    //handle event click on save changes
    public void saveChanges(){
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String firstName = ((EditText)getActivity().findViewById(R.id.txtFirstName)).getText().toString();
                String lastName = ((EditText)getActivity().findViewById(R.id.txtLastName)).getText().toString();
                String email = ((EditText)getActivity().findViewById(R.id.txtEmail)).getText().toString();

                if(firstName.length()==0 || lastName.length()==0 ||email.length()==0){
                    //show warning
                    return;
                }

                AccountModel account = new AccountModel();
                account=accountLayer.findAccount(session.getEmail(),session.getPassword());
                account.setEmail(email);
                account.setFirstName(firstName);
                account.setLastName(lastName);

                accountLayer.update(account);
                Toast.makeText(getActivity(),"Edit profile successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void backHome(){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}