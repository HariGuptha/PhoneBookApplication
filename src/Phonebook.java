import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Phonebook {
	TreeMap<String, Contact> contactlist = new TreeMap<>();
	boolean execute,namecontains;
	Contact c;
	Integer input;
	String uname, choice, userchoice,rawnumber;

	Scanner s = new Scanner(System.in);

	void add() {

		System.out.println("Enter the name");
		uname = s.next();
		
		if (!uname.equals("")) {
		
			namecontains = contactlist.containsKey(uname);
			
			if (namecontains == false) {
				c = new Contact();
				c.setName(uname);
				phonenumber();
			
			}
			else {
				System.out.println("Name already exists add another name");
				add();
			}
			
		} else {
			System.out.println("Enter a valid name to store");
			add();
		}
	}

	void phonenumber() {

			if(execute)
				{
				System.out.println("Enter the number ");
				
				String rawnumber = s.next();

				if (rawnumber.length() == 10) {

					c.setMobile(rawnumber);
					contactlist.put(uname, c);
					System.out.println("Added Succesfully\n\nTo add another number press y:");
					String y=s.next();
					if(y.equals("y")){
						phonenumber();	
					}
					
					else{
					execute=false;
					phonenumber();
					
					}
				}
				
				else {
					System.out.println("Enter a valid number\n\n");
					execute = true;
					phonenumber();
				}
				}
			
			else{
				menu();
			}

		} 
		
	

	void update() {

		if(execute){
			System.out.println("Enter the contact name to be updated");
			uname = s.next();
			boolean namecontain = contactlist.containsKey(uname);
			if (namecontain == true) {
				System.out.println(contactlist.get(uname).getName());
				System.out.println(contactlist.get(uname).getMobile());

				System.out.println("\nUpdate Contact by\n\n1.Change name\n2.Change number\n3.Add new number");
				System.out.println("Enter the above choice to modify");
				int i = s.nextInt();

				switch (i) {

				case 1:
					updatename();
					break;

				case 2:

					updatenumber();
					break;

				case 3:

					addnumber();
					break;

				default:
					System.out.println("Ënter a valid choice");
					update();

				}
			} else {
				System.out.println("\nEnter the correct name to be updated");
				choice();
				update();
			}
		} 
		else{
			menu();
		}
	}
	

	void updatename() {
		System.out.println("\nEnter the name to be updated");
		String changename = s.next();
		Contact temp = new Contact();
		boolean containname = contactlist.containsKey(uname);
		if (containname == true) {

			temp.setName(changename);

			for (String number : contactlist.get(uname).getMobile()) {
				temp.setMobile(number);
			}

			contactlist.remove(uname);
			contactlist.put(changename, temp);

			System.out.println("Contact updated successfully");
			display();
			choice();
			update();
		} else {
			System.out.println("Enter the correct name to modify");
			updatename();
		}
	}

	void updatenumber() {

		HashMap<Integer, Integer> indexRegistry = new HashMap<>();
		int sno = 1;
		int index = 0;
		for (String number : contactlist.get(uname).getMobile()) {
			System.out.println(sno + " " + number);
			indexRegistry.put(sno, index);
			sno++;
			index++;
		}

		System.out.println("Enter the choice of the number to be changed");
		int choicenumber = s.nextInt();

		System.out.println("Enter the new number");
		String newnumber = s.next();
		if (newnumber.length() == 10) {
			index = indexRegistry.get(choicenumber);

			contactlist.get(uname).getMobile().remove(index);
			contactlist.get(uname).getMobile().add(newnumber);

			System.out.println(" Contact Updated successfully");
			display();
			choice();
			update();

		}
		indexRegistry.clear();
	}

	void addnumber() {
		if(execute) {
			System.out.println("Enter the number to be added to the contact");

			String newNumber = s.next();

			if (newNumber.length() == 10) {
				contactlist.get(uname).setMobile(newNumber);
				System.out.println("Contact updated succesfully");
				display();

			}

			else {
				System.out.println("Add a valid number");
				execute = true;
			}

						
			choice();
			update();

		} 
		else{
			menu();
		}
	}

	void delete() {

		if(execute) {

			System.out.println("Enter the contact name to be deleted");
			String deletename = s.next();
			boolean isContains = contactlist.containsKey(deletename);
			if (isContains == true) {
				contactlist.remove(deletename);
				System.out.println("contact removed Successfully");
				choice();
				delete();

			} 
			else {

				System.out.println("Type the correct name to delete");
				choice();
				delete();

			}

		} 
		else{
			menu();
		}

	}

	void display() {
		
				
		if (!contactlist.isEmpty()) {
			Set<Map.Entry<String, Contact>> m = contactlist.entrySet();

			System.out.println("	Name               Number");
			int i = 1;

			for (Map.Entry<String, Contact> entry : m) {
				System.out.println(i + "	  " + entry.getKey() + "           " + entry.getValue().getMobile());
				i++;
			}
				menu();
		} else {
			System.out.println("No contacts found");
			menu();
		}
	}

	void search() {
		
		if(execute){
			System.out.println("Enter the name to search");
			uname = s.next();
			boolean isContains = contactlist.containsKey(uname);
			if (isContains == true) {

				System.out.println("\n" + contactlist.get(uname).getName() + "  " + contactlist.get(uname).getMobile());
				
				choice();
				search();

			} else {
				System.out.println("Enter a correct name to be searched");
				choice();
				search();
			}}
		else{
			menu();
		}

	}

	void first()
	{
		execute=true;
	}
	
	
	void choice() {
		System.out.println("Do you wish to continue press y and other keys to exit:");
		choice = s.next();
		if (choice.equalsIgnoreCase("y")) {
			execute = true;
		} else {
			execute = false;
		}
	}

	void menu() {

		
			System.out.println("\nPhone Book");
			System.out.println(
					"1.Add contact\n2.update contact\n3.Delete contact\n4.Display contact\n5.Search contact\n6.Exit");
			input = s.nextInt();

			first();
			switch (input) {
			case 1: {
				
				add();
				break;
			}
			case 2: {
				update();
				break;
			}
			case 3: {
				delete();
				break;
			}

			case 4: {

				display();
				break;

			}

			case 5: {
				search();
				break;
			}

			case 6: {
				System.out.println("Thanks for using");
				System.exit(0);
			}

			default:
				System.out.println("Enter a valid choice");

			}


	}

}
