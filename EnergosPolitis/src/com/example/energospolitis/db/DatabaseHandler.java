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
package com.example.energospolitis.db;

import java.util.ArrayList;
import java.util.List;

import com.example.energospolitis.classes.Report;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author Andreas 
 *         Database class to store reports for the prototype
 *         application. Note that the application will communicating with a
 *         remote server through PHP and SQL queries thus this class will not
 *         be needed. Its is only used for the prototype.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "reportManager";

	// Reports table name
	private static final String TABLE_REPORT = "reports";

	// Reports Table Columns names
	private static final String KEY_CODE = "code";
	private static final String KEY_TYPE = "type";
	private static final String KEY_DATE = "date";
	private static final String KEY_LOCATION = "location";
	private static final String KEY_REPORTED = "reportedby";
	private static final String KEY_FIXED = "fixed";
	private static final String KEY_CONFIRMED = "confirmed";
	private static final String KEY_RATING = "rating";
	private static final String KEY_DESCRIPTION = "description";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_REPORT_TABLE = "CREATE TABLE " + TABLE_REPORT + "("
				+ KEY_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TYPE
				+ " INTEGER , " + KEY_DATE + " TEXT , " + KEY_LOCATION
				+ " TEXT , " + KEY_REPORTED + " TEXT , " + KEY_FIXED
				+ " TEXT, " + KEY_CONFIRMED + " INTEGER, " + KEY_RATING
				+ " INTEGER, " + KEY_DESCRIPTION + " TEXT  " + ");";
		db.execSQL(CREATE_REPORT_TABLE);
	}

	// Upgrading database
	// not complete
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new report
	public void addReport(Report reporttoadd) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_TYPE, reporttoadd.getType());
		values.put(KEY_DATE, reporttoadd.getDate().toString());
		values.put(KEY_LOCATION, reporttoadd.getLocation());
		values.put(KEY_REPORTED, reporttoadd.getReported());
		values.put(KEY_FIXED, reporttoadd.getFixed());
		values.put(KEY_CONFIRMED, reporttoadd.getConfirmed());
		values.put(KEY_RATING, reporttoadd.getRating());
		values.put(KEY_DESCRIPTION, reporttoadd.getDesc());
		// Inserting Row
		Log.d("INSERTION ROW", db.insert(TABLE_REPORT, null, values) + "");
		db.close(); // Closing database connection
	}

	// Getting single report
	Report getReport(String report) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db
				.query(TABLE_REPORT, new String[] { KEY_CODE, KEY_TYPE,
						KEY_DATE, KEY_LOCATION, KEY_REPORTED, KEY_FIXED,
						KEY_CONFIRMED, KEY_RATING, KEY_DESCRIPTION }, KEY_CODE
						+ "=?", new String[] { String.valueOf(report) }, null,
						null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Report requested_report = new Report(cursor.getInt(0),
				cursor.getInt(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getInt(6),
				cursor.getInt(7), cursor.getString(8));
		// return report
		cursor.close();
		db.close();
		return requested_report;
	}

	// Getting All Reports
	public List<Report> getAllReports() {
		List<Report> reportlist = new ArrayList<Report>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_REPORT;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list

		if (cursor.moveToFirst()) {
			do {
				if (cursor.getString(5).compareToIgnoreCase("Y") == 0)
					continue;

				Report nextreport = new Report();
				nextreport.setCode(cursor.getInt(0));
				nextreport.setType(cursor.getInt(1));
				nextreport.setDate(cursor.getString(2));
				nextreport.setLocation(cursor.getString(3));
				nextreport.setReported(cursor.getString(4));
				nextreport.setFixed(cursor.getString(5));
				nextreport.setConfirmed(cursor.getInt(6));
				nextreport.setRating(cursor.getInt(7));
				nextreport.setDesc(cursor.getString(8));

				// Adding contact to list
				reportlist.add(nextreport);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		// return contact list
		return reportlist;
	}

	// Updating single report
	public int updateReport(Report reporttoadd) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, reporttoadd.getType());
		values.put(KEY_DATE, reporttoadd.getDate().toString());
		values.put(KEY_LOCATION, reporttoadd.getLocation());
		values.put(KEY_REPORTED, reporttoadd.getReported());
		values.put(KEY_FIXED, reporttoadd.getFixed());
		values.put(KEY_CONFIRMED, reporttoadd.getConfirmed());
		values.put(KEY_RATING, reporttoadd.getRating());
		values.put(KEY_DESCRIPTION, reporttoadd.getDesc());

		// updating row
		return db.update(TABLE_REPORT, values, KEY_CODE + "=?",
				new String[] { String.valueOf(reporttoadd.getCode()) });
	}

	// Deleting single report
	public void deleteReport(Report r) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_REPORT, KEY_DATE + "=?",
				new String[] { String.valueOf(r.getCode()) });
		db.close();
	}

	// Getting reports Count
	public int getReportsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_REPORT;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}