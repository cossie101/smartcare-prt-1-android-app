package co.ke.smartcare;

import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.ke.smartcare.EnquiriesFragment.OnListFragmentInteractionListener;
import co.ke.smartcare.dataclasses.Enquiry;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EnquiriesRecyclerViewAdapter extends RecyclerView.Adapter<EnquiriesRecyclerViewAdapter.ViewHolder> {

    private final List<Enquiry> mValues;
    private final OnListFragmentInteractionListener mListener;

    public EnquiriesRecyclerViewAdapter( List<Enquiry> items, OnListFragmentInteractionListener listener ) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_enquiries, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final ViewHolder holder, int position ) {

        holder.mItem = mValues.get(position);
        holder.enquiryTitle.setText(Html.fromHtml("<b>Enquiry: </b>"+holder.mItem.getEnquiryTitle()));
        holder.enquiryDate.setText(holder.mItem.getEnquiryDate());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView enquiryTitle;
        public final TextView enquiryDate;
        public Enquiry mItem;

        public ViewHolder( View view ) {
            super(view);
            mView = view;
            enquiryDate = (TextView) view.findViewById(R.id.enquiry_date);
            enquiryTitle = (TextView) view.findViewById(R.id.enquiry_title);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + enquiryTitle.getText() + "'";
        }
    }
}
