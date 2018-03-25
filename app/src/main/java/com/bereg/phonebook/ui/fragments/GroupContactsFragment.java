package com.bereg.phonebook.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.bereg.phonebook.presentation.presenters.GroupContactsPresenter;
import com.bereg.phonebook.presentation.view.GroupContactsView;
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
public class GroupContactsFragment extends MvpAppCompatFragment implements GroupContactsView {

    private static final String TAG = GroupContactsFragment.class.getSimpleName();

    @InjectPresenter
    GroupContactsPresenter mGroupContactsPresenter;

    @ProvidePresenter
    GroupContactsPresenter provideSearchPresenter() {
        Router router = PhoneBookApp.getInstance().getRouter();
        return new GroupContactsPresenter(router);
    }

    private List<ContactModel> mGroupContacts = new ArrayList<>();
    private AllContactsAdapter mAllContactsAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public GroupContactsFragment() {
    }

    public static GroupContactsFragment newInstance(String groupName) {
        GroupContactsFragment fragment = new GroupContactsFragment();
        Bundle args = new Bundle();
        args.putString("GROUP_NAME", groupName);
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
        return inflater.inflate(R.layout.fragment_group_contacts, container, false);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_all_contacts_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);

        Log.e(TAG, "onCreateOptionsMenu:   ");
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        //MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        //item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        //MenuItemCompat.setActionView(item, sv);
        item.setActionView(sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit:   " + query);
                mGroupContactsPresenter.onSearchTextSubmitted(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange:   " + newText);
                mGroupContactsPresenter.onSearchTextChanged(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        /*switch (menuItem.getItemId()) {
            case R.id.save_search_result_item:
                //mVacancyListPresenter.saveSearchResult(vacancies);
        }*/
        return true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGroupContactsPresenter.getGroupContacts(getArguments().getString("GROUP_NAME"));

        RecyclerView mRecyclerView = view.findViewById(R.id.rv_frag_group_contacts);
        mAllContactsAdapter = new AllContactsAdapter(mGroupContacts);
        Log.e(TAG, "onViewCreated:   " + mRecyclerView + mAllContactsAdapter + mGroupContacts);
        mRecyclerView.setAdapter(mAllContactsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        compositeDisposable.add(AllContactsAdapter.getItemClickedObservable()
                .subscribe(new Consumer<ContactModel>() {
                    @Override
                    public void accept(ContactModel contact) throws Exception {
                        Log.e(TAG, "RecyclerAdapter.getViewClickedObservable:   " + contact + contact.getId());
                        mGroupContactsPresenter.showContactsDetail(contact);
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
    public void showGroupContacts(List<ContactModel> groupContacts) {

        mGroupContacts.clear();
        mGroupContacts.addAll(groupContacts);
        mAllContactsAdapter.notifyDataSetChanged();
        Log.e(TAG, "showGroupContacts" + mGroupContacts);
    }
}
