package com.bereg.phonebook.repositories;

import com.bereg.phonebook.models.ContactModel;

import java.util.List;

/**
 * Created by 1 on 21.03.2018.
 */

public interface IContactsRepository {

    List<ContactModel> getAllContacts();
}
