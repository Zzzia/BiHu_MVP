package com.zia.bihu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zia.bihu.R;

/**
 * Created by zia on 2018/2/21.
 */
public class AnswerHolder extends RecyclerView.ViewHolder {

    public TextView username,content,date,acceptCount;
    public ImageView acceptIamge;

    public AnswerHolder(View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.answerItem_username);
        content = itemView.findViewById(R.id.answerItem_content);
        date = itemView.findViewById(R.id.answerItem_date);
        acceptCount = itemView.findViewById(R.id.answerItem_acceptCount);
        acceptIamge = itemView.findViewById(R.id.answerItem_accept);
    }
}
