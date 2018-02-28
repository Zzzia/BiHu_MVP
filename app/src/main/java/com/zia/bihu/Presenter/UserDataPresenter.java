package com.zia.bihu.Presenter;

import android.widget.Toast;

import com.zia.bihu.Bean.LoginResult;
import com.zia.bihu.Bean.RegisterResult;
import com.zia.bihu.Bean.UserData;
import com.zia.bihu.Interface.InputController;
import com.zia.bihu.Interface.InternetListener;
import com.zia.bihu.Util.App;

/**
 * Created by zia on 2018/2/19.
 */
public class UserDataPresenter {

    private InputController inputController;

    public UserDataPresenter(InputController inputController) {
        this.inputController = inputController;
    }

    public void login(final InternetListener internetListener) {
        final String username = inputController.getUsername();
        final String password = inputController.getPassword();
        if (!checkData(username, password))
            return;
        if (internetListener == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final LoginResult loginResult = App.getInstance().getInternetModel().login(username, password);
                inputController.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loginResult.getStatus() == 200) {
                            saveSelfData(loginResult.getData().getId(),
                                    loginResult.getData().getToken(),
                                    loginResult.getData().getUsername(),
                                    loginResult.getData().getAvatar());
                            internetListener.onFinish(loginResult);
                        } else {
                            internetListener.onBadCode(loginResult);
                        }
                    }
                });
            }
        }).start();
    }

    public void register(final InternetListener internetListener) {
        final String username = inputController.getUsername();
        final String password = inputController.getPassword();
        if (!checkData(username, password))
            return;
        if (internetListener == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final RegisterResult registerResult = App.getInstance().getInternetModel().register(username, password);
                inputController.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (registerResult.getStatus() == 200) {
                            saveSelfData(registerResult.getData().getId(),
                                    registerResult.getData().getToken(),
                                    registerResult.getData().getUsername(),
                                    registerResult.getData().getAvatar());
                            internetListener.onFinish(registerResult);
                        } else {
                            internetListener.onBadCode(registerResult);
                        }
                    }
                });
            }
        }).start();
    }

    private void saveSelfData(int id,String token,String username,String avatar){
        UserData selfData = new UserData();
        selfData.setId(id);
        selfData.setToken(token);
        selfData.setUsername(username);
        selfData.setAvatar(avatar);
        App.getInstance().setSelfData(selfData);
    }

    private boolean checkData(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(inputController.getActivity(), "输入不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void toast(String msg) {
        Toast.makeText(inputController.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
