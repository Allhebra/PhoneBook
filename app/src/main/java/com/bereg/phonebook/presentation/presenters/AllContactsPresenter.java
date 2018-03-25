package com.bereg.phonebook.presentation.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.phonebook.domain.ContactInteractor;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.presentation.view.AllContactsView;
import com.bereg.phonebook.utils.Screens;
import com.bereg.phonebook.utils.SearchOccurrence;

import java.util.List;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 20.03.2018.
 */

@InjectViewState
public class AllContactsPresenter extends MvpPresenter<AllContactsView> {

    private static final String TAG = AllContactsPresenter.class.getSimpleName();

    private ContactInteractor mContactInteractor;
    private Router mRouter;
    private List<ContactModel> mContacts;

    public AllContactsPresenter(Router router) {
        mRouter = router;
        mContactInteractor = new ContactInteractor();
    }

    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mContacts = mContactInteractor.getAllContacts();
        getViewState().showAllContacts(mContacts);
        Log.e(TAG, "onFirstViewAttach:   " + mContacts.size());
    }

    public void onSearchTextSubmitted(String keyword) {

        getViewState().showAllContacts(SearchOccurrence.checkForKeywordOccurrence(mContacts, keyword));
    }

    public void onSearchTextChanged(String keyword) {

        getViewState().showAllContacts(SearchOccurrence.checkForKeywordOccurrence(mContacts, keyword));
    }

    public void showContactsDetail(ContactModel contact) {

        mRouter.navigateTo(Screens.DETAIL_CONTACT_SCREEN, contact.getId());
    }
}
