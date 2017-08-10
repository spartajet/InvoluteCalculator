package com.ptb.length.involutecalculator.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.ptb.length.involutecalculator.R;
import com.ptb.length.involutecalculator.util.InvoluteEditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ValueFixedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ValueFixedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ValueFixedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private android.widget.EditText teethNumberValueText;
    private android.widget.EditText normalModuleValueText;
    private android.widget.EditText transverseModuleValueText;
    private android.widget.EditText axialModuleValueText;
    private android.widget.EditText baseModuleValueText;
    private android.widget.EditText normalPressureAngleValueText;
    private android.widget.EditText PressureAngleValueText;
    private android.widget.EditText helixAngleValueText;
    private android.widget.EditText leadAngleValueText;
    private android.widget.EditText referenceDiameterValueText;
    private android.widget.EditText baseDiameterValueText;
    private android.widget.CheckBox teethNumberFixedBox;
    private android.widget.CheckBox normalModuleFixedBox;
    private android.widget.CheckBox transverseModuleFixedBox;
    private android.widget.CheckBox axialModuleFixedBox;
    private android.widget.CheckBox baseModuleFixedBox;
    private android.widget.CheckBox normalPressureAngleFixedBox;
    private android.widget.CheckBox pressureAngleFixedBox;
    private android.widget.CheckBox helixAngleFixedBox;
    private android.widget.CheckBox leadAngleFixedBox;
    private android.widget.CheckBox referenceDiameterFixedBox;
    private android.widget.CheckBox baseDiameterFixedBox;
    private LinearLayout valueEditLayout;

    public ValueFixedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment ValueFixedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ValueFixedFragment newInstance(String param1, String param2) {
        ValueFixedFragment fragment = new ValueFixedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View valueFixedView = inflater.inflate(R.layout.fragment_value_fixed, container, false);
        this.valueEditLayout = (LinearLayout) valueFixedView.findViewById(R.id.valueEditsLayout);
        this.baseDiameterFixedBox = (CheckBox) valueFixedView.findViewById(R.id.baseDiameterFixedBox);
        this.referenceDiameterFixedBox = (CheckBox) valueFixedView.findViewById(R.id.referenceDiameterFixedBox);
        this.leadAngleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.leadAngleFixedBox);
        this.helixAngleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.helixAngleFixedBox);
        this.pressureAngleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.pressureAngleFixedBox);
        this.normalPressureAngleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.normalPressureAngleFixedBox);
        this.baseModuleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.baseModuleFixedBox);
        this.axialModuleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.axialModuleFixedBox);
        this.transverseModuleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.transverseModuleFixedBox);
        this.normalModuleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.normalModuleFixedBox);
        this.teethNumberFixedBox = (CheckBox) valueFixedView.findViewById(R.id.teethNumberFixedBox);
        this.teethNumberValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(teethNumberValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.normalModuleValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(normalModuleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.transverseModuleValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(transverseModuleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.axialModuleValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(axialModuleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.baseModuleValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(baseModuleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.normalPressureAngleValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(this.normalPressureAngleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.PressureAngleValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(PressureAngleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.helixAngleValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(helixAngleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.leadAngleValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(leadAngleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.referenceDiameterValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(referenceDiameterValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        this.baseDiameterValueText = new InvoluteEditText(getContext());
        this.valueEditLayout.addView(baseDiameterValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.baseDiameterValueText = (EditText) valueFixedView.findViewById(R.id.baseDiameterValueText);
//        this.referenceDiameterValueText = (EditText) valueFixedView.findViewById(R.id.referenceDiameterValueText);
//        this.leadAngleValueText = (EditText) valueFixedView.findViewById(R.id.leadAngleValueText);
//        this.helixAngleValueText = (EditText) valueFixedView.findViewById(R.id.helixAngleValueText);
//        this.PressureAngleValueText = (EditText) valueFixedView.findViewById(R.id.PressureAngleValueText);
//        this.normalPressureAngleValueText = (EditText) valueFixedView.findViewById(R.id.normalPressureAngleValueText);
//        this.baseModuleValueText = (EditText) valueFixedView.findViewById(R.id.baseModuleValueText);
//        this.axialModuleValueText = (EditText) valueFixedView.findViewById(R.id.axialModuleValueText);
//        this.transverseModuleValueText = (EditText) valueFixedView.findViewById(R.id.transverseModuleValueText);
//        this.normalModuleValueText = (EditText) valueFixedView.findViewById(R.id.normalModuleValueText);
//        this.teethNumberValueText = (EditText) valueFixedView.findViewById(R.id.teethNumberValueText);
//        this.teethNumberValueText.setOnFocusChangeListener(new InvoluteValueEditOnFocusChangeListener());

        return valueFixedView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
