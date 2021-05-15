package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.adapter.QuestionRecyclerAdapter;
import tr.edu.yildiz.erentutus.database.Database;
import tr.edu.yildiz.erentutus.entity.Question;

public class ListQuestions extends AppCompatActivity {
    private RecyclerView RecyclerList;
    private QuestionRecyclerAdapter questionRecyclerAdapter;
    SharedPreferences.Editor speditor;
    SharedPreferences sp;
    private Database database;
    private String username;
    private ArrayList<Question> questions = new ArrayList<Question>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        getInput();
        getSharedInformation();
        username = sp.getString("username","not defined.");
        questionRecyclerAdapter.notifyDataSetChanged();
        database = new Database(this);
        getQuestions(getData());


    }


    public void getInput(){
        RecyclerList = (RecyclerView) findViewById(R.id.RecyclerList);
        questionRecyclerAdapter = new QuestionRecyclerAdapter(questions,ListQuestions.this);
        RecyclerList.setAdapter(questionRecyclerAdapter);
        RecyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getSharedInformation(){
        sp = getSharedPreferences("LogInInformation",MODE_PRIVATE);
        speditor = sp.edit();
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
                Question q = new Question(question,choices,answer);
                questions.add(q);
            }
        }
    }

}