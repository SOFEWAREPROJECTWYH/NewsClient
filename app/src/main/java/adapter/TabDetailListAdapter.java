package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyh.newsclient.model.TabNewsData;

import java.util.List;

import com.wyh.newsclient.R;
import com.wyh.newsclient.model.TabNewsData;
import com.wyh.newsclient.utils.StringUtils;

public class TabDetailListAdapter extends BaseAdapter {
    List<TabNewsData.News> news;

    public TabDetailListAdapter(List<TabNewsData.News> news) {
        this.news=news;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tab_detail_list_item, null);

            holder = new ViewHolder();

            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(viewGroup.getContext()).load(StringUtils.fixUrl(news.get(position).listimage)).into(holder.iv_icon);

        holder.tv_title.setText(news.get(position).title);
        holder.tv_date.setText(news.get(position).pubdate);


        return convertView;
    }

    static class ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_date;
    }

    public void appendNews(List<TabNewsData.News> news) {
        this.news.addAll(news);
    }
}
