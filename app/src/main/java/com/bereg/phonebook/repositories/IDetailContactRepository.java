package com.bereg.phonebook.repositories;

import com.bereg.phonebook.models.ContactModel;

/**
 * Created by 1 on 21.03.2018.
 */

public interface IDetailContactRepository {

    ContactModel getContactDetails(int id);
}
