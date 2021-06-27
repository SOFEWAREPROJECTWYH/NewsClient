package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.wyh.newsclient.R;

public class LeftMenuListAdapter extends BaseAdapter {
    List<String> titles;
    private int currentPosition;


    public LeftMenuListAdapter(List<String> titles) {
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_menu_list_item, null);

            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.tv_left_menu_title);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(titles.get(position));

        if (currentPosition == position) {
            holder.titleTv.setEnabled(true);
        }else{
            holder.titleTv.setEnabled(false);
        }
        return convertView;
    }


    static class ViewHolder{
        TextView titleTv;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
