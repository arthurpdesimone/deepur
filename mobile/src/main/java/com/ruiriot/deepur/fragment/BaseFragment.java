package com.ruiriot.deepur.fragment;

import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by ruiri on 03-Jul-17.
 */

public class BaseFragment extends Fragment {

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
