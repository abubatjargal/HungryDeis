package mobileappdev.cstore;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class additem extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_additem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_additem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cancel(View view){
        Intent intent = new Intent(this, admin_display.class);
        startActivity(intent);
    }

    public void save(View view){
        Intent intent = new Intent(this, admin_display.class);
        EditText editText = (EditText) findViewById(R.id.itemname);
        String message = editText.getText().toString();
        intent.putExtra("itemname", message);
        EditText editText2 = (EditText) findViewById(R.id.itemprice);
        String message2 = editText2.getText().toString();
        intent.putExtra("itemprice", message2);
        EditText editText3 = (EditText) findViewById(R.id.itemamt);
        String message3 = editText3.getText().toString();
        intent.putExtra("itemamt", message3);
        try {
            double d = Double.parseDouble(message2);
            int i = Integer.parseInt(message3);
            startActivity(intent);
        }
        catch(NumberFormatException nfe)
        {
            TextView text = (TextView) findViewById(R.id.addwarning);
            text.setText("Price and Quantity has to be numbers");
        }
    }
}
