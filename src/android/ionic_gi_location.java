package com.gi.location;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.location.LocationManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.provider.Settings;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;
/**
 * This class echoes a string called from JavaScript.
 */
public class ionic_gi_location extends CordovaPlugin {
private Context context= null;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        context=this.cordova.getActivity().getApplicationContext();
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }else if("get_location_values".equals(action)){
          JSONObject loc_res = new JSONObject();
          getLocationValues loc_val = new getLocationValues(context);
          if(loc_val.canGetLocation){
            loc_res.put("status","1");
            loc_res.put("message",loc_val.getLocationStatus());
            loc_res.put("lat",""+loc_val.latitude);
            loc_res.put("lang",""+loc_val.longitude);
            callbackContext.success(loc_res);
          }else{
            loc_res.put("status","0");
            loc_res.put("message","Error while getting location");
            loc_res.put("full_message","Error "+loc_val.getLocationStatus());
            callbackContext.error(loc_res);
          }
        }else if("check_gps".equals(action)){
            JSONObject loc_res = new JSONObject();
          getLocationValues loc_val = new getLocationValues(context);
          if(loc_val.canGetLocation){
            loc_res.put("status","1");
            loc_res.put("message","We can get Location");
              callbackContext.success(loc_res);
          }else{
            loc_res.put("status","0");
            loc_res.put("message","Gps not enabled");
              callbackContext.error(loc_res);
          }

        }else if("show_toast".equals(action)){
            Toast.makeText(context, "testing toast", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    public static void goto_settings_screen(Context c) {
      final LocationManager manager = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);
      Boolean n_sts = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
      Boolean G_sts = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
      if (!n_sts || !G_sts) {//not enable
        Intent in = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        //startActivityForResult(
        Toast.makeText(c, "Enable High Accuracy Location", Toast.LENGTH_SHORT).show();
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(in);

      }
    }


}
