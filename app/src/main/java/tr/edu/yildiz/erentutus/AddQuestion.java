package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.entity.Question;

public class AddQuestion extends AppCompatActivity {
    private EditText TextQuestion,TextAnswerA,TextAnswerB,TextAnswerC,TextAnswerD;
    private Button ButtonSave;
    private RadioButton CheckA,CheckB,CheckC,CheckD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        getInputs();

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFilled()){
                    addQuestion();
                }
                else{
                    Toast.makeText(getApplicationContext(),"It should not be empty",Toast.LENGTH_SHORT).show();
                }
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
        CheckA = (RadioButton) findViewById(R.id.CheckA);
        CheckB = (RadioButton) findViewById(R.id.CheckB);
        CheckC = (RadioButton) findViewById(R.id.CheckC);
        CheckD = (RadioButton) findViewById(R.id.CheckD);
    }


    public boolean isFilled(){
        if(TextQuestion.getText().toString().equals("") || TextAnswerA.getText().toString().equals("") ||
                TextAnswerB.getText().toString().equals("") || TextAnswerC.getText().toString().equals("") || TextAnswerD.getText().toString().equals("") ||
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
        choices.add(TextAnswerA.getText().toString());
        choices.add(TextAnswerB.getText().toString());
        choices.add(TextAnswerC.getText().toString());
        choices.add(TextAnswerD.getText().toString());
        Question question = new Question(TextQuestion.getText().toString(),choices,isCheck());
        Question.questions.add(question);
    }
}