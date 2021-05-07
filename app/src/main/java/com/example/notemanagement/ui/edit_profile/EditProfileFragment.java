package com.example.notemanagement.ui.edit_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.notemanagement.DB.EntityClass.AccountModel;
import com.example.notemanagement.DB.DaoClass.AccountDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;
import com.google.android.material.navigation.NavigationView;

public class EditProfileFragment extends Fragment {
    Database db;
    AccountDaoClass accountLayer;
    Session session;
    Button btnEdit;
    Button btnHome;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editprofile, container, false);

        db=Database.getInstance(getActivity());
        accountLayer=db.accountDao();
        session= new Session(getActivity());

        btnEdit=root.findViewById(R.id.btnEditProfile);
        btnHome=root.findViewById(R.id.btnHomeinEditprofile);
        //handle event SaveChange
        saveChanges();
        //handle event back home
        backHome();
        return root;
    }
    public void saveChanges(){
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String firstName = ((EditText)getActivity().findViewById(R.id.txtFirstName)).getText().toString();
                String lastName = ((EditText)getActivity().findViewById(R.id.txtLastName)).getText().toString();
                String email = ((EditText)getActivity().findViewById(R.id.txtEmail)).getText().toString();

                boolean error=false;
                // check data is not null
                if(firstName.length()==0){
                    ((EditText)getActivity().findViewById(R.id.txtFirstName)).setError(getString(R.string.validate_first_name));
                    error=true;
                }
                if(lastName.length()==0){
                    ((EditText)getActivity().findViewById(R.id.txtLastName)).setError(getString(R.string.validate_last_name));
                    error=true;
                }
                if(email.length()==0){
                    ((EditText)getActivity().findViewById(R.id.txtEmail)).setError(getString(R.string.validate_email));
                    error=true;
                }
                // check validation email
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!email.matches(emailPattern)&&email.length()>0)
                {
                    ((EditText)getActivity().findViewById(R.id.txtEmail)).setError("Invalid email address");
                    error=true;
                }
                if(error==true){
                    return;
                }

                AccountModel account = accountLayer.findAccount(session.getEmail(),session.getPassword());
                account.setEmail(email);
                account.setFirstName(firstName);
                account.setLastName(lastName);
                accountLayer.update(account);

                //change email on session
                session.setEmail(email);

                //change email on nav_bar
                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                TextView text = navigationView.getHeaderView(0).findViewById(R.id.tvEmail);
                text.setText(session.getEmail());

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