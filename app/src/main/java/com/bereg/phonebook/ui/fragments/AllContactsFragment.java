package com.bereg.phonebook.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.phonebook.PhoneBookApp;
import com.bereg.phonebook.R;
import com.bereg.phonebook.models.ContactModel;
import com.bereg.phonebook.presentation.presenters.AllContactsPresenter;
import com.bereg.phonebook.presentation.view.AllContactsView;
import com.bereg.phonebook.ui.activities.MainActivity;
import com.bereg.phonebook.ui.adapters.AllContactsAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ru.terrakok.cicerone.Router;

/**
 *
 */

public class AllContactsFragment extends MvpAppCompatFragment implements AllContactsView {

    private static final String TAG = AllContactsFragment.class.getSimpleName();

    @InjectPresenter
    AllContactsPresenter mAllContactsPresenter;

    @ProvidePresenter
    AllContactsPresenter provideSearchPresenter() {
        Router router = PhoneBookApp.getInstance().getRouter();
        return new AllContactsPresenter(router);
    }

    private List<ContactModel> mAllContacts = new ArrayList<>();
    private AllContactsAdapter mAllContactsAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AllContactsFragment() {
    }

    public static AllContactsFragment newInstance() {
        Bundle args = new Bundle();
        AllContactsFragment fragment = new AllContactsFragment();
        fragment.setArguments(args);
        //Log.e(TAG, "getInstance");
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Log.e(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_all_contacts, container, false);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_all_contacts_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);

        Log.e(TAG, "onCreateOptionsMenu:   ");
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        item.setActionView(sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit:   " + query);
                mAllContactsPresenter.onSearchTextSubmitted(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange:   " + newText);
                mAllContactsPresenter.onSearchTextChanged(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        return true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mRecyclerView = view.findViewById(R.id.rv_frag_all_contacts);
        mAllContactsAdapter = new AllContactsAdapter(mAllContacts);
        mRecyclerView.setAdapter(mAllContactsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        compositeDisposable.add(AllContactsAdapter.getItemClickedObservable()
                .subscribe(new Consumer<ContactModel>() {
                    @Override
                    public void accept(ContactModel contact) throws Exception {
                        Log.e(TAG, "RecyclerAdapter.getViewClickedObservable:   " + contact + contact.getId());
                        mAllContactsPresenter.showContactsDetail(contact);
                    }
                }));

        //Log.e(TAG, "onViewCreated");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!compositeDisposable.isDisposed()) compositeDisposable.clear();
    }

    @Override
    public void showAllContacts(List<ContactModel> allContactsList) {

        mAllContacts.clear();
        mAllContacts.addAll(allContactsList);
        mAllContactsAdapter.notifyDataSetChanged();
        Log.e(TAG, "showAllContacts" + mAllContacts);
    }
}
