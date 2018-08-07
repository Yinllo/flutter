package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.bean.NoticeBean;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/28 0028
 * @Describe  公告下拉TextView适配器
 */

public class NoticeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context context;
    private List<NoticeBean> dataBeanList;
    private LayoutInflater mInflater;
    private OnScrollListener mOnScrollListener;

    public NoticeRecyclerAdapter(Context context, List<NoticeBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case NoticeBean.PARENT_ITEM:
                view = mInflater.inflate(R.layout.recycleview_item_parent, parent, false);
                return new ParentViewHolder(context, view);
            case NoticeBean.CHILD_ITEM:
                view = mInflater.inflate(R.layout.recycleview_item_child, parent, false);
                return new ChildViewHolder(context, view);
            default:
                view = mInflater.inflate(R.layout.recycleview_item_parent, parent, false);
                return new ParentViewHolder(context, view);
        }
    }

    /**
     * 根据不同的类型绑定View
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case NoticeBean.PARENT_ITEM:
                ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
                parentViewHolder.bindView(dataBeanList.get(position), position, itemClickListener);
                break;
            case NoticeBean.CHILD_ITEM:
                ChildViewHolder childViewHolder = (ChildViewHolder) holder;
                childViewHolder.bindView(dataBeanList.get(position), position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataBeanList.get(position).getType();
    }

    private ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onExpandChildren(NoticeBean bean) {
            int position = getCurrentPosition(bean.getID());//确定当前点击的item位置
            NoticeBean children = getChildDataBean(bean);//获取要展示的子布局数据对象，注意区分onHideChildren方法中的getChildBean()。
            if (children == null) {
                return;
            }
            add(children, position + 1);//在当前的item下方插入
            if (position == dataBeanList.size() - 2 && mOnScrollListener != null) { //如果点击的item为最后一个
                //                mOnScrollListener.scrollTo(position + 1);//向下滚动，使子布局能够完全展示
            }
        }

        @Override
        public void onHideChildren(NoticeBean bean) {
            int position = getCurrentPosition(bean.getID());//确定当前点击的item位置
            NoticeBean children = bean.getChildBean();//获取子布局对象
            if (children == null) {
                return;
            }
            remove(position + 1);//删除
            if (mOnScrollListener != null) {
                //                mOnScrollListener.scrollTo(position);
            }
        }
    };

    /**
     * 在父布局下方插入一条数据
     * @param bean
     * @param position
     */
    public void add(NoticeBean bean, int position) {
        dataBeanList.add(position, bean);
        notifyItemInserted(position);
    }

    /**
     *移除子布局数据
     * @param position
     */
    protected void remove(int position) {
        dataBeanList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 确定当前点击的item位置并返回
     * @param uuid
     * @return
     */
    protected int getCurrentPosition(String uuid) {
        for (int i = 0; i < dataBeanList.size(); i++) {
            if (uuid.equalsIgnoreCase(dataBeanList.get(i).getID())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 封装子布局数据对象并返回
     * 注意，此处只是重新封装一个DataBean对象，为了标注Type为子布局数据，进而展开，展示数据
     * 要和onHideChildren方法里的getChildBean()区分开来
     * @param bean
     * @return
     */
    private NoticeBean getChildDataBean(NoticeBean bean){
        NoticeBean child = new NoticeBean();
        child.setType(1);
        //父布局
        child.setParentTitleTxt(bean.getParentTitleTxt());
        child.setParentDataTxt(bean.getParentDataTxt());
        //子布局
        child.setChildTitleTxt(bean.getChildTitleTxt());
        child.setChildContentTxt(bean.getChildContentTxt());
        child.setChildAuthorTxt(bean.getChildAuthorTxt());
//        child.setChildDataTxt(bean.getChildDataTxt());
        return child;
    }

    /**
     * 滚动监听接口
     */
    public interface OnScrollListener{
        void scrollTo(int pos);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.mOnScrollListener = onScrollListener;
    }
}
