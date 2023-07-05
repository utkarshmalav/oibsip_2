//Oasis Infobyte (Java Intern) Task 2 : ATM interface.

import java.lang.Math;   
import java.util.*;

public class Task2 extends system
{
    static double data[][]=new double[100][100];
    static String name[]=new String[100];
    static Scanner s=new Scanner(System.in);
    system stm=new system();
    public static void main(String args[]) throws InterruptedException
    {
        clear();
        int ch=1;

        do
        {
            ch=start();
            switch(ch)
            {
                case 1:
                    register();
                    Thread.sleep(3000);
                    clear();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    clear();
                    System.out.print("\n\n\tYou have choosen to Report an issue \n\n\n\tTo report issue regarding ATM");
                    Thread.sleep(500);
                    System.out.print("\n\n\tPlease call on 7276663836\n\tEmail : ubmbank@gmail.com");
                    Thread.sleep(3000);
                    clear();
                    break;

            }
        }while(ch!=0);
    }

    static void clear()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    static int start() throws InterruptedException
    {
        String bankname="Welcome to U.B.M Atm";
        System.out.print("\n\n\t\t\t\t\t");

        for(int i=0;i<=19;i++)
        {
            System.out.print(bankname.charAt(i));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(500);

        System.out.print("\n\n\n\n\t\tNew user press '1'");
        System.out.print("\n\n\t\tExisting user press '2'");
        System.out.print("\n\n\t\tTo report an issue press '3'");
        System.out.print("\n\n\n\t\tEnter Your choice : ");
        int choice=s.nextInt();
        return choice;
    }

    static void register() throws InterruptedException
    {
        system stm=new system();
        clear();

        System.out.print("\n\n\n\t\t\t\tWe Welcome you to our ATM.\n\n\tEnter your name : ");
        String waste=s.nextLine();
        String username=s.nextLine();
        System.out.print(waste+"\n\n\tCreate User id :  ");
        int userid=s.nextInt();
        System.out.print("\n\n\tEnter 4 Digit PIN : ");
        double pin=s.nextInt();
        stm.verify();
        System.out.print("\n\n\t\tYou have successfully Registered!\n\n\t\tRedirecting to home page.");
        data[userid][1]=pin;
        name[userid]=username;
    }

    static void login() throws InterruptedException
    {
        clear();
        System.out.print("\n\n\n\t\t\t\tEnter your User Id : ");
        int userid=s.nextInt();
        if(data[userid][1]==0)
        {
            System.out.print("\n\n\tAccount Does not exist. Please try again.\n\n\n");
            Thread.sleep(3000);
            clear();
        }

        else
        {
            System.out.print("\n\n\t\t\t\tEnter 4 Digit PIN : ");
            double pin=s.nextInt();
        
        if(data[userid][1]==pin)
        {
            int ch2=1,f=0;
            System.out.print("\n\n\t\tWelcome Mr "+name[userid]+" to our Atm.");

            while(ch2!=0)
            {
            System.out.print("\n\n\n\t\tFor Withdraw press '1'\n\n\t\tFor Deposite press '2'\n\n\t\tFor Balance Check press '3'\n\n\t\tFor transfer press '4'");
            System.out.print("\n\n\t\tFor transcation History press '5'\n\n\t\tTo Exit press '0'\n\n\t\tEnter your choice : ");
            ch2=s.nextInt();

            switch(ch2)
            {
                case 1:
                    clear();
                    System.out.print("\n\n\n\n\tYou have choosen to Withdraw");
                    Thread.sleep(500);
                    System.out.print("\n\n\tEnter amount to withraw : ");
                    int amount=s.nextInt();
                    if(amount>data[userid][0])
                    {
                        System.out.print("\n\n\tWithdraw Unsuccessful, Insufficient Balance");
                        Thread.sleep(3000);
                        clear();
                    }
                    else
                    {
                        System.out.print("\n\n\tWithdraw Successful of amount "+amount+"/-");
                        data[userid][0]-=amount;
                        Thread.sleep(3000);

                        for(int i=10;i>2;i--)
                        {
                            data[userid][i]=data[userid][i-1];
                        }
                        amount*=-1;
                        data[userid][2]=amount;
                        clear();
                    }
                    break;

                case 2:
                clear();
                System.out.print("\n\n\n\n\tYou have choosen to Deposite");
                Thread.sleep(500);
                System.out.print("\n\n\tEnter amount to Deposite : ");
                amount=s.nextInt();
                if(amount<=0)
                {
                    System.out.print("\n\n\tDeposite Unsuccessful, Enter valid amount");
                    Thread.sleep(3000);
                    clear();
                }
                else
                {
                    data[userid][0]=data[userid][0]+amount;
                    System.out.print("\n\n\tDeposite Successful of amount "+amount+"/-");
                    Thread.sleep(3000);
                    for(int i=10;i>2;i--)
                        {
                            data[userid][i]=data[userid][i-1];
                        }
                        data[userid][2]=amount;
                    clear();
                }
                    break;

                case 3:
                    clear();
                    System.out.print("\n\n\tYour Current Balance : "+data[userid][0]);
                    Thread.sleep(3000);
                    clear();
                    break;

                case 4:
                    clear();
                    System.out.print("\n\n\tYou have choosen to Transfer");
                    Thread.sleep(500);
                    f=0;
                    while(f!=1)
                    {
                        System.out.print("\n\n\tUser Id of Reciver :  ");
                        int sent=s.nextInt();
                        if(data[sent][1]==0)
                        {
                            System.out.print("\n\n\tSuch account does not exist, Please check user id");
                        }
                        else
                        {
                            f=1;
                            System.out.print("\n\n\tEnter an amount to transfer : ");
                            amount=s.nextInt();
                            if(amount<=data[userid][0])
                            {
                                System.out.print("\n\n\tYou have Successfully Transfered "+amount+" to userid "+sent+" to Mr "+name[sent]);
                                data[sent][0]+=amount;
                                data[userid][0]-=amount;
                                for(int i=10;i>2;i--)
                                {
                                    data[userid][i]=data[userid][i-1];
                                    data[sent][i]=data[sent][i-1];

                                }
                                
                                data[sent][2]=amount;
                                amount*=-1;
                                data[userid][2]=amount;
                            }
                            else
                            {
                                System.out.print("\n\n\tUnsucessfull, You have Insufficient balance");
                            }
                        }
                    }
                    Thread.sleep(3000);
                    clear();
                    break;
                
                case 5:
                    clear();
                    System.out.print("\n\n\tTransactions history\n");
                    Thread.sleep(500);
                    for(int i=10;i>=2;i--)
                    {
                        if(data[userid][i]!=0)
                        {
                            if(data[userid][i]>0)
                            System.out.println("\n\tDeposited : "+data[userid][i]+"/-");

                            else if(data[userid][i]<0)
                            {
                                double num=data[userid][i]*-1;
                                System.out.println("\n\tWithdrawed : "+num+"/-");
                            }
                        }
                    }
                    Thread.sleep(3000);
                    clear();
                    break;

                case 0:
                    clear();
                    System.out.print("\n\n\tThankyou for visiting! Have a good day");
                    Thread.sleep(3000);
                    clear();
                    break;
            }
        }
        }
        else
        {
            System.out.print("\n\n\t\tWrong details, please try again!");
            Thread.sleep(3000);
            clear();
        }
    }
    }
}

class system
{
    
    Scanner sc=new Scanner(System.in);
    void verify() throws InterruptedException
    {
        int f=0;
        System.out.print("\n\n\tVerify you are not an robot.");
        while(f==0)
        {
            double num1= Math.random()*(20-10+1)+5;
            double num2= Math.random()*(10-5+1)+5;
    
            int number1 = (int)num1;
            int number2 = (int)num2;
            
            if(number1>number2)
            {
                System.out.print("\n\n\t"+number1+" - "+number2+" = ");
                int ans=sc.nextInt();
                if(ans==number1-number2)
                {
                    f=1;
                    System.out.print("\n\n\tVerified, You are not an Robot.\n\n\t\t");
                }
            }
            else
            {
                System.out.print("\n\n\t"+number1+" + "+number2+" = ");
                int ans=sc.nextInt();
                if(ans==number1+number2)
                {
                    f=1;
                    System.out.print("\n\n\tVerified, You are not an Robot.\n\n\t\t");
                }
            }
        }
        Thread.sleep(500);
        String load="Loading...";
        
        for(int i=0;i<=9;i++)
        {
            System.out.print(load.charAt(i));
            Thread.sleep(100);
            if(i<=7) 
            Thread.sleep(50);
        }
        Thread.sleep(1000); 
    }
}