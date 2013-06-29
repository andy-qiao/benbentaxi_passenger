package com.benbentaxi.passenger.confirm;

import com.benbentaxi.passenger.R;
import com.benbentaxi.passenger.demo.DemoApplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ConfirmPopupWindow extends PopupWindow{
	
	
	private final static String BTN_POS_TEXT= "确认";
	private final static String BTN_NEG_TEXT= "重新打车";

	private View mView;
	private TextView mTitle;
	private TextView mContent;
	private Button mBtnPos, mBtnNeg;
	private DemoApplication mApp;
	
	private View.OnClickListener mPosfunc = null, mNegfunc = null;

	
	public ConfirmPopupWindow(Context c)
	{
		super(c);
	}
	public ConfirmPopupWindow(Activity activity,int width,int height)
	{
		super(activity.getLayoutInflater().inflate(R.layout.confirm_dialog, null),width,height);
		
	}
	public ConfirmPopupWindow(Activity activity)
	{
		this(activity,600,400);
		DemoApplication mApp = (DemoApplication)activity.getApplicationContext();
		
		mView = this.getContentView();

		mTitle = (TextView)mView.findViewById(R.id.tvConfirmTitle);
    	mContent = (TextView)mView.findViewById(R.id.tvConfirmContent);
    	mBtnPos = (Button)mView.findViewById(R.id.btnConfirmOk);
    	mBtnNeg = (Button)mView.findViewById(R.id.btnConfirmCancel);
    	mTitle.setText("有司机响应，距离您约");
    	@SuppressWarnings("static-access")
		String d =(mApp.getCurrentTaxiRequest() != null) ? mApp.getCurrentTaxiRequest().getDistance().toString() : "0";
    	mContent.setText(d+"公里");
    	mBtnPos.setText(BTN_POS_TEXT);
    	mBtnNeg.setText(BTN_NEG_TEXT);
    	mPosfunc = mNegfunc = new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if ( ConfirmPopupWindow.this.isShowing() ) {
					ConfirmPopupWindow.this.dismiss();
				}
			}
		};
	}
	
	public void show()
	{
		mBtnPos.setOnClickListener(mPosfunc);
		mBtnNeg.setOnClickListener(mNegfunc);

		showAtLocation(mView, Gravity.CENTER, 0, 0);
	}

	
	
	
}