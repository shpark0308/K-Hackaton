package com.example.we_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.we_in.camera.CameraCardDownFragment1;
import com.example.we_in.camera.CameraFragment;
import com.example.we_in.stamp.StampFragment;
import com.example.we_in.storage.StorageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    StampFragment stampFragment;
    CameraFragment cameraFragment;
    StorageFragment storageFragment;
    CameraCardDownFragment1 cameraCardDownFragment1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemIconSize(180);
        bottomNavigationView.setSelectedItemId(R.id.cameraItem);


        stampFragment = new StampFragment();
        cameraFragment = new CameraFragment();
        storageFragment = new StorageFragment();
        cameraCardDownFragment1 = new CameraCardDownFragment1();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,cameraFragment).commitAllowingStateLoss();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.stampItem: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,stampFragment).commitAllowingStateLoss();

                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                        return true;
                    }

                    case R.id.cameraItem: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,cameraFragment).commitAllowingStateLoss();

                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

                        return true;
                    }
                    case R.id.storageItem: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,storageFragment).commitAllowingStateLoss();

                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                        return true;
                    }
                    default: return false;
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,cameraCardDownFragment1).commitAllowingStateLoss();
                break;
        }

    }
}
