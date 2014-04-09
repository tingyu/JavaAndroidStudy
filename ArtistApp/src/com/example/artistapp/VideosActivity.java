package com.example.artistapp;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.VideoView;
import android.os.Build;

public class VideosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.video, menu);
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

        private VideoView videoView; 
        private RadioButton videoRadioButton;
        private RadioGroup videoGroup;

        
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_video,
                    container, false);
            return rootView;
        }
        
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onActivityCreated(savedInstanceState);
            videoGroup = (RadioGroup) getView().findViewById(R.id.videoRadioGroup);
            Button playButton = (Button) getView().findViewById(R.id.buttonVideoPlay);
            
            playButton.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    int selectId = videoGroup.getCheckedRadioButtonId();

                    try 
                    {
                        playVideo(selectId);
                    } catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
            });

        }
        
        
        private void playVideo(int selectId) throws Exception{
            
          VideoView videoView =(VideoView)getActivity().findViewById(R.id.videoView1); 
            
            //Creating MediaController  
            MediaController mediaController= new MediaController(getActivity());  
            mediaController.setAnchorView(videoView);          
             
            //specify the location of media file  
            // Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/media/1.mp4");          
             Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"+R.raw.test);       
            //Setting MediaController and URI, then starting the videoView  
 
               
            switch (selectId) {
            case R.id.videoRadio0:
                uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"+R.raw.test); 
                break;
            case R.id.videoRadio1:
                uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"+R.raw.test1); 
                break;
            case R.id.videoRadio2:
                uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"+R.raw.test2); 
                break;
            default:
                break;
            }
            videoView.setMediaController(mediaController);  
            videoView.setVideoURI(uri);     
          //videoView.setVideoPath("/sdcard/videooutput.mp4");

            videoView.requestFocus();  
            videoView.start();  
            
        }
    }

}
