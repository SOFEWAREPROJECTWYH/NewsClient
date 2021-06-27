package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wyh.newsclient.ui.fragment.BaseFragment;

import java.util.List;

public class ContentPageAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragments;

    public ContentPageAdapter(FragmentManager supportFragmentManager, List<BaseFragment> fragments) {
        super(supportFragmentManager);
        this.fragments=fragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
