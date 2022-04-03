package co.ke.smartcare;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.ke.smartcare.dataclasses.Appointment;
import co.ke.smartcare.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AppointmentRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentRecyclerViewAdapter.ViewHolder> {

    private final List<Appointment> mValues;

    public AppointmentRecyclerViewAdapter( List<Appointment> items ) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final ViewHolder holder, int position ) {
        holder.mItem = mValues.get(position);
        holder.appointment.setText(holder.mItem.getAppointment());
        holder.title.setText(holder.mItem.getTitle());
        holder.date.setText(holder.mItem.getDate());
        holder.doctor.setText(holder.mItem.getDoctor());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView doctor;
        public final TextView date;
        public final TextView title;
        public final TextView appointment;
        public Appointment mItem;

        public ViewHolder( View view ) {
            super(view);
            mView = view;
            doctor = view.findViewById(R.id.doctor);
            date = view.findViewById(R.id.date);
            title = view.findViewById(R.id.title);
            appointment = view.findViewById(R.id.appointment);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + appointment.getText() + "'";
        }
    }
}