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
import co.ke.smartcare.dataclasses.Payment;
import co.ke.smartcare.dataclasses.Status;
import co.ke.smartcare.dummy.DummyContent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class PaymentFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_USER_ID = "user-id";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int user;
    private ApiService apiService;
    private ArrayList<Payment> PAYMENTS = new ArrayList<>();
    RecyclerView.Adapter paymentsAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PaymentFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PaymentFragment newInstance( int columnCount, int patient ) {
        PaymentFragment fragment = new PaymentFragment();
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
        View view = inflater.inflate(R.layout.fragment_payment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            paymentsAdapter = new PaymentRecyclerViewAdapter(PAYMENTS);
            recyclerView.setAdapter(paymentsAdapter);
        }
        loadPayments();
        return view;
    }

    public void loadPayments(){

        apiService = ApiUtility.getAPIService();
        apiService.fetchPayments(user).enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse( Call<List<Payment>> call, Response<List<Payment>> response ) {

                if( response.body() != null ){

                    PAYMENTS.addAll(response.body());
                    paymentsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure( Call<List<Payment>> call, Throwable t ) {

            }
        });
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction( Status status );
    }
}