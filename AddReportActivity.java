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



import com.example.energospolitis.classes.Report;
import com.example.energospolitis.classes.ReportCategory;
import com.example.energospolitis.db.DatabaseHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
 * The User fills a report form with the basic info about a report and submits it to the database.
 * **NOTE** This is just a prototype of the application. Some functions such as getLocation() and getId() are not fully implemented yet.
 * For the purpose of this prototype those values are inserted manually.
 */
public class AddReportActivity extends Activity implements OnItemSelectedListener {
	String category="";
	int cat=0;
	String[] categories;
	String Location="";
	String ID="THE ID";
	Spinner typespin;
	
	public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
     setContentView(R.layout.addreportform);
     //Report Description Textview
     final TextView Desc = (TextView) findViewById(R.id.desctxt);
     // Submit button
     Button ok = (Button) findViewById(R.id.ok);
     // Dropdown list with the available categories
     // ** NOTE ** not all categories are included in this prototype
     typespin = (Spinner) findViewById(R.id.typesp);
     typespin.setOnItemSelectedListener(this);
     
     ok.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// get the location of the user
				getLocation();
				// get the id of the user
				getId();
				// open database handler to add report to database **SEE DB HANDLER CLASS**
				DatabaseHandler db = new DatabaseHandler(getApplicationContext());
				Log.d("ID", ID);

				Report r = new Report(0,cat,"30/10/2013","Location","User","N",1,1,Desc.getText().toString());
				
				db.addReport(r);
				db.close();
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		        AddReportActivity.this.startActivity(intent);
			}
			//** NOT IMPLEMENTED YET **//
			private void getId() {
				// TODO Auto-generated method stub

				
			}
			//** NOT IMPLEMENTED YET **//
			private void getLocation() {
				// TODO Auto-generated method stub
			}
     });
     


     categories=new String[ReportCategory.Cats.size()] ;
     int i=0;
     
     for(ReportCategory r : ReportCategory.Cats){ categories[i]=r.getName(); i++;};
     
	ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categories);

     a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     typespin.setAdapter(a);
   
 }
	/**
	 * On item selection from spinner.
	 */
	public void onItemSelected(AdapterView<?> parent, View v, int position,
         long id) {
     category=categories[position];
     cat=position;
 }
	/**
	 * When nothing selected. The "" is set by default.
	 */
	public void onNothingSelected(AdapterView<?> parent) {
     category="";
    // cat=-1;
 }
     
	}

