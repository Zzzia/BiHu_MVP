package com.zia.bihu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zia.bihu.R;

/**
 * Created by zia on 2018/2/21.
 */
public class QuestionHolder extends RecyclerView.ViewHolder {

    public LinearLayout acceptLayout, naiveLayout, expandLayout;
    public TextView title, username, content, acceptCount, naiveCount, date, answerCount;
    public ImageView acceptImage, naiveImage, favoriteImage, avatarImage,expandImage;
    public RecyclerView answerRv;

    public QuestionHolder(View itemView) {
        super(itemView);
        acceptLayout = itemView.findViewById(R.id.item_acceptLayout);
        naiveLayout = itemView.findViewById(R.id.item_naiveLayout);
        title = itemView.findViewById(R.id.item_title);
        username = itemView.findViewById(R.id.item_username);
        content = itemView.findViewById(R.id.item_content);
        acceptCount = itemView.findViewById(R.id.item_acceptCount);
        naiveCount = itemView.findViewById(R.id.item_naiveCount);
        acceptImage = itemView.findViewById(R.id.item_acceptImage);
        naiveImage = itemView.findViewById(R.id.item_naiveImage);
        favoriteImage = itemView.findViewById(R.id.item_favoriteImage);
        avatarImage = itemView.findViewById(R.id.item_avatar);
        date = itemView.findViewById(R.id.item_date);
        answerRv = itemView.findViewById(R.id.item_recyclerView);
        answerCount = itemView.findViewById(R.id.item_answerCount);
        expandLayout = itemView.findViewById(R.id.item_expandLayout);
        expandImage = itemView.findViewById(R.id.item_expand);
    }

}
