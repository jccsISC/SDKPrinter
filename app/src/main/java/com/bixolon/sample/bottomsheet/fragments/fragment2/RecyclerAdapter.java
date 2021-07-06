package com.bixolon.sample.bottomsheet.fragments.fragment2;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bixolon.sample.R;
import com.tistory.zladnrms.roundablelayout.RoundableLayout;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderDevices> {

    private List<BluetoothDevice> listDevices;
    private int layout;
    private ItemListener itemListener;

    public RecyclerAdapter(List<BluetoothDevice> listDevices, int layout, ItemListener itemListener) {
        this.listDevices = listDevices;
        this.layout = layout;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolderDevices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, null, false);

        return new ViewHolderDevices(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDevices holder, final int position) {
        holder.bind(listDevices.get(position), itemListener);
    }

    @Override
    public int getItemCount() {
        return listDevices.size();
    }

    static class ViewHolderDevices extends RecyclerView.ViewHolder {

        TextView txtName;
        RoundableLayout btnInfo;

        public ViewHolderDevices(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtDevice);
            btnInfo = itemView.findViewById(R.id.btnInfoDevice);

        }

        void bind(final BluetoothDevice device, final ItemListener itemListener) {
            txtName.setText(device.getName());

            btnInfo.setOnClickListener(v -> itemListener.onCLickListenerInfo(device, getAdapterPosition()));

            itemView.setOnClickListener(v -> itemListener.onCLickListenerCard(device, getAdapterPosition()));
        }
    }
}
