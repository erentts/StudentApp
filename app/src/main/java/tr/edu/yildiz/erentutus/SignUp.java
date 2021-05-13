package tr.edu.yildiz.erentutus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import tr.edu.yildiz.erentutus.entity.User;
import tr.edu.yildiz.erentutus.utilities.PasswordUtils;

import static android.provider.CalendarContract.CalendarCache.URI;

public class SignUp extends AppCompatActivity {
    private EditText editTextUsernameSignUp,editTextPasswordSignUp,SignUpEmail,SignUpName,SignUpSurname,SignUpPhone,SignUpBirthdate,Repassword;
    private TextView textView2,txtFilePath;
    private Button buttonSignUp,ButtonChooseFile;
    private Bitmap selectedImageBitmap;
    private ImageView photo;
    Intent myFileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getInputs();

        ButtonChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                myFileIntent.setType("image/*");
                myFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
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
        //txtFilePath = (TextView) findViewById(R.id.txtFilePath);
        photo = (ImageView) findViewById(R.id.photo);
    }


    public boolean isFilled(){
        if(editTextUsernameSignUp.getText().toString().equals("") || editTextPasswordSignUp.getText().toString().equals("") ||
                SignUpEmail.getText().toString().equals("") || SignUpName.getText().toString().equals("") || SignUpSurname.getText().toString().equals("") ||
                SignUpPhone.getText().toString().equals("") || SignUpBirthdate.getText().toString().equals("") || Repassword.getText().toString().equals("") ||
                selectedImageBitmap == null
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
        byte[] photo = ConvertByteArray();
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
                    Uri inf = data.getData();
                    try {
                        if(Build.VERSION.SDK_INT >= 28){
                            ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),inf);
                            selectedImageBitmap = ImageDecoder.decodeBitmap(source);
                            photo.setImageBitmap(selectedImageBitmap);
                        }
                        else{
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),inf);
                            photo.setImageBitmap(selectedImageBitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

    public byte[] ConvertByteArray(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImageBitmap.compress(Bitmap.CompressFormat.PNG,50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

}