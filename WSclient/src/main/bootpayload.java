package webClient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class bootpayload {

	
		 private String currentTime;
		 private float interval;
		 private String status;


		 // Getter Methods 

		 public String getCurrentTime() {
		  return currentTime;
		 }

		 public float getInterval() {
		  return interval;
		 }

		 public String getStatus() {
		  return status;
		 }

		 // Setter Methods 

		 public void setCurrentTime(String currentTime) {
		  this.currentTime = currentTime;
		 }

		 public void setInterval(float interval) {
		  this.interval = interval;
		 }

		 public void setStatus(String status) {
		  this.status = status;
		 }
		}

