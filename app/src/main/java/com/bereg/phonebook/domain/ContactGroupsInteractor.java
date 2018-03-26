package com.bereg.phonebook.domain;

import com.bereg.phonebook.data.SharedPreferencesManager;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.repositories.ContactsResourceRepositoryImpl;
import com.bereg.phonebook.repositories.IContactsRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 1 on 22.03.2018.
 */

public class ContactGroupsInteractor {

    private IContactsRepository mIContactsRepository;
    private SharedPreferencesManager mSharedPreferencesManager;

    public ContactGroupsInteractor() {

        mIContactsRepository = new ContactsResourceRepositoryImpl();
        mSharedPreferencesManager = new SharedPreferencesManager();
    }

    public void saveContactGroupsToPrefs(ContactModel contact) {

        mSharedPreferencesManager.writeContactGroups(contact);
    }

    public List<ContactModel> getContactsFromPrefsByGroupName(String groupName) {

        List<ContactModel> contacts = mIContactsRepository.getAllContacts();
        Iterator<ContactModel> iterator = contacts.iterator();

        while (iterator.hasNext()) {
            ContactModel contact = iterator.next();
            contact = mSharedPreferencesManager.readContactGroups(contact);

            switch (groupName) {
                case ("FRIENDS"):
                    if (!contact.isFriendsGroupContained()) {
                        iterator.remove();
                    }
                    break;
                case ("PEOPLES"):
                    if (!contact.isPeoplesGroupContained()) {
                        iterator.remove();
                    }
                    break;
                case ("ANIMALS"):
                    if (!contact.isAnimalsGroupContained()) {
                        iterator.remove();
                    }
                    break;
            }
        }
        return contacts;
    }
}
