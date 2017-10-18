package fr.codevallee.formation.tp16app;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import fr.codevallee.formation.tp16app.fragment.ListUserFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ListUserFragment firstFragment = new ListUserFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }
}
