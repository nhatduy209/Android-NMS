package com.example.notemanagement.ui.edit_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;

import com.example.notemanagement.R;

public class EditProfileFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editprofile, container, false);
        final TextView textView = root.findViewById(R.id.texteditprofile);
        return root;
    }
}
