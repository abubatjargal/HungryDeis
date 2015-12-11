package mobileappdev.cstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Brian on 12/3/15.
 */
public class splash extends Activity {

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setTitle("HungryDeis");
        setContentView(R.layout.activity_splash);

        final ImageView loading_icon = (ImageView) findViewById(R.id.loading_icon);
        final Animation an = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);


        loading_icon.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent i = new Intent(getApplicationContext(),main.class);
                startActivity(i);

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        Thread thread = new Thread(){
//            public void run(){
//                try {
//                    sleep(2500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Intent i = new Intent(getApplicationContext(),main.class);
//                startActivity(i);
//                finish();
//            }
//        };
//        thread.start();
    }
}
