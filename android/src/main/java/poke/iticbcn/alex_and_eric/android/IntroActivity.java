package poke.iticbcn.alex_and_eric.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import poke.iticbcn.alex_and_eric.R;

public class IntroActivity extends Activity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);
        videoView = new VideoView(this);

        // Assegura’t que ocupa tota la pantalla
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        );
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        videoView.setLayoutParams(params);
        videoView.setZOrderOnTop(true);
        layout.addView(videoView);
        setContentView(layout);

        // Carrega el vídeo des de /res/raw/intro.mp4
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro);
        videoView.setVideoURI(videoUri);

        videoView.requestFocus(); // ajuda en alguns dispositius
        videoView.setOnPreparedListener(mp -> mp.setVolume(1.0f, 1.0f));
        
        // Quan acaba o toques la pantalla, arrenca el joc
        videoView.setOnCompletionListener(mp -> startGame());
        videoView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                videoView.stopPlayback();
                startGame();
                return true;
            }
            return false;
        });

        videoView.start();
    }

    private void startGame() {
        Intent intent = new Intent(this, AndroidLauncher.class);
        // Evita que es pugui tornar enrere a la intro
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
