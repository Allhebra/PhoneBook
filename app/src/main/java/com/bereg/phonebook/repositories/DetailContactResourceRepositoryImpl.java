package com.bereg.phonebook.repositories;

import android.content.res.Resources;
import android.util.Log;

import com.bereg.phonebook.PhoneBookApp;
import com.bereg.phonebook.R;
import com.bereg.phonebook.models.ContactModel;

/**
 * Created by 1 on 21.03.2018.
 */

public class DetailContactResourceRepositoryImpl implements IDetailContactRepository {

    private static final String TAG = DetailContactResourceRepositoryImpl.class.getSimpleName();

    private String[] firstNameContainer;
    private String[] secondNameContainer;
    private String[] countryContainer;
    private String[] cityContainer;
    private String[] streetContainer;
    private String[] buildingContainer;
    private String[] phoneContainer;

    @Override
    public ContactModel getContactDetails(int id) {

        ContactModel contactModel = new ContactModel();
        Resources resources = PhoneBookApp.getInstance()
                .getApplicationContext()
                .getResources();

        firstNameContainer = resources.getStringArray(R.array.string_array_first_name);
        secondNameContainer = resources.getStringArray(R.array.string_array_second_name);
        countryContainer = resources.getStringArray(R.array.string_array_country);
        cityContainer = resources.getStringArray(R.array.string_array_city);
        streetContainer = resources.getStringArray(R.array.string_array_street);
        buildingContainer = resources.getStringArray(R.array.string_array_building_number);
        phoneContainer = resources.getStringArray(R.array.string_array_phone_number);

        contactModel.setFirstName(firstNameContainer[id]);
        contactModel.setSecondName(secondNameContainer[id]);
        contactModel.setCountry(countryContainer[id]);
        contactModel.setCity(cityContainer[id]);
        contactModel.setStreet(streetContainer[id]);
        contactModel.setBuildingNumber(buildingContainer[id]);
        contactModel.setPhoneNumber(phoneContainer[id]);

        Log.e(TAG, "getContactDetails:   " + id + "contact" + contactModel);

        return contactModel;
    }
}
