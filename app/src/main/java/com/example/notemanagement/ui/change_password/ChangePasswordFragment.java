package com.example.notemanagement.ui.change_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.notemanagement.DB.Account;
import com.example.notemanagement.DB.AccountLayer;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;

public class ChangePasswordFragment extends Fragment {
    Database db;
    AccountLayer accountLayer;
    Session session;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        db= Room.databaseBuilder(getActivity(),Database.class,Database.Databasename).allowMainThreadQueries().build();
        accountLayer=db.accountDao();
        session= new Session(getActivity());

        View root = inflater.inflate(R.layout.fragment_changepassword, container, false);
//        final TextView textView = root.findViewById(R.id.textchangepass);
        return root;
    }
    //handle button save changes
    public void saveChanges(){
        Button btn = getActivity().findViewById(R.id.btnChangePassword);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPassword=((EditText)getActivity().findViewById(R.id.txtCurrentPassword)).getText().toString();
                String newPassword=((EditText)getActivity().findViewById(R.id.txtNewPassword)).getText().toString();
                String confirmPassword=((EditText)getActivity().findViewById(R.id.txtConfirmPassword)).getText().toString();

                if(currentPassword==null || newPassword==null || confirmPassword==null){
                    //show warning
                    return;
                }

                if(!newPassword.equals(confirmPassword)){
                    //password is not match
                    return;
                }

                Account account = new Account();
                account= accountLayer.findAccount(session.getEmail(),session.getPassword());
                account.password=newPassword;
                accountLayer.update(account);

                Toast.makeText(getActivity(),"Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
