package com.bereg.phonebook.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.phonebook.domain.ContactGroupsInteractor;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.presentation.view.GroupContactsView;
import com.bereg.phonebook.utils.Screens;
import com.bereg.phonebook.utils.SearchOccurrence;

import java.util.List;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 25.03.2018.
 */

@InjectViewState
public class GroupContactsPresenter extends MvpPresenter<GroupContactsView> {


    private ContactGroupsInteractor mContactGroupsInteractor;
    private Router mRouter;
    private List<ContactModel> mContacts;

    public GroupContactsPresenter(Router router) {
        mRouter = router;
        mContactGroupsInteractor = new ContactGroupsInteractor();
    }

    public void getGroupContacts(String groupName) {

        mContacts = mContactGroupsInteractor.getContactsFromPrefsByGroupName(groupName);
        getViewState().showGroupContacts(mContacts);
    }

    public void onSearchTextSubmitted(String keyword) {

        getViewState().showGroupContacts(SearchOccurrence.checkForKeywordOccurrence(mContacts, keyword));
    }

    public void onSearchTextChanged(String keyword) {

        getViewState().showGroupContacts(SearchOccurrence.checkForKeywordOccurrence(mContacts, keyword));
    }

    public void showContactsDetail(ContactModel contact) {

        mRouter.navigateTo(Screens.DETAIL_CONTACT_SCREEN, contact.getId());
    }
}
