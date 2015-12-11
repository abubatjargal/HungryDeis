package mobileappdev.cstore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class mulan extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulan);
        String url = "https://www.zagat.com/r/mulan-taiwanese-restaurant-waltham/menu";
        WebView webview = (WebView) findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }@Override
     public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mulan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mulan) {
            String url = "tel:7816429263";
            call(url);
        }
        if (id == R.id.wok) {
            Intent intent = new Intent(this, AsiaWok.class);
            startActivity(intent);
        }
        if (id == R.id.pho) {
            Intent intent = new Intent(this, pho.class);
            startActivity(intent);
        }
        if (id == R.id.back) {
            Intent intent = new Intent(this, main.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
    public void call(String url){
        try{
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
            startActivity(intent);
        }
        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(), "Activity is not founded", Toast.LENGTH_SHORT).show();
        }
    }
}
