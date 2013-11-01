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

/**
 * 
 * @author Andreas
 * Class for the user of the system. This class is NOT used in this prototype.
 */
public class User {

	private String mac;
	private int reports;
	private int validreports;
	private int no_conf;
	private int rating;
	private int remaining_reports;
	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * @param mac the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * @return the reports
	 */
	public int getReports() {
		return reports;
	}
	/**
	 * @param reports the reports to set
	 */
	public void setReports(int reports) {
		this.reports = reports;
	}
	/**
	 * @return the validreports
	 */
	public int getValidreports() {
		return validreports;
	}
	/**
	 * @param validreports the validreports to set
	 */
	public void setValidreports(int validreports) {
		this.validreports = validreports;
	}
	/**
	 * @return the no_conf
	 */
	public int getNo_conf() {
		return no_conf;
	}
	/**
	 * @param no_conf the no_conf to set
	 */
	public void setNo_conf(int no_conf) {
		this.no_conf = no_conf;
	}
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @return the remaining_reports
	 */
	public int getRemaining_reports() {
		return remaining_reports;
	}
	/**
	 * @param remaining_reports the remaining_reports to set
	 */
	public void setRemaining_reports(int remaining_reports) {
		this.remaining_reports = remaining_reports;
	}
}
