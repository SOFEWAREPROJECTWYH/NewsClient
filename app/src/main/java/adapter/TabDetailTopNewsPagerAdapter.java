package adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.wyh.newsclient.model.TabNewsData;
import com.wyh.newsclient.utils.StringUtils;

import java.util.List;

public class TabDetailTopNewsPagerAdapter extends PagerAdapter {
    List<TabNewsData.TopNews> topNews;

    public TabDetailTopNewsPagerAdapter(List<TabNewsData.TopNews> topNews) {
        this.topNews = topNews;
    }

    @Override
    public int getCount() {
        return topNews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(container.getContext());

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(container.getContext()).load(StringUtils.fixUrl(topNews.get(position).topimage)).into(imageView);

        /*
        如果不将itemView添加到container之中,viewpager将是空白的
         */
        container.addView(imageView);


        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }


}
