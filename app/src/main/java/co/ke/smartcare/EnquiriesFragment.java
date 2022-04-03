package co.ke.smartcare;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.ke.smartcare.apiutilities.ApiService;
import co.ke.smartcare.apiutilities.ApiUtility;
import co.ke.smartcare.dataclasses.Enquiry;
import co.ke.smartcare.dataclasses.UploadInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class EnquiriesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_USER_ID = "userId";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int userId;
    private OnListFragmentInteractionListener mListener;
    private ApiService apiService;
    private List<Enquiry> ENQUIRIES = new ArrayList<>();
    RecyclerView.Adapter enquiriesAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EnquiriesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EnquiriesFragment newInstance( int columnCount, int userId ) {
        EnquiriesFragment fragment = new EnquiriesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            userId = getArguments().getInt(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_enquiries_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            enquiriesAdapter = new EnquiriesRecyclerViewAdapter(ENQUIRIES, mListener);
            recyclerView.setAdapter(enquiriesAdapter);
        }
        loadEnquiries();
        return view;
    }



    public void loadEnquiries(){

        apiService = ApiUtility.getAPIService();
        apiService.viewEnquiry(userId).enqueue(new Callback<List<Enquiry>>() {
            @Override
            public void onResponse( Call<List<Enquiry>> call, Response<List<Enquiry>> response ) {
                if( response.body() != null){
                    ENQUIRIES.addAll(response.body());
                    enquiriesAdapter.notifyDataSetChanged();
                    Log.i("message", "Loaded enquiries "+response.body().toString());
                }
            }

            @Override
            public void onFailure( Call<List<Enquiry>> call, Throwable t ) {

            }
        });

    }


    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction( Enquiry item );
    }
}
