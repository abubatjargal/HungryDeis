package mobileappdev.cstore;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.widget.TextView;
import android.widget.Toast;

public class main extends Activity implements GestureDetector.OnGestureListener {
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.gestureDetector = new GestureDetectorCompat(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.admin_menu_login) {
            Intent intent = new Intent(this, admin_login.class);
            startActivity(intent);
        }
        if (id == R.id.mulan) {
            String url = "tel:7816429263";
            call(url);
        }
        if (id == R.id.pho) {
            String url = "tel:7817888899";
            call(url);

        }
        if (id == R.id.wok) {
            String url = "tel:7816471675";
            call(url);
        }
        if(id == R.id.Mmulan){
            Intent intent = new Intent(this,mulan.class);
            startActivity(intent);
        }
        if(id == R.id.Mpho){
            Intent intent = new Intent(this,pho.class);
            startActivity(intent);
        }
        if(id == R.id.Mwok){
            Intent intent = new Intent(this,AsiaWok.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
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

    @Override
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

    /**********END GESTURE METHODS**********/

    //program detect gesture first before regular touch from user.
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    //call when swipe left
    public void onSwipeLeft() {
        Intent intent = new Intent(this, Usdan.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //call when swipe right
    public void onSwipeRight() {
        Intent intent = new Intent(this, sherman.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }



    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, display.class);
        EditText editText = (EditText) findViewById(R.id.search_bar);
        String message = editText.getText().toString();
        intent.putExtra("searchVal", message);
        startActivity(intent);
    }
    //called when administrator entered login info. and click login.


    public void browse(View view){
        Intent intent = new Intent(this, browse.class);
        startActivity(intent);
    }

    public void call(String url){
        try{
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
            startActivity(intent);
        }
        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"Activity is not founded", Toast.LENGTH_SHORT).show();
        }
    }

}
