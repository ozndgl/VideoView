package com.example.videoview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    VideoView videoView;
    String movieurl = Environment.getExternalStorageDirectory() + "/saat.mp4";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreeen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//ekranı açık tutma
        videoView = findViewById(R.id.video_view);

        oku();




        //int sure = videoView.getDuration();
        //System.out.println("süre : " + sure);
        // MediaController mediaController = new MediaController(this);
        //videoView.setMediaController(mediaController);
        //mediaController.setAnchorView(videoView);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void Singİn() {
        mAuth.signInWithEmailAndPassword("cihaz1@ivo.com", "123456")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println("singIn if");

                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                           // Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            System.out.println("singIn else");
                            //updateUI(null);

                        }
                    }
                });
    }

    public void videoOynat() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.donen1);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();


    }

    private void blink() {
        final Handler hander = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(16000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        //yaz
                        videoOynat();
                        blink();
                    }
                });
            }
        }).start();
    }

    public void videoMinScreen() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) videoView.getLayoutParams();
        params.width = (int) (300 * metrics.density);
        params.height = (int) (250 * metrics.density);
        params.leftMargin = 0;
        videoView.setLayoutParams(params);
    }

    public void videoFullScreen() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) videoView.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        videoView.setLayoutParams(params);
    }
    public void yaz() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("videoSira");

        myRef.setValue(0);

    }
    public void oku() {

        DatabaseReference myRef = database.getReference("videoSira");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                System.out.println("gelen değer: " + value);

                if (value == 1) {
                    System.out.println("oynat 1 çalıştı");
                    yaz();
                    videoOynat1();
                } else if (value == 2) {
                    System.out.println("oynat 2 çalıştı");
                    yaz();
                    videoOynat2();
                } else if (value == 3) {
                    System.out.println("oynat 3 çalıştı");
                    yaz();
                    videoOynat3();
                } else if (value == 4) {
                    System.out.println("oynat 4 çalıştı");
                    yaz();
                    videoOynat4();
                } else if (value == 5) {
                    System.out.println("oynat 5 çalıştı");
                    yaz();
                    videoOynat5();
                } else if (value == 6) {
                    System.out.println("resim 2 çalıştı");
                    yaz();
                    resim1();
                } else if (value == 7) {
                    System.out.println("resim3 çalıştı");
                    yaz();
                    resim2();
                }
                else if (value == 8) {
                    System.out.println("resim3 çalıştı");
                    yaz();
                    resim3();
                }
                else if (value == 9) {
                    System.out.println("resim3 çalıştı");
                    yaz();
                    resim4();
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public void videoOynat1() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.donen1);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }

    public void videoOynat2() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.donen2);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void videoOynat3() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.donen3);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void videoOynat4() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.donen4);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void videoOynat5() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.atv);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void resim1() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.resim1);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void resim2() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.resim2);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void resim3() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yayatvboards);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void resim4() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ivogrup);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}