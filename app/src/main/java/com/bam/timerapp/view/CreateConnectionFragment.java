package com.bam.timerapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bam.timerapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateConnectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateConnectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText __editTextHost, __editTextPort;
    private Button __button;

    private AppViewModel __viewModel;

    public CreateConnectionFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateConnectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateConnectionFragment newInstance(String param1, String param2) {
        CreateConnectionFragment fragment = new CreateConnectionFragment();
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
        View _view = inflater.inflate(R.layout.fragment_create_connection, container, false);


        __viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
        __button = _view.findViewById(R.id.connect);
        __editTextHost = _view.findViewById(R.id.hostTextView);
        __editTextPort = _view.findViewById(R.id.portTextView);
        __button.setOnClickListener(v -> {
            String host = __editTextHost.getText().toString();
            String port = __editTextPort.getText().toString();

            __viewModel.setConnectData(host, Integer.parseInt(port));
            Navigation.findNavController(_view).navigate(R.id.timerFragment);
        });



        return _view;
    }
}