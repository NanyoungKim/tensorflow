package org.tensorflow.demo;

//import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity{
    FragmentPagerAdapter adapterViewPager;
    public static Context mContext;
    ViewPager vpPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpPager = (ViewPager)findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        vpPager.setCurrentItem(1);


        mContext = this;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEM = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = HelpActivity.newInstance();
                    break;
                case 1:
                    fragment = MapActivity.newInstance();
                    break;
                default:
                    fragment = null;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_ITEM;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        final int pos = 3;
        vpPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                vpPager.setCurrentItem(pos);
            }
        }, 100);
    }
}
