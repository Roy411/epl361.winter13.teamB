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

import com.example.energospolitis.classes.ReportList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * 
 * @author Andreas
 * The user can choose to add a new report or view a list of reports.
 */
public class MainActivity extends Activity {

	//declare components
	private Button btnaddreport,btnviewreport;
	static ReportList L;
	public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        L= new ReportList(getApplicationContext());
        MainActivity.L.populate();
        //get  component view
        btnaddreport = (Button) findViewById(R.id.btnaddreport);
        btnviewreport = (Button)findViewById(R.id.btnviewreport);
        
        
        
        btnaddreport.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), AddReportActivity.class);
		        MainActivity.this.startActivity(intent);
		        MainActivity.L.populate();
			}
        });
        
        btnviewreport.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				ReportList L = new ReportList(getApplicationContext());
		        Intent intent = new Intent(getApplicationContext(), ReportListActivity.class);
		        MainActivity.this.startActivity(intent);
			}
        });
        
        
}
}
