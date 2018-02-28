package com.zia.bihu.View;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.zia.bihu.Interface.PagerController;
import com.zia.bihu.Presenter.UserDataPresenter;
import com.zia.bihu.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements PagerController {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewPager = findViewById(R.id.loginActivity_viewPager);
        viewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);

    }

    @Override
    public void scrollToPage(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
        //管理返回键
        if (viewPager.getCurrentItem() == 1)
            scrollToPage(0);
        else super.onBackPressed();
    }

    private class Adapter extends FragmentPagerAdapter {

        private List<Fragment> views;

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (views == null) {
                views = new ArrayList<>();
                LoginFragment loginFragment = new LoginFragment();
                loginFragment.setPagerController(LoginActivity.this);
                RegisterFragment registerFragment = new RegisterFragment();
                views.add(loginFragment);
                views.add(registerFragment);
            }
            return views.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
