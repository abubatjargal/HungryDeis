package mobileappdev.cstore;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class admin_display extends Activity {
    DBAdapter myDb;
    ListView listview;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display);
        setTitle("Administrator");
        openDB();
        Intent intent = getIntent();
        listview = (ListView)findViewById(R.id.list3);
        Context context = admin_display.this;
        if (intent.getStringExtra("itemname") != null){
            String name = intent.getStringExtra("itemname");
            String price = intent.getStringExtra("itemprice");
            String amt = intent.getStringExtra("itemamt");
            if (intent.getStringExtra("Edit") != null && intent.getStringExtra("Position") != null){
                long pos = Long.parseLong(intent.getStringExtra("Position"));
                myDb.updateRow(pos,name,price,amt);
            } else {
                myDb.insertRow(name, price, amt);
            }
        }
        adapter = new SimpleCursorAdapter(context,
                R.layout.food,
                myDb.getAllRows(),
                new String[] {"Item","Price", "Quantity"},
                new int[] {R.id.item, R.id.price, R.id.amt}
                ,1);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                myDb.deleteRow(arg3);
                adapter.changeCursor(myDb.getAllRows());
                return true;
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                editItem(arg3);
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin_display, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.add:
                addItem();
                break;
            case R.id.Home:
                goHome();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void addItem() {
        Intent intent = new Intent(this, additem.class);
        startActivity(intent);
    }

    public void editItem(long pos) {
        Intent intent = new Intent(this, edititem.class);
        Cursor cursor = myDb.getRow(pos);
        String name = cursor.getString(1);
        String price = cursor.getString(2);
        String amt = cursor.getString(3);
        intent.putExtra("Edit", "yes");
        intent.putExtra("Position", Long.toString(pos));
        intent.putExtra("name", name);
        intent.putExtra("price", price);
        intent.putExtra("amt", amt);
        startActivity(intent);
    }

    public void goHome(){
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }

}
