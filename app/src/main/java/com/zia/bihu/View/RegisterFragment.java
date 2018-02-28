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
import android.widget.Toast;

import com.zia.bihu.Bean.RegisterResult;
import com.zia.bihu.Interface.InputController;
import com.zia.bihu.Interface.InternetListener;
import com.zia.bihu.Interface.PagerController;
import com.zia.bihu.Presenter.UserDataPresenter;
import com.zia.bihu.R;

import java.util.Objects;


public class RegisterFragment extends Fragment implements InputController {

    private TextInputEditText userEdit, pswEdit;
    private Button completeButton;
    private UserDataPresenter presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new UserDataPresenter(this);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register(new InternetListener() {
                    @Override
                    public void onFinish(Object obj) {
                        Toast.makeText(getContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                        if (getActivity() != null) {
                            getActivity().startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        }
                    }

                    @Override
                    public void onBadCode(Object obj) {
                        Toast.makeText(getContext(), ((RegisterResult) obj).getInfo(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initView(View view) {
        userEdit = view.findViewById(R.id.register_username);
        pswEdit = view.findViewById(R.id.register_password);
        completeButton = view.findViewById(R.id.register_button);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
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
