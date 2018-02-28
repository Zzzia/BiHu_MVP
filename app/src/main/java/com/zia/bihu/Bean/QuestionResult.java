package com.zia.bihu.Bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zia on 2018/2/19.
 */
public class QuestionResult extends BaseResult {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public QuestionResult(String json) {
        super(json);
        if (getDataObject() != null) {
            try {
                JSONObject jsonObject = (JSONObject) getDataObject();
                data = new Data();
                data.setCurPage(jsonObject.getInt("curPage"));
                data.setTotalCount(jsonObject.getInt("totalCount"));
                data.setTotalPage(jsonObject.getInt("totalPage"));
                JSONArray jsonArray = jsonObject.getJSONArray("questions");
                List<Data.Question> questions = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    Data.Question question = new Data.Question();
                    question.setAnswerCount(jb.getInt("answerCount"));
                    question.setAuthorAvatar(jb.getString("authorAvatar"));
                    question.setAuthorId(jb.getInt("authorId"));
                    question.setAuthorName(jb.getString("authorName"));
                    question.setContent(jb.getString("content"));
                    question.setDate(jb.getString("date"));
                    question.setExciting(jb.getInt("exciting"));
                    question.setId(jb.getInt("id"));
                    question.setImages(jb.getString("images"));
                    question.setIs_exciting(jb.getBoolean("is_exciting"));
                    question.setIs_favorite(jb.getBoolean("is_favorite"));
                    question.setIs_naive(jb.getBoolean("is_naive"));
                    question.setNaive(jb.getInt("naive"));
                    question.setRecent(jb.getString("recent"));
                    question.setTitle(jb.getString("title"));
                    questions.add(question);
                }
                data.setQuestions(questions);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public QuestionResult() {
    }

    public static class Data {
        int totalCount;
        int totalPage;
        List<Question> questions;
        int curPage;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public static class Question {
            int id;
            String title;
            String content;
            String images;
            String date;
            int exciting;
            int naive;
            String recent;
            int answerCount;
            int authorId;
            String authorName;
            String authorAvatar;
            boolean is_exciting;
            boolean is_naive;
            boolean is_favorite;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getExciting() {
                return exciting;
            }

            public void setExciting(int exciting) {
                this.exciting = exciting;
            }

            public int getNaive() {
                return naive;
            }

            public void setNaive(int naive) {
                this.naive = naive;
            }

            public String getRecent() {
                return recent;
            }

            public void setRecent(String recent) {
                this.recent = recent;
            }

            public int getAnswerCount() {
                return answerCount;
            }

            public void setAnswerCount(int answerCount) {
                this.answerCount = answerCount;
            }

            public int getAuthorId() {
                return authorId;
            }

            public void setAuthorId(int authorId) {
                this.authorId = authorId;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }

            public String getAuthorAvatar() {
                return authorAvatar;
            }

            public void setAuthorAvatar(String authorAvatar) {
                this.authorAvatar = authorAvatar;
            }

            public boolean isIs_exciting() {
                return is_exciting;
            }

            public void setIs_exciting(boolean is_exciting) {
                this.is_exciting = is_exciting;
            }

            public boolean isIs_naive() {
                return is_naive;
            }

            public void setIs_naive(boolean is_naive) {
                this.is_naive = is_naive;
            }

            public boolean isIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(boolean is_favorite) {
                this.is_favorite = is_favorite;
            }
        }
    }
}
