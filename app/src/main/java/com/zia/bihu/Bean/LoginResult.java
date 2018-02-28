package com.zia.bihu.Bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zia on 2018/2/19.
 */
public class LoginResult extends BaseResult {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public LoginResult(String json) {
        super(json);
        if (getDataObject() != null){
            try {
                JSONObject jsonObject = (JSONObject) getDataObject();
                data = new Data();
                data.setId(jsonObject.getInt("id"));
                data.setAvatar(jsonObject.getString("avatar"));
                data.setUsername(jsonObject.getString("username"));
                data.setToken(jsonObject.getString("token"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public LoginResult() {
    }

    public static class Data{
        private int id;
        private String username;
        private String avatar;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
