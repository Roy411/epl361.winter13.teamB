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
 * Handles the Report List View and calls Report Detail View when a report is selected
 */
public class ReportListActivity extends FragmentActivity
        implements ReportListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        
        
        
        if (findViewById(R.id.report_detail_container) != null) {
            mTwoPane = true;
            ((ReportListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.report_list))
                    .setActivateOnItemClick(true);
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
                    .replace(R.id.report_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, ReportDetailActivity.class);
            detailIntent.putExtra(ReportDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
