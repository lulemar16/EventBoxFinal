package com.example.eventbox.ui.profile;

import android.app.AlertDialog;
import android.app.Dialog;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.eventbox.DataBaseHelper;
import com.example.eventbox.MainActivity;
import com.example.eventbox.R;
import com.example.eventbox.UserModel;
import com.example.eventbox.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        UserModel current_user = MainActivity.getCurrentUser();
        String currentUserName = current_user.getName();
        String currentUserEmail = current_user.getEmail();
        String currentUserPassword = current_user.getPassword();

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        TextView userName = root.findViewById(R.id.userNameView);
        TextView email = root.findViewById(R.id.emailView);

        userName.setText(currentUserName);
        email.setText(currentUserEmail);

        binding.btnReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProfileFragment.this)
                        .navigate(R.id.action_nav_profile_to_nav_home);
            }
        });

        binding.btnImage.btnToChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new Dialog object
                Dialog changePasswordDialog = new Dialog(getContext());
                changePasswordDialog.setContentView(R.layout.change_password_popup);

                changePasswordDialog.show();

                // Find the current password, new password, and confirm password fields in the dialog
                EditText currentPasswordEditText = changePasswordDialog.findViewById(R.id.edit_text_current_password);
                EditText newPasswordEditText = changePasswordDialog.findViewById(R.id.edit_text_new_password);
                EditText confirmPasswordEditText = changePasswordDialog.findViewById(R.id.edit_text_confirm_password);

                // Find the change password button in the dialog
                Button changePasswordButton = changePasswordDialog.findViewById(R.id.button_change_password);

                // Set an OnClickListener for the change password button in the dialog
                changePasswordButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get the values entered in the password fields
                        String currentPassword = currentPasswordEditText.getText().toString();
                        String newPassword = newPasswordEditText.getText().toString();
                        String confirmPassword = confirmPasswordEditText.getText().toString();


                        // Validate the password fields and change the password
                        if (!currentPassword.equals(currentUserPassword)) {
                            Toast.makeText(getContext(), "Wrong current password", Toast.LENGTH_SHORT).show();
                        } else if (!newPassword.equals(confirmPassword)) {
                            Toast.makeText(getContext(), "New passwords do not coincide", Toast.LENGTH_SHORT).show();
                        } else if (currentPassword.equals(confirmPassword)) {
                            Toast.makeText(getContext(), "Cannot use the same password", Toast.LENGTH_SHORT).show();
                        } else {
                            DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                            dataBaseHelper.updateUserPassword(current_user, newPassword);
                            Toast.makeText(getContext(), "Password changed", Toast.LENGTH_SHORT).show();
                            changePasswordDialog.dismiss();
                        }

                    }
                });
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}