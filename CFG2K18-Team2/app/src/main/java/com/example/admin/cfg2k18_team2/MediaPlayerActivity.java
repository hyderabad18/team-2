package com.example.admin.cfg2k18_team2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MediaPlayerActivity extends AppCompatActivity {
    String url;

    private Button b1,b2,b3,b4;
    private ImageView iv;
    MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tx1,tx2,tx3;

    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        Bundle b=getIntent().getExtras();
        if(b!=null){
            url=b.getString("url");
        }



        /*iv=(ImageView)findViewById(R.id.iv1);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = new MediaPlayer();

                try {
                    mp.setDataSource("http://techslides.com/demos/samples/sample.mp3");
                    mp.prepare();
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        AndroidAudioConverter.load(this, new ILoadCallback() {
            @Override
            public void onSuccess() {
                // Great!
                Toast.makeText(MainActivity.this, "COnverted", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Exception error) {
                // FFmpeg is not supported by device
            }
        });
        File flacFile = new File(Environment.getExternalStorageDirectory(), "my_audio.flac");
        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                // So fast? Love it!
            }
            @Override
            public void onFailure(Exception error) {
                // Oops! Something went wrong
            }
        };
        AndroidAudioConverter.with(this)
                // Your current audio file
                .setFile(flacFile)

                // Your desired audio format
                .setFormat(AudioFormat.MP3)

                // An callback to know when conversion is finished
                .setCallback(callback)

                // Start conversion
                .convert();

        Button b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("audio/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Audio"), 2);
            }
        });*/

                b1 = (Button) findViewById(R.id.button);
                b2 = (Button) findViewById(R.id.button2);
                b3 = (Button)findViewById(R.id.button3);
                b4 = (Button)findViewById(R.id.button4);
                iv = (ImageView)findViewById(R.id.imageView);

                tx1 = (TextView)findViewById(R.id.textView2);
                tx2 = (TextView)findViewById(R.id.textView3);
                tx3 = (TextView)findViewById(R.id.textView4);
                tx3.setText("Song.mp3");


                mediaPlayer = new MediaPlayer();
                seekbar = (SeekBar)findViewById(R.id.seekBar);
                seekbar.setClickable(false);

                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                }
                catch (Exception e) {
                }
                b2.setEnabled(false);

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Playing sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.start();

                        finalTime = mediaPlayer.getDuration();
                        startTime = mediaPlayer.getCurrentPosition();

                        if (oneTimeOnly == 0) {
                            seekbar.setMax((int) finalTime);
                            oneTimeOnly = 1;
                        }

                        tx2.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                                finalTime)))
                        );

                        tx1.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                                startTime)))
                        );

                        seekbar.setProgress((int)startTime);
                        myHandler.postDelayed(UpdateSongTime,100);
                        b2.setEnabled(true);
                        b3.setEnabled(false);
                    }
                });

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();
                        b2.setEnabled(false);
                        b3.setEnabled(true);
                    }
                });

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int temp = (int)startTime;

                        if((temp+forwardTime)<=finalTime){
                            startTime = startTime + forwardTime;
                            mediaPlayer.seekTo((int) startTime);
                            Toast.makeText(getApplicationContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int temp = (int)startTime;

                        if((temp-backwardTime)>0){
                            startTime = startTime - backwardTime;
                            mediaPlayer.seekTo((int) startTime);
                            Toast.makeText(getApplicationContext(),"You have Jumped backward 5 seconds",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            private Runnable UpdateSongTime = new Runnable() {
                public void run() {
                    startTime = mediaPlayer.getCurrentPosition();
                    tx1.setText(String.format("%d min, %d sec",
                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                            toMinutes((long) startTime)))
                    );
                    seekbar.setProgress((int)startTime);
                    myHandler.postDelayed(this, 100);
                }
            };

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if(requestCode==2){
                    Uri uri=data.getData();
                    Toast.makeText(MediaPlayerActivity.this," "+uri.toString(),Toast.LENGTH_SHORT).show();
                }

            }



}
