package application.taufiqrahman.com.ovto.bottomTabBar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import application.taufiqrahman.com.ovto.MainActivity;
import application.taufiqrahman.com.ovto.R;

public class Options extends Fragment {

    public Options() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.mToolbarText.setText("Settings");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false);
    }
}
