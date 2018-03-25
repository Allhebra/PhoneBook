package com.bereg.phonebook.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.bereg.phonebook.models.ContactModel;

/**
 * Created by 1 on 21.03.2018.
 */

public interface DetailContactView extends MvpView {

    void showContactDetails(ContactModel contactModel);
}
