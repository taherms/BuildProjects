package mainCode;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;

import automationCode.wa_login;
import dataCollection.ReadCsv;
import firstTime.MacAddress;
import firstTime.sendMail;
import readData.contactData;
import readData.globalValues;

public class wa_main {

	static WebDriver driver = null;
	static wa_login wa = new wa_login();
	static globalValues GV = new globalValues();
	static ReadCsv CSV = new ReadCsv();

	private static boolean firstTime = false;

	public static void main(String[] args) {

		try {
			String macAddr = new MacAddress().getMac();
			int macAddrHash = macAddr.hashCode();
			// System.out.println(macAddrHash);
			File file = new File("./" + globalValues.getHashFile());
			if (file.exists()) {// lic present
				String licFromFile = "";

				// System.out.println("lic present");
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					// System.out.println(data);
					licFromFile = data;
				}
				myReader.close();

				if (licFromFile.equals(macAddrHash + "")) {// lic valid
					// System.out.println("lic valid");
					firstTime = false;
				} else {// lic invalid
					// System.out.println("lic invalid reseting lic");
					firstTime = true;
				}

			} else {
				// System.out.println("FirstTime");
				firstTime = true;

			}

			if (firstTime) {

				FileWriter licFile = new FileWriter(file);
				licFile.write("" + macAddrHash);
				licFile.close();
				// System.out.println("writing:"+macAddrHash);
				new sendMail().sendEmail("Whatsapp Automation tool was executed at New System",
						"This is auto mail sent to note that there was Whatsapp automation application was executed for first time on system: \n"
								+ macAddr + "\n at " + new Date().toString() + "\nMac Address:" + macAddrHash + "");
			}
		} catch (Exception e) {
			System.out.println("Error while checking lic or sending mail");
		}

		boolean flagToRun = false;
		File csvFile = null, msgFile = null;
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("-r") && !args[1].isEmpty()) {
				csvFile = new File(args[1]);
				if (csvFile.exists()) {
					sendMultiMsgMultiContacts(csvFile);
				}
			}
		} else if (args.length == 3) {
			if (args[0].equalsIgnoreCase("-r") && !args[1].isEmpty() && !args[2].isEmpty()) {
				csvFile = new File(args[1]);
				msgFile = new File(args[2]);
				if (csvFile.exists()) {
					sendSingleMsgMultiContacts(csvFile, msgFile);
				}
			}
		} else {
			System.out.println("Invalid arguments\nValid arguments is java -jar app.jar -r <path to csv>");
		}

		if (flagToRun) {

		}
	}

	private static void sendMultiMsgMultiContacts(File csvFile) {
		List<contactData> list = new ArrayList<contactData>();
		list = CSV.getPropertyDataMultiMsg(csvFile);
		if (list.size() <= 0) {
			System.out.println("No list of messages found in csv file");
			System.exit(0);
		}

		shootMsgs(list);
	}

	private static void sendSingleMsgMultiContacts(File csvFile, File msgFile) {
		List<contactData> list = new ArrayList<contactData>();
		list = CSV.getPropertyDataSingleMsg(csvFile);
		String msg = CSV.getMessage(msgFile);
		if (list.size() <= 0) {
			System.out.println("No contacts found in csv file");
			System.exit(0);
		}
		if (msg.isEmpty()) {
			System.out.println("No message found in txt file");
			System.exit(0);
		}
		for (contactData contactData : list) {
			contactData.setMessage(msg);
		}

		shootMsgs(list);
	}

	private static void shootMsgs(List<contactData> list) {
		System.out.println("Total Contacts found:" + list.size());
		driver = wa.wa_qrCode(GV.getWebURL());

		driver = wa.waitTillLoggedin(driver);

		int count = 1;
		for (contactData contactData : list) {
			System.out.println("In Progress: " + (count++) + "/" + list.size());
			driver = wa.sendMessage(driver, contactData.getContactNumber(), contactData.getMessage()
					+ "\n\n-sent via WhatsappAutomationTool\nDeveloped by Hakimi iTech\nContact:tahershabbiri@gmail.com");
			// System.out.println(contactData.getContactNumber()+"***"+
			// contactData.getMessage());
		}

		System.out.println("All messages sent.");
		driver.quit();
	}
}
