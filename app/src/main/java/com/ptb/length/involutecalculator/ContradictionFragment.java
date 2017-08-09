package com.ptb.length.involutecalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContradictionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContradictionFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private EditText teethNumberContradictionText;
    private EditText normalModuleContradictionText;
    private EditText transverseModuleContradictionText;
    private EditText axialModuleContradictionText;
    private EditText baseModuleContradictionText;
    private EditText normalPressureAngleContradictionText;
    private EditText pressureAngleContradictionText;
    private EditText helixAngleContradictionText;
    private EditText leadAngleContradictionText;
    private EditText referenceDiameterContradictionText;
    private EditText baseDiameterContradictionText;

    public ContradictionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment ContradictionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContradictionFragment newInstance(String param1, String param2) {
        ContradictionFragment fragment = new ContradictionFragment();
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
        View contradictionView = inflater.inflate(R.layout.fragment_contradiction, container, false);
        this.baseDiameterContradictionText = (EditText) contradictionView.findViewById(R.id.baseDiameterContradictionText);
        this.referenceDiameterContradictionText = (EditText) contradictionView.findViewById(R.id.referenceDiameterContradictionText);
        this.leadAngleContradictionText = (EditText) contradictionView.findViewById(R.id.leadAngleContradictionText);
        this.helixAngleContradictionText = (EditText) contradictionView.findViewById(R.id.helixAngleContradictionText);
        this.pressureAngleContradictionText = (EditText) contradictionView.findViewById(R.id.pressureAngleContradictionText);
        this.normalPressureAngleContradictionText = (EditText) contradictionView.findViewById(R.id.normalPressureAngleContradictionText);
        this.baseModuleContradictionText = (EditText) contradictionView.findViewById(R.id.baseModuleContradictionText);
        this.axialModuleContradictionText = (EditText) contradictionView.findViewById(R.id.axialModuleContradictionText);
        this.transverseModuleContradictionText = (EditText) contradictionView.findViewById(R.id.transverseModuleContradictionText);
        this.normalModuleContradictionText = (EditText) contradictionView.findViewById(R.id.normalModuleContradictionText);
        this.teethNumberContradictionText = (EditText) contradictionView.findViewById(R.id.teethNumberContradictionText);
        return contradictionView;
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
