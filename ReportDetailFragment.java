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
import com.example.energospolitis.classes.ReportList;
import com.example.energospolitis.db.DatabaseHandler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Andreas
 * Form that is filled after the User selects a report from the list.
 * The form is filled with the selected report details.
 */
public class ReportDetailFragment extends Fragment {

	DatabaseHandler db ;
    public static final String ARG_ITEM_ID = "item_id";

    Report mItem;

    public ReportDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = ReportList.ITEM_MAP.get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));
            
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_report_detail, container, false);
        if (mItem != null) {
        	// Declare components
            ((TextView) rootView.findViewById(R.id.fr_date)).setText("Ημ. : "+mItem.getDate().toString());
            ((TextView) rootView.findViewById(R.id.fr_location)).setText("Τοποθεσία: "+mItem.getLocation());
            ((TextView) rootView.findViewById(R.id.fr_type)).setText("Τύπος: "+ Integer.toString(mItem.getType()));
            ((TextView) rootView.findViewById(R.id.fr_desc)).setText("Περιγραφή: \n"+ mItem.getDesc());
            ((TextView) rootView.findViewById(R.id.fr_confirmed)).setText("Επικυρώθηκε: "+Integer.toString(mItem.getConfirmed()));
            
        	final Button frconf = (Button) rootView.findViewById(R.id.fr_conf_btn);
        	final Button frfixed = (Button) rootView.findViewById(R.id.fr_fix_btn);
        	final Button frinvalid = (Button) rootView.findViewById(R.id.fr_inv_btn);
        	
        	// Confirm Report
        	frconf.setOnClickListener(new Button.OnClickListener() {
    			public void onClick(View v) {
    				
    				mItem.setConfirmed(mItem.getConfirmed()+1);
    				db = new DatabaseHandler(getActivity());
    				db.updateReport(mItem);
    				db.close();
    				((TextView) rootView.findViewById(R.id.fr_confirmed)).setText("Επικυρώθηκε: "+Integer.toString(mItem.getConfirmed()));
    				Toast.makeText(getActivity(), "Η αναφορά έχει επικυρωθεί", Toast.LENGTH_SHORT).show();
    				frconf.setClickable(false);
    				frinvalid.setClickable(false);
    				
    			}
            });
        	//  Report as fixed
        	frfixed.setOnClickListener(new Button.OnClickListener() {
    			public void onClick(View v) {
    				mItem.setFixed("Y");
    				Log.d("Fixed",mItem.getCode()+"");
    				db = new DatabaseHandler(getActivity());
    				db.updateReport(mItem);
    				db.close();
    				
    			}
            });
        	// Report as invalid
        	frinvalid.setOnClickListener(new Button.OnClickListener() {
    			public void onClick(View v) {
    				mItem.setConfirmed(mItem.getConfirmed()-1);
    				db = new DatabaseHandler(getActivity());
    				db.updateReport(mItem);
    				db.close();
    				((TextView) rootView.findViewById(R.id.fr_confirmed)).setText("Επικυρώθηκε: "+Integer.toString(mItem.getConfirmed()));
    				frconf.setClickable(false);
    				frinvalid.setClickable(false);
    				
    				
    			}
            });
        }
        return rootView;
    }
}
