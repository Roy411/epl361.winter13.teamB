/**Copyright [2013] [Andreas]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. **/

/**
 * @version 0.1
 * 
 */
package com.example.energospolitis;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.example.energospolitis.classes.Installation;
import com.example.energospolitis.classes.Report;
import com.example.energospolitis.classes.ReportCategory;
import com.example.energospolitis.db.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 
 * @author Andreas
 * 
 *         The User fills a report form with the basic info about a report and
 *         submits it to the database. **NOTE** This is just a prototype of the
 *         application. Some functions such as getLocation() and getId() are not
 *         fully implemented yet. For the purpose of this prototype those values
 *         are inserted manually.
 */
public class AddReportActivity extends Activity implements
		OnItemSelectedListener, LocationListener {
	String category = "";
	int cat = 0;
	String[] categories;
	String useraddress = "";
	String ID = "THE ID";
	Spinner typespin;
	private double lat, lon = 0;
	private Location location;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addreportform);

		// Report Description Textview
		final TextView Desc = (TextView) findViewById(R.id.desctxt);
		// Submit button
		Button ok = (Button) findViewById(R.id.ok);
		// Dropdown list with the available categories
		// ** NOTE ** not all categories are included in this prototype
		typespin = (Spinner) findViewById(R.id.typesp);
		typespin.setOnItemSelectedListener(this);
		categories = new String[ReportCategory.Cats.size()];
		int i = 0;

		for (ReportCategory r : ReportCategory.Cats) {
			categories[i] = r.getName();
			i++;
		}
		;

		ArrayAdapter<String> a = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, categories);

		a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typespin.setAdapter(a);
		
		ok.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// get the location of the user
				getLocation();
				// get the id of the user
				getId();
				// open database handler to add report to database **SEE DB
				// HANDLER CLASS**
				DatabaseHandler db = new DatabaseHandler(
						getApplicationContext());
				Log.d("ID", ID);
				Log.d("ADDRESS", useraddress);
				Report r = new Report(0, cat, "30/10/2013", useraddress, ID,
						"N", 1, 1, Desc.getText().toString());

				db.addReport(r);
				db.close();
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				AddReportActivity.this.startActivity(intent);
			}

		});



	}

	/**
	 * On item selection from spinner.
	 */
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		category = categories[position];
		
		cat = position;
		Log.d("Category", category+" "+Integer.toString(cat));
	}

	/**
	 * When nothing selected. The "" is set by default.
	 */
	public void onNothingSelected(AdapterView<?> parent) {
		category = "";
		// cat=-1;
	}

	@Override
	public void onLocationChanged(android.location.Location arg0) {
		// TODO Auto-generated method stub
		lat = arg0.getLatitude();
		lon = arg0.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	private void getId() {
		// TODO Auto-generated method stub
		ID = Installation.id(getApplicationContext());
	}

	// ** NOT IMPLEMENTED YET **//
	private void getLocation() {

		// TODO Auto-generated method stub
		try {
			LocationManager locationManager = (LocationManager) getApplicationContext()
					.getSystemService(LOCATION_SERVICE);

			// getting GPS status
			boolean isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			boolean isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				boolean canGetLocation = true;
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER, 2, 1, this);
					Log.d("Network", "Network Enabled");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							lat = location.getLatitude();
							lon = location.getLongitude();
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER, 2, 1, this);
						Log.d("GPS", "GPS Enabled");
						if (locationManager != null) {
							Log.d("LocationManagerNotNUll", "loc");
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {

								lat = location.getLatitude();
								lon = location.getLongitude();
								useraddress = getAddress();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getAddress() {
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		try {
			Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
			List<Address> addresses = geocoder.getFromLocation(lat,lon, 1);
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				Log.d("ADDRESS", "SOMEADDRESS");
				String locality = address.getLocality();
				String city = address.getCountryName();
				String region_code = address.getCountryCode();

				result.append(locality + " ");
				result.append(city + " " + region_code + " ");

			}
			else{
				Log.d("ADDRESS", "NOADDRESS");
			}
		} catch (IOException e) {
			Log.d("tag", e.getMessage());
		}

		return result.toString();
	}
}
