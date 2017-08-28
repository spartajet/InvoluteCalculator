package com.ptb.length.involutecalculator.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.ptb.length.involutecalculator.R;
import com.ptb.length.involutecalculator.calculator.CalculateResult;
import com.ptb.length.involutecalculator.calculator.Parameters;
import com.ptb.length.involutecalculator.listener.InvoluteEditRealKeyListener;
import com.ptb.length.involutecalculator.listener.InvoluteIntEditTextChange;
import com.ptb.length.involutecalculator.listener.InvoluteRealEditTextChange;
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

    private static final String TAG = "ValueFixedFragment";

    private OnFragmentInteractionListener mListener;
    private InvoluteEditText teethNumberValueText;
    private InvoluteEditText normalModuleValueText;
    private InvoluteEditText transverseModuleValueText;
    private InvoluteEditText axialModuleValueText;
    private InvoluteEditText baseModuleValueText;
    private InvoluteEditText normalPressureAngleValueText;
    private InvoluteEditText PressureAngleValueText;
    private InvoluteEditText helixAngleValueText;
    private InvoluteEditText leadAngleValueText;
    private InvoluteEditText referenceDiameterValueText;
    private InvoluteEditText baseDiameterValueText;
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
    private LinearLayout fixedLinerlayout;

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
        this.fixedLinerlayout = (LinearLayout) valueFixedView.findViewById(R.id.fixedLinerlayout);
        this.baseDiameterFixedBox = (CheckBox) valueFixedView.findViewById(R.id.baseDiameterFixedBox);
        this.baseDiameterFixedBox.setOnCheckedChangeListener((buttonView, isChecked) -> Parameters.diameterBase.setFixed(isChecked));
        this.referenceDiameterFixedBox = (CheckBox) valueFixedView.findViewById(R.id.referenceDiameterFixedBox);
        this.referenceDiameterFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.diameterReference.setFixed(isChecked)));
        this.leadAngleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.leadAngleFixedBox);
        this.leadAngleFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.angleLead.setFixed(isChecked)));
        this.helixAngleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.helixAngleFixedBox);
        this.helixAngleFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.angleHelix.setFixed(isChecked)));
        this.pressureAngleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.pressureAngleFixedBox);
        this.pressureAngleFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.anglePressure.setFixed(isChecked)));
        this.normalPressureAngleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.normalPressureAngleFixedBox);
        this.normalPressureAngleFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.anglePressureNormal.setFixed(isChecked)));
        this.baseModuleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.baseModuleFixedBox);
        this.baseModuleFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.moduleBase.setFixed(isChecked)));
        this.axialModuleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.axialModuleFixedBox);
        this.axialModuleFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.moduleAxial.setFixed(isChecked)));
        this.transverseModuleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.transverseModuleFixedBox);
        this.transverseModuleFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.moduleTransverse.setFixed(isChecked)));
        this.normalModuleFixedBox = (CheckBox) valueFixedView.findViewById(R.id.normalModuleFixedBox);
        this.normalModuleFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.moduleNormal.setFixed(isChecked)));
        this.teethNumberFixedBox = (CheckBox) valueFixedView.findViewById(R.id.teethNumberFixedBox);
        this.teethNumberFixedBox.setOnCheckedChangeListener(((buttonView, isChecked) -> Parameters.teethNumber.setFixed(isChecked)));
        this.teethNumberValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.INTEGER);
        this.valueEditLayout.addView(teethNumberValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.teethNumberValueText.setKeyListener(new InvoluteEditIntKeyListener(Parameters.teethNumber));
        this.teethNumberValueText.addTextChangedListener(new InvoluteIntEditTextChange(this.teethNumberValueText, Parameters.teethNumber, this.getContext()));

        this.normalModuleValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(normalModuleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.normalModuleValueText, new InvoluteEditRealKeyListener(this.normalModuleValueText, Parameters.moduleNormal));
        this.normalModuleValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.normalModuleValueText, Parameters.moduleNormal, this.getContext()));

        this.transverseModuleValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(transverseModuleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.transverseModuleValueText, new InvoluteEditRealKeyListener(this.transverseModuleValueText, Parameters.moduleTransverse));
        this.transverseModuleValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.transverseModuleValueText, Parameters.moduleTransverse, this.getContext()));

        this.axialModuleValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(axialModuleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.axialModuleValueText, new InvoluteEditRealKeyListener(this.axialModuleValueText, Parameters.moduleAxial));
        this.axialModuleValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.axialModuleValueText, Parameters.moduleAxial, this.getContext()));

        this.baseModuleValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(baseModuleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.baseModuleValueText, new InvoluteEditRealKeyListener(this.baseModuleValueText, Parameters.moduleBase));
        this.baseModuleValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.baseModuleValueText, Parameters.moduleBase, this.getContext()));

        this.normalPressureAngleValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(this.normalPressureAngleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.normalPressureAngleValueText, new InvoluteEditRealKeyListener(this.normalPressureAngleValueText, Parameters.anglePressureNormal));
        this.normalPressureAngleValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.normalPressureAngleValueText, Parameters.anglePressureNormal, this.getContext()));

        this.PressureAngleValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(PressureAngleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.PressureAngleValueText, new InvoluteEditRealKeyListener(this.PressureAngleValueText, Parameters.anglePressure));
        this.PressureAngleValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.PressureAngleValueText, Parameters.anglePressure, this.getContext()));

        this.helixAngleValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(helixAngleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.helixAngleValueText, new InvoluteEditRealKeyListener(this.helixAngleValueText, Parameters.angleHelix));
        this.helixAngleValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.helixAngleValueText, Parameters.angleHelix, this.getContext()));

        this.leadAngleValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(leadAngleValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.leadAngleValueText, new InvoluteEditRealKeyListener(this.leadAngleValueText, Parameters.angleLead));
        this.leadAngleValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.leadAngleValueText, Parameters.angleLead, this.getContext()));

        this.referenceDiameterValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(referenceDiameterValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.referenceDiameterValueText, new InvoluteEditRealKeyListener(this.referenceDiameterValueText, Parameters.diameterReference));
        this.referenceDiameterValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.referenceDiameterValueText, Parameters.diameterReference, this.getContext()));

        this.baseDiameterValueText = new InvoluteEditText(getContext(), InvoluteEditText.InvoluteParameterType.DECIMAL);
        this.valueEditLayout.addView(baseDiameterValueText, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
//        this.addInvoluteRealEditTextListener(this.baseDiameterValueText, new InvoluteEditRealKeyListener(this.baseDiameterValueText, Parameters.diameterBase));
        this.baseDiameterValueText.addTextChangedListener(new InvoluteRealEditTextChange(this.baseDiameterValueText, Parameters.diameterBase, this.getContext()));

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

    public void showResult() {
        if (!CalculateResult.isSucceed()) {
            Log.i(TAG, "showResult: calculate not success");
            return;
        }
        if (!Parameters.teethNumber.isFixed()) {
            this.teethNumberValueText.setText(CalculateResult.getTeethNumberResultValue());
        }
        if (!Parameters.moduleNormal.isFixed()) {
            this.normalModuleValueText.setText(CalculateResult.getModuleNormalResultValue());
        }
        if (!Parameters.moduleTransverse.isFixed()) {
            this.transverseModuleValueText.setText(CalculateResult.getModuleTransverseResultValue());
        }
        if (!Parameters.moduleAxial.isFixed()) {
            this.axialModuleValueText.setText(CalculateResult.getModuleAxialResultValue());
        }
        if (!Parameters.moduleBase.isFixed()) {
            this.baseModuleValueText.setText(CalculateResult.getModuleBasicResultValue());
        }
        if (!Parameters.anglePressureNormal.isFixed()) {
            this.normalPressureAngleValueText.setText(CalculateResult.getAnglePressureNormalResultValue());
        }
        if (!Parameters.anglePressure.isFixed()) {
            this.PressureAngleValueText.setText(CalculateResult.getAnglePressureResultValue());
        }
        if (!Parameters.angleHelix.isFixed()) {
            this.helixAngleValueText.setText(CalculateResult.getAngleHelixResultValue());
        }
        if (!Parameters.angleLead.isFixed()) {
            this.leadAngleValueText.setText(CalculateResult.getAngleLeadResultValue());
        }
        if (!Parameters.diameterBase.isFixed()) {
            this.baseDiameterValueText.setText(CalculateResult.getDiameterBaseResultValue());
        }
        if (!Parameters.diameterReference.isFixed()) {
            this.referenceDiameterValueText.setText(CalculateResult.getDiameterReferenceResultValue());
        }
    }

    public void clear() {
        for (int i = 0; i < this.valueEditLayout.getChildCount(); i++) {
            View view = this.valueEditLayout.getChildAt(i);
            if (view instanceof InvoluteEditText) {
                ((InvoluteEditText) view).setText("");
            }
        }
        for (int i = 0; i < this.fixedLinerlayout.getChildCount(); i++) {
            View view = this.fixedLinerlayout.getChildAt(i);
            if (view instanceof CheckBox) {
                ((CheckBox) view).setChecked(true);
            }
        }
    }

    public void checkFixed() {
        if (this.teethNumberValueText.getText().toString().equals("")) {
            this.teethNumberFixedBox.setChecked(false);
            Parameters.teethNumber.setFixed(false);
        }
        if (this.normalModuleValueText.getText().toString().equals("")) {
            this.normalModuleFixedBox.setChecked(false);
            Parameters.moduleNormal.setFixed(false);
        }
        if (this.transverseModuleValueText.getText().toString().equals("")) {
            this.transverseModuleFixedBox.setChecked(false);
            Parameters.moduleTransverse.setFixed(false);
        }
        if (this.axialModuleValueText.getText().toString().equals("")) {
            this.axialModuleFixedBox.setChecked(false);
            Parameters.moduleAxial.setFixed(false);
        }
        if (this.baseModuleValueText.getText().toString().equals("")) {
            this.baseModuleFixedBox.setChecked(false);
            Parameters.moduleBase.setFixed(false);
        }
        if (this.normalPressureAngleValueText.getText().toString().equals("")) {
            this.normalPressureAngleFixedBox.setChecked(false);
            Parameters.anglePressureNormal.setFixed(false);
        }
        if (this.PressureAngleValueText.getText().toString().equals("")) {
            this.pressureAngleFixedBox.setChecked(false);
            Parameters.anglePressure.setFixed(false);
        }
        if (this.helixAngleValueText.getText().toString() .equals("")) {
            this.helixAngleFixedBox.setChecked(false);
            Parameters.angleHelix.setFixed(false);
        }
        if (this.leadAngleValueText.getText().toString() .equals("")) {
            this.leadAngleFixedBox.setChecked(false);
            Parameters.angleLead.setFixed(false);
        }
        if (this.baseDiameterValueText.getText().toString() .equals("")) {
            this.baseDiameterFixedBox.setChecked(false);
            Parameters.diameterBase.setFixed(false);
        }
        if (this.referenceDiameterValueText.getText().toString() .equals("")) {
            this.referenceDiameterFixedBox.setChecked(false);
            Parameters.diameterReference.setFixed(false);
        }
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
        void onFragmentInteraction(Uri uri);
    }

    private void addInvoluteRealEditTextListener(InvoluteEditText editText, InvoluteEditRealKeyListener listener) {
        editText.setKeyListener(listener);
        editText.addTextChangedListener(listener);
    }
}
