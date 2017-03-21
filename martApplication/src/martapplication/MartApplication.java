/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martapplication;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author asher.ansari
 */
class itemList {

    int itemId;
    String name;
    int quantity;

    public itemList(int itemId, String name, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

public class MartApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);

        ArrayList<itemList> itemListData = new ArrayList<>();
        MartApplication application = new MartApplication();
        itemListData = application.addDataList(itemListData);
        ArrayList<itemList> userDashboard = new ArrayList<>();
        int sizeofDashboard = 0;

        while (true) {
            System.out.println("welcome to the Super Mart..");
            System.out.println("1. Show Items List");
            System.out.println("2. place Order");
            System.out.println("3. cancel Order");
            System.out.println("4. show Order List");
            System.out.println("5. Add more Items in current List..");
            System.out.println("6. exit");

            int choice = 0;
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    application.displayItems(itemListData);
                    break;
                case 2:
                    System.out.println("Please Enter The Item Id: ");
                    int id = s.nextInt();
                    ArrayList<itemList> arrayList = new ArrayList<>();
                    boolean check = false;
                    for (int i = 0; i < itemListData.size(); i++) {
                        if (id == itemListData.get(i).getItemId()) {
                            arrayList.add(itemListData.get(i));
                            itemListData.remove(i);
                        }
                    }
                    if (arrayList != null) {
                        System.out.println("Please Enter The Item quantity: ");
                        int quantity = s.nextInt();
                        if (quantity <= 0) {
                            System.out.println("Please Enter The proper Quantity -ve Quantity not allow");
                        } else if (quantity > arrayList.get(0).getQuantity()) {
                            System.out.println("Please Enter only limited Quantity from actual Quantity");
                        } else {

                            arrayList.get(0).setQuantity((arrayList.get(0).getQuantity() - quantity));
                            System.out.println("" + arrayList.get(0).getName() + " added with " + quantity);
//                                sizeofDashboard = userDashboard.size();
                            userDashboard.add(new itemList(arrayList.get(0).getItemId(), arrayList.get(0).getName(), quantity));
                        }

                        itemListData.add(arrayList.get(0));
                    } else {
                        System.out.println("Item Not Found...!!");
                    }
                    arrayList.clear();
                    break;
                case 3:
                    System.out.println("Welcome to The Cancel order Menu");
                    System.out.println("\t\tYour Recorded Orders : ");
                    application.displayItems(userDashboard);
                    System.out.println("please Enter the Cancel Order Id: ");
                    int choiceId = s.nextInt();
                    for (int i = 0; i < userDashboard.size(); i++) {
                        if (choiceId == userDashboard.get(i).getItemId()) {
//                                System.out.println("");
                            int confirm = 0;
                            System.err.println("\tPress 1 to confirm, or Press 2 to (not Done)..");
                            confirm = s.nextInt();
                            if (confirm == 1) {
                                application.addItemInList(itemListData, userDashboard.get(i));
                                userDashboard.remove(i);
                                System.out.println("Item has been Canceled..");
                            } else {
                                System.out.println("Item Not Cancelled...");
                            }
                        } else {
                            System.out.println("Item Not Found...!!");
                        }
                    }
                    break;
                case 4:
                    application.displayItems(userDashboard);
                    break;
                case 5:
                    application.displayItems(userDashboard);
                    if (userDashboard == null) {
                        System.out.println("no Dashboard Item is Created...");
                    } else {
                        System.out.println("Please Enter The Item id: ");
                        int choieId = s.nextInt();
                        for (int i = 0; i < userDashboard.size(); i++) {
                            if (choieId == userDashboard.get(i).getItemId()) {
                                boolean chec = true;
                                int j = 0;
                                while (chec == true) {
                                    if (itemListData.get(j).getItemId() == choieId) {
                                        System.out.println("total Selected Item quantity in Store = " + itemListData.get(j).getQuantity() + " , and your Quantity = " + userDashboard.get(i).getQuantity());
                                        System.out.println("Please Enter The addittional Quantity of Item");
                                        int updatedQuan = s.nextInt();

                                        if (updatedQuan <= 0) {
                                            System.out.println("Please Enter The proper Quantity -ve Quantity not allow");
                                        } else if (updatedQuan > itemListData.get(j).getQuantity()) {
                                            System.out.println("Please Enter only limited Quantity from actual Quantity");
                                        } else {
                                            itemListData.get(j).setQuantity((itemListData.get(j).getQuantity() - updatedQuan));
                                            System.out.println("" + userDashboard.get(i).getName() + " added with " + updatedQuan);
                                            userDashboard.get(i).setQuantity(userDashboard.get(i).getQuantity() + updatedQuan);
                                        }
                                        chec = false;
                                    }
                                    j++;
                                }
                            }
                        }
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    public ArrayList<itemList> addDataList(ArrayList<itemList> arrayList) {
        String[] itemName = {"Apple", "Mango", "Banana", "PineApple", "Starwberry", "peach", "kharboza", "Aam", "Chocolate", "Desert", "Kheer", "orange", "Candies", "Ice creams", "butter", "Bread"};

        int randomquantity = 0;
        for (int i = 0; i < itemName.length; i++) {
            arrayList.add(new itemList(i + 1, itemName[i], (int) Math.floor((Math.random() * 200))));
        }
        return arrayList;
    }

    public void displayItems(ArrayList<itemList> al) {
        for (int i = 0; i < al.size(); i++) {
            System.out.println("Item Id: " + al.get(i).getItemId() + "\tName: " + al.get(i).getName() + "\tQuantity: " + al.get(i).getQuantity());
        }
    }

    public void addItemInList(ArrayList<itemList> parentList, itemList childList) {
        for (int i = 0; i < parentList.size(); i++) {
            if (parentList.get(i).getItemId() == childList.getItemId()) {
                parentList.get(i).setQuantity(parentList.get(i).getQuantity() + childList.getQuantity());
            }
        }
    }
}
