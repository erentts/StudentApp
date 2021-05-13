package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.entity.Question;

public class UpdateQuestion extends AppCompatActivity {
    private EditText TextQuestion,TextAnswerA,TextAnswerB,TextAnswerC,TextAnswerD;
    private Button ButtonSave;
    private RadioButton CheckA,CheckB,CheckC,CheckD,rdtwo,rdthree,rdfour;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

        getInputs();
        Intent intent = getIntent();
        position = intent.getIntExtra("update",0);

        fillInputs();

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFilled()){
                    addQuestion();
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

    public void addQuestion(){
        ArrayList<String> choices = new ArrayList<String>();

        if(rdtwo.isChecked()){
            choices.add(TextAnswerA.getText().toString());
            choices.add(TextAnswerB.getText().toString());
        }
        else if(rdthree.isChecked()){
            choices.add(TextAnswerA.getText().toString());
            choices.add(TextAnswerB.getText().toString());
            choices.add(TextAnswerC.getText().toString());
        }
        else if(rdfour.isChecked()) {
            choices.add(TextAnswerA.getText().toString());
            choices.add(TextAnswerB.getText().toString());
            choices.add(TextAnswerC.getText().toString());
            choices.add(TextAnswerD.getText().toString());
        }
        Question.questions.get(position).setQuestion(TextQuestion.getText().toString());
        Question.questions.get(position).setChoices(choices);
        Question.questions.get(position).setAnswer(isCheck());

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
}