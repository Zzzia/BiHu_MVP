package com.zia.bihu.Bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zia on 2018/2/21.
 */
public class AnswerResult extends BaseResult {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public AnswerResult(String json) {
        super(json);
        if (getDataObject() != null) {
            try {
                JSONObject jsonObject = (JSONObject) getDataObject();
                data = new Data();
                data.setCurPage(jsonObject.getInt("curPage"));
                data.setTotalCount(jsonObject.getInt("totalCount"));
                data.setTotalPage(jsonObject.getInt("totalPage"));
                JSONArray jsonArray = jsonObject.getJSONArray("answers");
                List<Data.Answer> questions = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    Data.Answer answer = new Data.Answer();
                    answer.setBest(jb.getInt("best"));
                    answer.setAuthorAvatar(jb.getString("authorAvatar"));
                    answer.setAuthorId(jb.getInt("authorId"));
                    answer.setAuthorName(jb.getString("authorName"));
                    answer.setContent(jb.getString("content"));
                    answer.setDate(jb.getString("date"));
                    answer.setExciting(jb.getInt("exciting"));
                    answer.setId(jb.getInt("id"));
                    answer.setImages(jb.getString("images"));
                    answer.setIs_exciting(jb.getBoolean("is_exciting"));
                    answer.setIs_naive(jb.getBoolean("is_naive"));
                    answer.setNaive(jb.getInt("naive"));
                    questions.add(answer);
                }
                data.setAnswers(questions);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public AnswerResult() {
    }

    public static class Data {
        int totalCount;
        int totalPage;
        List<Answer> answers;
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

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> questions) {
            this.answers = questions;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public static class Answer {
            int id;
            String content;
            String images;
            String date;
            int best;
            int exciting;
            int naive;
            int authorId;
            String authorName;
            String authorAvatar;
            boolean is_exciting;
            boolean is_naive;

            public int getBest() {
                return best;
            }

            public void setBest(int best) {
                this.best = best;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

        }
    }
}
