package com.bereg.phonebook.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bereg.phonebook.models.ContactModel;

import java.util.List;

/**
 * Created by 1 on 20.03.2018.
 */

public interface AllContactsView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showAllContacts(List<ContactModel> allContacts);
}
