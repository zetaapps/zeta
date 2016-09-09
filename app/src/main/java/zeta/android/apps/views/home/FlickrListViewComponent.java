package zeta.android.apps.views.home;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.annotation.ParametersAreNonnullByDefault;

import butterknife.Bind;
import zeta.android.apps.R;
import zeta.android.apps.appconfig.GlideConfigModule;
import zeta.android.apps.views.common.BaseViews;
import zeta.android.apps.views.utils.ViewUtils;
import zeta.apps.flickr.models.flickr.FlickrImages;

@ParametersAreNonnullByDefault
public class FlickrListViewComponent extends FrameLayout {

    private Views mViews;

    public FlickrListViewComponent(Context context) {
        super(context);
        init();
    }

    public FlickrListViewComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlickrListViewComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setItemData(FlickrImages itemData) {
        Context context = getContext();
        Glide.with(context)
                .load(itemData.getImageUrl())
                .thumbnail(GlideConfigModule.SIZE_MULTIPLIER)
                .crossFade()
                .into(mViews.flickrImage);

        final String title = itemData.getTitle();
        mViews.imageTitle.setContentDescription(context.getString(R.string.zeta_flick_image_cd, title));
        mViews.imageTitle.setText(Html.fromHtml(title));
        mViews.imageSubTitle.setText(Html.fromHtml(itemData.getDescription()));
        ViewUtils.setMultipleToVisible(mViews.flickrImage, mViews.imageTitle, mViews.imageSubTitle);
    }

    private void init() {
        final Context context = getContext();
        inflate(context, R.layout.view_flickr_list_item, this);
        mViews = new Views(this);
    }

    static class Views extends BaseViews {

        @Bind(R.id.zeta_flickr_image)
        ImageView flickrImage;

        @Bind(R.id.zeta_flickr_image_title)
        TextView imageTitle;

        @Bind(R.id.zeta_flickr_image_subtitle)
        TextView imageSubTitle;

        Views(View rootView) {
            super(rootView);
        }
    }
}
