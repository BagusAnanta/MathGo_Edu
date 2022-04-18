package com.mathgoproject.mathgoedu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Soal_maingame_fragment extends Fragment {

    private ImageView imagesoal2;
    private TextView soaltext,soal_count_text;
    private RadioButton opsia,opsib,opsic,opsid;
    private RadioGroup radioGroup;
    private Button submit,selanjutnya;
    private View view;

    Maingameactivity maingameactivity = new Maingameactivity();

    public Soal_maingame_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.soal_maingame_fragment, container, false);

        imagesoal2 = view.findViewById(R.id.gambar_soal);
        soaltext = view.findViewById(R.id.soal);
        radioGroup = view.findViewById(R.id.radiogrup);
        opsia = view.findViewById(R.id.opsiA);
        opsib = view.findViewById(R.id.opsiB);
        opsic = view.findViewById(R.id.opsiC);
        opsid = view.findViewById(R.id.opsiD);
        submit = view.findViewById(R.id.submit_button);
        selanjutnya = view.findViewById(R.id.selanjutnya_button);


        return view;
    }
}