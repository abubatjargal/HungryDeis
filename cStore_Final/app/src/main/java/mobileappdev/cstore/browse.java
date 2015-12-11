package mobileappdev.cstore;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Date;

public class browse extends Activity {
    DBAdapter myDb;
    ListView listview;
    SimpleCursorAdapter adapter;
    String message; //search phrase input from user
    public final static String NOT_FOUND = "Item not found";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        setTitle("Browse");
        openDB();
        Intent intent = getIntent();
        listview = (ListView)findViewById(R.id.list2);
        Context context = browse.this;
        Cursor query = myDb.getAllRows();
        adapter = new SimpleCursorAdapter(context,
                R.layout.food,
                query,
                new String[] {"Item","Price", "Quantity"},
                new int[] {R.id.item, R.id.price, R.id.amt}
                ,1);
        listview.setAdapter(adapter);
        /*
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                myDb.deleteRow(arg3);
                adapter.changeCursor(myDb.getAllRows());
            }
        });
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }


    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }
    private void closeDB() {
        myDb.close();
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
}

