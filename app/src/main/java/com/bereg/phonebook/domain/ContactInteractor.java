package com.bereg.phonebook.domain;

import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.repositories.ContactsResourceRepositoryImpl;
import com.bereg.phonebook.repositories.IContactsRepository;

import java.util.List;

/**
 * Created by 1 on 21.03.2018.
 */

public class ContactInteractor {

    private static final String TAG = ContactInteractor.class.getSimpleName();

    private IContactsRepository mIContactsRepository;

    public ContactInteractor() {

        mIContactsRepository = new ContactsResourceRepositoryImpl();
    }

    public List<ContactModel> getAllContacts() {

        return mIContactsRepository.getAllContacts();
    }
}
