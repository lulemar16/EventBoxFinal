package com.example.eventbox.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.eventbox.R;

import java.util.ArrayList;
import java.util.List;

public class DuringFragment extends Fragment {

    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private static final int TAKE_PICTURE = 101;
    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200;
    private Context context;
    ImageButton btnCamera;
    LinearLayout photosLayout;
    List<Bitmap> photosList = new ArrayList<>(); // List to store the photos taken
    ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_during, container, false);

        context = getContext();

        btnCamera = rootView.findViewById(R.id.camera_button);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        photosLayout = rootView.findViewById(R.id.photos_layout); // LinearLayout to hold the photos
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int dpWidth = (int) ((displayMetrics.widthPixels / getResources().getDisplayMetrics().density) * 0.8); // 80% of the device width in dp

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Bundle extras = result.getData().getExtras();
                Bitmap imgBitmap = (Bitmap) extras.get("data");
                photosList.add(imgBitmap); // Add the new photo to the list

                // Create a new ImageView for the new photo and add it to the view
                ImageView imageView = new ImageView(context);
                imageView.setImageBitmap(imgBitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8); // Set the padding between images
                photosLayout.addView(imageView);
                // Increase the size of all images in the photosLayout
                for (int i = 0; i < photosLayout.getChildCount(); i++) {
                    View childView = photosLayout.getChildAt(i);
                    ViewGroup.LayoutParams params = childView.getLayoutParams();
                    params.width = dpWidth*3;
                    params.height = dpWidth*3;
                    childView.setLayoutParams(params);
                }
            }
        });

        return rootView;
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(context.getPackageManager()) != null){
            cameraLauncher.launch(intent);
        }
    }
}
