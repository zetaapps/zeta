package zeta.android.apps.views.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

/**
 * A collection of common methods around setting views to Visible and Gone. This
 * code performs basic functionality that was littered throughout the code base,
 * like null checks and asserting that the visibility is not already what you
 * are trying to set.
 */
public class ViewUtils {

    /**
     * This is a helper method for a situation that shows up a surprising number
     * of times. Make the first view visible, and all remaining views gone
     *
     * @param visibleView The view to set to visibility = View.VISIBLE
     * @param goneViews   The views to set to visibility = View.GONE
     */
    public static void showFirstAndHideOthers(View visibleView, View... goneViews) {
        setMultipleToGone(goneViews);
        setToVisible(visibleView);
    }

    /**
     * @param goneViews
     */
    public static void setMultipleToGone(View... goneViews) {
        setMultipleVisibility(View.GONE, goneViews);
    }

    /**
     * @param visibleViews
     */
    public static void setMultipleToVisible(View... visibleViews) {
        setMultipleVisibility(View.VISIBLE, visibleViews);
    }

    /**
     * @param invisibleViews
     */
    public static void setMultipleToInvisible(View... invisibleViews) {
        setMultipleVisibility(View.INVISIBLE, invisibleViews);
    }

    /**
     * @param view
     * @return
     */
    public static boolean isVisible(View view) {
        return (view.getVisibility() == View.VISIBLE);
    }

    /**
     * @param visibility
     * @param views
     */
    public static void setMultipleVisibility(int visibility, View... views) {
        if (views == null) {
            return;
        }
        int length = views.length;
        for (int i = 0; i < length; i++) {
            View view = views[i];
            setVisibility(view, visibility);
        }
    }

    /**
     * @param alpha set the views to this alpha value.
     * @param views the varargs whose view we set.
     */
    public static void setMultipleAlpha(float alpha, View... views) {
        if (views == null) {
            return;
        }
        int length = views.length;
        for (int i = 0; i < length; i++) {
            View view = views[i];
            view.setAlpha(alpha);
        }
    }

    /**
     * This is a common use case: if the text is not empty set the text of the TextView and set the TextView to VISIBLE,
     * otherwise set the TextView to GONE
     *
     * @param view The text view
     * @param text The text to set
     */
    public static void setTextAndVisibility(TextView view, CharSequence text) {
        if (view == null) {
            return;
        }
        if (text != null && text.length() > 0) {
            view.setText(text);
            setToVisible(view);
        } else {
            setToGone(view);
        }
    }

    /**
     * This is a common use case: set the text of the text view and set the text view to visible
     *
     * @param view The text view
     * @param text The text to set
     */
    public static void setTextAndMakeVisible(TextView view, CharSequence text) {
        if (view == null) {
            return;
        }
        view.setText(text);
        setToVisible(view);
    }

    /**
     * This is a common use case: set the text of the text view and set the text
     * view to visible
     *
     * @param view The text view
     * @param id   The ID of the text to use
     */
    public static void setTextAndMakeVisible(TextView view, int id) {
        if (view == null) {
            return;
        }
        view.setText(id);
        setToVisible(view);
    }

    /**
     * @param view
     */
    public static void setToVisible(View view) {
        setVisibility(view, View.VISIBLE);
    }

    /**
     * @param view
     */
    public static void setToGone(View view) {
        setVisibility(view, View.GONE);
    }

    /**
     * @param view
     */
    public static void setToInvisible(View view) {
        setVisibility(view, View.INVISIBLE);
    }

    /**
     * @param view
     * @param visibility
     */
    public static void setVisibility(View view, int visibility) {
        if (view != null && view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }

    /**
     * Allows for a view to be set to either visible or gone using a boolean flag.
     *
     * @param view    the view to change the visibility of
     * @param visible true if the view should be set to VISIBLE, false if it should be set to GONE
     */
    public static void setVisibility(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * @param views
     */
    public static void clearOnClickListener(View... views) {
        setOnClickListener(null, views);
    }

    /**
     * @param listener
     * @param views
     */
    public static void setOnClickListener(OnClickListener listener, View... views) {
        if (views == null) {
            return;
        }
        int length = views.length;
        for (int i = 0; i < length; i++) {
            View view = views[i];
            if (view != null) {
                view.setOnClickListener(listener);
            }
        }
    }

    /**
     * @param isClickable
     * @param view
     */
    public static void setClickable(boolean isClickable, View view) {
        if (view == null) {
            return;
        }
        view.setClickable(isClickable);
    }

    /**
     * @param layout
     * @param id
     * @return
     */
    public static View ensureInflated(ViewGroup layout, int id) {
        return ensureInflated(layout.findViewById(id));
    }

    /**
     * @param layout
     * @param id
     * @param inflatedId
     * @return
     */
    public static View ensureInflated(ViewGroup layout, int id, int inflatedId) {
        View stub = ensureInflated(layout.findViewById(id));
        if (stub == null) {
            return layout.findViewById(inflatedId);
        }
        return stub;
    }

    /**
     * @param view
     * @return
     */
    public static View ensureInflated(View view) {
        if (view instanceof ViewStub) {
            return ((ViewStub) view).inflate();
        }
        return view;
    }

    /**
     * @param container
     * @param id
     * @param view
     * @return
     */
    public static View findViewById(View container, int id, View view) {
        if (view != null) {
            return view;
        }
        return container.findViewById(id);
    }

    /**
     * @param view
     * @param parentView
     * @return
     */
    public static int getBottomWithinParent(View view, View parentView) {
        int bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while (parent != null && parent instanceof View && parent != parentView) {
            bottom += ((View) parent).getTop();
            parent = parent.getParent();
        }
        return bottom;
    }

    /**
     * Padding disappears when you set the background. This method call ensures
     * that the padding values are retained
     *
     * @param button   The button to apply the background to
     * @param drawable The new drawable background to apply
     */
    @TargetApi(16)
    public
    static void setButtonBackground(Button button, Drawable drawable) {
        int left = button.getPaddingLeft();
        int right = button.getPaddingRight();
        int top = button.getPaddingTop();
        int bottom = button.getPaddingBottom();
        button.setBackground(drawable);
        button.setPadding(left, top, right, bottom);
    }

    /**
     * @param view
     * @return
     */
    public static View getParentView(View view) {
        ViewParent parent = view.getParent();
        while (parent != null) {
            if (parent instanceof View) {
                return (View) parent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    /**
     * @param view  : view class
     * @param clazz : clazz
     * @param <T>   :
     * @return
     */
    public static <T extends View> T getParentView(View view, Class<T> clazz) {
        ViewParent parent = view.getParent();
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                @SuppressWarnings("unchecked")
                T cparent = (T) parent;
                return cparent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    /**
     * @param view
     * @return
     */
    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    /**
     * Sets the margins for the specified view.
     *
     * @param view         the view on which to act
     * @param leftMargin   the left margin
     * @param topMargin    the top margin
     * @param rightMargin  the right margin
     * @param bottomMargin the bottom margin
     */
    public static void setMargins(View view, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            view.requestLayout();
        }
    }

}
