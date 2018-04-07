package com.bereg.phonebook.presentation.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.phonebook.domain.ContactInteractor;
import com.bereg.phonebook.domain.KtContactInteractor;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.presentation.view.AllContactsView;
import com.bereg.phonebook.utils.Screens;
import com.bereg.phonebook.utils.SearchOccurrence;

import java.util.ArrayList;
import java.util.List;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 20.03.2018.
 */

@InjectViewState
public class AllContactsPresenter extends MvpPresenter<AllContactsView> {

    private static final String TAG = AllContactsPresenter.class.getSimpleName();

    private KtContactInteractor mContactInteractor;
    private Router mRouter;
    private List<ContactModel> mContacts;
    private List<ContactModel> searchResult;
    private int lastKeywordSymbolQuantity = 0;

    public AllContactsPresenter(Router router) {
        mRouter = router;
        mContactInteractor = new KtContactInteractor();
    }

    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mContacts = mContactInteractor.getAllContacts();
        searchResult = new ArrayList<>(mContacts);
        getViewState().showAllContacts(mContacts);
        Log.e(TAG, "onFirstViewAttach:   " + mContacts.size());
    }

    public void onSearchTextSubmitted(String keyword) {

        //searchResult.clear();
        //searchResult = new ArrayList<>(mContacts);
        getViewState()
                .showAllContacts(SearchOccurrence.checkForKeywordOccurrence(searchResult, keyword));
    }

    public void onSearchTextChanged(String keyword) {

        if (keyword.length() >= lastKeywordSymbolQuantity) {
            getViewState()
                    .showAllContacts(SearchOccurrence.checkForKeywordOccurrence(searchResult, keyword));
            lastKeywordSymbolQuantity = keyword.length();
        }else {
            searchResult.clear();
            searchResult.addAll(mContacts);
            getViewState()
                    .showAllContacts(SearchOccurrence.checkForKeywordOccurrence(searchResult, keyword));
        }
    }

    public void showContactsDetail(ContactModel contact) {

        mRouter.navigateTo(Screens.DETAIL_CONTACT_SCREEN, contact.getId());
    }
}
