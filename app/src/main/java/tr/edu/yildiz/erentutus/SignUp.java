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

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.entity.User;

public class SignUp extends AppCompatActivity {
    private EditText editTextUsernameSignUp,editTextPasswordSignUp,SignUpEmail,SignUpName,SignUpSurname,SignUpPhone,SignUpBirthdate;
    private TextView textView2;
    private Button buttonSignUp;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private ArrayList<User> users = new ArrayList<User>();

    public void getInputs(){
        editTextUsernameSignUp = (EditText) findViewById(R.id.editTextUsernameSignUp);
        editTextPasswordSignUp = (EditText) findViewById(R.id.editTextPasswordSignUp);
        SignUpEmail = (EditText) findViewById(R.id.SignUpEmail);
        SignUpName = (EditText) findViewById(R.id.SignUpName);
        SignUpSurname = (EditText) findViewById(R.id.SignUpSurname);
        SignUpPhone = (EditText) findViewById(R.id.SignUpPhone);
        SignUpBirthdate = (EditText) findViewById(R.id.SignUpBirthdate);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        textView2 = (TextView) findViewById(R.id.textView2);
    }

    public void getSharedInformation(){
        sp = getSharedPreferences("LogInInformation",MODE_PRIVATE);
        editor = sp.edit();
    }

    public boolean isFilled(){
       if(editTextUsernameSignUp.getText().toString().equals("") || editTextPasswordSignUp.getText().toString().equals("") ||
               SignUpEmail.getText().toString().equals("") || SignUpName.getText().toString().equals("") || SignUpSurname.getText().toString().equals("") ||
               SignUpPhone.getText().toString().equals("") || SignUpBirthdate.getText().toString().equals("")
       ){
            return false;
       }
       return true;
    }

    public User createUser(){
        String name = SignUpName.getText().toString();
        String surname = SignUpSurname.getText().toString();
        String username = editTextUsernameSignUp.getText().toString();
        String password = editTextPasswordSignUp.getText().toString();
        String email = SignUpEmail.getText().toString();
        String phone = SignUpPhone.getText().toString();
        String birthdate = SignUpBirthdate.getText().toString();
        User newUser = new User(name,surname,username,email,phone,birthdate,password);
        return newUser;
    }

    public void carryToMainActivity(User user){
        editor.putString("username",user.getUsername());
        editor.putString("password",user.getPassword());
        editor.putString("name",user.getName());
        editor.putString("surname",user.getSurname());
        editor.putString("phone",user.getPhone());
        editor.putString("birthdate",user.getBirthDate());
        editor.putString("email",user.getEmail());
        editor.commit();
    }

    public void clearInputs(){
        editTextUsernameSignUp.setText("");
        editTextPasswordSignUp.setText("");
        SignUpEmail.setText("");
        SignUpName.setText("");
        SignUpSurname.setText("");
        SignUpPhone.setText("");
        SignUpBirthdate.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getInputs();
        getSharedInformation();


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFilled()){
                    User user = createUser();
                    users.add(user);
                    carryToMainActivity(user);
                    startActivity(new Intent(SignUp.this,MainActivity.class));
                    finish();
                    clearInputs();
                }
                else{
                    Toast.makeText(getApplicationContext(),"It should not be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}