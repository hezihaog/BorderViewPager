package com.hzh.border.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hzh.border.pager.adapter.ViewPagerAdapter;
import com.hzh.border.pager.event.CheckPlateEvent;
import com.hzh.border.pager.fragment.PagerFragment;
import com.hzh.border.pager.widget.DotIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private TextView descTv;
    private TextView writeWishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏TitleBar和设置全屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        bindContent();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        dotLayout = findViewById(R.id.dotLayout);
        descTv = findViewById(R.id.desc);
        writeWishBtn = findViewById(R.id.write_wish_btn);
    }

    private void bindContent() {
        setViewPagerTransformer(180, 0.38f);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        String typeKey = PagerFragment.BUNDLE_KEY_TYPE;
        String positionKey = PagerFragment.BUNDLE_KEY_POSITION;
        for (int i = 0; i < 3; i++) {
            Bundle args = new Bundle();
            PagerFragment pagerFragment = new PagerFragment();
            if (i == 0) {
                args.putInt(typeKey, PagerFragment.FREE_TYPE);
            } else if (i == 1) {
                args.putInt(typeKey, PagerFragment.HIGHT_TYPE);
            } else if (i == 2) {
                args.putInt(typeKey, PagerFragment.NOMAL_TYPE);
            }
            args.putInt(positionKey, i);
            pagerFragment.setArguments(args);
            fragments.add(pagerFragment);
        }
        adapter.setDataAndNotify(fragments);
        final DotIndicator dotIndicator = new DotIndicator(this, fragments.size());
        dotLayout.addView(dotIndicator);
        viewPager.setOffscreenPageLimit(fragments.size() - 1);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                dotIndicator.checkCurrentPosition(position);
                if (position == 0) {
                    descTv.setText("免费许愿牌3天体验，需要定期重新挂上许愿树哦，购买中级或高级许愿牌永久挂上许愿树，可以尽早实现愿望！");
                } else if (position == 1) {
                    descTv.setText("草绿底色，配上纯白百合花纹，寓意祝福祈愿，许愿树守护祈愿之人，博弈、比赛等运气开旺，事事顺利。");
                } else if (position == 2) {
                    descTv.setText("求博彩、比赛、赌运等好运大增，旗开得胜。");
                }
            }
        });
        viewPager.setCurrentItem(1);
        writeWishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "填写愿望", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置视差缩放，ViewPager的PageTransformer
     */
    public void setViewPagerTransformer(float maxOffset, float scaleRate) {
        int cardMaxOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, maxOffset, getResources().getDisplayMetrics());
        ScalePagerTransformer transformer = new ScalePagerTransformer(cardMaxOffset, scaleRate);
        viewPager.setPageTransformer(false, transformer);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckEvent(CheckPlateEvent event) {
        viewPager.setCurrentItem(event.getPosition());
    }
}