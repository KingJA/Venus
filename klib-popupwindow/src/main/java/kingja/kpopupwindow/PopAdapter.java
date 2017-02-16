package kingja.kpopupwindow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/4/13 13:45
 * 修改备注：
 */
public class PopAdapter extends BaseLvAdapter<String> {

    public PopAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_pop, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_item.setText(list.get(position));
        return convertView;
    }

    public void reset() {
        this.list.clear();
        notifyDataSetChanged();
    }


    public class ViewHolder {
        public final TextView tv_item;
        public final View root;

        public ViewHolder(View root) {
            tv_item = (TextView) root.findViewById(R.id.tv_item);
            this.root = root;
        }
    }
}
