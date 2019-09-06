import java.util.Scanner;
public class VendingMachine {
    int count = 0;
    int sales = 0;
    int amount = 0;
    Scanner scan = new Scanner(System.in);
    public void pay() {
        String str = welcome();
        while(!str.equals("exit")) {
            int i;
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException nfex) {
                System.out.println("Please put money.");
                str = welcome();
                continue;
            }
            System.out.println(str + " yen input");
            getMoney(i);

            if(amount < 100){
                System.out.println("Add money or Cancel?(a/c)");
                str = scan.nextLine();
                if(str.equals("a")) {
                    continue;
                } else if(str.equals("c")) {
                    returnMoney();
                    str = welcome();
                    continue;
                } else {
                    System.out.println("a or c ?");
                    continue;
                }
                /*addOrCancel();
                continue;*/
                
            }else if(amount >= 100){

                if(!buyOrCancel()){
                    continue;   
                }
            }

            outputOnigiri();
            System.out.println("sales = " + sales + " yen");
            System.out.println("times = " + count);
            str = welcome();
        }

        scan.close();
    }
    public void getMoney(int money){
        amount += money;
        System.out.println("I have " + amount + " yen");
    }
    public int returnMoney(){
        int temp = amount;
        amount = 0;
        System.out.println("Return " + temp + " yen. Please put 100 yen!");
        return temp;
    }
    public void outputOnigiri() {
        System.out.println("Onigiri output");
        count++;
        sales += amount;
        amount = 0;
    }
    public String welcome() {
        System.out.println("If you spend 100 yen, you can get onigiri!");
        String str = scan.nextLine();
        return str;
    }

    public boolean addOrCancel(){
        while(true){
            System.out.println("Add money or Cancel?(a/c)");
            String str = scan.nextLine();
            if(str.equals("a")) {
                return true;
            } else if(str.equals("c")) {
                returnMoney();
                str = welcome();
                return false;
            } else {
                System.out.println("a or c ?");
            }
        }
    }

    public boolean buyOrCancel(){
        while(true){
                System.out.println("Buy or Cancel?(b/c)");
                String str = scan.nextLine();
                if(str.equals("b")) {
                    //outputOnigiri();
                    //continue;
                    return true;
                } else if(str.equals("c")) {
                    returnMoney();
                    str = welcome();
                    return false;
                } else {
                    System.out.println("b or c ?");
                }
         }
    }
}
