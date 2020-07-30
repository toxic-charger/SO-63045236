package com.stackoverflow.q63045236;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.stackoverflow.q63045236.model.POJOListItem;
import java.util.List;

public class MyContentViewAdapter extends RecyclerView.Adapter<MyContentViewAdapter.ViewHolder> {
  private List<POJOListItem> data;

  MyContentViewAdapter(List<POJOListItem> data) {
    this.data = data;
  }

  @Override
  public MyContentViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(MyContentViewAdapter.ViewHolder holder, int position) {
    holder.bind(data.get(position));
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    private TextView tvHeader;
    private LinearLayout tvContainer;
    private Context context;

    ViewHolder(View v) {
      super(v);
      context = v.getContext();
      tvHeader = v.findViewById(R.id.tv_header);
      tvContainer = v.findViewById(R.id.tv_container);
    }

    void bind(POJOListItem item) {
      tvHeader.setText(item.id);
      tvContainer.removeAllViews();
      for (int i = 0; i < item.contents.size(); i++) {
        View childView =
            LayoutInflater.from(context).inflate(R.layout.layout_content_key_view, null, false);
        TextView tvKey = childView.findViewById(R.id.tv_key);
        TextView tvContent = childView.findViewById(R.id.tv_content);
        tvKey.setText(item.keys.get(i));
        tvContent.setText(item.contents.get(i));
        tvContainer.addView(childView);
      }
    }
  }
}