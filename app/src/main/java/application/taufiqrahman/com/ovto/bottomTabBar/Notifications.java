package application.taufiqrahman.com.ovto.bottomTabBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import application.taufiqrahman.com.ovto.MainActivity;
import application.taufiqrahman.com.ovto.R;
import application.taufiqrahman.com.ovto.adapters.CartRecyclerAdapter;
import application.taufiqrahman.com.ovto.utils.ListSharedPreference;

public class Notifications extends Fragment {

    View rootView;
    ListSharedPreference.Set setSharedPreference;
    ListSharedPreference.Get getSharedPreference;
    private CartRecyclerAdapter adapter;
    private GridLayoutManager layoutManager;
    RecyclerView recyclerView;
    Button confirm;
    TextView tvTotal;
    EditText tableCode;
    public static int total = 0;

    public Notifications() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_notifications, container, false);

        MainActivity.mToolbarText.setText(R.string.notifications_title);
        confirm = (Button) rootView.findViewById(R.id.confirm);
        tvTotal = (TextView) rootView.findViewById(R.id.textView2);
        tableCode = (EditText) rootView.findViewById(R.id.editText);
        tvTotal.setText("No notifications");

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setSharedPreference = new ListSharedPreference.Set(getContext());
        getSharedPreference = new ListSharedPreference.Get(getContext());
    }

}
