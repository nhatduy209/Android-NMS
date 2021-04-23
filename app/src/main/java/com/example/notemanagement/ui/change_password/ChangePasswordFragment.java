package com.example.notemanagement.ui.change_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.notemanagement.R;

public class ChangePasswordFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_changepassword, container, false);
//        final TextView textView = root.findViewById(R.id.textchangepass);
        return root;
    }
}
