package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.database.Database;
import tr.edu.yildiz.erentutus.entity.Question;

public class AddQuestion extends AppCompatActivity {
    private EditText TextQuestion,TextAnswerA,TextAnswerB,TextAnswerC,TextAnswerD;
    private Button ButtonSave;
    private RadioButton CheckA,CheckB,CheckC,CheckD,rdtwo,rdthree,rdfour;
    private Database database;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        getInputs();
        getSharedInformation();
        username = sp.getString("username","not defined.");
        database = new Database(this);

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFilled()){
                    AddQuestion();
                }
                else{
                    Toast.makeText(getApplicationContext(),"It should not be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rdtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextAnswerC.setEnabled(false);
                TextAnswerD.setEnabled(false);
                CheckC.setEnabled(false);
                CheckD.setEnabled(false);
                TextAnswerC.setText("");
                TextAnswerD.setText("");
            }
        });

        rdthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextAnswerC.setEnabled(true);
                TextAnswerD.setEnabled(false);
                CheckC.setEnabled(true);
                CheckD.setEnabled(false);
                TextAnswerD.setText("");
            }
        });
        rdfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextAnswerC.setEnabled(true);
                TextAnswerD.setEnabled(true);
                CheckC.setEnabled(true);
                CheckD.setEnabled(true);
            }
        });

    }

    public void getInputs(){
        ButtonSave = (Button) findViewById(R.id.ButtonSave);
        TextQuestion = (EditText) findViewById(R.id.TextQuestion);
        TextAnswerA = (EditText) findViewById(R.id.TextAnswerA);
        TextAnswerB = (EditText) findViewById(R.id.TextAnswerB);
        TextAnswerC = (EditText) findViewById(R.id.TextAnswerC);
        TextAnswerD = (EditText) findViewById(R.id.TextAnswerD);
        CheckA = (RadioButton) findViewById(R.id.CheckTwo);
        CheckB = (RadioButton) findViewById(R.id.CheckThree);
        CheckC = (RadioButton) findViewById(R.id.CheckFour);
        CheckD = (RadioButton) findViewById(R.id.CheckFive);
        rdtwo = (RadioButton) findViewById(R.id.rdtwo);
        rdthree = (RadioButton) findViewById(R.id.rdthree);
        rdfour = (RadioButton) findViewById(R.id.rdfour);
    }

    public void getSharedInformation(){
        sp = getSharedPreferences("LogInInformation",MODE_PRIVATE);
        speditor = sp.edit();
    }

    public boolean isFilled(){
        if(TextQuestion.getText().toString().equals("") || TextAnswerA.getText().toString().equals("") ||
                TextAnswerB.getText().toString().equals("") || !C() || !D() ||
                (!CheckA.isChecked() && !CheckB.isChecked() && !CheckC.isChecked() && !CheckD.isChecked())
        ){
            return false;
        }
        return true;
    }

    public String isCheck(){
        if(CheckA.isChecked()){
            return "A";
        }
        else if(CheckB.isChecked()){
            return "B";
        }
        else if(CheckC.isChecked()){
            return "C";
        }
        return "D";
    }

    public void AddQuestion() {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues information = new ContentValues();
        information.put("question", TextQuestion.getText().toString());
        if (rdtwo.isChecked()) {
            information.put("choiceOne", TextAnswerA.getText().toString());
            information.put("choiceTwo", TextAnswerB.getText().toString());
            information.put("choiceCount", "2");
        } else if (rdthree.isChecked()) {
            information.put("choiceOne", TextAnswerA.getText().toString());
            information.put("choiceTwo", TextAnswerB.getText().toString());
            information.put("choiceThree", TextAnswerC.getText().toString());
            information.put("choiceCount", "3");
        } else if (rdfour.isChecked()) {
            information.put("choiceOne", TextAnswerA.getText().toString());
            information.put("choiceTwo", TextAnswerB.getText().toString());
            information.put("choiceThree", TextAnswerC.getText().toString());
            information.put("choiceFour", TextAnswerD.getText().toString());
            information.put("choiceCount", "4");
        }
        information.put("userfk", username);
        information.put("answer", isCheck());
        db.insertOrThrow("Question", null, information);
    }


    public boolean C(){
        if(TextAnswerC.isEnabled()){
            if(TextAnswerC.getText().toString().equals("")){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }
    public boolean D(){
        if(TextAnswerD.isEnabled()){
            if(TextAnswerD.getText().toString().equals("")){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }
}