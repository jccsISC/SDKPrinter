package com.bixolon.sample.bottomsheet.fragments;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bixolon.sample.R;
import com.bixolon.sample.databinding.CardItemDeviceBinding;
import com.tistory.zladnrms.roundablelayout.RoundableLayout;

import java.util.List;

public class ListDeviceAdapter extends ArrayAdapter<BluetoothDevice> {

    private CardItemDeviceBinding mBinding;
    private Context context;
    private List<BluetoothDevice> listDevice;
    private int resourceLayout;

    public ListDeviceAdapter(Context context, int resource, List<BluetoothDevice> objects) {
        super(context, resource, objects);
        this.listDevice = objects;
        this.context = context;
        this.resourceLayout = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(resourceLayout, null);

            BluetoothDevice device = listDevice.get(position); //elemento que estamos clickeando
            TextView txtName = view.findViewById(R.id.txtDevice);
            txtName.setText(device.getName());

            RoundableLayout btnInfo = view.findViewById(R.id.btnInfoDevice);
        }

        return view;
    }
}
