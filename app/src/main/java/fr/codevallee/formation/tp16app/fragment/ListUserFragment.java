package fr.codevallee.formation.tp16app.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.codevallee.formation.tp16app.R;

public class ListUserFragment extends Fragment {
    OnHeadlineSelectedListener mCallback;

    public ListUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);
        ListView listUsers = view.findViewById(R.id.list_users);

        List<User> newUsers = new ArrayList<User>();
        newUsers.add(new User("Robert", 40));
        newUsers.add(new User("Jeanne", 34));

        Log.d("ACTION","Set listener to" + listUsers.toString());
        final ArrayAdapter<User> adapter = new ArrayAdapter<User>(
                ListUserFragment.this.getContext(), android.R.layout.simple_list_item_1, newUsers);
        listUsers.setAdapter(adapter);

        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UserFragment usrFrag = (UserFragment) getFragmentManager().findFragmentById(R.id.frag2);
                if (usrFrag != null) {
                    usrFrag.updateUserView(position);
                }
                else {
                    // Create fragment and give it an argument specifying the article it should show
                    UserFragment newFragment = new UserFragment();
                    Bundle args = new Bundle();
                    args.putInt(User.ARG_POSITION, position);
                    newFragment.setArguments(args);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }
            }
        });

        return view;
    }

    public interface OnHeadlineSelectedListener {
        public void onUserSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
