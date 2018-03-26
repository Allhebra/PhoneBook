package com.bereg.phonebook.utils;

import android.util.Log;

import com.bereg.phonebook.models.ContactModel;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 1 on 24.03.2018.
 */

public class SearchOccurrence {

    private static final String TAG = SearchOccurrence.class.getSimpleName();

    public static List<ContactModel> checkForKeywordOccurrence(List<ContactModel> contactModels,
                                                          String keyword) {

        if (keyword.equals("")) return contactModels;
        if (!contactModels.isEmpty()) {
            Iterator<ContactModel> iterator = contactModels.iterator();

            while (iterator.hasNext()) {
                ContactModel contact = iterator.next();
                Log.e(TAG, "while:   " + iterator + contact + contact.getFirstName() + contact.getSecondName());
                if (!contact.getFirstName().toLowerCase().contains(keyword.toLowerCase())
                        && !contact.getSecondName().toLowerCase().contains(keyword.toLowerCase())) {
                    iterator.remove();
                }
            }
        }
        return contactModels;
    }
}
