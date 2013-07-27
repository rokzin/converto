package com.rokzin.converto.storage;

import java.util.Date;

public class StoreItem{
		
		private Date date;
		private String value;

		public StoreItem(String value, Date date) {
			this.value = value;
			this.date = date;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
