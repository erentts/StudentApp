package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ProxyFileDescriptorCallback;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ExamManager extends AppCompatActivity {
    private EditText TxtTime,TxtPuan;
    private Button ButtonSub;
    private RadioButton CheckTwo,CheckThree,CheckFour,CheckFive;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_manager);

        getInputs();
        getSharedInformation();

        ButtonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFilled()){
                    editor.putString("time",TxtTime.getText().toString());
                    editor.putString("point",TxtPuan.getText().toString());
                    String difficultyCheck = isCheck();
                    editor.putString("difficulty",difficultyCheck);
                    editor.commit();
                    startActivity(new Intent(ExamManager.this, Profile.class));
                    finish();
                    Toast.makeText(getApplicationContext(),"Exam has been arranged !",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"It should not be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getInputs(){
        ButtonSub = (Button) findViewById(R.id.ButtonSub);
        TxtTime = (EditText) findViewById(R.id.TxtTime);
        TxtPuan = (EditText) findViewById(R.id.TxtPuan);
        CheckTwo = (RadioButton) findViewById(R.id.CheckTwo);
        CheckThree = (RadioButton) findViewById(R.id.CheckThree);
        CheckFour = (RadioButton) findViewById(R.id.CheckFour);
        CheckFive = (RadioButton) findViewById(R.id.CheckFive);
    }

    public void getSharedInformation(){
        sp = getSharedPreferences("LogInInformation",MODE_PRIVATE);
        editor = sp.edit();
    }

    public boolean isFilled(){
        if(TxtTime.getText().toString().equals("") || TxtPuan.getText().toString().equals("") ||
                (!CheckTwo.isChecked() && !CheckThree.isChecked() && !CheckFour.isChecked() && !CheckFive.isChecked())
        ){
            return false;
        }
        return true;
    }

    public String isCheck(){
        if(CheckTwo.isChecked()){
            return "2";
        }
        else if(CheckThree.isChecked()){
            return "3";
        }
        else if(CheckFour.isChecked()){
            return "4";
        }
        return "5";
    }
}