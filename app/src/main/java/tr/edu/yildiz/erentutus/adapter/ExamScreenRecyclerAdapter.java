package tr.edu.yildiz.erentutus.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tr.edu.yildiz.erentutus.R;
import tr.edu.yildiz.erentutus.entity.Question;

import static tr.edu.yildiz.erentutus.CreateExam.rdfour;
import static tr.edu.yildiz.erentutus.CreateExam.rdthree;
import static tr.edu.yildiz.erentutus.CreateExam.rdtwo;

public class ExamScreenRecyclerAdapter extends RecyclerView.Adapter<ExamScreenRecyclerAdapter.MyViewHolder> {

    private ArrayList<Question> questions;
    Activity activity;

    public ExamScreenRecyclerAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ExamScreenRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_for_examscreen, parent, false);
        return new ExamScreenRecyclerAdapter.MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.setIsRecyclable(false);
            if(rdtwo.isChecked() && 2 == questions.get(position).getChoices().size()){
                holder.QuestionText.setText("Soru : " + questions.get(position).getQuestion());
                holder.AnswerA.setText("A) " + questions.get(position).getChoices().get(0));
                holder.AnswerB.setText("B) " + questions.get(position).getChoices().get(1));
                holder.CorrectAnswer.setText("Correct Answer : " + questions.get(position).getAnswer());
            }

            if(rdthree.isChecked() && 3 == questions.get(position).getChoices().size()){
                holder.QuestionText.setText("Soru : " + questions.get(position).getQuestion());
                holder.AnswerA.setText("A) " + questions.get(position).getChoices().get(0));
                holder.AnswerB.setText("B) " + questions.get(position).getChoices().get(1));
                if(!questions.get(position).getChoices().get(2).matches("")){
                    holder.AnswerC.setText("C) " + questions.get(position).getChoices().get(2));
                }
                holder.CorrectAnswer.setText("Correct Answer : " + questions.get(position).getAnswer());
            }
            if(rdfour.isChecked() && 4 == questions.get(position).getChoices().size()){
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
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView QuestionText, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            QuestionText = itemView.findViewById(R.id.QuestionText);
            AnswerA = itemView.findViewById(R.id.AnswerA);
            AnswerB = itemView.findViewById(R.id.AnswerB);
            AnswerC = itemView.findViewById(R.id.AnswerC);
            AnswerD = itemView.findViewById(R.id.AnswerD);
            CorrectAnswer = itemView.findViewById(R.id.CorrectAnswer);
        }
    }
}
