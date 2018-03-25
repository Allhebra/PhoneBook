package com.bereg.phonebook.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.phonebook.PhoneBookApp;
import com.bereg.phonebook.R;
import com.bereg.phonebook.presentation.presenters.MainPresenter;
import com.bereg.phonebook.presentation.view.MainView;
import com.bereg.phonebook.ui.fragments.AllContactsFragment;
import com.bereg.phonebook.ui.fragments.DetailContactFragment;
import com.bereg.phonebook.ui.fragments.GroupContactsFragment;
import com.bereg.phonebook.utils.Screens;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class MainActivity extends MvpAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    @InjectPresenter
    MainPresenter mMainPresenter;

    @ProvidePresenter
    MainPresenter provideMainPresenter() {

        Router router = PhoneBookApp.getInstance().getRouter();
        return new MainPresenter(router);
    }

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragment_container) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.ALL_CONTACTS_SCREEN:
                    Log.e(TAG, "ALL_CONTACTS_SCREEN");
                    return AllContactsFragment.newInstance();
                case Screens.GROUP_CONTACTS_SCREEN:
                    Log.e(TAG, "GROUP_CONTACTS_SCREEN");
                    return GroupContactsFragment.newInstance((String) data);
                case Screens.DETAIL_CONTACT_SCREEN:
                    Log.e(TAG, "DETAIL_CONTACT_SCREEN");
                    return DetailContactFragment.newInstance((int) data);
            }
            Log.e(TAG, "NULL");
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PhoneBookApp.getInstance().getNavigatorHolder().setNavigator(navigator);
        Log.e(TAG, "onResume");
        Toast.makeText(MainActivity.this, "ActivityOnResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {

        PhoneBookApp.getInstance().getNavigatorHolder().removeNavigator();
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_all_contacts) {
            mMainPresenter.onAllContactsClicked();
        } else if (id == R.id.nav_friends) {
            mMainPresenter.onFriendsClicked();
        } else if (id == R.id.nav_people) {
            mMainPresenter.onPeoplesClicked();
        } else if (id == R.id.nav_animals) {
            mMainPresenter.onAnimalsClicked();
        } else if (id == R.id.nav_about_program) {

        } else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
