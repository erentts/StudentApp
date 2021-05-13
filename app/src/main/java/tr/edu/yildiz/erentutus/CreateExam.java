package tr.edu.yildiz.erentutus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Logger;

import tr.edu.yildiz.erentutus.adapter.ExamScreenRecyclerAdapter;
import tr.edu.yildiz.erentutus.adapter.QuestionRecyclerAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        rdtwo = (RadioButton) findViewById(R.id.rdtwo);
        rdthree = (RadioButton) findViewById(R.id.rdthree);
        rdfour = (RadioButton) findViewById(R.id.rdfour);
        TxtExamTime = (EditText) findViewById(R.id.TxtExamTime);
        TxtPoint = (EditText) findViewById(R.id.TxtPoint);
        getSharedInformation();
        examTime = sp.getString("time","no time");
        examPoint = sp.getString("point","no point");
        examDifficulty = sp.getString("difficulty","no difficulty");
        fillInputs(examTime,examPoint,examDifficulty);
        initialize();
        writeStringAsFile();


        rdtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Question> list = createList();
                RecyclerList = (RecyclerView) findViewById(R.id.RecyclerList);
                examScreenRecyclerAdapter = new ExamScreenRecyclerAdapter(list);
                RecyclerList.setAdapter(examScreenRecyclerAdapter);
                RecyclerList.setLayoutManager(new LinearLayoutManager(CreateExam.this));
                examScreenRecyclerAdapter.notifyDataSetChanged();
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
            }
        });

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

    public void writeStringAsFile() {
        try {
            String MYFILE = "my_file";
            String strText = "My text";

            // MODE_APPEND, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE
            // create new file or rewrite existing
            //FileOutputStream fos = openFileOutput(MYFILE, getApplicationContext().MODE_PRIVATE);
            // append to file
            FileOutputStream fos = openFileOutput(MYFILE, getApplicationContext().MODE_PRIVATE);

            fos.write(strText.getBytes());
            fos.close();
        } catch (IOException e) {
            e.toString();
        }

    }

}