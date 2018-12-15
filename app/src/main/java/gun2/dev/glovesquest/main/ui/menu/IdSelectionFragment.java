package gun2.dev.glovesquest.main.ui.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import gun2.dev.glovesquest.R;
import gun2.dev.glovesquest.utils.LogManager;


public class IdSelectionFragment extends Fragment implements View.OnClickListener{

    public interface Callback{
        void idSelectionFragmentCallback(int id);
    }

    LogManager Log = new LogManager(getClass().getName().trim());

    private Callback mCallback;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IdSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IdSelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IdSelectionFragment newInstance(String param1, String param2) {
        IdSelectionFragment fragment = new IdSelectionFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_id_selection, container, false);
        // Inflate the layout for this fragment
        ImageButton id1Btn = (ImageButton) view.findViewById(R.id.slot1_fragment_id_selection_ImageButton);
        ImageButton id2Btn = (ImageButton) view.findViewById(R.id.slot2_fragment_id_selection_ImageButton);
        id1Btn.setTag(1);
        id2Btn.setTag(2);

        id1Btn.setOnClickListener(this);
        id2Btn.setOnClickListener(this);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Callback) mCallback = (Callback)context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.slot1_fragment_id_selection_ImageButton:
                Log.msg("slot1_fragment_id_selection_ImageButton is clicked");
                mCallback.idSelectionFragmentCallback((int)v.getTag());
                break;

            case R.id.slot2_fragment_id_selection_ImageButton:
                Log.msg("slot2_fragment_id_selection_ImageButton is clicked");
                mCallback.idSelectionFragmentCallback((int)v.getTag());
                break;
        }
    }
}
