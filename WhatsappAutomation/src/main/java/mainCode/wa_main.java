package mainCode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

import automationCode.wa_login;
import dataCollection.ReadCsv;
import readData.contactData;
import readData.globalValues;

public class wa_main {

	static WebDriver driver = null;
	static wa_login wa = new wa_login();
	static globalValues GV =new globalValues();
	static ReadCsv CSV=new ReadCsv();

	public static void main(String[] args) {
		boolean flagToRun = false;
		File csvFile = null,msgFile=null;
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("-r") && !args[1].isEmpty() ) {
				csvFile = new File(args[1]);
				if (csvFile.exists()) {
					sendMultiMsgMultiContacts(csvFile);
				}
			}
		}else if(args.length == 3) {
			if (args[0].equalsIgnoreCase("-r") && !args[1].isEmpty() && !args[2].isEmpty()) {
				csvFile = new File(args[1]);
				msgFile = new File(args[2]);
				if (csvFile.exists()) {
					sendSingleMsgMultiContacts(csvFile,msgFile);
				}
			}
		} else {
			System.out.println("Invalid arguments\nValid arguments is java -jar app.jar -r <path to csv>");
		}
		
		if (flagToRun) {
			

		}
	}

	private static void sendMultiMsgMultiContacts (File csvFile) {
		List<contactData> list = new ArrayList<contactData>();
		list = CSV.getPropertyDataMultiMsg(csvFile);
		if(list.size()<=0) {
			System.out.println("No list of messages found in csv file");
			System.exit(0);
		}
		
		shootMsgs(list);
	}
	
	private static void sendSingleMsgMultiContacts (File csvFile, File msgFile) {
		List<contactData> list = new ArrayList<contactData>();
		list = CSV.getPropertyDataSingleMsg(csvFile);
		String msg= CSV.getMessage(msgFile);
		if(list.size()<=0) {
			System.out.println("No contacts found in csv file");
			System.exit(0);
		}
		if(msg.isEmpty()) {
			System.out.println("No message found in txt file");
			System.exit(0);
		}
		for (contactData contactData : list) {
			contactData.setMessage(msg);
		}
		
		shootMsgs(list);
	}

	private static void shootMsgs(List<contactData> list) {
		System.out.println("Total Contacts found:"+list.size());
		driver = wa.wa_qrCode(GV.getWebURL());

		driver = wa.waitTillLoggedin(driver);

		int count=1;
		for (contactData contactData : list) {
			System.out.println("In Progress: "+(count++)+"/"+list.size());
			driver = wa.sendMessage(driver, contactData.getContactNumber(), contactData.getMessage()+"\n\n-sent via WhatsappAutomationTool\nDeveloped by Hakimi iTech\nContact:tahershabbiri@gmail.com");
			//System.out.println(contactData.getContactNumber()+"***"+ contactData.getMessage());
		}

		System.out.println("All messages sent.");
		driver.quit();
	}
}
