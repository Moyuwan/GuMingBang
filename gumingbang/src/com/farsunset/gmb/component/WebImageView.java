package com.farsunset.gmb.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.farsunset.gmb.net.AsyncImageLoader;
import com.farsunset.gmb.net.AsyncImageLoader.ImageCallback;
import com.farsunset.gmb.ui.R;


public class WebImageView extends ImageView  {
 
    private String url;

    Context _context;
  


    public WebImageView(Context context) {
        super(context);
        _context = context;
    }

    public WebImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        
        _context = context;
    }

    public WebImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
        _context = context;
    }

    /**
     * @return download url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置download url，开始下载
     * 
     * @param url
     */
    public void load(String url) {
         
    	 this.url = url;
    	 AsyncImageLoader.getAsyncImageLoader(_context).loadDrawable(_context.getResources().getDrawable(R.drawable.image_empty),url, new ImageCallback() {
		         public void imageLoaded(Drawable imageDrawable) {
		        	 WebImageView.this.destroyDrawingCache();
		        	 WebImageView.this.setImageDrawable(imageDrawable);
		          }
		 });
    }

    
}
