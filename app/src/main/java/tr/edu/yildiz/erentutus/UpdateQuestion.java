package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class UpdateQuestion extends AppCompatActivity {
    private EditText TextQuestion,TextAnswerA,TextAnswerB,TextAnswerC,TextAnswerD;
    private Button ButtonSave;
    private RadioButton CheckA,CheckB,CheckC,CheckD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

        getInputs();

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFilled()){

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
        CheckA = (RadioButton) findViewById(R.id.CheckTwo);
        CheckB = (RadioButton) findViewById(R.id.CheckThree);
        CheckC = (RadioButton) findViewById(R.id.CheckFour);
        CheckD = (RadioButton) findViewById(R.id.CheckFive);
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
}