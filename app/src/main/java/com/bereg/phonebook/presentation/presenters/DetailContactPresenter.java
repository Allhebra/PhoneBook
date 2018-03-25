package com.bereg.phonebook.presentation.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.phonebook.domain.ContactGroupsInteractor;
import com.bereg.phonebook.domain.DetailContactInteractor;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.presentation.view.DetailContactView;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 21.03.2018.
 */

@InjectViewState
public class DetailContactPresenter extends MvpPresenter<DetailContactView> {

    private static final String TAG = DetailContactPresenter.class.getSimpleName();

    private DetailContactInteractor mDetailContactInteractor;
    private ContactGroupsInteractor mContactGroupsInteractor;
    private Router mRouter;

    public DetailContactPresenter(Router router) {

        mDetailContactInteractor = new DetailContactInteractor();
        mContactGroupsInteractor = new ContactGroupsInteractor();
        mRouter = router;
    }

    public void getContact(int id) {

        Log.e(TAG, "getContact: " + id);
        getViewState().showContactDetails(mDetailContactInteractor.getContactDetails(id));
    }

    public void saveContactGroups(ContactModel contact) {

        mContactGroupsInteractor.saveContactGroupsToPrefs(contact);
    }
}
