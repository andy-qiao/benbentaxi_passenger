package com.benbentaxi.passenger.location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class DriverOverlay extends ItemizedOverlay<OverlayItem> {
    public List<OverlayItem> mGeoList = new ArrayList<OverlayItem>();
	private Context mContext = null;
    static PopupOverlay pop = null;
    private Handler mH = null;

    Toast mToast = null;

    
	public DriverOverlay(Drawable marker,Context context, MapView mapView){
		super(marker,mapView);
		this.mContext = context;
//        pop = new PopupOverlay( mapView,new PopupClickListener() {
//			
//			@Override
//			public void onClickedPopup(int index) {
//			    if (null == mToast)
//			        mToast = Toast.makeText(mContext, "popup item :" + index + " is clicked.", Toast.LENGTH_SHORT);
//			    else mToast.setText("popup item :" + index + " is clicked.");
//			    mToast.show();
//			}
//		});
       // 自2.1.1 开始，使用 add/remove 管理overlay , 无需调用以下接口.
	   // populate();
		
	}
	
	public DriverOverlay(Drawable marker,Context context, MapView mapView, Handler h){
		this(marker, context, mapView);
		mH = h;
	}
	
	protected boolean onTap(int index) {
		//System.out.println("item onTap: "+index);
	    
	    Bitmap[] bmps = new Bitmap[3];
	    if (index %2 == 0) {
	        try {
	            bmps[0] = BitmapFactory.decodeStream(mContext.getAssets().open("marker1.png"));
	            bmps[1] = BitmapFactory.decodeStream(mContext.getAssets().open("marker2.png"));
	            bmps[2] = BitmapFactory.decodeStream(mContext.getAssets().open("marker3.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    else {
	        try {
                bmps[2] = BitmapFactory.decodeStream(mContext.getAssets().open("marker1.png"));
                bmps[1] = BitmapFactory.decodeStream(mContext.getAssets().open("marker2.png"));
                bmps[0] = BitmapFactory.decodeStream(mContext.getAssets().open("marker3.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
        
	    
	    if ( mH != null ) {
	    	mH.sendMessage(mH.obtainMessage(LocationOverlayDemo.MSG_HANDLE_ITEM_TOUCH,this.getItem(index)));
	    }
		
		return true;
	}
	public boolean onTap(GeoPoint pt, MapView mapView){
		super.onTap(pt,mapView);
		return false;
	}
	
	// 自2.1.1 开始，使用 add/remove 管理overlay , 无需重写以下接口
	/*
	@Override
	protected OverlayItem createItem(int i) {
		return mGeoList.get(i);
	}
	
	@Override
	public int size() {
		return mGeoList.size();
	}
	*/
}