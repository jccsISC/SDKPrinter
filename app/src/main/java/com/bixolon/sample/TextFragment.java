package com.bixolon.sample;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TextFragment extends Fragment implements OnClickListener{

    private EditText textData = null;

    private CheckBox checkBold = null;
    private CheckBox checkUnderline = null;
    private CheckBox checkReverse = null;

    private TextView deviceMessagesTextView;

    private int spinnerAlignment = 0;
    private int spinnerFont = 0;
    private int spinnerSize = 0;

    public static TextFragment newInstance() {
        TextFragment fragment = new TextFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        view.findViewById(R.id.buttonPrintText).setOnClickListener(this);

        textData = view.findViewById(R.id.editTextData);
        textData.setText("Bixolon Text Print!!\n");

        checkBold = view.findViewById(R.id.checkBoxBold);
        checkUnderline = view.findViewById(R.id.checkBoxUnderline);
        checkReverse = view.findViewById(R.id.checkBoxReverse);

        deviceMessagesTextView = view.findViewById(R.id.textViewDeviceMessages);
        deviceMessagesTextView.setMovementMethod(new ScrollingMovementMethod());
        deviceMessagesTextView.setVerticalScrollBarEnabled(true);

        Spinner textAlignment = view.findViewById(R.id.textAlignment);
        Spinner textFont = view.findViewById(R.id.textFont);
        Spinner textSize = view.findViewById(R.id.textSize);

        ArrayAdapter alignmentAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.Alignment, android.R.layout.simple_spinner_dropdown_item);
        alignmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textAlignment.setAdapter(alignmentAdapter);
        textAlignment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                spinnerAlignment = position;
            }

            public void onNothingSelected(AdapterView<?> parent)
            {}
        });

        ArrayAdapter fontAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.textFont, android.R.layout.simple_spinner_dropdown_item);
        fontAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textFont.setAdapter(fontAdapter);
        textFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                spinnerFont = position;
            }

            public void onNothingSelected(AdapterView<?> parent)
            {}
        });

        ArrayAdapter sizeAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.textSize, android.R.layout.simple_spinner_dropdown_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textSize.setAdapter(sizeAdapter);
        textSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                spinnerSize = position;
            }

            public void onNothingSelected(AdapterView<?> parent)
            {}
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonPrintText:
                String strData = textData.getText().toString() + "\n";
                int alignment, attribute = 0;

                String textoHtml = getString(R.string.Personalized);
                String strDataPersonalized = "         23/Jun/2021      \n" +
                                             "<b font-size=25px;>PAGO EXITOSO!!</b>";


                String content="<h1>Heading 1</h1>\n" +
                        "        <h2>Heading 2</h2>\n" +
                        "        <p>This is some html. Look, here's an <u>underline</u>.</p>\n" +
                        "        <p>Look, this is <em>emphasized.</em> And here's some <b>bold</b>.</p>\n" +
                        "        <p>Here are UL list items:\n" +
                        "        <ul>\n" +
                        "        <li>One</li>\n" +
                        "        <li>Two</li>\n" +
                        "        <li>Three</li>\n" +
                        "        </ul>\n" +
                        "        <p>Here are OL list items:\n" +
                        "        <ol>\n" +
                        "        <li>One</li>\n" +
                        "        <li>Two</li>\n" +
                        "        <li>Three</li>\n" +
                        "        </ol>";

                switch(spinnerAlignment)
                {
                    case 0:		alignment = PrinterConnectActivity.getPrinterInstance().ALIGNMENT_LEFT;		break;
                    case 1:		alignment = PrinterConnectActivity.getPrinterInstance().ALIGNMENT_CENTER;	    break;
                    case 2:		alignment = PrinterConnectActivity.getPrinterInstance().ALIGNMENT_RIGHT;		break;
                    default:	alignment = PrinterConnectActivity.getPrinterInstance().ALIGNMENT_LEFT;		break;
                }

                switch(spinnerFont)
                {
                    case 0:		attribute |= PrinterConnectActivity.getPrinterInstance().ATTRIBUTE_FONT_A;	break;
                    case 1:		attribute |= PrinterConnectActivity.getPrinterInstance().ATTRIBUTE_FONT_B;	break;
                    case 2:		attribute |= PrinterConnectActivity.getPrinterInstance().ATTRIBUTE_FONT_C;	break;
                    /*case 3:		attribute |= PrinterConnectActivity.getPrinterInstance().ATTRIBUTE_FONT_D;	break;*/
                    default:	attribute |= PrinterConnectActivity.getPrinterInstance().ATTRIBUTE_FONT_A;	break;
                }

                if(checkBold.isChecked())
                {
                    attribute |= PrinterConnectActivity.getPrinterInstance().ATTRIBUTE_BOLD;
                }

                if(checkUnderline.isChecked())
                {
                    attribute |= PrinterConnectActivity.getPrinterInstance().ATTRIBUTE_UNDERLINE;
                }

                if(checkReverse.isChecked())
                {
                    attribute |= PrinterConnectActivity.getPrinterInstance().ATTRIBUTE_REVERSE;
                }

//                PrinterConnectActivity.getPrinterInstance().printText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY).toString(), alignment, attribute, (spinnerSize + 1));
                PrinterConnectActivity.getPrinterInstance().printText(textoHtml, alignment, attribute, (spinnerSize + 1));

                break;
        }
    }

    public void setDeviceLog(String data)
    {
        mHandler.obtainMessage(0, 0, 0, data).sendToTarget();
    }

    public final Handler mHandler = new Handler(new Handler.Callback()
    {
        @SuppressWarnings("unchecked")
        @Override
        public boolean handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    deviceMessagesTextView.append((String)msg.obj + "\n");

                    Layout layout = deviceMessagesTextView.getLayout();
                    if (layout != null) {
                        int y = layout.getLineTop(
                                deviceMessagesTextView.getLineCount()) - deviceMessagesTextView.getHeight();
                        if (y > 0) {
                            deviceMessagesTextView.scrollTo(0, y);
                            deviceMessagesTextView.invalidate();
                        }
                    }
                    break;
            }
            return false;
        }
    });
}
