package com.hzh.border.pager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hzh.border.pager.R;
import com.hzh.border.pager.event.CheckPlateEvent;

/**
 * Package: com.hzh.borderviewpager.fragment
 * FileName: PagerFragment
 * Date: on 2017/12/29  上午11:04
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class PagerFragment extends Fragment {
    public static final String BUNDLE_KEY_TYPE = "key_type";
    public static final String BUNDLE_KEY_POSITION = "key_position";

    public static final int FREE_TYPE = 1;
    public static final int HIGHT_TYPE = 2;
    public static final int NOMAL_TYPE = 3;
    private int position;
    private int type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_pager_wt_plate, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView plateIv = view.findViewById(R.id.plate_iv);
        Bundle arguments = getArguments();
        if (arguments != null) {
            type = arguments.getInt(PagerFragment.BUNDLE_KEY_TYPE);
            position = arguments.getInt(PagerFragment.BUNDLE_KEY_POSITION);
            if (type == FREE_TYPE) {
                plateIv.setImageResource(R.mipmap.wishingtree_image_0_0);
            } else if (type == HIGHT_TYPE) {
                plateIv.setImageResource(R.mipmap.wishingtree_image_0_1);
            } else if (type == NOMAL_TYPE) {
                plateIv.setImageResource(R.mipmap.wishingtree_image_0_0);
            } else {
                plateIv.setImageResource(R.mipmap.wishingtree_image_0_0);
            }
        }
        plateIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPlateEvent.send(position);
            }
        });
    }
}