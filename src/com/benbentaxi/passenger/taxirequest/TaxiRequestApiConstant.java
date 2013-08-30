package com.benbentaxi.passenger.taxirequest;

import java.util.HashMap;
import java.util.Map;

import com.benbentaxi.passenger.taxirequest.state.TaxiRequestState;

public class TaxiRequestApiConstant {
	public final static String ID 				= "id";
	public final static String PASSENGER_MOBILE = "passenger_mobile";
	public final static String DRIVER_MOBILE 	= "driver_mobile";
	public final static String DRIVER_LAT		= "driver_lat";
	public final static String DRIVER_LNG		= "driver_lng";
	public final static String PASSENGER_LAT	= "passenger_lat";
	public final static String PASSENGER_LNG	= "passenger_lng";
	public final static String PLATE		 	= "plate";
	public final static String DISTANCE			= "distance";
	public final static String STATE			= "state";
	public final static String DRIVER_NAME		= "driver_name";
	public final static String SOURCE 			="source";
	public final static String CREATED_AT 		="created_at";
	
	public static Map<String,TaxiRequestState>  _s = null;
	
	static {
		_s = new HashMap<String,TaxiRequestState>();
		for(TaxiRequestState t :TaxiRequestState.values() ){
			addState(t);
		}

	}
	
	private static void addState(TaxiRequestState taxiRequestState)
	{
		_s.put(taxiRequestState.toString().toLowerCase(),taxiRequestState);
	}
	public static TaxiRequestState getState(String s)
	{
		TaxiRequestState t = _s.get(s.toLowerCase());
		if (t != null){
			return t;
		}else{
			return TaxiRequestState.UNKONW;
		}
	}
	
}
