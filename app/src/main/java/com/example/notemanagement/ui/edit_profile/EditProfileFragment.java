package com.example.notemanagement.ui.edit_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notemanagement.DB.Account;
import com.example.notemanagement.DB.AccountLayer;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.MainActivity;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;
import com.example.notemanagement.SignInActivity;

public class EditProfileFragment extends Fragment {
    Database db;
    AccountLayer accountLayer;
    Session session;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        db= Database.getInstance(getActivity());
//        accountLayer=db.accountDao();
//        session= new Session(getActivity());
//        //button savechange
//        saveChanges();
        //button home
       // backHome();
//        final TextView textView = root.findViewById(R.id.texteditprofile);
        View root = inflater.inflate(R.layout.fragment_editprofile, container, false);
//        final TextView textView = root.findViewById(R.id.textchangepass);
        return root;
    }
    //handle event click on save changes
    public void saveChanges(){
        Button btn = getActivity().findViewById(R.id.btnEditProfile);
        btn.setOnClickListener(new View.OnClickListener(){
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
            }
        });
    }
    public void backHome(){
        Button btn= getActivity().findViewById(R.id.btnHomeinEditprofile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}
