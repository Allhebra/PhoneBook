package com.bereg.phonebook.domain;

import com.bereg.phonebook.data.SharedPreferencesManager;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.repositories.ContactsResourceRepositoryImpl;
import com.bereg.phonebook.repositories.IContactsRepository;

import java.util.List;

/**
 * Created by 1 on 21.03.2018.
 */

public class ContactInteractor {

    private static final String TAG = ContactInteractor.class.getSimpleName();

    private SharedPreferencesManager mSharedPreferencesManager;
    private IContactsRepository mIContactsRepository;

    public ContactInteractor() {

        mSharedPreferencesManager = new SharedPreferencesManager();
        mIContactsRepository = new ContactsResourceRepositoryImpl();
    }

    public List<ContactModel> getAllContacts() {

        List<ContactModel> contacts = mIContactsRepository.getAllContacts();
        return mSharedPreferencesManager.readAllContactsGroups(contacts);
    }
}
