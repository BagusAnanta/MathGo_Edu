package com.mathgoproject.mathgoedu;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Jawaban_maingame_fragment extends Fragment {
    private CardView card_jawaban;
    private TextView title_jawaban,jawaban_contain;
    private ImageView image_jawaban;
    private Button next_button;
    private View view;

    public Jawaban_maingame_fragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_jawaban_maingame_fragment, container, false);

        card_jawaban = view.findViewById(R.id.jawaban_card);
        title_jawaban = view.findViewById(R.id.jawaban_title_text);
        jawaban_contain = view.findViewById(R.id.jawaban_contain_text);
        image_jawaban = view.findViewById(R.id.image_jawaban);
        next_button = view.findViewById(R.id.selanjutnya_button);

        return view;
    }
}