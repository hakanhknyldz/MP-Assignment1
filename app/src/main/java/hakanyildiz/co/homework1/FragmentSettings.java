package hakanyildiz.co.homework1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dilkom-hak on 8.03.2016.
 */
public class FragmentSettings extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public FragmentSettings(){

    }

    public static FragmentSettings newInstance(String param1,String param2)
    {
        FragmentSettings fragment = new FragmentSettings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_settings,container,false);

        /*
        * ICERIK BURAYA EKLENECEK.
        *
        * */



        return layout;
    }
}
