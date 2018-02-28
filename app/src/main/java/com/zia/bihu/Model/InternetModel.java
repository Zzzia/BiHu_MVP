package com.zia.bihu.Model;

import com.zia.bihu.Bean.AnswerResult;
import com.zia.bihu.Bean.BaseResult;
import com.zia.bihu.Bean.LoginResult;
import com.zia.bihu.Bean.QuestionResult;
import com.zia.bihu.Bean.RegisterResult;
import com.zia.bihu.Util.App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zia on 2018/2/19.
 */
public class InternetModel {

    private static final String baseUrl = "http://bihu.jay86.com/";

    public LoginResult login(String username, String password) {
        return new LoginResult(getJson("login.php", new ParametersBuilder()
                .put("username", username)
                .put("password", password)));
    }

    public RegisterResult register(String username, String password) {
        return new RegisterResult(getJson("register.php", new ParametersBuilder()
                .put("username", username)
                .put("password", password)));
    }

    public QuestionResult getQuestionList(int page, int count) {
        return new QuestionResult(getJson("getQuestionList.php", new ParametersBuilder()
                .put("page", String.valueOf(page))
                .put("count", String.valueOf(count))
                .put("token", App.getInstance().getSelfData().getToken())));
    }

    public int excitingQuestion(int id) {
        return exciting(id, 1, "exciting.php");
    }

    public int excitingAnswer(int id) {
        return exciting(id, 2, "exciting.php");
    }

    public int cancelExcitingQuestion(int id) {
        return exciting(id, 1, "cancelExciting.php");
    }

    public int cancelExcitingAnswer(int id) {
        return exciting(id, 2, "cancelExciting.php");
    }

    public int naiveQuestion(int id) {
        return naive(id, 1, "naive.php");
    }

    public int naiveAnswer(int id) {
        return naive(id, 2, "naive.php");
    }

    public int cancelNaiveQuestion(int id) {
        return naive(id, 1, "cancelNaive.php");
    }

    public int cancelNaiveAnswer(int id) {
        return naive(id, 2, "cancelNaive.php");
    }

    public int favorite(int qid) {
        return favorite(qid, "favorite.php");
    }

    public int cancelFavorite(int qid) {
        return favorite(qid, "cancelFavorite.php");
    }

    private int favorite(int qid, String url) {
        return new BaseResult(getJson(url, new ParametersBuilder()
                .put("qid", String.valueOf(qid))
                .put("token", App.getInstance().getSelfData().getToken())))
                .getStatus();
    }

    private int naive(int id, int type, String url) {
        return new BaseResult(getJson(url, new ParametersBuilder()
                .put("id", String.valueOf(id))
                .put("type", String.valueOf(type))
                .put("token", App.getInstance().getSelfData().getToken())))
                .getStatus();
    }

    private int exciting(int id, int type, String url) {
        return new BaseResult(getJson(url, new ParametersBuilder()
                .put("id", String.valueOf(id))
                .put("type", String.valueOf(type))
                .put("token", App.getInstance().getSelfData().getToken())))
                .getStatus();
    }

    public AnswerResult getAnswerList(int page, int count, int qid) {
        return new AnswerResult(getJson("getAnswerList.php", new ParametersBuilder()
                .put("page", String.valueOf(page))
                .put("count", String.valueOf(count))
                .put("qid", String.valueOf(qid))
                .put("token", App.getInstance().getSelfData().getToken())));
    }

    private String getJson(String url, ParametersBuilder parametersBuilder) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        // post请求的参数
        url = baseUrl + url;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            if (parametersBuilder != null) {
                connection.setDoOutput(true);
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(parametersBuilder.getParameters().getBytes());
            }
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println(result);
        return result.toString();
    }

    private class ParametersBuilder {
        private StringBuilder parameters = new StringBuilder();

        public ParametersBuilder put(String key, String value) {
            parameters.append(key);
            parameters.append("=");
            parameters.append(value);
            parameters.append("&");
            return this;
        }

        public String getParameters() {
            int lastPosition = parameters.length();
            parameters.delete(lastPosition - 1, lastPosition);
            return parameters.toString();
        }

        @Override
        public String toString() {
            return getParameters();
        }
    }
}
