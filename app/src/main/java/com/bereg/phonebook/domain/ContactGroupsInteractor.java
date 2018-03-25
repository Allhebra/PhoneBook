package com.bereg.phonebook.domain;

import android.util.Log;

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

    private static final String TAG = ContactGroupsInteractor.class.getSimpleName();

    private IContactsRepository mIContactsRepository;
    private SharedPreferencesManager mSharedPreferencesManager;

    public ContactGroupsInteractor() {

        mIContactsRepository = new ContactsResourceRepositoryImpl();
        mSharedPreferencesManager = new SharedPreferencesManager();
    }

    public void saveContactGroupsToPrefs(ContactModel contact) {

        mSharedPreferencesManager.writeContactGroups(contact);
    }

    public List<ContactModel> getContactsByGroupName(String groupName) {

        List<ContactModel> contacts = mIContactsRepository.getAllContacts();
        Iterator<ContactModel> iterator = contacts.iterator();

        while (iterator.hasNext()) {
            ContactModel contact = iterator.next();
            Log.e(TAG, "while:   " + iterator + contact + contact.getFirstName() + contact.getSecondName());
            Log.e(TAG, "before:   " + contact.isFriendsGroupContained() + contact.isPeoplesGroupContained() + contact.isAnimalsGroupContained());
            contact = mSharedPreferencesManager.readContactGroups(contact);
            Log.e(TAG, "post:   " + contact.isFriendsGroupContained() + contact.isPeoplesGroupContained() + contact.isAnimalsGroupContained());

            switch (groupName) {
                case ("FRIENDS"):
                    Log.e(TAG, "FRIENDS:   ");
                    if (!contact.isFriendsGroupContained()) {
                        iterator.remove();
                        Log.e(TAG, "FRIENDS:   remove");
                    }
                    break;
                case ("PEOPLES"):
                    Log.e(TAG, "PEOPLES:   ");
                    if (!contact.isPeoplesGroupContained()) {
                        iterator.remove();
                        Log.e(TAG, "PEOPLES:   remove");
                    }
                    break;
                case ("ANIMALS"):
                    Log.e(TAG, "ANIMALS:   ");
                    if (!contact.isAnimalsGroupContained()) {
                        iterator.remove();
                        Log.e(TAG, "ANIMALS:   remove");
                    }
                    break;
            }
        }
        Log.e(TAG, "getContactsByGroupName:   " + contacts);
        return contacts;
    }
}
