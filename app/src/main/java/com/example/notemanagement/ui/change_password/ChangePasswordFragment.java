package com.example.notemanagement.ui.change_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.notemanagement.DB.DaoClass.AccountDaoClass;
import com.example.notemanagement.DB.EntityClass.AccountModel;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.AlertDialogFragment;
import com.example.notemanagement.extension.Session;

public class ChangePasswordFragment extends Fragment {
    Database db;
    AccountDaoClass accountLayer;
    Session session;
    Button btnChangePassword;
    Button btnHome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_changepassword, container, false);
        db= Room.databaseBuilder(getActivity(),Database.class,Database.Databasename).allowMainThreadQueries().build();
        accountLayer=db.accountDao();
        session= new Session(getActivity());
        btnChangePassword=root.findViewById(R.id.btnChangePassword);
        btnHome=root.findViewById(R.id.btnHomeinChangepassword);

        saveChanges();
        backHome();
        return root;
    }
    //handle button save changes
    public void saveChanges(){
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPassword=((EditText)getActivity().findViewById(R.id.txtCurrentPassword)).getText().toString();
                String newPassword=((EditText)getActivity().findViewById(R.id.txtNewPassword)).getText().toString();
                String confirmPassword=((EditText)getActivity().findViewById(R.id.txtConfirmPassword)).getText().toString();

                if(currentPassword==null || newPassword==null || confirmPassword==null){
                    //show warning
                    return;
                }
                boolean error=false;
                // check data is not null
                if(currentPassword.length()==0){
                    ((EditText)getActivity().findViewById(R.id.txtCurrentPassword)).setError(getString(R.string.validate_password));
                    error=true;
                }
                if(newPassword.length()==0){
                    ((EditText)getActivity().findViewById(R.id.txtNewPassword)).setError(getString(R.string.validate_password));
                    error=true;
                }
                if(confirmPassword.length()==0){
                    ((EditText)getActivity().findViewById(R.id.txtConfirmPassword)).setError(getString(R.string.validate_password));
                    error=true;
                }
                if(error==true){
                    return;
                }

                if(!newPassword.equals(confirmPassword)){
                    AlertDialogFragment alert = new AlertDialogFragment(getString(R.string.tt_changepassword_fail),
                            getString(R.string.msg_password_notmatch));
                    alert.show(getActivity().getSupportFragmentManager(),"change password fail");
                    return;
                }
                String pass=session.getPassword();
                String em= session.getEmail();
                AccountModel account =accountLayer.findAccount(session.getEmail(),session.getPassword());
                //check password
                if(!account.getPassword().equals(currentPassword)){
                    AlertDialogFragment alert = new AlertDialogFragment(getString(R.string.tt_changepassword_fail),
                            getString(R.string.msg_password_incorrect));
                    alert.show(getActivity().getSupportFragmentManager(),"change password fail");
                    return;
                }
                account.setPassword(newPassword);
                session.setPassword(account.getPassword());
                accountLayer.update(account);

                Toast.makeText(getActivity(),"Change password successfully", Toast.LENGTH_SHORT).show();
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