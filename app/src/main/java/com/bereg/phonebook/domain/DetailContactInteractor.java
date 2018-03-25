package com.bereg.phonebook.domain;

import com.bereg.phonebook.data.SharedPreferencesManager;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.repositories.DetailContactResourceRepositoryImpl;
import com.bereg.phonebook.repositories.IDetailContactRepository;

/**
 * Created by 1 on 21.03.2018.
 */

public class DetailContactInteractor {

    private SharedPreferencesManager mSharedPreferencesManager;
    private IDetailContactRepository mIDetailContactRepository;

    public DetailContactInteractor() {

        mIDetailContactRepository = new DetailContactResourceRepositoryImpl();
        mSharedPreferencesManager = new SharedPreferencesManager();
    }

    public ContactModel getContactDetails(int id) {

        ContactModel mContact = mIDetailContactRepository.getContactDetails(id);
        return mSharedPreferencesManager.readContactGroups(mContact);
    }
}
