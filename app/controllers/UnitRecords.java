package controllers;

import java.util.HashMap;

/**
 * A Java program to store the transformation from an arbitrary unit to the SI unit
 * @author Chetan Raj Rupakheti
 */
public class UnitRecords {
	HashMap<String, String> unitStringRecord = new HashMap<String, String>();
	HashMap<String, Double> unitNumericRecord = new HashMap<String, Double>();
		
	/**
	 * This method stores the input unit string as key and SI unit string as value
	 */
	public void addCurrentStringRecords() {
		unitStringRecord.put("minute", "s");
		unitStringRecord.put("min", "s");
		unitStringRecord.put("hour", "s");
		unitStringRecord.put("h", "s");
		unitStringRecord.put("s", "s"); // SI to SI 
		unitStringRecord.put("day", "s");
		unitStringRecord.put("d", "s");
		unitStringRecord.put("degree", "rad");
		unitStringRecord.put("°", "rad");
		unitStringRecord.put("rad", "rad"); // SI to SI
		unitStringRecord.put("arcminute", "rad");
		unitStringRecord.put("\'", "rad");
		unitStringRecord.put("arcsecond", "rad");
		unitStringRecord.put("\"", "rad");
		unitStringRecord.put("hectare", "m\u00B2");
		unitStringRecord.put("ha", "m\u00B2");
		unitStringRecord.put("litre", "m\u00B3");
		unitStringRecord.put("l", "m\u00B3");
		unitStringRecord.put("tonne", "kg");
		unitStringRecord.put("t", "kg");
	}

	/**
	 * Stores the input unit string as key and SI unit conversion factor as value
	 */
	public void addCurrentunitNumericRecords() {	
		unitNumericRecord.put("minute", 60.0);
		unitNumericRecord.put("min", 60.0);
		unitNumericRecord.put("hour", 3600.0);
		unitNumericRecord.put("h", 3600.0);
		unitNumericRecord.put("day", 86400.0);
		unitNumericRecord.put("d", 86400.0);
		unitNumericRecord.put("degree", Math.PI / 180.0);
		unitNumericRecord.put("°", Math.PI / 180.0);
		unitNumericRecord.put("arcminute", Math.PI / 10800.0);
		unitNumericRecord.put("\'", Math.PI / 10800.0);
		unitNumericRecord.put("arcsecond", Math.PI / 64800.0);
		unitNumericRecord.put("\"", Math.PI / 64800.0);
		unitNumericRecord.put("hectare", 10000.0);
		unitNumericRecord.put("ha", 10000.0);
		unitNumericRecord.put("litre", 0.001);
		unitNumericRecord.put("l", 0.001);
		unitNumericRecord.put("tonne", 1000.0);
		unitNumericRecord.put("t", 1000.0);
	}

	/**
	 * Method if new records need to be added
	 * @param unit string in arbitrary unit
	 * @param siString string in SI unit
	 * @param siFactor conversion factor 
	 */
	public void addNewStringRecord(String unit, String siString, Double siFactor) {
		unitStringRecord.put(unit, siString);
		unitNumericRecord.put(unit, siFactor);
	}
}
