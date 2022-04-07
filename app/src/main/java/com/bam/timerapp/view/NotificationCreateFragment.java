package com.bam.timerapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.bam.timerapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationCreateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AppViewModel __viewModel;

    private EditText __editTextName, __editTextMsg;
    private TimePicker __timePicker;
    private Button __button;

    public NotificationCreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationCreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationCreateFragment newInstance(String param1, String param2) {
        NotificationCreateFragment fragment = new NotificationCreateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        __viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
        View _view = inflater.inflate(R.layout.fragment_notification_create, container, false);

        __editTextName = _view.findViewById(R.id.notificationNameTextView);
        __editTextMsg = _view.findViewById(R.id.notificationMsgTextView);
        __timePicker = _view.findViewById(R.id.notificationTimePicker);
        __button = _view.findViewById(R.id.createNotBtn);

        __button.setOnClickListener(v ->  {
            __viewModel.createNotification(__editTextName.getText().toString()
                    , __editTextMsg.getText().toString()
                    ,__timePicker.getHour(),
                    __timePicker.getMinute(),
                    0);

            Navigation.findNavController(_view).navigate(R.id.timerFragment);
        });




        return _view;
    }
}