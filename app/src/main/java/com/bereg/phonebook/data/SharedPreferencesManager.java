package com.bereg.phonebook.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.bereg.phonebook.PhoneBookApp;
import com.bereg.phonebook.models.ContactModel;
import com.google.gson.Gson;

import java.util.HashMap;
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

        boolean isContained = mPrefs.contains(valueOf(contactModel.getId()));
        String json;

        if (!isContained) {
            Map<String, Boolean> contactGroups = new HashMap<>();
            contactGroups.put(FRIENDS, contactModel.isFriendsGroupContained());
            contactGroups.put(PEOPLES, contactModel.isPeoplesGroupContained());
            contactGroups.put(ANIMALS, contactModel.isAnimalsGroupContained());
            json = gson.toJson(contactGroups);
        }else {
            json = mPrefs.getString(valueOf(contactModel.getId()), "");

            Map<String, Boolean> contactGroups = gson.fromJson(json, HashMap.class);
            contactGroups.put(FRIENDS, contactModel.isFriendsGroupContained());
            contactGroups.put(PEOPLES, contactModel.isPeoplesGroupContained());
            contactGroups.put(ANIMALS, contactModel.isAnimalsGroupContained());
            json = gson.toJson(contactGroups);
        }
        SharedPreferences.Editor edit = mPrefs.edit();
        edit.putString(valueOf(contactModel.getId()), json);
        edit.apply();
    }

    public ContactModel readContactGroups(ContactModel contactModel) {

        String json = mPrefs.getString(valueOf(contactModel.getId()), "");
        if (!json.equals("")) {
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
            return contactModel;
        }
    }
}
