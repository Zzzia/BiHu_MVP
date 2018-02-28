package com.zia.bihu.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zia.bihu.Bean.QuestionResult;
import com.zia.bihu.Interface.ItemController;
import com.zia.bihu.Presenter.ItemPresenter;
import com.zia.bihu.R;
import com.zia.bihu.Util.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zia on 2018/2/19.
 */
public class QuestionRvAdapter extends RecyclerView.Adapter<QuestionHolder> {

    private List<QuestionResult.Data.Question> questionList;
    private Context context;
    private ItemPresenter presenter;
    private ItemController itemController;
    private static final int countPerPage = 20;
    private int totalCount;

    public void init() {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                QuestionResult.Data data = App
                        .getInstance()
                        .getInternetModel()
                        .getQuestionList(0, countPerPage)
                        .getData();
                questionList = data.getQuestions();
                totalCount = data.getTotalCount();
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void loadMoreQuestions() {
        App.getInstance().startNewThread(new Runnable() {
            @Override
            public void run() {
                questionList.addAll(App
                        .getInstance()
                        .getInternetModel()
                        .getQuestionList(questionList.size() / countPerPage, countPerPage)
                        .getData()
                        .getQuestions());
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void setItemController(ItemController itemController) {
        this.itemController = itemController;
    }

    public QuestionRvAdapter(Context context) {
        this.context = context;
        questionList = new ArrayList<>();
        presenter = new ItemPresenter(context);
    }

    @Override
    public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(final QuestionHolder holder, int position) {
        final QuestionResult.Data.Question question = questionList.get(position);
        presenter.loadText(question, holder);
        presenter.loadImage(question, holder);

        holder.expandLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemController != null) {
                    int position = questionList.indexOf(question);
                    itemController.top(position);
                }
                presenter.switchAnswerRv(question, holder);
            }
        });

        holder.acceptLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.switchExciting(question, holder);
            }
        });

        holder.naiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.switchNaive(question, holder);
            }
        });

        holder.favoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.switchFavorite(question, holder);
            }
        });

        if (position + 1 < totalCount && position == questionList.size()) {
            loadMoreQuestions();
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
