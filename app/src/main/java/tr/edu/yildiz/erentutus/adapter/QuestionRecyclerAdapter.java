package tr.edu.yildiz.erentutus.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.ListQuestions;
import tr.edu.yildiz.erentutus.MainActivity;
import tr.edu.yildiz.erentutus.Profile;
import tr.edu.yildiz.erentutus.R;
import tr.edu.yildiz.erentutus.SignUp;
import tr.edu.yildiz.erentutus.UpdateQuestion;
import tr.edu.yildiz.erentutus.entity.Question;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.startActivity;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.MyViewHolder> {

    private ArrayList<Question> questions;
    Activity activity;

    public QuestionRecyclerAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.QuestionText.setText("Soru : " + questions.get(position).getQuestion());
        holder.AnswerA.setText("A) " + questions.get(position).getChoices().get(0));
        holder.AnswerB.setText("B) " + questions.get(position).getChoices().get(1));
        holder.AnswerC.setText("C) " + questions.get(position).getChoices().get(2));
        holder.AnswerD.setText("D) " + questions.get(position).getChoices().get(3));
        holder.CorrectAnswer.setText("Correct Answer : " + questions.get(position).getAnswer());
        holder.ButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.remove(position);
                QuestionRecyclerAdapter.super.notifyDataSetChanged();
            }
        });
        holder.ButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //questions.get(position);
                activity.startActivity(new Intent(activity, UpdateQuestion.class));
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
}
