package mobileappdev.cstore;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Usdan extends Activity implements GestureDetector.OnGestureListener{
    private String pattern = "HH:mm";
    private final String HOUR = "HH";
    private final String MIN = "mm";
    private final String DAY_OF_WEEK = "EEEE";
    public static final String OPENING = "Opening";
    public static final String CLOSING = "Closing";
    private GestureDetectorCompat gestureDetector;
    private TextView countdownHR;
    private TextView countdownMIN;
    private TextView openOrClose;
    private Date now;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usdan);
        setTitle("L. Usdan Operating Hours");
        initializeVars();
        determineStatus();
        gestureDetector = new GestureDetectorCompat(this,this);
    }
    public void openURL(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://brandeis.sodexomyway.com/images/Hours%20of%20Operation%20Fall%202015_tcm246-75136.pdf"));
        startActivity(i);
    }



    /*********methods for gesturedetector*********/

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distanceY = e2.getY() - e1.getY();
        float distanceX = e2.getX() - e1.getX();
        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > 100 && Math.abs(velocityX) > 100) {
            if (distanceX > 0)
                onSwipeRight();
            else
                onSwipeLeft();
            return true;
        }
        return false;
    }
    /*********END GESTURE********/

    //program detect gesture first before regular touch from user.
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //call when swipe left
    public void onSwipeLeft() {
        Intent intent = new Intent(this, sherman.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //call when swipe right
    public void onSwipeRight() {
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public void determineStatus() {
        now = new Date();
        int hourStr = Integer.parseInt(new SimpleDateFormat(HOUR).format(now)); //hour in 24 hour format
        int minStr = Integer.parseInt(new SimpleDateFormat(MIN).format(now)); //min
        String day_of_weekStri = new SimpleDateFormat(DAY_OF_WEEK).format(now); //day of week...e.g. Monday, Tuesday
        TextView open = (TextView) findViewById(R.id.open);
        TextView closed = (TextView) findViewById(R.id.closed);

        //monday-friday
        if (day_of_weekStri.equals("Monday") || day_of_weekStri.equals("Tuesday") || day_of_weekStri.equals("Wednesday") ||
                day_of_weekStri.equals("Thursday") || day_of_weekStri.equals("Friday")) {
            //8am-7pm (open)
            if(hourStr >=8 && hourStr < 19){
                open.setVisibility(View.VISIBLE);
                closed.setVisibility(View.INVISIBLE);

                int totalMin = 19 * 60;
                int now = hourStr * 60 + minStr;
                int hours_left = (totalMin - now) / 60;
                int min_left = (totalMin - now) % 60;
                setCountdown(hours_left, min_left, CLOSING);
            }
            //7:01pm - 7:59am (close)
            else{
                open.setVisibility(View.INVISIBLE);
                closed.setVisibility(View.VISIBLE);

                if(hourStr >= 19 && hourStr <=23){
                    int totalMin = (24+11) * 60;
                    int now = hourStr * 60 + minStr;
                    int hours_left = (totalMin - now) / 60;
                    int min_left = (totalMin - now) % 60;
                    setCountdown(hours_left,min_left,OPENING);
                }
                if(hourStr >= 0 && hourStr < 11){
                    int totalMin = 11 * 60;
                    int now = hourStr * 60 + minStr;
                    int hours_left = (totalMin - now) / 60;
                    int min_left = (totalMin - now) % 60;
                    setCountdown(hours_left,min_left,OPENING);
                }
            }
        }
        if (day_of_weekStri.equals("Saturday") || day_of_weekStri.equals("Sunday")) {
            if(hourStr >=11 && hourStr < 19){
                open.setVisibility(View.VISIBLE);
                closed.setVisibility(View.INVISIBLE);

                int totalMin = 19 * 60;
                int now = hourStr * 60 + minStr;
                int hours_left = (totalMin - now) / 60;
                int min_left = (totalMin - now) % 60;
                setCountdown(hours_left, min_left, CLOSING);
            }
            else{
                open.setVisibility(View.VISIBLE);
                closed.setVisibility(View.INVISIBLE);

                if(hourStr >= 19 && hourStr <=23){
                    int totalMin = (24+8) * 60;
                    int now = hourStr * 60 + minStr;
                    int hours_left = (totalMin - now) / 60;
                    int min_left = (totalMin - now) % 60;
                    setCountdown(hours_left,min_left,OPENING);
                }
                if(hourStr >= 0 && hourStr < 8){
                    int totalMin = 8 * 60;
                    int now = hourStr * 60 + minStr;
                    int hours_left = (totalMin - now) / 60;
                    int min_left = (totalMin - now) % 60;
                    setCountdown(hours_left,min_left,OPENING);
                }
            }
        }
    }
    public void setCountdown(int hours,int min, String openClose){
        String hoursStr = String.valueOf(hours);
        String minStr = String.valueOf(min);
        countdownHR.setText(hoursStr);
        countdownMIN.setText(minStr);
        openOrClose.setText(openClose);
    }
    private void initializeVars(){
        countdownHR = (TextView) findViewById(R.id.countdownHR);
        countdownMIN = (TextView) findViewById(R.id.countdownMIN);
        openOrClose = (TextView) findViewById(R.id.OpenClose);
    }
}

