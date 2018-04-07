package com.bereg.phonebook.domain

import android.util.Log;

import com.bereg.phonebook.data.SharedPreferencesManager
import com.bereg.phonebook.models.ContactModel
import com.bereg.phonebook.repositories.ContactsResourceRepositoryImpl
import com.bereg.phonebook.repositories.IContactsRepository

/**
 * Created by 1 on 30.03.2018.
 */

class KtContactInteractor {

    private val tag: String = "KT_CONTACT_INTERACTOR"
    private val mSharedPreferencesManager: SharedPreferencesManager = SharedPreferencesManager()
    private val mIContactsRepository: IContactsRepository = ContactsResourceRepositoryImpl()

    fun getAllContacts(): MutableList<ContactModel> {

        Log.e(tag, "in KtContactInteractor")
        return mSharedPreferencesManager.readAllContactsGroups(mIContactsRepository.allContacts)!!
    }
}