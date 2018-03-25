package com.bereg.phonebook.presentation.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.phonebook.presentation.view.MainView;
import com.bereg.phonebook.utils.Screens;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 20.03.2018.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private Router mRouter;

    public MainPresenter(Router router) {
        mRouter = router;
    }

    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mRouter.navigateTo(Screens.ALL_CONTACTS_SCREEN);
    }

    public void onAllContactsClicked() {

        mRouter.navigateTo(Screens.ALL_CONTACTS_SCREEN);
    }

    public void onFriendsClicked() {

        mRouter.navigateTo(Screens.GROUP_CONTACTS_SCREEN, "FRIENDS");
    }

    public void onPeoplesClicked() {

        mRouter.navigateTo(Screens.GROUP_CONTACTS_SCREEN, "PEOPLES");
    }

    public void onAnimalsClicked() {

        mRouter.navigateTo(Screens.GROUP_CONTACTS_SCREEN, "ANIMALS");
    }
}
