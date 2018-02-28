package com.zia.bihu.Bean;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterResult extends BaseResult {
    private Data data;

    public RegisterResult(){}
    public RegisterResult(String json) {
        super(json);
        if (getDataObject() != null) {
            try {
                JSONObject jsonObject = (JSONObject) getDataObject();
                data = new Data();
                data.setId(jsonObject.getInt("id"));
                data.setAvatar(jsonObject.getString("avatar"));
                data.setPassword(jsonObject.getString("password"));
                data.setUsername(jsonObject.getString("username"));
                data.setToken(jsonObject.getString("token"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString() + data;
    }

    public static class Data {
        private int id;
        private String username;
        private String password;
        private String avatar;
        private String token;

        @Override
        public String toString() {
            return id + username + password + avatar + token;
        }

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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
