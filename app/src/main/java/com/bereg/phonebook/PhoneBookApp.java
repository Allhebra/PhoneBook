package com.bereg.phonebook;

import android.app.Application;
import android.util.Log;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 20.03.2018.
 */

public class PhoneBookApp extends Application {

    private static final String TAG = PhoneBookApp.class.getSimpleName();

    private static PhoneBookApp instance;
    private Cicerone<Router> cicerone;
    public Router mRouter;
    public NavigatorHolder mNavigatorHolder;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        cicerone = Cicerone.create();
        Log.e(TAG, String.valueOf(cicerone));
    }

    public NavigatorHolder getNavigatorHolder() {
        Log.e(TAG, "getNavigatorHolder");
        if (mNavigatorHolder == null) mNavigatorHolder = cicerone.getNavigatorHolder();
        return mNavigatorHolder;
    }

    public Router getRouter() {
        Log.e(TAG, "getRouter");
        if (mRouter == null) mRouter = cicerone.getRouter();
        return mRouter;
    }

    public static PhoneBookApp getInstance() {
        return instance;
    }
}
