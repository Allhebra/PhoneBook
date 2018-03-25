package com.bereg.phonebook.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.bereg.phonebook.models.ContactModel;

import java.util.List;

/**
 * Created by 1 on 25.03.2018.
 */

public interface GroupContactsView extends MvpView {

    void showGroupContacts(List<ContactModel> groupContacts);
}
