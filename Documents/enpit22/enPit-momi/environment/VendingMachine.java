import java.util.Scanner;
public class VendingMachine {
   int count = 0;
   int sales = 0;
   int amount = 0;
   Scanner scan = new Scanner(System.in);
   String inputData="";
   Content nowContent;
   
   public void pay() {
       inputData = welcome(true);
       while(!inputData.equals("exit")) {
           int i;
           if(inputData.equals("admin")){
               System.out.println("あなたは管理者ですか？(Y/n)");
               
               inputData = scan.nextLine();
               if(inputData.equals("y")) {
                   System.out.println("パスワードを入力してください.");
                   inputData = scan.nextLine();
                   if(inputData.equals("daigo")) {
                       System.out.println("売上金額：" + sales + "円");
                       System.out.println("売上個数：" + count + "個");
                   }else{
                       System.out.println("パスワードが間違っています.");
                   }
                   inputData = welcome(true);
                   continue;
               }else if(inputData.equals("n")) {
                   inputData = welcome(true);
                   continue;
               }else{
                   System.out.println("yまたはnを入力してください.");
               }
           }else{
               try {
                   i = Integer.parseInt(inputData);
               } catch (NumberFormatException nfex) {
                   System.out.println("お金を入れてください");
                   inputData = welcome(false);
                   continue;
               }
               System.out.println( inputData + "円が投入されました.");
               getMoney(i);
               if(amount < 100){
                    addOrCancel();
                    continue;
               }else if(amount >= 100){
                   selectContent();
                   if(!buyOrCancel()){
                       continue;
                   }
               }
               outputOnigiri();
               System.out.println("ご利用ありがとうございました．");
               inputData = welcome(true);
           }
       }
       scan.close();
   }
   public void getMoney(int money){
       amount += money;
       System.out.println("投入金額の合計は，" + amount + "円です.");
   }
   public int returnMoney(){
       int temp = amount;
       amount = 0;
       System.out.println("返金額は，" + temp + "円です.");
       return temp;
   }
   public void calSales() {
       sales += 100;
       amount -= 100;
   }
   public void outputOnigiri() {
       System.out.println("おにぎりの購入が完了しました.");
       count++;
       if(count%3==0){//3回に一回無料処理
            System.out.println("おめでとうございます！おにぎり１個を無料で受け取ることができます！全額返金します！");
       }else{
            calSales();
       }
       returnMoney();
   }
   public String welcome(boolean dispKanban) {
       if(dispKanban){
           menu();
           dispKanban = false;
       }
       System.out.print("お金を入れてください：　");
       String str = scan.nextLine();
       return str;
   }
   public void menu(){
        for(int i=0;i<20;i++){
            System.out.print("=");
       }
       System.out.println("=");
       System.out.println("|   朝食自動販売機   |");
       for(int i=0;i<20;i++){
           System.out.print("=");
       }
       System.out.println("=");
       for(int i=0;i<1;i++){
           System.out.println("|                   |");
       }
       System.out.println("| １：おにぎり 100円 |");
       for(int i=0;i<1;i++){
           System.out.println("|                   |");
       }
       for(int i=0;i<20;i++){
           System.out.print("=");
       }
       System.out.println("=");
   }
   public boolean addOrCancel(){
       while(true){
           System.out.println("お金を追加する場合はaを、購入をキャンセルする場合はcを入力してください.");
           String str = scan.nextLine();
           if(str.equals("a")) {
               inputData = welcome(false);
               return true;
           } else if(str.equals("c")) {
               returnMoney();
               inputData = welcome(true);
               return false;
           } else {
               System.out.println("aまたはcを入力してください.");
           }
       }
   }
   public boolean buyOrCancel(){
       while(true){
           System.out.println("購入される場合はbを、購入をキャンセルする場合はcを入力してください.");
           String str = scan.nextLine();
           if(str.equals("b")) {
               return true;
           } else if(str.equals("c")) {
               returnMoney();
               inputData = welcome(true);
               return false;
           } else {
           System.out.println("bまたはcを入力してください.");
           }
       }
   }
   
   void selectContent(){
       while(true){
       System.out.println("商品を選んでください");
       String str = scan.nextLine();
       int i;
                  i = Integer.parseInt(str);
      
       switch (i) {
           case 0:
               nowContent=new Content(1,"おにぎり（昆布）",100);
               return;
           case 1:
               nowContent=new Content(2,"おにぎり（梅）",100);
               return;
           case 2:
               nowContent=new Content(3,"サンドイッチ",100);
               return;
           default:
               System.out.println("１~３を入力してください");
               break;
       }
           
       }
  }


}