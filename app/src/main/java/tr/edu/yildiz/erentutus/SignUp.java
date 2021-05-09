package tr.edu.yildiz.erentutus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import tr.edu.yildiz.erentutus.entity.User;
import tr.edu.yildiz.erentutus.utilities.PasswordUtils;

public class SignUp extends AppCompatActivity {
    private EditText editTextUsernameSignUp,editTextPasswordSignUp,SignUpEmail,SignUpName,SignUpSurname,SignUpPhone,SignUpBirthdate,Repassword;
    private TextView textView2,txtFilePath;
    private Button buttonSignUp,ButtonChooseFile;
    Intent myFileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getInputs();

        ButtonChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,10);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFilled()){
                    if(!isMatch(editTextPasswordSignUp.getText().toString(),Repassword.getText().toString())) {
                        Toast.makeText(getApplicationContext(),"Passwords have not been matched !",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        User user = createUser();
                        User.users.add(user);
                        //carryToMainActivity(user);
                        startActivity(new Intent(SignUp.this,MainActivity.class));
                        finish();
                        clearInputs();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"It should not be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

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
        Repassword = (EditText) findViewById(R.id.Repassword);
        ButtonChooseFile = (Button) findViewById(R.id.ButtonChooseFile);
        txtFilePath = (TextView) findViewById(R.id.txtFilePath);
    }


    public boolean isFilled(){
        if(editTextUsernameSignUp.getText().toString().equals("") || editTextPasswordSignUp.getText().toString().equals("") ||
                SignUpEmail.getText().toString().equals("") || SignUpName.getText().toString().equals("") || SignUpSurname.getText().toString().equals("") ||
                SignUpPhone.getText().toString().equals("") || SignUpBirthdate.getText().toString().equals("") || Repassword.getText().toString().equals("") ||
                txtFilePath.getText().toString().equals("")
        ){
            return false;
        }
        return true;
    }

    public boolean isMatch(String password, String rePassword){
        return password.equals(rePassword) ? true : false;
    }

    public User createUser(){
        String name = SignUpName.getText().toString();
        String surname = SignUpSurname.getText().toString();
        String username = editTextUsernameSignUp.getText().toString();
        String password = editTextPasswordSignUp.getText().toString();
        String email = SignUpEmail.getText().toString();
        String phone = SignUpPhone.getText().toString();
        String birthdate = SignUpBirthdate.getText().toString();
        String photo = txtFilePath.getText().toString();
        // PasswordHash and Password Salt
        String salt = PasswordUtils.getSalt(30);
        String hash = PasswordUtils.generateSecurePassword(password, salt);

        User newUser = new User(name,surname,username,email,phone,birthdate,photo,hash,salt);
        return newUser;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode==RESULT_OK){
                    String path = data.getData().getPath();
                    txtFilePath.setText(path);
                }
                    break;
        }
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


}