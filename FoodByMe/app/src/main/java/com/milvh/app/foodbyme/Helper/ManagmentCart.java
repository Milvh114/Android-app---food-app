package com.milvh.app.foodbyme.Helper;
import android.content.Context;
import android.widget.Toast;

import com.milvh.app.foodbyme.Domain.Food;

import java.util.ArrayList;


public class ManagmentCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
    }

    public void insertFood(Food item) {
        ArrayList<Food> listCart = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            listCart.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listCart.add(item);
        }
        tinyDB.putListObject("CartList",listCart);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Food> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public Double getTotalFee(){
        ArrayList<Food> listItem=getListCart();
        double fee=0;
        for (int i = 0; i < listItem.size(); i++) {
            fee=fee+(listItem.get(i).getPrice()*listItem.get(i).getNumberInCart());
        }
        return fee;
    }
    public void minusNumberItem(ArrayList<Food> listItem,int position,ChangeNumberItemsListener changeNumberItemsListener){
        if(listItem.get(position).getNumberInCart()==1){
            listItem.remove(position);
        }else{
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",listItem);
        changeNumberItemsListener.change();
    }
    public  void plusNumberItem(ArrayList<Food> listItem,int position,ChangeNumberItemsListener changeNumberItemsListener){
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listItem);
        changeNumberItemsListener.change();
    }
}
