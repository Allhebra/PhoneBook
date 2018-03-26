package com.bereg.phonebook.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bereg.phonebook.R;
import com.bereg.phonebook.models.ContactModel;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by 1 on 20.03.2018.
 */

public class AllContactsAdapter extends RecyclerView.Adapter<AllContactsAdapter.ViewHolder>{

    private static final String TAG = AllContactsAdapter.class.getSimpleName();

    private List<ContactModel> items;
    private static PublishSubject<ContactModel> mItemClickSubject = PublishSubject.create();

    public AllContactsAdapter(List<ContactModel> list) {
        this.items = list;
    }

    public static Observable<ContactModel> getItemClickedObservable() {
        return mItemClickSubject.hide();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.e(TAG, "onCreateViewHolder:  " + viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Log.e(TAG, "onBindViewHolder:  " + holder + "to" + position + "with" + items.get(position));
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        Observable<Object> itemClicksObservable;

        private TextView firstName;

        ViewHolder(View v) {
            super(v);
            this.view = v;
            firstName = v.findViewById(R.id.tv_name);
            itemClicksObservable = RxView.clicks(view);
        }

        void bind(final ContactModel contact) {

            firstName.setText(contact.getFirstName());

            itemClicksObservable
                    .map(new Function<Object, ContactModel>() {
                        @Override
                        public ContactModel apply(Object o) throws Exception {
                            return contact;
                        }
                    })
                    .subscribe(mItemClickSubject);
        }

        View getView() {
            return view;
        }
    }
}
