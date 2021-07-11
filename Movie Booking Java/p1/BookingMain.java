package p1;
import p2.*;
import java.util.*;
import java.io.*;


class BookingMain{
	static void disp()throws IOException{
		int j=9,z=1;
		char c = 'a';
		FileInputStream fdisp = new FileInputStream("C:/Users/Dell/Desktop/OOP/p1/Shows.txt");
		while(c!='@'){
			c = (char)fdisp.read();
			if(c=='#'){
				char a[] = new char[10];
				int i =0;
				c = (char)fdisp.read();
				while(c!='*'){
					a[i] = c;
					i++;
					c = (char)fdisp.read();
				}
				String s = new String(a);
				System.out.print("\n ["+z+"] Enter Movie name for "+j+":00 to "+(j+3)+":00 show : "+s);
				z++;
				j+=3;
			}
		}
		fdisp.close();
	}
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		Movie m[] = new Movie[5];
		int f=1;
		System.out.print("\n[1]Admin\n[2]User\nYour Choice : ");
		int type = sc.nextInt();
		if(type==1){
			while(f==1){
				FileInputStream fin = new FileInputStream("C:/Users/Dell/Desktop/OOP/p1/Admin.txt");
				char[] uidf = new char[20];
				char[] pwdf = new char[20];
				int l=0;
				char c;
				c = (char)fin.read();
				uidf[l] = c;
				l++;
				c = (char)fin.read();
				while(c!='*'){
					uidf[l] = c;
					l++;
					c = (char)fin.read();
				}
				String s1 = new String(uidf,0,l);
				l=0;
				c = (char)fin.read();
				pwdf[l] = c;
				l++;
				c = (char)fin.read();
				while(c!='*'){
					pwdf[l] = c;
					l++;
					c = (char)fin.read();
				}
				String s2 = new String(pwdf,0,l);
				//System.out.print("\n"+s1+"\n"+s2);
				
				String temp = sc.nextLine();
				System.out.print("\nUSER ID  : ");
				String uid = sc.nextLine();
				System.out.print("\nPASSWORD : ");
				String pwd = sc.nextLine();
				if(uid.equals(s1) && pwd.equals(s2)){
					FileWriter fShows = new FileWriter("C:/Users/Dell/Desktop/OOP/p1/Shows.txt",false);
					int j=9;
					for(int i=0;i<5;i++){
			 			System.out.print("\nEnter Movie name for "+j+":00 to "+(j+3)+":00 show : ");
						String nm = sc.nextLine();
						fShows.write("#");
						fShows.write(nm);
						m[i] = new Movie(nm);
						f=0;
						fShows.write("*\n");
						j+=3;
					}
					fShows.write("@");
					fShows.close();
					for(int x=1;x<=5;x++){
						String mn = "C:/Users/Dell/Desktop/OOP/p1/m"+x+".txt";
						FileWriter mov = new FileWriter(mn,false);
						mov.write("");
						mov.close();
					}
				}
				else
					System.out.println("\n****Incorrect ID or pwd****");
				fin.close();
			}
		}
		if(type == 2){
			String tp = sc.nextLine();
			int j=9,l=0;
			char c = 'a';
			FileInputStream fdisp = new FileInputStream("C:/Users/Dell/Desktop/OOP/p1/Shows.txt");
			while(c!='@'){
				c = (char)fdisp.read();
				if(c=='#'){
					char a[] = new char[10];
					int i =0;
					c = (char)fdisp.read();
					while(c!='*'){
						a[i] = c;
						i++;
						c = (char)fdisp.read();
					}
					String s = new String(a);
					m[l] = new Movie(s);
					l++;
					//System.out.print("\nEnter Movie name for "+j+":00 to "+(j+3)+":00 show : "+s);
					j+=3;
				}
			}
			fdisp.close();
			int ch = 1;
			choice1 : while(ch!=4){
				int temp = 0;
				while(temp==0){
				try{
					System.out.print("\n\n1.Book Tickets\n2.Show Tickets\n3.Cancel Tickets\n4.EXIT\nYour Choice : ");
					ch=sc.nextInt();
					//temp=1;
					if(ch==4)
						break choice1;
				if(ch==1){
					//FileWriter fout = new FileWriter("p1");
					int p=0,tot=0;
					disp();
					System.out.print("\nYour Choice : ");
					int ch2 = sc.nextInt();
					System.out.print("\nEnter No. of tickets : ");
					int nt = sc.nextInt();
					System.out.print("\n\n1.Platinum\n2.Gold\n3.Silver\nYour Choice : ");
					int ch3 = sc.nextInt();
					m[ch2-1].showSeat(ch3);
					for(int i=0;i<nt;i++){
						System.out.print("\nSelect Row and Column no. : ");
						int row = sc.nextInt();
						int col = sc.nextInt();
						if((ch3 == 1 && row<5) || (ch3 == 2 && row<15 && row >4) || (ch3 == 3 && row<20 && row >14)){
							p = m[ch2-1].selectSeat(ch2,row,col);
							tot=tot+p;
						}
						else{
							System.out.print("****Invalid****");
							i--;
						}
					}
					System.out.print("\n TOTAL AMOUNT : "+tot+" Rs.");
				}
				if(ch==2){
					disp();
					System.out.print("\nYour Choice : ");
					int ch2 = sc.nextInt();
					m[ch2-1].showSeat(1);
					System.out.println();
					m[ch2-1].showSeat(2);
					System.out.println();
					m[ch2-1].showSeat(3);
				}
				if(ch==3){
					disp();
					System.out.print("\nSelect Movie : ");
					int ch2 = sc.nextInt();
					System.out.print("\nSelect Row and Column no. : ");
					int row = sc.nextInt();
					int col = sc.nextInt();
					m[ch2-1].cancel(ch2,row,col);
				}
				}catch(InputMismatchException e){
					System.out.println("\n****INVALID INPUT****");
				}
				String z = sc.nextLine();
				}
				temp=1;
				ch=1;
			}	
		}
	}
}