/*
 * Order class
 *
 * Version 2
 *
 * класс - заказ
 */

package com.dmitry;
import java.text.SimpleDateFormat;


public class Order {
    String nameOfUser;    //персонализирована по пользователю
    String date;
    int number;
    Good[] good= new Good[100];    //хранит до 100 наименований товаров
    int[] quantityOfGood= new int[100];    //"ячейка" количества для каждого "слота" с товаром

    Order() {    //конструктор пустой корзины
        nameOfUser="Тестовый Вася";
        date=getDate();
        number=0;
        for (int i=0;i<100;i++) {
            good[i]=new Good();    //в "пустую" корзину - "пустые" товары
            quantityOfGood[i]=0;
        }
    }

    Order(String fldName, String fldDate, int fldNumb) {                 //конструктор персонализированной корзины
        nameOfUser=fldName;
        date=fldDate;
        number=fldNumb;
    }

    Order(String fldName,  int fldNumb){    //конструктор персонализированной корзины
        nameOfUser=fldName;
        date=getDate();
        number=fldNumb;
    }

    public String getDate(){
        long curTime=System.currentTimeMillis();
        String curStringDate = new SimpleDateFormat("yyyy.MM.dd").format(curTime);
        return curStringDate;
    }

    public void addGood(int indx,Good newgood,int quantity){     //заполнение корзины реальными товарами извне(нужно знать в какую позицию кладёшь товар)
        good[indx]=newgood;
        quantityOfGood[indx]=quantity;
    }

    public String askGood(int indx) {    //метод - возвращает строку с наименованием товара и количеством этого товара в "слоте" корзины
        return "товар " + good[indx].giveName() + " в количестве " + quantityOfGood[indx] + " штук";
    }

    public int askQuant(int indx) {    //метод - возвращает количетсво товара в "слоте" корзины
        return quantityOfGood[indx];
    }

    public float goodCalculate(int indx){    //метод - возвращает стоимость товаров и их доставки по выбранному слоту
        return (good[indx].givePrice()+good[indx].givePriceOfDelivery())*quantityOfGood[indx];
    }

    public String giveCustomer(){
        return nameOfUser;
    }

    public int giveNumber() {
        return number;
    }

    public String giveDate(){
        try {
            return date;
        } catch (NumberFormatException e) {
            return "0";                                               //если не число и не 'X' - возвращаем ноль
        }
    }

    public float goodsCalculate(){
        int i=0;
        float result=0;
        do {
            result+=(good[i].givePrice()+good[i].givePriceOfDelivery())*quantityOfGood[i];
            i++;
        } while (quantityOfGood[i]!=0);
        return result;
    }
}
