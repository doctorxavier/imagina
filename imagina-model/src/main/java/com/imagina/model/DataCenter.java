package com.imagina.model;

public enum DataCenter {

	BARCELONA {
		public String toString() {
			return "bcn";
		}
	},

	MADRID {
		public String toString() {
			return "mad";
		}
	};
	
	public static String parse(int code) {
		switch(code) {
			case 1:
				return "bcn";
			case 2:
				return "mad";
			default:
				return "";
		}
	}
	
	public static int parse(String code) {
		switch(code) {
			case "bcn":
				return 1;
			case "mad":
				return 2;
			default:
				return -1;
		}
	}

}
