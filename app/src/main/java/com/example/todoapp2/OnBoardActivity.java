package com.example.todoapp2;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chabbal.slidingdotsplash.SlidingSplashView;

import me.relex.circleindicator.CircleIndicator;

public class OnBoardActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        dots=new ImageView[]{findViewById(R.id.dot1),findViewById(R.id.dot2),findViewById(R.id.dot3)};

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                dots[0].setImageResource(R.drawable.ic_dot_uncheck);
                dots[1].setImageResource(R.drawable.ic_dot_uncheck);
                dots[2].setImageResource(R.drawable.ic_dot_uncheck);
                dots[i].setImageResource(R.drawable.ic_dot_check);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_on_board, container, false);
            TextView textView = rootView.findViewById(R.id.textTitle);
            ImageView imageView=rootView.findViewById(R.id.imageView);
            Button button=rootView.findViewById(R.id.button);
            Button btnNext=rootView.findViewById(R.id.button_next);
            Button btnBefore=rootView.findViewById(R.id.button_before);
            int number=getArguments().getInt(ARG_SECTION_NUMBER);


            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            switch (number){
                case 0:
                    textView.setText("Hello");
                    imageView.setImageResource(R.drawable.smile1);
                    rootView.setBackgroundResource(R.color.yellow);
                    button.setVisibility(View.GONE);
                    btnBefore.setVisibility(View.GONE);
                    btnNext.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    textView.setText("Kak dela?");
                    imageView.setImageResource(R.drawable.smile2);
                    rootView.setBackgroundResource(R.color.red);
                    button.setVisibility(View.GONE);
                    btnBefore.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    textView.setText("Chto delaesh?");
                    imageView.setImageResource(R.drawable.smile3);
                    rootView.setBackgroundResource(R.color.green);
                    button.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.GONE);
                    break;
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveState();
                    startActivity(new Intent(getContext(),MainActivity.class));
                    getActivity().finish();
                }
            });



            return rootView;
        }

        private void saveState() {
            SharedPreferences preferences=getContext().getSharedPreferences("settings",MODE_PRIVATE);
            preferences.edit().putBoolean("shown",true).apply();
        }



    }

    public void clickBefore(View view) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
    }

    public void clickNext(View view) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(position );

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}
