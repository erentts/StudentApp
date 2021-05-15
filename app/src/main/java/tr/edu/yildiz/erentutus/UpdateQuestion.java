package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.database.Database;
import tr.edu.yildiz.erentutus.entity.Question;

public class UpdateQuestion extends AppCompatActivity {
    private EditText TextQuestion,TextAnswerA,TextAnswerB,TextAnswerC,TextAnswerD;
    private Button ButtonSave;
    private RadioButton CheckA,CheckB,CheckC,CheckD,rdtwo,rdthree,rdfour;
    private int position;
    private Database database;
    private String questionName;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

        getInputs();
        getSharedInformation();
        Intent intent = getIntent();
        database = new Database(this);
        position = intent.getIntExtra("update",0);
        questionName = intent.getStringExtra("questionName");
        userName = sp.getString("username","no username");
        getQuestionInformation(getData(),questionName);

        //fillInputs();

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFilled()){
                    addQuestion(questionName);
                    Intent intent = new Intent(UpdateQuestion.this, ListQuestions.class);
                    startActivity(intent);
                    finish();
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

    public void addQuestion(String questionName){
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
        information.put("userFK", userName);
        information.put("answer", isCheck());
        db.update("Question",information,"question"+"=?",new String[]{questionName});
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

    public void fillInputs(){
        TextQuestion.setText(Question.questions.get(position).getQuestion());
        int choices = Question.questions.get(position).getChoices().size();

        if(choices == 2){
            TextAnswerA.setText(Question.questions.get(position).getChoices().get(0));
            TextAnswerB.setText(Question.questions.get(position).getChoices().get(1));
        }
        else if(choices == 3){
            TextAnswerA.setText(Question.questions.get(position).getChoices().get(0));
            TextAnswerB.setText(Question.questions.get(position).getChoices().get(1));
            TextAnswerC.setText(Question.questions.get(position).getChoices().get(2));
        }
        else{
            TextAnswerA.setText(Question.questions.get(position).getChoices().get(0));
            TextAnswerB.setText(Question.questions.get(position).getChoices().get(1));
            TextAnswerC.setText(Question.questions.get(position).getChoices().get(2));
            TextAnswerD.setText(Question.questions.get(position).getChoices().get(3));
        }

        if(Integer.parseInt(rdtwo.getText().toString()) == choices){
            rdtwo.setChecked(true);
        }
        else if (Integer.parseInt(rdthree.getText().toString()) == choices){
            rdthree.setChecked(true);
        }
        else{
            rdfour.setChecked(true);
        }

        String correctAnswer = Question.questions.get(position).getAnswer();
        if(CheckA.getText().toString().equals(correctAnswer)){
            CheckA.setChecked(true);
        }
        else if(CheckB.getText().toString().equals(correctAnswer)){
            CheckB.setChecked(true);
        }
        else if(CheckC.getText().toString().equals(correctAnswer)){
            CheckC.setChecked(true);
        }
        else{
            CheckD.setChecked(true);
        }
    }


    private String[] columns={"question","choiceOne","choiceTwo","choiceThree","choiceFour","answer","choiceCount","userFK"};
    private Cursor getData(){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.query("Question",columns,null,null,null,null,null);
        return cursor;
    }

    private void getQuestionInformation(Cursor cursor, String qname){
        while(cursor.moveToNext()) {
            if(qname.equals(cursor.getString(cursor.getColumnIndex("question")))){
                TextQuestion.setText(cursor.getString(cursor.getColumnIndex("question")));
                String temp = cursor.getString(cursor.getColumnIndex("choiceCount"));
                if(temp.equals("2")){
                    rdtwo.setChecked(true);
                    TextAnswerC.setEnabled(false);
                    TextAnswerD.setEnabled(false);
                    TextAnswerC.setText(null);
                    TextAnswerD.setText(null);
                    CheckC.setEnabled(false);
                    CheckD.setEnabled(false);
                    CheckA.setChecked(true);
                    TextAnswerA.setText(cursor.getString(cursor.getColumnIndex("choiceOne")));
                    TextAnswerB.setText(cursor.getString(cursor.getColumnIndex("choiceTwo")));
                }else if(temp.equals("3")){
                    rdthree.setChecked(true);
                    TextAnswerC.setEnabled(true);
                    TextAnswerD.setEnabled(false);
                    TextAnswerD.setText(null);
                    CheckC.setEnabled(true);
                    CheckD.setEnabled(false);
                    CheckA.setChecked(true);
                    TextAnswerA.setText(cursor.getString(cursor.getColumnIndex("choiceOne")));
                    TextAnswerB.setText(cursor.getString(cursor.getColumnIndex("choiceTwo")));
                    TextAnswerC.setText(cursor.getString(cursor.getColumnIndex("choiceThree")));
                }else if(temp.equals("4")){
                    rdfour.setChecked(true);
                    TextAnswerC.setEnabled(true);
                    TextAnswerD.setEnabled(true);
                    CheckC.setEnabled(true);
                    CheckD.setEnabled(true);
                    CheckA.setChecked(true);
                    TextAnswerA.setText(cursor.getString(cursor.getColumnIndex("choiceOne")));
                    TextAnswerB.setText(cursor.getString(cursor.getColumnIndex("choiceTwo")));
                    TextAnswerC.setText(cursor.getString(cursor.getColumnIndex("choiceThree")));
                    TextAnswerD.setText(cursor.getString(cursor.getColumnIndex("choiceFour")));
                }
                if(CheckA.getText().toString().equals(cursor.getString(cursor.getColumnIndex("answer")))){
                    CheckA.setChecked(true);
                }else if(CheckB.getText().toString().equals(cursor.getString(cursor.getColumnIndex("answer")))){
                    CheckB.setChecked(true);
                }else if(CheckC.getText().toString().equals(cursor.getString(cursor.getColumnIndex("answer")))){
                    CheckC.setChecked(true);
                }else if(CheckD.getText().toString().equals(cursor.getString(cursor.getColumnIndex("answer")))){
                    CheckD.setChecked(true);
                }
            }
        }
    }
}