package com.bam.timerapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bam.timerapp.R;
import com.bam.timerapp.presenter.Presenter;
import com.bam.timerapp.responce.Notification;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment implements IViewContract {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private AppViewModel __viewModel;

    private Button __button;

    private TextView __timertextView;

    private ListView __listView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Presenter __presenter;

    public TimerFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimerFragment newInstance(String param1, String param2) {
        TimerFragment fragment = new TimerFragment();
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
        View _view =  inflater.inflate(R.layout.fragment_timer, container, false);


        __button = _view.findViewById(R.id.newNotifBtn);
        __timertextView = _view.findViewById(R.id.timerText);

        __listView = _view.findViewById(R.id.notificationList);

        __button.setOnClickListener(v -> {
            Navigation.findNavController(_view).navigate(R.id.notificationCreateFragment);
        });

        __viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        __viewModel.getPresenter().bindView(this);
        return _view;
    }

    @Override
    public void updateTimer(String time) {
        __timertextView.setText(time);
    }

    @Override
    public void updateNotifications(List<Notification> notificationList) {

        Toast.makeText(requireContext(), "Work", Toast.LENGTH_SHORT).show();
        MyAdapter _adapter = new MyAdapter(notificationList);
        __listView.setAdapter(_adapter);
    }

    @Override
    public void alarm(Notification notification) {

        Toast.makeText(requireContext(), notification.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void start() {

    }

    private class MyAdapter extends BaseAdapter {

        // override other abstract methods here

        private List<Notification> __notifications;

        public MyAdapter(List<Notification> notifications) {
            __notifications = notifications;
        }

        @Override
        public int getCount() {
            return __notifications.size();
        }

        @Override
        public Object getItem(int position) {
            return __notifications.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ViewHolder _viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                _viewHolder = new ViewHolder();
                _viewHolder.title = convertView.findViewById(R.id.titleT);
                _viewHolder.content = convertView.findViewById(R.id.contentT);
                _viewHolder.time = convertView.findViewById(R.id.timeT);

                convertView.setTag(_viewHolder);
                _viewHolder.title.setText(__notifications.get(position).getName());
                _viewHolder.content.setText(__notifications.get(position).getMessage());

                Notification _notification = __notifications.get(position);
                String time = _notification.getHour() + ":" + _notification.getMin() + ":" + _notification.getSec();
                _viewHolder.time.setText(time);
            }

            return convertView;
        }



    }

    static class ViewHolder {

        TextView title;
        TextView content;
        TextView time;
    }
}