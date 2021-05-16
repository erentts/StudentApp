package tr.edu.yildiz.erentutus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Logger;

import tr.edu.yildiz.erentutus.adapter.ExamScreenRecyclerAdapter;
import tr.edu.yildiz.erentutus.adapter.QuestionRecyclerAdapter;
import tr.edu.yildiz.erentutus.database.Database;
import tr.edu.yildiz.erentutus.entity.Question;

import static tr.edu.yildiz.erentutus.entity.Question.questions;

public class CreateExam extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    String examTime,examPoint,examDifficulty;
    private RecyclerView RecyclerList;
    private ExamScreenRecyclerAdapter examScreenRecyclerAdapter;
    private EditText TxtExamTime,TxtPoint;
    public static RadioButton rdtwo,rdthree,rdfour;
    private Database database;
    private String username;
    private Button ButtonSubmit;
    private ArrayList<Question> questions = new ArrayList<Question>();
    public static ArrayList<Question> questionsTwo = new ArrayList<Question>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        getInputs();
        getSharedInformation();
        database = new Database(this);

        examTime = sp.getString("time","no time");
        examPoint = sp.getString("point","no point");
        examDifficulty = sp.getString("difficulty","no difficulty");
        username = sp.getString("username","no username");
        fillInputs(examTime,examPoint,examDifficulty);
        initialize();
        getQuestions(getData());
        String filename = "quiz.txt";
        File file = new File(getApplicationContext().getFilesDir(), filename);

        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileContents = fileOperations(examTime,examPoint,examDifficulty);
                try(FileOutputStream fos = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE)){
                    fos.write(fileContents.getBytes());
                    Toast.makeText(getApplicationContext(),"Saved to File !",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        rdtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Question> list = createList();
                RecyclerList = (RecyclerView) findViewById(R.id.RecyclerList);
                examScreenRecyclerAdapter = new ExamScreenRecyclerAdapter(list);
                RecyclerList.setAdapter(examScreenRecyclerAdapter);
                RecyclerList.setLayoutManager(new LinearLayoutManager(CreateExam.this));
                examScreenRecyclerAdapter.notifyDataSetChanged();
                questionsTwo.clear();
                examDifficulty = "2";
            }
        });
        rdthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Question> list = createList();
                RecyclerList = (RecyclerView) findViewById(R.id.RecyclerList);
                examScreenRecyclerAdapter = new ExamScreenRecyclerAdapter(list);
                RecyclerList.setAdapter(examScreenRecyclerAdapter);
                RecyclerList.setLayoutManager(new LinearLayoutManager(CreateExam.this));
                examScreenRecyclerAdapter.notifyDataSetChanged();
                questionsTwo.clear();
                examDifficulty = "3";

            }
        });
        rdfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Question> list = createList();
                RecyclerList = (RecyclerView) findViewById(R.id.RecyclerList);
                examScreenRecyclerAdapter = new ExamScreenRecyclerAdapter(list);
                RecyclerList.setAdapter(examScreenRecyclerAdapter);
                RecyclerList.setLayoutManager(new LinearLayoutManager(CreateExam.this));
                examScreenRecyclerAdapter.notifyDataSetChanged();
                questionsTwo.clear();
                examDifficulty = "4";
            }
        });
    }

    public void getInputs(){
        rdtwo = (RadioButton) findViewById(R.id.rdtwo);
        rdthree = (RadioButton) findViewById(R.id.rdthree);
        rdfour = (RadioButton) findViewById(R.id.rdfour);
        TxtExamTime = (EditText) findViewById(R.id.TxtExamTime);
        TxtPoint = (EditText) findViewById(R.id.TxtPoint);
        ButtonSubmit = (Button) findViewById(R.id.ButtonSubmit);
    }

    public ArrayList<Question> createList(){

        ArrayList<Question> list = new ArrayList<Question>();
        for(Question q : questions){
            if(rdtwo.isChecked()){
                if(q.getChoices().size() == 2){
                    list.add(q);
                }
            }
            if(rdthree.isChecked()){
                if(q.getChoices().size() == 3){
                    list.add(q);
                }
            }
            if(rdfour.isChecked()){
                if(q.getChoices().size() == 4){
                    list.add(q);
                }
            }
        }
        return list;
    }

    public void getSharedInformation(){
        sp = getSharedPreferences("LogInInformation",MODE_PRIVATE);
        editor = sp.edit();
    }

    public void fillInputs(String time,String point,String difficulty){
        TxtExamTime.setText(time);
        TxtPoint.setText(point);
        if(rdtwo.getText().toString().equals(difficulty)){
            rdtwo.setChecked(true);
        }
        else if(rdthree.getText().toString().equals(difficulty)){
            rdthree.setChecked(true);
        }
        else{
            rdfour.setChecked(true);
        }
    }

    public void initialize(){
        ArrayList<Question> list = new ArrayList<Question>();
        for(Question q : questions){
            if(rdtwo.isChecked()){
                if(q.getChoices().size() == 2){
                    list.add(q);
                }
            }
            if(rdthree.isChecked()){
                if(q.getChoices().size() == 3){
                    list.add(q);
                }
            }
            if(rdfour.isChecked()){
                if(q.getChoices().size() == 4){
                    list.add(q);
                }
            }
        }
        RecyclerList = (RecyclerView) findViewById(R.id.RecyclerList);
        examScreenRecyclerAdapter = new ExamScreenRecyclerAdapter(list);
        RecyclerList.setAdapter(examScreenRecyclerAdapter);
        RecyclerList.setLayoutManager(new LinearLayoutManager(CreateExam.this));
        examScreenRecyclerAdapter.notifyDataSetChanged();
    }

    public String fileOperations(String time, String point, String difficulty){
        String text = "";
        text += "Time ---> "+time+" minutes\nPoint --->"+point+" point\nDifficulty ---> "+difficulty+"\n\n";
        //text += time+"\t\t\t"+point+"\t\t\t"+difficulty+"\n\n";

        for(Question question: questionsTwo){
            text += "*******Question*********\n"+question.getQuestion()+"\n********Answers********\nCorrect Answer : '"+question.getAnswer()+"')\n\n";
            text += "A) "+question.getChoices().get(0)+"\n";
            text += "B) "+question.getChoices().get(1)+"\n";
            if(question.getChoices().size() == 3){
                text += "C) "+question.getChoices().get(2)+"\n\n";
            }else if(question.getChoices().size() == 4){
                text += "C) "+question.getChoices().get(2)+"\n";
                text += "D) "+question.getChoices().get(3)+"\n\n";
            }
        }
        return text;
    }

    private String[] columns={"question","choiceOne","choiceTwo","choiceThree","choiceFour","answer","choiceCount","userFK"};
    private Cursor getData(){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.query("Question",columns,null,null,null,null,null);
        return cursor;
    }
    private void getQuestions(Cursor cursor){
        while(cursor.moveToNext()) {
            if(username.equals(cursor.getString(cursor.getColumnIndex("userFK")))){

                String question = cursor.getString(cursor.getColumnIndex("question"));
                ArrayList<String> choices = new ArrayList<String>();
                if(cursor.getString(cursor.getColumnIndex("choiceCount")).equals("2")){
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceOne")));
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceTwo")));

                }else if(cursor.getString(cursor.getColumnIndex("choiceCount")).equals("3")){
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceOne")));
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceTwo")));
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceThree")));
                }else if(cursor.getString(cursor.getColumnIndex("choiceCount")).equals("4")){
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceOne")));
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceTwo")));
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceThree")));
                    choices.add(cursor.getString(cursor.getColumnIndex("choiceFour")));
                }
                String answer = cursor.getString(cursor.getColumnIndex("answer"));
                Question newQuestion = new Question(question,choices,answer);
                questions.add(newQuestion);
            }
        }
    }
}