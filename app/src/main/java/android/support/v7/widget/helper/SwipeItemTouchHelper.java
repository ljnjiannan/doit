package android.support.v7.widget.helper;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class SwipeItemTouchHelper extends ItemTouchHelper {

  public SwipeItemTouchHelper(ItemTouchHelper.Callback callback) {
    super(callback);
  }


  public ItemTouchHelper.Callback getCallback() {
    return mCallback;
  }

}