import java.util.*;
import java.io.*;


abstract class person {
	Scanner Input = new Scanner(System.in);
	public int ID;
	public String name;
	public byte age;
	public String sex;
	public String nationality;
	public String phoneNumber;
	public byte ratePoint;
	public String favoritePlace;
	public int dayStaying;
	public byte typeOfRoom;

	public void input() {
		do {
			System.out.print("ID: ");
			this.ID = Input.nextInt();
			if (this.ID < 0)
				System.out.println("Invalid ID, type Again");
		} while (this.ID < 0);

		Input.nextLine();
		System.out.print("Name: ");
		this.name = Input.nextLine();

		do {
			System.out.print("Age: ");
			this.age = Input.nextByte();
			if (this.age < 0 || this.age > 100)
				System.out.println("Invalid, type again");
		} while (this.age < 0 || this.age > 100);

		Input.nextLine();
		System.out.print("Sex ( male / female): ");
		this.sex = Input.nextLine();

		System.out.print("Nationality: ");
		this.nationality = Input.nextLine();

		System.out.print("Favorite Place in DN: ");
		this.favoritePlace = Input.nextLine();

		System.out.print("PhoneNumber: ");
		this.phoneNumber = Input.nextLine();

		do {
			System.out.print(" So' ngay o:  ");
			this.dayStaying = Input.nextInt();
			if (this.dayStaying < 0 || this.dayStaying > 1000)
				System.out.println("Invalid, type again");
		} while (this.dayStaying < 0 || this.dayStaying > 1000);

		do {
			System.out.print("VIP or NORMAL ROOM (1.VIP - 2.Normal ): ");
			this.typeOfRoom = Input.nextByte();
			if (this.typeOfRoom < 1 || this.typeOfRoom > 2)
				System.out.println("Invalid, type again");
		} while (this.typeOfRoom < 1 || this.typeOfRoom > 2);

	}

	abstract public String typeOfTourist();

	abstract public float setDiscount();

	abstract public int calsPrice();

}

class domecticTourist extends person {
	public int serviceFee;
	public int roomID;

	public void input() {
		super.input();
		System.out.print("Service Fee: ");
		this.serviceFee = Input.nextInt();
		System.out.print("Room ID: ");
		this.roomID = Input.nextInt();
		do {
			System.out.print("Please rate me ? point(1-10): ");
			this.ratePoint = Input.nextByte();
			if (this.ratePoint <= 0 || this.ratePoint > 10)
				System.out.println("Invalid, type again");
		} while (this.ratePoint <= 0 || this.ratePoint > 10);

	}

	public String typeOfTourist() {
		return "Domectic";
	}

	public float setDiscount() {
		return (float) 0.85; // discount 15%
	}

	public int calsPrice() {
		if (this.typeOfRoom == 1)
			return (int) (this.serviceFee + (2000000 * this.dayStaying * this.setDiscount())); // Phong vip 2 trieu/ngay
																								// thuong` 1trieu/ngay
		else
			return (int) (this.serviceFee + (1000000 * this.dayStaying * this.setDiscount()));

	}

}

class foreignTourist extends person {
	public int roomID;
	public int serviceFee;
	public int securityFee;
	public int rentVehicleFee;

	public void input() {
		super.input();
		System.out.print("Room ID: ");
		this.roomID = Input.nextInt();
		do {
			System.out.print("Service Fee: ");
			this.serviceFee = Input.nextInt();
		} while (this.serviceFee < 0);
		this.serviceFee = 100000;
		this.rentVehicleFee = 200000;
		do {
			System.out.print("Please rate me ? point(1-10): ");
			this.ratePoint = Input.nextByte();
			if (this.ratePoint <= 0 || this.ratePoint > 10)
				System.out.println("Invalid, type again");
		} while (this.ratePoint <= 0 || this.ratePoint > 10);
	}

	public String typeOfTourist() {
		return "Foreign";
	}

	public float setDiscount() {
		return (float) 0.95; // discound 5%
	}

	public int calsPrice() {
		if (this.typeOfRoom == 1)
			return (int) (this.serviceFee + this.securityFee + this.rentVehicleFee
					+ (4000000 * this.dayStaying * this.setDiscount())); // Phong vip 4 trieu/ngay
																			// thuong` 2trieu/ngay
		else
			return (int) (this.serviceFee + this.securityFee + this.rentVehicleFee
					+ (2000000 * this.dayStaying * this.setDiscount()));

	}

}

class touristManage {

	person objTourist[] = new person[4];
	int n = 0;

	public void setInfor() throws IOException {

		do {
			char type = ' ';
			char opt = ' ';
			System.in.skip(type);
			System.in.skip(opt);
			System.out.print("DomesticTourist or ForeignTourist (D/F): ");
			type = (char) System.in.read();
			if (type == 'D' || type == 'd')
				objTourist[n] = new domecticTourist();
			else
				objTourist[n] = new foreignTourist();
			objTourist[n].input();
			n++;
			System.in.skip(type);
			System.in.skip(opt);
			System.out.print("Continue? (Y/N): ");
			opt = (char) System.in.read();
			if (opt == 'N' || opt == 'n') {
				break;
			}
		} while (true);
	}

	public void out() {
		for (int i = 0; i < n; i++) {
			System.out.printf("\n%-4d |%-12s |%-3d |%-6s |%-11s |%-7s |%-14s |%-10d |%-31s |%-15s |%-20d |%-10d ",
					objTourist[i].ID, objTourist[i].name, objTourist[i].age, objTourist[i].sex,
					objTourist[i].nationality, objTourist[i].phoneNumber, objTourist[i].favoritePlace,
					objTourist[i].dayStaying, objTourist[i].typeOfRoom, objTourist[i].typeOfTourist(),
					objTourist[i].calsPrice(), objTourist[i].ratePoint);
		}
	}

	public void getInfor() {
		System.out.printf("\n%-4s |%-12s |%-3s |%-6s |%-11s |%-7s |%-14s |%-10s |%-31s |%-15s |%-20s |%-10s", "ID",
				"NAME", "AGE", "SEX", "NATIONALITY", "PHONE", "FAVORITE PLACE", "DAY STAY",
				"TYPE OF ROOM(1:vip/2:normal)", "TYPE OF TOURIST", "PRICE", "RATE POINT");
		this.out();
	}

	public void sortByAge() {
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (objTourist[j].age < objTourist[i].age) {
					person temp = objTourist[j];
					objTourist[j] = objTourist[i];
					objTourist[i] = temp;
				}
			}
		}
		System.out.println("\n\nDanh Sach Sap Xep TANG DAN tuoi\n");
		getInfor();
	}

	public void sortByType() {
		String s1 = "Foreign";
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (objTourist[j].typeOfTourist().equals(s1)) {
					person temp = objTourist[j];
					objTourist[j] = objTourist[i];
					objTourist[i] = temp;
				}
			}
		}
		System.out.println("\n\nDanh Sach Sap Xep theo kieu khach\n");
		getInfor();
	}

	public void maxPrice() {
		int max = objTourist[0].calsPrice();
		int vt = 0;
		for (int i = 1; i < n; i++) {
			if (objTourist[i].calsPrice() > max) {
				vt = i;
			}
		}
		System.out.println("\n\nThong tin khach hang co tien phai tra cao nhat");
		System.out.printf("\n%-4s |%-12s |%-3s |%-6s |%-11s |%-7s |%-14s |%-10s |%-31s |%-15s |%-20s |%-10s", "ID",
				"NAME", "AGE", "SEX", "NATIONALITY", "PHONE", "FAVORITE PLACE", "DAY STAY",
				"TYPE OF ROOM(1:vip/2:normal)", "TYPE OF TOURIST", "PRICE", "RATE POINT");
		System.out.printf("\n%-4d |%-12s |%-3d |%-6s |%-11s |%-7s |%-14s |%-10d |%-31s |%-15s |%-20d |%-10d ",
				objTourist[vt].ID, objTourist[vt].name, objTourist[vt].age, objTourist[vt].sex,
				objTourist[vt].nationality, objTourist[vt].phoneNumber, objTourist[vt].favoritePlace,
				objTourist[vt].dayStaying, objTourist[vt].typeOfRoom, objTourist[vt].typeOfTourist(),
				objTourist[vt].calsPrice(), objTourist[vt].ratePoint);
	}

	public void findAtourist() {
		Scanner Input = new Scanner(System.in);
		int a, vt = 0, kt = 0;
		System.out.print("ID need to find (ID must be in List above) : ");
		a = Input.nextInt();
		for (int i = 0; i < n; i++) {
			if (objTourist[i].ID == a) {
				kt = 1;
				vt = i;
			}
		}
		if (kt != 1) {
			System.out.println("ID NOT FOUND ! ");
		} else {
			System.out.println("\n\nKhach hang co ID " + a);
			System.out.printf("\n%-4s |%-12s |%-3s |%-6s |%-11s |%-7s |%-14s |%-10s |%-31s |%-15s |%-20s |%-10s", "ID",
					"NAME", "AGE", "SEX", "NATIONALITY", "PHONE", "FAVORITE PLACE", "DAY STAY",
					"TYPE OF ROOM(1:vip/2:normal)", "TYPE OF TOURIST", "PRICE", "RATE POINT");
			System.out.printf("\n%-4d |%-12s |%-3d |%-6s |%-11s |%-7s |%-14s |%-10d |%-31s |%-15s |%-20d |%-10d ",
					objTourist[vt].ID, objTourist[vt].name, objTourist[vt].age, objTourist[vt].sex,
					objTourist[vt].nationality, objTourist[vt].phoneNumber, objTourist[vt].favoritePlace,
					objTourist[vt].dayStaying, objTourist[vt].typeOfRoom, objTourist[vt].typeOfTourist(),
					objTourist[vt].calsPrice(), objTourist[vt].ratePoint);
		}
	}

	public void countType() {
		int count = 0;
		String s1 = "Domectic";
		for (int i = 0; i < n; i++) {
			if (objTourist[i].typeOfTourist().equals(s1)) {
				count++;
			}
		}
		System.out.print("DOMECTIC TOURISTS: " + count + " ");
		System.out.print("\nFOREIGN TOURISTS:  " + (n - count));
	}

	public void menu() {
		System.out.println("--------------Option------------");
		System.out.println("1. Get information");
		System.out.println("2. sort by AGE ");
		System.out.println("3. sort by type of Tourist ");
		System.out.println("4. Max Price ");
		System.out.println("5. find a Tourist by ID ");
		System.out.println("6. count type of Tourist ");
	}

}

public class IndividualPro {

	public static void main(String[] args) throws IOException {
		Scanner Input = new Scanner(System.in);
		byte choose;
		char type = ' ';
		touristManage A = new touristManage();
		A.setInfor();
		do {
			A.menu();
			System.out.print("Please choose: ");
			choose = Input.nextByte();
			switch (choose) {
			case 1:
				A.getInfor();
				break;
			case 2:
				A.sortByAge();
				break;
			case 3:
				A.sortByType();
				break;
			case 4:
				A.maxPrice();
				break;
			case 5:
				A.findAtourist();
				break;
			case 6:
				A.countType();
				break;
			}
			System.in.skip(type);
			System.out.print("\nContinue choose ? (Y/N): ");
			type = (char) System.in.read();
			if (type == 'N' || type == 'n') {
				System.out.println("STOPPED ! THANK YOU FOR USING ");
				break;
			}
		} while (type == 'Y' || type == 'y');
	}

}
