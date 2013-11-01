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
package com.example.energospolitis.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.energospolitis.db.DatabaseHandler;

@SuppressLint("UseSparseArrays")
/**
 * 
 * @author Andreas
 *	This class is the report list that is used at the report list activity
 */
public class ReportList {
	private static Context context;
	
	// Populate list with reports with the database. Note that this line may not be necessary when communicating with a remote server. 
	public ReportList(Context context){ this.context=context; populate();}
	
    public static List<Report> ITEMS  = new ArrayList<Report>();

    // Map reports with their Codes. 
    public static Map<Integer, Report>  ITEM_MAP= new HashMap<Integer, Report>();
      
    /**
     * Populate the list from database
     */
	public void populate() {
		// TODO Auto-generated method stub
		DatabaseHandler db = new DatabaseHandler(context); 
		ITEMS = db.getAllReports();
	    for(Report r : ITEMS){ ITEM_MAP.put(r.getCode(), r);}
	}

}
