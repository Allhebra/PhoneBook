package com.bereg.phonebook.repositories;

import android.content.Context;

import com.bereg.phonebook.PhoneBookApp;
import com.bereg.phonebook.R;
import com.bereg.phonebook.models.ContactModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 21.03.2018.
 */

public class ContactsResourceRepositoryImpl implements IContactsRepository {

    private static final int CONTACTS_NUM = 20;

    private String[] firstNameContainer = new String[CONTACTS_NUM];
    private String[] secondNameContainer = new String[CONTACTS_NUM];
    private String[] countryContainer = new String[CONTACTS_NUM];
    private String[] cityContainer = new String[CONTACTS_NUM];
    private String[] streetContainer = new String[CONTACTS_NUM];
    private String[] buildingContainer = new String[CONTACTS_NUM];
    private String[] phoneContainer = new String[CONTACTS_NUM];

    @Override
    public List<ContactModel> getAllContacts() {

        List<ContactModel> contactModelList = new ArrayList<>(CONTACTS_NUM);

        Context applicationContext = PhoneBookApp.getInstance().getApplicationContext();
        firstNameContainer = applicationContext.getResources().getStringArray(R.array.string_array_first_name);
        secondNameContainer = applicationContext.getResources().getStringArray(R.array.string_array_second_name);
        countryContainer = applicationContext.getResources().getStringArray(R.array.string_array_country);
        cityContainer = applicationContext.getResources().getStringArray(R.array.string_array_city);
        streetContainer = applicationContext.getResources().getStringArray(R.array.string_array_street);
        buildingContainer = applicationContext.getResources().getStringArray(R.array.string_array_building_number);
        phoneContainer = applicationContext.getResources().getStringArray(R.array.string_array_phone_number);

        for (int i=0; i<CONTACTS_NUM; i++) {
            ContactModel contact = new ContactModel();
            contact.setId(i);
            contact.setFirstName(firstNameContainer[i]);
            contact.setSecondName(secondNameContainer[i]);
            contact.setCountry(countryContainer[i]);
            contact.setCity(cityContainer[i]);
            contact.setStreet(streetContainer[i]);
            contact.setBuildingNumber(buildingContainer[i]);
            contact.setPhoneNumber(phoneContainer[i]);

            contactModelList.add(contact);
        }
        return contactModelList;
    }
}
