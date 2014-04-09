package com.example.artistapp;

import com.example.artistapp.MainActivity.PlaceholderFragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.R.integer;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Build;

public class SongsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.songs, menu);
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
        
        private MediaPlayer mediaPlayer;
        private int playbackPosition=0;
        static final String AUDIO_PATH =
                "http://URL to .mp3 file";
        private RadioButton radioButton;
        private RadioGroup songGroup;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_songs,
                    container, false);
            return rootView;
        }
        
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onActivityCreated(savedInstanceState);
            
            songGroup = (RadioGroup) getActivity().findViewById(R.id.radioGroup1);
            Button buttonPlay = (Button) getActivity().findViewById(R.id.buttonPlay);
            Button buttonPause = (Button) getActivity().findViewById(R.id.buttonPause);
            Button buttonRePlay = (Button) getActivity().findViewById(R.id.buttonRePlay);
          
            buttonPlay.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // get selected radio button from radioGroup
                    int selectId = songGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) getActivity().findViewById(selectId);
                    
                    try 
                    {
                     //   playAudio(AUDIO_PATH);
                        playLocalAudio(selectId);
                     //   playLocalAudio_UsingDescriptor(selectId);
                    } catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
               
            });
            
            buttonPause.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if(mediaPlayer!=null)
                    {
                        playbackPosition = mediaPlayer.getCurrentPosition();
                        mediaPlayer.pause();
                    }
                }
            });
            
            buttonRePlay.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if(mediaPlayer!=null && !mediaPlayer.isPlaying())
                    {
                        mediaPlayer.seekTo(playbackPosition);
                        mediaPlayer.start();
                    }
                }
            });
            
        }
        

        private void playAudio(String url)throws Exception
        {
                    //killMediaPlayer();
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
        }

        
        @Override
        public void onDestroy()
        {
                super.onDestroy();
                killMediaPlayer();
        }
        
        private void killMediaPlayer()
        {
            if(mediaPlayer!=null)
            {
                try
                {
                    mediaPlayer.release();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
         }  

        private void playLocalAudio(int selectId)throws Exception
        {
            switch (selectId) {
            case R.id.radio0:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.audio_test);
                break;
            case R.id.radio1:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.audio_test1);
                break;
            case R.id.radio2:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.audio_test2);
                break;
            default:
                break;
            }
            mediaPlayer.start();
        }
        
        private void playLocalAudio_UsingDescriptor(int selectId) throws Exception 
        {
            AssetFileDescriptor fileDesc=null;
            switch (selectId) {
            case 0:
                fileDesc = getResources().openRawResourceFd(R.raw.audio_test);
                break;
            case 1:
                fileDesc = getResources().openRawResourceFd(R.raw.audio_test1);
                break;
            case 2:
                fileDesc = getResources().openRawResourceFd(R.raw.audio_test2);
                break;
            default:
                break;
            }
            //AssetFileDescriptor fileDesc = getResources().openRawResourceFd(R.raw.music_file);
            if (fileDesc != null) 
            {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(fileDesc.getFileDescriptor(), fileDesc.getStartOffset(), fileDesc.getLength());
                fileDesc.close();
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        }
    }

}
