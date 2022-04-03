package co.ke.smartcare;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.ke.smartcare.EnquiryStatusFragment.OnListFragmentInteractionListener;
import co.ke.smartcare.dataclasses.Status;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EnquiryStatusRecyclerViewAdapter extends RecyclerView.Adapter<EnquiryStatusRecyclerViewAdapter.ViewHolder> {

    private final List<Status> mValues;
    private final OnListFragmentInteractionListener mListener;

    public EnquiryStatusRecyclerViewAdapter( List<Status> items, OnListFragmentInteractionListener listener ) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_enquiry_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final ViewHolder holder, int position ) {
        holder.mItem = mValues.get(position);
        holder.statusID.setText(holder.mItem.getId());
        holder.status.setText(holder.mItem.getDescription());
        holder.author.setText(holder.mItem.getAuthor());
        holder.statusDate.setText(holder.mItem.getDate());


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
        public final TextView statusID;
        public final TextView status;
        public final TextView statusDate;
        public final TextView author;
        public Status mItem;

        public ViewHolder( View view ) {
            super(view);
            mView = view;
            status = (TextView) view.findViewById(R.id.status);
            statusDate = (TextView) view.findViewById(R.id.status_date);
            statusID = view.findViewById(R.id.status_id);
            author = view.findViewById(R.id.author);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + status.getText() + "'";
        }
    }
}
