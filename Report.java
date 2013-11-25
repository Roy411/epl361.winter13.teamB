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
package com.example.energospolitis.classes;

/**
 * 
 * @author Andreas 
 * User Report Class
 */
public class Report {

	private String reported;
	private int code;
	private String fixed = "N";
	private String date;
	private int type;
	private String desc;
	private String location;
	private int rating;
	private int confirmed;

	public Report(int code, int type, String date, String location,
			String reported, String fixed, int confirmed, int rating,
			String desc) {
		// TODO Auto-generated constructor stub

		this.code = code;
		this.date = date;
		this.location = location;
		this.reported = reported;
		this.fixed = fixed;
		this.confirmed = confirmed;
		this.rating = rating;
		this.desc = desc;
		this.type=type;

	}

	public Report() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the reported
	 */
	public String getReported() {
		return reported;
	}

	/**
	 * @param reported
	 *            the reported to set
	 */
	public void setReported(String reported) {
		this.reported = reported;
	}

	/**
	 * @return the fixed
	 */
	public String getFixed() {
		return fixed;
	}

	/**
	 * @param fixed
	 *            the fixed to set
	 */
	public void setFixed(String f) {
		this.fixed = f;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return confirmed;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the confirmed
	 */
	public int getConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed
	 *            the confirmed to set
	 */
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public String toString() {
		return Integer.toString(getCode());

	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
