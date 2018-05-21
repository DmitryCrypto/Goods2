/*
 * Good class
 *
 * Version 1
 *
 * класс - товар
 */
package com.dmitry;

public class Good {
    private String name;    //наименование товара
    private Float price;    //цена товара за шт.
    private Float priceOfDelivery;    //цена доставки товара

    /*конструктор без параметров*/
    Good() {    //товар "по умолчанию" - бесплатный)
        name="blank good";
        price=0f;
        priceOfDelivery=0f;
    }

    /*конструктор с параметрами*/
    Good(String n, float p, float pOd) {    //товар создаётся по данным "снаружи"
        name=n;
        price=p;
        priceOfDelivery=pOd;
    }

    public String giveName() {   //вывод полей в интересах инкапсуляции
        return name;
    }

    public float givePrice(){    //вывод полей в интересах инкапсуляции
        return price;
    }

    public float givePriceOfDelivery() {   //вывод полей в интересах инкапсуляции
            return priceOfDelivery;
    }
}
