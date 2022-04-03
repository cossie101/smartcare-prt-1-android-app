package co.ke.smartcare;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.ke.smartcare.dataclasses.Payment;
import co.ke.smartcare.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PaymentRecyclerViewAdapter extends RecyclerView.Adapter<PaymentRecyclerViewAdapter.ViewHolder> {

    private final List<Payment> mValues;

    public PaymentRecyclerViewAdapter( List<Payment> items ) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final ViewHolder holder, int position ) {
        holder.mItem = mValues.get(position);
        holder.reference.setText(holder.mItem.getReference());
        holder.amount.setText(holder.mItem.getAmount());
        holder.date.setText(holder.mItem.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView amount;
        public final TextView reference;
        public final TextView date;
        public Payment mItem;

        public ViewHolder( View view ) {
            super(view);
            mView = view;
            amount = view.findViewById(R.id.amount);
            reference = view.findViewById(R.id.reference);
            date = view.findViewById(R.id.date);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + date.getText() + "'";
        }
    }
}