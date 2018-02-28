package com.zia.bihu.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zia.bihu.Bean.AnswerResult;
import com.zia.bihu.Bean.QuestionResult;
import com.zia.bihu.R;
import com.zia.bihu.Util.App;

import java.util.List;

/**
 * Created by zia on 2018/2/21.
 */
public class AnswerRvAdapter extends RecyclerView.Adapter<AnswerHolder> {

    private List<AnswerResult.Data.Answer> answers;
    private Context context;

    public AnswerRvAdapter(List<AnswerResult.Data.Answer> answers, Context context) {
        this.answers = answers;
        this.context = context;
    }

    @Override
    public AnswerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answer, parent, false);
        return new AnswerHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnswerHolder holder, int position) {
        final AnswerResult.Data.Answer answer = answers.get(position);
        holder.acceptCount.setText(wrapCount(answer.getBest()));
        holder.date.setText(answer.getDate());
        holder.content.setText(answer.getContent());
        holder.username.setText(answer.getAuthorName());
        if (answer.isIs_exciting())
            holder.acceptIamge.setImageResource(R.drawable.good2);
        holder.acceptIamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchExciting(answer, holder);
            }
        });
    }

    private void switchExciting(AnswerResult.Data.Answer answer, AnswerHolder holder) {
        if (!answer.isIs_exciting())
            exciting(answer, holder);
        else
            cancelExciting(answer, holder);
    }

    private int code = 400;

    private void exciting(final AnswerResult.Data.Answer answer, final AnswerHolder holder) {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                code = App.getInstance().getInternetModel().excitingAnswer(answer.getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            holder.acceptIamge.setImageResource(R.drawable.good2);
                            answer.setExciting(answer.getExciting() + 1);
                            holder.acceptCount.setText(wrapCount(answer.getExciting()));
                            answer.setIs_exciting(true);
                        } else Toast.makeText(context, "网络错误..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void cancelExciting(final AnswerResult.Data.Answer answer, final AnswerHolder holder) {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                code = App.getInstance().getInternetModel().cancelExcitingAnswer(answer.getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            holder.acceptIamge.setImageResource(R.drawable.good);
                            answer.setExciting(answer.getExciting() - 1);
                            holder.acceptCount.setText(wrapCount(answer.getExciting()));
                            answer.setIs_exciting(false);
                        } else Toast.makeText(context, "网络错误..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    private String wrapCount(int count) {
        return "(" + count + ")";
    }

    private void runOnUiThread(Runnable runnable) {
        ((Activity) context).runOnUiThread(runnable);
    }
}
