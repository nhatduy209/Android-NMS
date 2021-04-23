package com.example.notemanagement.ui.edit_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;

import com.example.notemanagement.R;

public class EditProfileFragment extends Fragment {
    Database db;
    AccountLayer accountLayer;
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

                if(firstName==null || lastName==null ||email==null){
                    //show warning
                    return;
                }

                Account account = new Account();
                account=accountLayer.findAccount(session.getEmail(),session.getPassword());
                account.email=email;
                account.firstName=firstName;
                account.lastName=lastName;

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
