package com.bereg.phonebook.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.phonebook.PhoneBookApp;
import com.bereg.phonebook.R;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.presentation.presenters.DetailContactPresenter;
import com.bereg.phonebook.presentation.view.DetailContactView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import ru.terrakok.cicerone.Router;

/**
 *
 */

public class DetailContactFragment extends MvpAppCompatFragment implements DetailContactView {

    private static final String TAG = DetailContactFragment.class.getSimpleName();

    ContactModel mContact;
    //CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.tv_first_name)
    TextView firstName;
    @BindView(R.id.tv_second_name)
    TextView secondName;
    @BindView(R.id.tv_country_name)
    TextView country;
    @BindView(R.id.tv_city_name)
    TextView city;
    @BindView(R.id.tv_street_name)
    TextView street;
    @BindView(R.id.tv_building_number)
    TextView building;
    @BindView(R.id.tv_phone_number)
    TextView phone;
    @BindView(R.id.chk_friends)
    CheckBox friends;
    @BindView(R.id.chk_people)
    CheckBox peoples;
    @BindView(R.id.chk_animals)
    CheckBox animals;

    @InjectPresenter
    DetailContactPresenter mDetailContactPresenter;

    @ProvidePresenter
    DetailContactPresenter provideDetailContactPresenter() {
        Router router = PhoneBookApp.getInstance().getRouter();
        return new DetailContactPresenter(router);
    }

    public DetailContactFragment() {
    }
    
    public static DetailContactFragment newInstance(int id) {
        DetailContactFragment fragment = new DetailContactFragment();
        Bundle args = new Bundle();
        args.putInt("ID", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDetailContactPresenter.getContact(getArguments().getInt("ID"));
        ButterKnife.bind(this, view);
        Log.e(TAG, "onViewCreated: " + getArguments().getInt("ID"));


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        //if (!compositeDisposable.isDisposed()) compositeDisposable.clear();
    }

    @Override
    public void showContactDetails(ContactModel contactModel) {

        Log.e(TAG, "showContactDetails: " + contactModel + contactModel.getFirstName());
        mContact = contactModel;

        firstName.setText(contactModel.getFirstName());
        secondName.setText(contactModel.getSecondName());
        country.setText(contactModel.getCountry());
        city.setText(contactModel.getCity());
        street.setText(contactModel.getStreet());
        building.setText(contactModel.getBuildingNumber());
        phone.setText(contactModel.getPhoneNumber());
        friends.setChecked(contactModel.isFriendsGroupContained());
        peoples.setChecked(contactModel.isPeoplesGroupContained());
        animals.setChecked(contactModel.isAnimalsGroupContained());

        RxCompoundButton
                .checkedChanges(friends)
                .skipInitialValue()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        friends.setChecked(aBoolean);
                        mContact.setFriendsGroupContained(aBoolean);
                        mDetailContactPresenter.saveContactGroups(mContact);
                    }
                });

        RxCompoundButton
                .checkedChanges(peoples)
                .skipInitialValue()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        peoples.setChecked(aBoolean);
                        mContact.setPeoplesGroupContained(aBoolean);
                        mDetailContactPresenter.saveContactGroups(mContact);
                    }
                });

        RxCompoundButton
                .checkedChanges(animals)
                .skipInitialValue()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        animals.setChecked(aBoolean);
                        mContact.setAnimalsGroupContained(aBoolean);
                        mDetailContactPresenter.saveContactGroups(mContact);
                    }
                });
    }
}
