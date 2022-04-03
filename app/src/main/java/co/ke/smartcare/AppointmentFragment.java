package co.ke.smartcare;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.ke.smartcare.apiutilities.ApiService;
import co.ke.smartcare.apiutilities.ApiUtility;
import co.ke.smartcare.dataclasses.Appointment;
import co.ke.smartcare.dataclasses.Enquiry;
import co.ke.smartcare.dummy.DummyContent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class AppointmentFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_USER_ID = "";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int user;
    private ApiService apiService;
    private List<Appointment> APPOINTMENTS = new ArrayList<>();
    RecyclerView.Adapter appointmentAdapter;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AppointmentFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AppointmentFragment newInstance( int columnCount, int patient ) {
        AppointmentFragment fragment = new AppointmentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_USER_ID, patient);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            user = getArguments().getInt(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_appointment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            appointmentAdapter = new AppointmentRecyclerViewAdapter(APPOINTMENTS);
            recyclerView.setAdapter(appointmentAdapter);
        }
        loadAppointments();
        return view;
    }

    public void loadAppointments(){

        apiService = ApiUtility.getAPIService();
        apiService.fetchAppointments(user).enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse( Call<List<Appointment>> call, Response<List<Appointment>> response ) {

                if( response.body() != null ){

                    APPOINTMENTS.addAll(response.body());
                    appointmentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure( Call<List<Appointment>> call, Throwable t ) {

            }
        });
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction( Appointment item );
    }
}