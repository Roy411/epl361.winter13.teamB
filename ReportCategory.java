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

import java.util.ArrayList;

/**
 * 
 * @author Andreas Report Categories
 */
public class ReportCategory {

	private int type;
	private String name;
	private int importance;

	public ReportCategory(int i, String string, int j) {
		// TODO Auto-generated constructor stub
		setType(i);
		setName(string);
		setImportance(j);
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the importance
	 */
	public int getImportance() {
		return importance;
	}

	/**
	 * @param importance
	 *            the importance to set
	 */
	public void setImportance(int importance) {
		this.importance = importance;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public static ArrayList<ReportCategory> Cats = new ArrayList<ReportCategory>();

	// Added Categories for reports. NOTE : this is only a prototype, categories
	// may change before the application release.
	static {
		Cats.add(new ReportCategory(1, "Παράνομο Παρκάρισμα", 1));
		Cats.add(new ReportCategory(2, "Φώτα Τροχαίας", 4));
		Cats.add(new ReportCategory(3, "Παρεμπόδιση κυκλοφορίας", 3));
		Cats.add(new ReportCategory(4, "Ατύχημα", 5));
		Cats.add(new ReportCategory(5, "Πρόβλημα σκυβάλων", 1));
	}
}
