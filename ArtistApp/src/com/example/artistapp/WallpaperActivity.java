package com.example.artistapp;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.os.Build;

public class WallpaperActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wallpaper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        ImageView selectedImage;
        private Integer[] mImageIds = { R.drawable.image1, R.drawable.image2,
                R.drawable.image3, R.drawable.image4, R.drawable.image5,
                R.drawable.image6, R.drawable.image7, R.drawable.image8 };

        Button setWallpaerButton;
        
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_wallpaper,
                    container, false);
            return rootView;
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onActivityCreated(savedInstanceState);
            Gallery gallery = (Gallery) getActivity().findViewById(
                    R.id.gallery1);
            selectedImage = (ImageView) getActivity().findViewById(
                    R.id.imageView1);
            gallery.setSpacing(1);
            gallery.setAdapter(new GalleryImageAdapter(getActivity()));

            // clicklistener for Gallery
            gallery.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                        int position, long id) {
                    Toast.makeText(getActivity(),
                            "Your selected position = " + position,
                            Toast.LENGTH_SHORT).show();
                    // show the selected Image
                    selectedImage.setImageResource(mImageIds[position]);
                }
            });
            
            
            setWallpaerButton = (Button) getView().findViewById(R.id.setWallpaperButton);
            setWallpaerButton.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    setAsWallpaper(v);
                    
                }
            });
        }
        
        public void setAsWallpaper(View v) {

            selectedImage.buildDrawingCache();
            Bitmap mBitmap = selectedImage.getDrawingCache();

            WallpaperManager myWallpaperManager = WallpaperManager
            .getInstance(getActivity().getApplicationContext());
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels << 1;
            int width1=width/2;
            mBitmap = Bitmap.createScaledBitmap(mBitmap,width1, height, true);
            try {
            myWallpaperManager.setBitmap(mBitmap);
            myWallpaperManager.suggestDesiredDimensions(width1, height);
            Toast.makeText(getActivity(), "Wallpaper set",
            Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
            Toast.makeText(getActivity(), "Error setting wallpaper",
            Toast.LENGTH_SHORT).show();
            }
            }
    }

}
