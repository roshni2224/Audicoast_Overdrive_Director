package com.example.audicoastoverdrivedirector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class MusicPlayActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvTime, tvDuration, tvTitle, tvArtist;
    SeekBar seekBarTime, seekBarVolume;
    Button btnPlay,btnPrev,btnNext;
    MediaPlayer musicPlayer;
    ImageView back_to_songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        Song song = (Song) getIntent().getSerializableExtra("song");

        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnPlay = findViewById(R.id.btnPlay);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);


        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setMarqueeRepeatLimit(-1);
        tvTitle.setHorizontallyScrolling(true);
        tvTitle.setSingleLine();
        tvTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvTitle.setSelected(true);
        tvTitle.setText(song.getTitle());




        tvArtist = findViewById(R.id.tvArtist);
        tvArtist.setMarqueeRepeatLimit(-1);
        tvArtist.setHorizontallyScrolling(true);
        tvArtist.setSingleLine();
        tvArtist.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvArtist.setSelected(true);
        tvArtist.setText(song.getArtist());


        //btnNext--->Starts
        //===============================================================================
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicPlayer!=null)
                {
                    musicPlayer.stop();
                    musicPlayer=MediaPlayer.create(MusicPlayActivity.this,R.raw.song4);
                    tvTitle = findViewById(R.id.tvTitle);
                    tvTitle.setMarqueeRepeatLimit(-1);
                    tvTitle.setHorizontallyScrolling(true);
                    tvTitle.setSingleLine();
                    tvTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    tvTitle.setSelected(true);
                    tvTitle.setText("One Dance || Olagist.co");

                    tvArtist = findViewById(R.id.tvArtist);
                    tvArtist.setMarqueeRepeatLimit(-1);
                    tvArtist.setHorizontallyScrolling(true);
                    tvArtist.setSingleLine();
                    tvArtist.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    tvArtist.setSelected(true);
                    tvArtist.setText("Drake ft Wizkid & Kyla");
                    musicPlayer.start();





                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(musicPlayer!=null)
                            {
                                musicPlayer.stop();
                                musicPlayer=MediaPlayer.create(MusicPlayActivity.this,R.raw.song8);
                                tvTitle = findViewById(R.id.tvTitle);
                                tvTitle.setMarqueeRepeatLimit(-1);
                                tvTitle.setHorizontallyScrolling(true);
                                tvTitle.setSingleLine();
                                tvTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                                tvTitle.setSelected(true);
                                tvTitle.setText("Can't Let Go (Official Audio)");

                                tvArtist = findViewById(R.id.tvArtist);
                                tvArtist.setMarqueeRepeatLimit(-1);
                                tvArtist.setHorizontallyScrolling(true);
                                tvArtist.setSingleLine();
                                tvArtist.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                                tvArtist.setSelected(true);
                                tvArtist.setText("Faydee");
                                musicPlayer.start();
                            }
                        }
                    });



                }
            }
        });

        //btnNext--->Ends
        //======================================================================================




        //btnPrev---> Starts

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicPlayer!=null)
                {
                    musicPlayer.stop();
                    musicPlayer=MediaPlayer.create(MusicPlayActivity.this,R.raw.song4);
                    tvTitle = findViewById(R.id.tvTitle);
                    tvTitle.setMarqueeRepeatLimit(-1);
                    tvTitle.setHorizontallyScrolling(true);
                    tvTitle.setSingleLine();
                    tvTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    tvTitle.setSelected(true);
                    tvTitle.setText("One Dance || Olagist.co");

                    tvArtist = findViewById(R.id.tvArtist);
                    tvArtist.setMarqueeRepeatLimit(-1);
                    tvArtist.setHorizontallyScrolling(true);
                    tvArtist.setSingleLine();
                    tvArtist.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    tvArtist.setSelected(true);
                    tvArtist.setText("Drake ft Wizkid & Kyla");
                    musicPlayer.start();
                }
            }
        });



        //===============================================================================
        //btnPrev---> Ends
  
        musicPlayer = new MediaPlayer();
        try {
            musicPlayer.setDataSource(song.getPath());
            musicPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        musicPlayer.setLooping(true);
        musicPlayer.seekTo(0);
        musicPlayer.setVolume(0.5f, 0.5f);

        String duration = milisecondsToString(musicPlayer.getDuration());
        tvDuration.setText(duration);


        //musicPlayer.start();
        btnPlay.setOnClickListener(this);
        seekBarVolume.setProgress(50);
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean isfromUser) {
                float volume = progress / 100f;
                musicPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTime.setMax(musicPlayer.getDuration());
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean isfromUser) {
                if (isfromUser) {
                    musicPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicPlayer != null) {
                    if (musicPlayer.isPlaying()) {
                        try {

                            final double current = musicPlayer.getCurrentPosition();
//double duration = musicPlayer.getDuration();
//final double position = (100.0/duration)*current;
                            final String elapsedTime = milisecondsToString((int) current);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvTime.setText(elapsedTime);


                                    seekBarTime.setProgress((int) current);
                                }
                            });

                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }).start();



    }

    public String milisecondsToString(int time)
    {
        String elapsedTime=" ";
        int minutes = time/1000/60;
        int seconds = time/1000%60;
        elapsedTime = minutes+":";
        if(seconds<10)
        {
            elapsedTime +="0";
        }
        elapsedTime +=seconds;
        return elapsedTime ;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnPlay)
        {
            if(musicPlayer.isPlaying())
            {
                //isPlaying
                musicPlayer.pause();
                btnPlay.setBackgroundResource(R.drawable.ic_play);
            }
            else
            {
                //onPause
                musicPlayer.start();
                btnPlay.setBackgroundResource(R.drawable.ic_pause);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
            if(musicPlayer.isPlaying())
            {
                musicPlayer.stop();
            }
        }
        return super.onOptionsItemSelected(item);
    }





}