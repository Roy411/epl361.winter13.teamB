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
 * limitations under the License.
 *  **/

/**
 * @version 0.2
 * 
 */
package com.example.energospolitis;

import java.util.Collections;

import com.example.energospolitis.classes.ReportList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



/**
 * 
 * @author Andreas Handles the Report List View and calls Report Detail View
 *         when a report is selected
 */
public class ReportListActivity extends FragmentActivity implements
		ReportListFragment.Callbacks {

	private boolean mTwoPane;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_list);
		 
		    getActionBar().setDisplayHomeAsUpEnabled(true);
		    getActionBar().setDisplayShowHomeEnabled(false);		    
		    getActionBar().setDisplayShowTitleEnabled(true);
		if (findViewById(R.id.report_detail_container) != null) {
			mTwoPane = true;

			((ReportListFragment) getSupportFragmentManager().findFragmentById(
					R.id.report_list)).setActivateOnItemClick(true);
		}
	}

	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putString(ReportDetailFragment.ARG_ITEM_ID, id);
			ReportDetailFragment fragment = new ReportDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.report_detail_container, fragment).commit();

		} else {
			Intent detailIntent = new Intent(this, ReportDetailActivity.class);
			detailIntent.putExtra(ReportDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 0, "Ταξινόμηση");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 1:
			final Dialog dialog = new Dialog(ReportListActivity.this);
			dialog.setContentView(R.layout.customdialog);
			dialog.setTitle("Ταξινόμηση");
 
			Button recent = (Button) dialog.findViewById(R.id.recbtn);
			recent.setText("Πρόσφατα");
			Button type = (Button) dialog.findViewById(R.id.typebtn);
			type.setText("Τύπος");
			Button rate = (Button) dialog.findViewById(R.id.ratebtn);
			rate.setText("Βαθμολογία");
			
			recent.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ReportList.sort("recent");
					restart();
					dialog.dismiss();
				}
			});
			rate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ReportList.sort("rate");
					restart();
					dialog.dismiss();
				}


			});
			type.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ReportList.sort("type");
					dialog.dismiss();
				}
			});
 
			dialog.show();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}

		return true;
	}
	public void restart() {
		// TODO Auto-generated method stub
		finish();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        startActivity(new Intent(getBaseContext(), ReportListActivity.class));
	}
}
