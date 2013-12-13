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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;


/**
 * 
 * @author Andreas 
 * 			Calls the second fragment of the screen with the report
 *         details when a users clicks on a report from the list
 */
public class ReportDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_detail);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			Bundle arguments = new Bundle();
			arguments.putString(ReportDetailFragment.ARG_ITEM_ID, getIntent()
					.getStringExtra(ReportDetailFragment.ARG_ITEM_ID));
			ReportDetailFragment fragment = new ReportDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.report_detail_container, fragment).commit();
		}
		

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			NavUtils.navigateUpTo(this, new Intent(this,
					ReportListActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
