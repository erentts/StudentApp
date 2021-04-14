package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import tr.edu.yildiz.erentutus.entity.User;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername,editTextPassword;
    private Button buttonSignIn,buttonSignUpInLogin;
    private TextView textView;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String username,password,name,surname,phone,email,birthdate;
    private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int errorCount = 0;
    private ArrayList<User> users = new ArrayList<>();

    public void getInputs(){
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUpInLogin = (Button) findViewById(R.id.buttonSignUpInLogin);
        textView = (TextView) findViewById(R.id.textView);
    }
    
    public void getSharedInformation(){
        sp = getSharedPreferences("LogInInformation",MODE_PRIVATE);
        editor = sp.edit();
    }

    public void getSessions(){
        username = sp.getString("username","no username");
        password = sp.getString("password","no password");
        name = sp.getString("name","no name");
        surname = sp.getString("surname","no surname");
        email = sp.getString("email","no email");
        phone = sp.getString("phone","no phone");
        birthdate = sp.getString("birthdate","no birthdate");
    }
    
    public boolean userCheckLogin(String username,String password){
        getInputs();
        final boolean[] result = new boolean[1];
        users.forEach((information) ->{
            if(information.getUsername().equals(username) && information.getPassword().equals(password)){
                result[0] = true;
            }
        });
        if(result[0]){
            return true;
        }
        else{
            return false;
        }
    }

    public void clearInputs(){
        editTextUsername.setText("");
        editTextPassword.setText("");
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInputs();
        getSharedInformation();
        getSessions();

        // Default user creating
        User ali = new User("Ali","Kemal","alikemal", "alikemal@gmail.com","05419874532", "1010101","123");
        users.add(ali);

        // New User from Sign Up
        User newUser = new User(name,surname,username,email,phone,birthdate,password);
        users.add(newUser);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                if(userCheckLogin(username,password)){
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.commit();
                    startActivity(new Intent(MainActivity.this,Profile.class));
                    finish();
                    clearInputs();
                }
                else{
                    errorCount++;
                    if(errorCount < 3){
                        Toast.makeText(getApplicationContext(),"Sign In has not been done",Toast.LENGTH_SHORT).show();
                        clearInputs();
                    }
                    else{
                        buttonSignIn.setEnabled(false);
                        Toast.makeText(getApplicationContext(),"You exceed error count : " +errorCount,Toast.LENGTH_SHORT).show();
                        clearInputs();
                    }
                }
            }
        });

        buttonSignUpInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
                finish();
            }
        });
    }
}