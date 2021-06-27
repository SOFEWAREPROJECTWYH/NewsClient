package adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


public class NewsTabPagerAdapter extends FragmentPagerAdapter {
    List<NewsTabFragment> newsTabFragmentList;

    public NewsTabPagerAdapter(FragmentManager fm, List<NewsTabFragment> newsTabFragmentList) {
        super(fm);
        this.newsTabFragmentList = newsTabFragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsTabFragmentList.get(position).getNewsTabData().title;
    }

    @Override
    public int getCount() {
        return newsTabFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return newsTabFragmentList.get(position);
    }
}
