package com.zia.bihu.Presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zia.bihu.Adapter.AnswerRvAdapter;
import com.zia.bihu.Adapter.QuestionHolder;
import com.zia.bihu.Bean.AnswerResult;
import com.zia.bihu.Bean.QuestionResult;
import com.zia.bihu.R;
import com.zia.bihu.Util.App;

/**
 * Created by zia on 2018/2/19.
 */
public class ItemPresenter {

    private Context context;
    private RequestOptions requestOptions = new RequestOptions()
            .override(100, 100);

    public ItemPresenter(Context context) {
        this.context = context;
    }

    public void loadText(final QuestionResult.Data.Question question, final QuestionHolder holder) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                holder.username.setText(question.getAuthorName());
                holder.title.setText(question.getTitle());
                holder.content.setText(question.getContent());
                holder.date.setText(question.getDate());
                holder.answerCount.setText(wrapAnswerCount(question.getAnswerCount()));
                holder.acceptCount.setText(wrapCount(question.getExciting()));
                holder.naiveCount.setText(wrapCount(question.getNaive()));
            }
        });
    }

    public void loadImage(QuestionResult.Data.Question question, QuestionHolder holder) {
        Glide.with(context)
                .load(question.getAuthorAvatar())
                .apply(requestOptions)
                .into(holder.avatarImage);

        if (question.isIs_favorite())
            holder.favoriteImage.setImageResource(R.drawable.like2);
        if (question.isIs_naive())
            holder.naiveImage.setImageResource(R.drawable.dislike_1);
        if (question.isIs_exciting())
            holder.acceptImage.setImageResource(R.drawable.good2);
    }

    public void switchExciting(QuestionResult.Data.Question question, QuestionHolder holder) {
        if (!question.isIs_exciting())
            exciting(question, holder);
        else
            cancelExciting(question, holder);
    }

    public void switchNaive(QuestionResult.Data.Question question, QuestionHolder holder) {
        if (question.isIs_naive())
            cancelNaive(question, holder);
        else
            naive(question, holder);
    }

    public void switchFavorite(QuestionResult.Data.Question question, QuestionHolder holder) {
        if (question.isIs_favorite())
            cancelFavorite(question, holder);
        else favorite(question, holder);
    }

    private int code = 400;

    private void exciting(final QuestionResult.Data.Question question, final QuestionHolder holder) {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                code = App.getInstance().getInternetModel().excitingAnswer(question.getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            holder.acceptImage.setImageResource(R.drawable.good2);
                            question.setExciting(question.getExciting() + 1);
                            holder.acceptCount.setText(wrapCount(question.getExciting()));
                            question.setIs_exciting(true);
                        } else Toast.makeText(context, "网络错误..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void cancelExciting(final QuestionResult.Data.Question question, final QuestionHolder holder) {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                code = App.getInstance().getInternetModel().cancelExcitingQuestion(question.getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            holder.acceptImage.setImageResource(R.drawable.good);
                            question.setExciting(question.getExciting() - 1);
                            holder.acceptCount.setText(wrapCount(question.getExciting()));
                            question.setIs_exciting(false);
                        } else Toast.makeText(context, "网络错误..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void naive(final QuestionResult.Data.Question question, final QuestionHolder holder) {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                code = App.getInstance().getInternetModel().naiveAnswer(question.getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            holder.naiveImage.setImageResource(R.drawable.dislike_1);
                            question.setNaive(question.getNaive() + 1);
                            holder.naiveCount.setText(wrapCount(question.getNaive()));
                            question.setIs_naive(true);
                        } else Toast.makeText(context, "网络错误..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void cancelNaive(final QuestionResult.Data.Question question, final QuestionHolder holder) {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                code = App.getInstance().getInternetModel().cancelNaiveAnswer(question.getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            holder.naiveImage.setImageResource(R.drawable.dislike);
                            question.setNaive(question.getNaive() - 1);
                            holder.naiveCount.setText(wrapCount(question.getNaive()));
                            question.setIs_naive(false);
                        } else Toast.makeText(context, "网络错误..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void favorite(final QuestionResult.Data.Question question, final QuestionHolder holder) {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                code = App.getInstance().getInternetModel().favorite(question.getId());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            question.setIs_favorite(true);
                            holder.favoriteImage.setImageResource(R.drawable.like2);
                        } else Toast.makeText(context, "网络错误..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void cancelFavorite(final QuestionResult.Data.Question question, final QuestionHolder holder) {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                code = App.getInstance().getInternetModel().cancelFavorite(question.getId());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            holder.favoriteImage.setImageResource(R.drawable.like);
                            question.setIs_favorite(false);
                        } else Toast.makeText(context, "网络错误..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void switchAnswerRv(QuestionResult.Data.Question question, QuestionHolder holder) {
        if (question.getAnswerCount() == 0) return;
        if (holder.answerRv.getVisibility() == View.GONE) {
            openRv(question, holder);
        } else {
            closeRv(question, holder);
        }

    }

    private void openRv(final QuestionResult.Data.Question question, final QuestionHolder holder) {
        //加载动画
        holder.answerRv.setLayoutManager(new LinearLayoutManager(context));
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                final AnswerResult answerResult = App.getInstance().getInternetModel().getAnswerList(0, 20, question.getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        holder.answerRv.setAdapter(new AnswerRvAdapter(answerResult.getData().getAnswers(), context));
                        holder.answerRv.setVisibility(View.VISIBLE);
                        holder.expandImage.setImageResource(R.drawable.narrow);
                        holder.answerCount.setText("收起回复");
                    }
                });
            }
        });
    }

    private void closeRv(QuestionResult.Data.Question question, QuestionHolder holder) {
        holder.answerRv.setVisibility(View.GONE);
        holder.expandImage.setImageResource(R.drawable.expand);
        holder.answerCount.setText(wrapAnswerCount(question.getAnswerCount()));
    }

    private void runOnUiThread(Runnable runnable) {
        ((Activity) context).runOnUiThread(runnable);
    }

    private String wrapCount(int count) {
        return "(" + count + ")";
    }

    private String wrapAnswerCount(int answerCount) {
        if (answerCount > 0)
            return answerCount + "条回复";
        else return "没有回复..";
    }
}
