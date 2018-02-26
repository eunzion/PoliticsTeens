package droidmentor.PoliticTeens_Client;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import droidmentor.PoliticTeens_Client.Fragment.CongressFragment;
import droidmentor.PoliticTeens_Client.Fragment.MyJungdangFragment;
import droidmentor.PoliticTeens_Client.Fragment.PlazaFragment;
import droidmentor.PoliticTeens_Client.Fragment.MagazineFragment;

public class MainActivity extends BaseActivity2 {

    BottomNavigationView bottomNavigationView;


    //This is our viewPager
    private ViewPager viewPager;


    //Fragments

    MyJungdangFragment myJungdangFragment;

    CongressFragment congressFragment;
    PlazaFragment plazaFragment;
    MagazineFragment magazineFragment;
    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.


        Toolbar parent = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(parent);
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);

        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_congress:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_myjungdang:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_plaza:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.action_magazine:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */

        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        congressFragment = new CongressFragment();
        myJungdangFragment = new MyJungdangFragment();
        plazaFragment = new PlazaFragment();
        magazineFragment = new MagazineFragment();

        adapter.addFragment(congressFragment);
        adapter.addFragment(myJungdangFragment);
        adapter.addFragment(plazaFragment);
        adapter.addFragment(magazineFragment);
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SplashActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
