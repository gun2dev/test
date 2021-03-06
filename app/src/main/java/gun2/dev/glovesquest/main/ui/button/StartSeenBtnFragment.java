package gun2.dev.glovesquest.main.ui.button;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import gun2.dev.glovesquest.R;
import gun2.dev.glovesquest.utils.LogManager;

public class StartSeenBtnFragment extends Fragment
        implements View.OnClickListener{
    LogManager Log = new LogManager(getClass().getName().trim());


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public StartSeenBtnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartSeenBtnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartSeenBtnFragment newInstance(String param1, String param2) {
        StartSeenBtnFragment fragment = new StartSeenBtnFragment();
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
        View view = inflater.inflate(R.layout.fragment_start_seen_btn, container, false);
        ImageButton organizeBtn = (ImageButton) view.findViewById(R.id.organize_fragment_start_seen_btn_imageButton);
        ImageButton adventureBtn = (ImageButton) view.findViewById(R.id.adventure_fragment_start_seen_btn_imageButton);

        organizeBtn.setOnClickListener(this);
        adventureBtn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.organize_fragment_start_seen_btn_imageButton:
                Log.msg("organize_fragment_start_seen_btn_imageButton is clicked");
                break;

            case R.id.adventure_fragment_start_seen_btn_imageButton:
                Log.msg("adventure_fragment_start_seen_btn_imageButton is clicked");
                break;
        }
    }
}
