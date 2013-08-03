package com.benbentaxi.passenger.login;

import android.content.Intent;
import android.util.Log;

import com.benbentaxi.Configure;
import com.benbentaxi.Passenger;
import com.benbentaxi.api.PostTask;
import com.benbentaxi.passenger.location.DemoApplication;
import com.benbentaxi.passenger.location.LocationOverlayDemo;
import com.benbentaxi.session.SessionResponse;

public class LoginTask extends PostTask{
	private final String API1 			="/api/v1/sessions/passenger_signin";

	private LoginForm 			mLoginForm;
	private LoginRequest		mLoginRequest;
	private Configure       	mConfigure;
	private DemoApplication 	mApp;

	private final String TAG			     = LoginTask.class.getName();
	
	public LoginTask(LoginForm loginForm)
	{
		this.mLoginForm 			= loginForm;
		this.mLoginRequest			= new LoginRequest(mLoginForm);
		this.mConfigure 			= new Configure();
		this.mApp 					= (DemoApplication) loginForm.getActivity().getApplication();
	}
	
	public void go()
	{
		this.mLoginForm.showProgress(true);
		this.mApp.setLoginMobile(this.mLoginForm.getMobile());
		this.mApp.setLoginPass(this.mLoginForm.getPass());
		execute();
	}
	
	protected void onPostExecute(final Boolean succ) 
	{
		SessionResponse sessionResponse = new SessionResponse(mLoginForm,this.getResult());
		this.mLoginForm.showProgress(false);
		if (!succ){
			sessionResponse.setSysErrorMessage(this.getErrorMsg());
		}
		if (!sessionResponse.hasError()){
			
			DemoApplication app = (DemoApplication)this.mLoginForm.getActivity().getApplicationContext();

			app.setCurrentSession(sessionResponse);
			Passenger passenger = new Passenger(this.mLoginForm.getMobile());
			app.setCurrentPassenger(passenger);
			/**/ 
			String a = null;
			a.length();
			/**/
			Intent intent = new Intent(this.mLoginForm.getActivity(),LocationOverlayDemo.class);
			this.mLoginForm.getActivity().startActivity(intent);
		}
	}
	protected String getApiUrl()
	{
		return "http://"+mConfigure.getService()+API1;
	}
	
	@Override
	protected String getPostParams() {
		if ( mLoginRequest != null ) {
			String k = mLoginRequest.toJson().toString();
			Log.d(TAG,k);
			return k;
		}		
		return "";
	}



}
