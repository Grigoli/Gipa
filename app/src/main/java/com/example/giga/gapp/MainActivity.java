package com.example.giga.gapp;

import android.app.Activity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.content.Context;


public class MainActivity extends Activity implements OnClickListener {

 private ProgressBar playSeekBar;

 private Button buttonPlay;

 private Button buttonmenu;

 private Button buttonStopPlay;

 private MediaPlayer player;


 SeekBar music=null;
 AudioManager mgr=null;

 /** Called when the activity is first created. */
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);



  initializeMediaPlayer();



  initializeUIElements();



     NotificationPanel nPanel = new NotificationPanel(this);






  //test

  mgr=(AudioManager)getSystemService(Context.AUDIO_SERVICE);


  music=(SeekBar)findViewById(R.id.music);




  initBar(music, AudioManager.STREAM_MUSIC);

 }


 private void initBar(SeekBar bar, final int stream) {
  bar.setMax(mgr.getStreamMaxVolume(stream));
  bar.setProgress(mgr.getStreamVolume(stream));

  bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
   public void onProgressChanged(SeekBar bar, int progress,
                                 boolean fromUser) {
    mgr.setStreamVolume(stream, progress,
            AudioManager.FLAG_PLAY_SOUND);
   }

   public void onStartTrackingTouch(SeekBar bar) {
    // no-op
   }

   public void onStopTrackingTouch(SeekBar bar) {
    // no-op
   }
  });
 }

 //test





 private void initializeUIElements() {



  playSeekBar = (ProgressBar) findViewById(R.id.progressBar1);
  playSeekBar.setMax(99);
  playSeekBar.setVisibility(View.INVISIBLE);
  buttonmenu = (Button) findViewById(R.id.button5);
  buttonmenu.setOnClickListener(this);


  buttonPlay = (Button) findViewById(R.id.buttonPlay);
  buttonPlay.setOnClickListener(this);


  buttonStopPlay = (Button) findViewById(R.id.buttonStopPlay);
  buttonStopPlay.setOnClickListener(this);
     buttonStopPlay.setVisibility(Button.GONE);
 }

 public void onClick(View v) {

 if (v==buttonmenu){  Intent i = new Intent(getApplicationContext(),social.class);
  startActivity(i);}else {

  if (v == buttonPlay) {
   startPlaying();
  } else if (v == buttonStopPlay) {
   stopPlaying();
  }


  switch (v.getId()) {
   case R.id.buttonPlay:
    // play music here
    buttonPlay.setVisibility(Button.GONE);
    buttonStopPlay.setVisibility(Button.VISIBLE);
    break;
   case R.id.buttonStopPlay:
    // pause music here
    buttonPlay.setVisibility(Button.VISIBLE);
    buttonStopPlay.setVisibility(Button.GONE);
    break;
  }

 }

 }

 private void startPlaying() {

  player.prepareAsync();

  player.setOnPreparedListener(new OnPreparedListener() {

   public void onPrepared(MediaPlayer mp) {
    player.start();
   }
  });

  buttonStopPlay.setEnabled(true);
  buttonPlay.setEnabled(false);


  playSeekBar.setVisibility(View.VISIBLE);





 }

 public void stopPlaying() {
  if (player.isPlaying()) {
   player.stop();
   player.release();
   initializeMediaPlayer();
  }
  buttonPlay.setEnabled(true);
  buttonStopPlay.setEnabled(false);
     playSeekBar.setVisibility(View.INVISIBLE);

 }

 private void initializeMediaPlayer() {

  player = new MediaPlayer();
  try {
   player.setDataSource("http://31.146.205.144:8000/livemp3");
  } catch (IllegalArgumentException e) {
   e.printStackTrace();
  } catch (IllegalStateException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }

  player.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {

   public void onBufferingUpdate(MediaPlayer mp, int percent) {
    playSeekBar.setSecondaryProgress(percent);
    Log.i("Buffering", "" + percent);
   }
  });
 }

 @Override
 protected void onPause() {
  super.onPause();
  if (player.isPlaying()) {


  }
 }
}
