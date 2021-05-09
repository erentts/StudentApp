package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;

import tr.edu.yildiz.erentutus.adapter.QuestionRecyclerAdapter;
import tr.edu.yildiz.erentutus.entity.Question;

public class ListQuestions extends AppCompatActivity {
    private RecyclerView RecyclerList;
    private QuestionRecyclerAdapter questionRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        getInput();
        questionRecyclerAdapter.notifyDataSetChanged();

    }


    public void getInput(){
        RecyclerList = (RecyclerView) findViewById(R.id.RecyclerList);
        questionRecyclerAdapter = new QuestionRecyclerAdapter(Question.questions,ListQuestions.this);
        RecyclerList.setAdapter(questionRecyclerAdapter);
        RecyclerList.setLayoutManager(new LinearLayoutManager(this));
    }


}