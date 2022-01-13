package dataCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import readData.contactData;
import readData.globalValues;

public class ReadCsv {

	// PropertyData pData = new PropertyData();
	String seperator = globalValues.getSeperator();
	String FileName = globalValues.getPropertyFileName();

	public List<contactData> getPropertyDataMultiMsg(File csvFile) {

		// File file = new
		// File("D:\\ProgramsData\\Github\\DeveloperCodes\\webautomation_gamca\\webautomation\\"+FileName);
		File file = csvFile;
		if (!file.exists()) {
			System.out.println("Property file not found");
			System.exit(0);
		}
		List<contactData> list = new ArrayList<contactData>();
		try {
			Scanner sc;
			String line, key, value;
			sc = new Scanner(file);

			while (sc.hasNext()) {
				line = sc.nextLine();
				if (!line.startsWith("#")  && !line.isEmpty()) {
					key = line.split(seperator)[0];
					if (!line.endsWith(seperator))
						value = line.split(seperator)[1];
					else
						value = "";

					contactData c = new contactData(key, value);
					list.add(c);

				}

			}
			sc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public List<contactData> getPropertyDataSingleMsg(File csvFile) {
		File file = csvFile;
		if (!file.exists()) {
			System.out.println("Property file not found");
			System.exit(0);
		}
		List<contactData> list = new ArrayList<contactData>();
		try {
			Scanner sc;
			String line;
			sc = new Scanner(file);

			while (sc.hasNext()) {
				line = sc.nextLine();
				if (!line.startsWith("#") && !line.isEmpty()) {

					contactData c = new contactData(line, "");
					list.add(c);

				}

			}
			sc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public String getMessage(File msgFile) {
		File file = msgFile;
		String finalMsg="";
		if (!file.exists()) {
			System.out.println("Property file not found");
			System.exit(0);
		}
		
		try {
			Scanner sc;
			String line;
			sc = new Scanner(file);

			
			while (sc.hasNext()) {
				line = sc.nextLine();
				finalMsg+=line;

			}
			sc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalMsg;
	}
}
