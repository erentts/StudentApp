package tr.edu.yildiz.erentutus.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.ListQuestions;
import tr.edu.yildiz.erentutus.MainActivity;
import tr.edu.yildiz.erentutus.Profile;
import tr.edu.yildiz.erentutus.R;
import tr.edu.yildiz.erentutus.SignUp;
import tr.edu.yildiz.erentutus.UpdateQuestion;
import tr.edu.yildiz.erentutus.database.Database;
import tr.edu.yildiz.erentutus.entity.Question;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.startActivity;
import static tr.edu.yildiz.erentutus.CreateExam.rdfour;
import static tr.edu.yildiz.erentutus.CreateExam.rdthree;
import static tr.edu.yildiz.erentutus.CreateExam.rdtwo;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.MyViewHolder> {

    private ArrayList<Question> questions;
    Activity activity;
    Context context;
    SharedPreferences.Editor speditor;
    SharedPreferences sp;
    private Database database;
    String username;

    public QuestionRecyclerAdapter(ArrayList<Question> questions,Context context) {
        this.questions = questions;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        getSharedInformation();
        username = sp.getString("username","not defined.");
        database = new Database(context);

        holder.setIsRecyclable(false);
        if(2 == questions.get(position).getChoices().size()){
            holder.QuestionText.setText("Soru : " + questions.get(position).getQuestion());
            holder.AnswerA.setText("A) " + questions.get(position).getChoices().get(0));
            holder.AnswerB.setText("B) " + questions.get(position).getChoices().get(1));
            holder.CorrectAnswer.setText("Correct Answer : " + questions.get(position).getAnswer());
        }

        if(3 == questions.get(position).getChoices().size()){
            holder.QuestionText.setText("Soru : " + questions.get(position).getQuestion());
            holder.AnswerA.setText("A) " + questions.get(position).getChoices().get(0));
            holder.AnswerB.setText("B) " + questions.get(position).getChoices().get(1));
            if(!questions.get(position).getChoices().get(2).matches("")){
                holder.AnswerC.setText("C) " + questions.get(position).getChoices().get(2));
            }
            holder.CorrectAnswer.setText("Correct Answer : " + questions.get(position).getAnswer());
        }
        if(4 == questions.get(position).getChoices().size()){
            holder.QuestionText.setText("Soru : " + questions.get(position).getQuestion());
            holder.AnswerA.setText("A) " + questions.get(position).getChoices().get(0));
            holder.AnswerB.setText("B) " + questions.get(position).getChoices().get(1));
            if(!questions.get(position).getChoices().get(2).matches("")){
                holder.AnswerC.setText("C) " + questions.get(position).getChoices().get(2));
            }
            if(!questions.get(position).getChoices().get(3).matches("")){
                holder.AnswerD.setText("D) " + questions.get(position).getChoices().get(3));
            }
            holder.CorrectAnswer.setText("Correct Answer : " + questions.get(position).getAnswer());
        }
        holder.ButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Deleting An Item");
                builder.setMessage("Are you sure ?");
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeQuestion(questions.get(position).getQuestion());
                        questions.remove(position);
                        QuestionRecyclerAdapter.super.notifyDataSetChanged();
                    }
                });
                builder.show();

            }
        });
        holder.ButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateQuestion.class);
                intent.putExtra("update",position);
                intent.putExtra("questionName",questions.get(position).getQuestion());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView QuestionText,AnswerA,AnswerB,AnswerC,AnswerD,CorrectAnswer,ButtonUpdate,ButtonRemove;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            QuestionText = itemView.findViewById(R.id.QuestionText);
            AnswerA = itemView.findViewById(R.id.AnswerA);
            AnswerB = itemView.findViewById(R.id.AnswerB);
            AnswerC = itemView.findViewById(R.id.AnswerC);
            AnswerD = itemView.findViewById(R.id.AnswerD);
            CorrectAnswer = itemView.findViewById(R.id.CorrectAnswer);
            ButtonUpdate = itemView.findViewById(R.id.ButtonUpdate);
            ButtonRemove = itemView.findViewById(R.id.ButtonRemove);
        }
    }

    public void getSharedInformation(){
        sp = context.getSharedPreferences("LogInInformation",MODE_PRIVATE);
        speditor = sp.edit();
    }

    private void removeQuestion(String questionname)
    {
        SQLiteDatabase db=database.getReadableDatabase();
        db.delete("Question","question"+"=?",new String[]{questionname});
    }
}
