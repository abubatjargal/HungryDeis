package mobileappdev.cstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Abu on 11/27/15.
 */
public class admin_login extends Activity {

    private String login = "admin";
    private String pw = "1234";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        setTitle("Administrator Login");
        adminLogin();
    }

    public void adminLogin(){
        Button login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        Intent intent = new Intent(this, admin_display.class);
        EditText editText = (EditText) findViewById(R.id.userInput);
        EditText editText2 = (EditText) findViewById(R.id.passwordInput);
        String username = editText.getText().toString();
        String password = editText2.getText().toString();
        if (username.equals(login) && password.equals(pw)){
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            startActivity(intent);
        } else {
            TextView textview = (TextView)findViewById(R.id.error);
            textview.setText("Credentials Not Found");
        }
    }
}
