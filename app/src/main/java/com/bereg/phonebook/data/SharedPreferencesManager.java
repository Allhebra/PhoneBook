package com.bereg.phonebook.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.bereg.phonebook.PhoneBookApp;
import com.bereg.phonebook.models.ContactModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

/**
 * Created by 1 on 22.03.2018.
 */

public class SharedPreferencesManager {

    private static final String TAG = SharedPreferencesManager.class.getSimpleName();
    private static final String FRIENDS = "FRIENDS";
    private static final String PEOPLES = "PEOPLES";
    private static final String ANIMALS = "ANIMALS";

    private SharedPreferences mPrefs;
    private Gson gson = new Gson();

    public SharedPreferencesManager() {

        mPrefs = PreferenceManager
                .getDefaultSharedPreferences(PhoneBookApp.getInstance().getApplicationContext());
    }

    public void writeContactGroups(ContactModel contactModel) {

        Log.e(TAG, "writeContactGroups start:   " + mPrefs.getAll());
        boolean isContained = mPrefs.contains(valueOf(contactModel.getId()));
        String json;

        if (!isContained) {
            Map<String, Boolean> contactGroups = new HashMap<>();
            contactGroups.put(FRIENDS, contactModel.isFriendsGroupContained());
            contactGroups.put(PEOPLES, contactModel.isPeoplesGroupContained());
            contactGroups.put(ANIMALS, contactModel.isAnimalsGroupContained());
            json = gson.toJson(contactGroups);
            Log.e(TAG, "IF json:   " + json);
        }else {
            //json = mPrefs.getString(valueOf(contactModel.getId()), "");

            Map<String, Boolean> contactGroups = new HashMap<>()/*gson.fromJson(json, HashMap.class)*/;
            contactGroups.put(FRIENDS, contactModel.isFriendsGroupContained());
            contactGroups.put(PEOPLES, contactModel.isPeoplesGroupContained());
            contactGroups.put(ANIMALS, contactModel.isAnimalsGroupContained());
            json = gson.toJson(contactGroups);
            Log.e(TAG, "ELSE json:   " + json);
        }
        SharedPreferences.Editor edit = mPrefs.edit();
        edit.putString(valueOf(contactModel.getId()), json);
        edit.apply();
        Log.e(TAG, "writeContactGroups end:   " + mPrefs.getAll());
    }

    public ContactModel readContactGroups(ContactModel contactModel) {

        boolean isContained = mPrefs.contains(valueOf(contactModel.getId()));

        if (isContained) {
            String json = mPrefs.getString(valueOf(contactModel.getId()), "");
            Map contactGroups = gson.fromJson(json, HashMap.class);
            Log.e(TAG, "readContactGroups:   " + "json" + json + "json" + contactGroups);

            if (contactGroups.containsKey(FRIENDS)) {
                contactModel.setFriendsGroupContained((Boolean) contactGroups.get(FRIENDS));
                Log.e(TAG, "inFRIENDS:   " + contactModel.isFriendsGroupContained());
            }
            if (contactGroups.containsKey(PEOPLES)) {
                contactModel.setPeoplesGroupContained((Boolean) contactGroups.get(PEOPLES));
                Log.e(TAG, "inPEOPLES:   " + contactModel.isPeoplesGroupContained());
            }
            if (contactGroups.containsKey(ANIMALS)) {
                contactModel.setAnimalsGroupContained((Boolean) contactGroups.get(ANIMALS));
                Log.e(TAG, "inANIMALS:   " + contactModel.isAnimalsGroupContained());
            }
            Log.e(TAG, "if:   " + contactModel.isFriendsGroupContained() + contactModel.isPeoplesGroupContained() + contactModel.isAnimalsGroupContained());
            return contactModel;
        }else {
            Log.e(TAG, "else:   " + contactModel.isFriendsGroupContained() + contactModel.isPeoplesGroupContained() + contactModel.isAnimalsGroupContained());
            contactModel.setFriendsGroupContained(false);
            contactModel.setPeoplesGroupContained(false);
            contactModel.setAnimalsGroupContained(false);
            return contactModel;
        }
    }

    public List<ContactModel> readAllContactsGroups(List<ContactModel> contactModels) {

        for (ContactModel contact : contactModels) {
            Log.e(TAG, "readAllContactsGroups:   " + contact);
            readContactGroups(contact);
            Log.e(TAG, "readAllContactsGroups:   " + contact);
        }
        return contactModels;
    }
}
