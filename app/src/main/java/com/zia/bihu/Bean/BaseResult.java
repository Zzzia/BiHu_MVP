package com.zia.bihu.Bean;


import org.json.JSONException;
import org.json.JSONObject;

public class BaseResult {
    private int status;
    private String info;
    private Object dataObject;

    public BaseResult(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            status = jsonObject.getInt("status");
            info = jsonObject.getString("info");
            dataObject = jsonObject.get("data");
        } catch (JSONException ignored) {
        }
    }

    public BaseResult() {
    }

    protected Object getDataObject() {
        return dataObject;
    }

    @Override
    public String toString() {
        return status + info + "\n";
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
