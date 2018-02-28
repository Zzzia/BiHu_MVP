package com.zia.bihu.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zia.bihu.Bean.LoginResult;
import com.zia.bihu.Interface.InputController;
import com.zia.bihu.Interface.InternetListener;
import com.zia.bihu.Interface.PagerController;
import com.zia.bihu.Presenter.UserDataPresenter;
import com.zia.bihu.R;


public class LoginFragment extends Fragment implements InputController {

    private PagerController pagerController;
    private Button loginButton;
    private TextInputEditText userEdit, pswEdit;
    private TextView registerView;
    private UserDataPresenter presenter;

    public void setPagerController(PagerController controller) {
        pagerController = controller;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new UserDataPresenter(this);

        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewPager滚动到注册fragment
                pagerController.scrollToPage(1);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(new InternetListener() {
                    @Override
                    public void onFinish(Object obj) {
                        Toast.makeText(getContext(), "登录成功！", Toast.LENGTH_SHORT).show();
                        if (getActivity() != null) {
                            getActivity().startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        }
                    }

                    @Override
                    public void onBadCode(Object obj) {
                        Toast.makeText(getContext(), ((LoginResult) obj).getInfo()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initView(View view) {
        loginButton = view.findViewById(R.id.login_mainButton);
        userEdit = view.findViewById(R.id.login_username);
        pswEdit = view.findViewById(R.id.login_password);
        registerView = view.findViewById(R.id.login_quickRegister);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return view;
    }

    @Override
    public String getUsername() {
        return userEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return pswEdit.getText().toString();
    }
}
