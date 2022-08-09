package canada.montreal.pierre.android2_labo3;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class SwipeCallback<T> extends ItemTouchHelper.Callback {

    private RecyclerView.Adapter<? extends RecyclerView.ViewHolder> mAdapter;
    private List<T> mList;

    public SwipeCallback(RecyclerView.Adapter adapter, List<T> list) {
        mAdapter = adapter;
        mList = list;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag= ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipeFlags=ItemTouchHelper.END|ItemTouchHelper.START;

        return makeMovementFlags(dragFlag,swipeFlags);
    }

    //用于移动item的位置
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Collections.swap(mList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
        mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    //一般用于滑动删除
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mList.remove(viewHolder.getAdapterPosition());
        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
    }
}
