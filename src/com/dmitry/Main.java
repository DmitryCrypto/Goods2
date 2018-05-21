/*
 * Main class
 *
 * Version 2
 *
 */
    package com.dmitry;
    import java.util.Objects;
    import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*создаём товары, заполняем массив товаров магазина*/
        Good[] tableOfGoods = new Good[8];
        tableOfGoods[0] = new Good("Товар01", 100, 15);
        tableOfGoods[1] = new Good("Товар02", 521, 15);
        tableOfGoods[2] = new Good("Товар03", 115, 15);
        tableOfGoods[3] = new Good("Товар04", 135, 15);
        tableOfGoods[4] = new Good("Товар05", 130, 15);
        tableOfGoods[5] = new Good("Товар06", 140, 15);
        tableOfGoods[6] = new Good("Товар07", 180, 15);
        tableOfGoods[7] = new Good("Товар08", 700, 15);

        /*создаём таблицу заказов*/
        Order[] tableOfOrders = new Order[8];

        /*наполняем таблицу заказов заказами, заказы товарами*/
        tableOfOrders[0] = new Order("Петров Юрий", "2012.12.21", 2);    //заказ под номером 2
        tableOfOrders[0].addGood(0, tableOfGoods[0], 1);    //товары заказа
        tableOfOrders[0].addGood(1, tableOfGoods[3], 2);
        tableOfOrders[0].addGood(2, tableOfGoods[4], 3);
        tableOfOrders[0].addGood(3, tableOfGoods[5], 4);
        tableOfOrders[1] = new Order("Петров Юрий", "2012.12.23", 3);
        tableOfOrders[1].addGood(0, tableOfGoods[5], 3);
        tableOfOrders[1].addGood(1, tableOfGoods[6], 2);
        tableOfOrders[2] = new Order("Петров Юрий", "2018.04.03", 4);
        tableOfOrders[2].addGood(0, tableOfGoods[1], 2);
        tableOfOrders[2].addGood(1, tableOfGoods[6], 15);
        tableOfOrders[2].addGood(2, tableOfGoods[7], 3);
        tableOfOrders[3] = new Order("Петров Юрий", "2018.03.17", 5);
        tableOfOrders[3].addGood(0, tableOfGoods[4], 3);
        tableOfOrders[3].addGood(1, tableOfGoods[6], 1);
        tableOfOrders[3].addGood(2, tableOfGoods[5], 10);
        tableOfOrders[4] = new Order("Петров Юрий", "2018.05.10", 6);
        tableOfOrders[4].addGood(0, tableOfGoods[1], 1);
        tableOfOrders[4].addGood(1, tableOfGoods[2], 2);
        tableOfOrders[4].addGood(2, tableOfGoods[4], 3);

        /*вызов консольного интерфейса пользователя*/
        userInterface(tableOfGoods,tableOfOrders);
    }

    /*метод - проверяет является ли первая "дата" более ранней чем вторая*/
    private static boolean compareDates(String first, String second){
       int yearF,yearS,monthF,monthS,dayF,dayS;

       /*разбор текстовой "даты" на поля*/
        yearF=Integer.parseInt(first.substring(0, 4));
        yearS=Integer.parseInt(second.substring(0, 4));
        monthF=Integer.parseInt(first.substring(5, 7));
        monthS=Integer.parseInt(second.substring(5, 7));
        dayF=Integer.parseInt(first.substring(8, 10));
        dayS=Integer.parseInt(second.substring(8, 10));

        /*сравнение дат по полям*/
        if (yearF<yearS){
            return true;
        } else if (yearF==yearS) {
            if (monthF<monthS) {
                return true;
            } else if (monthF==monthS) {
                if (dayF<=dayS) {
                    return true;
                } else {
                    return false;}
            } else {
                return false;}
        } else {
            return false;}
    }

    /*обработка некорректного ввода номера товара, или признака завершения ввода 'X'*/
    private static int convertStr2Int(String val) {
        if (val.equalsIgnoreCase("X")) {
            return -1;   //если 'Х' - возвращаем -1
        } else {
            try {
                return Integer.parseInt(val);    //если число - возвращаем число
            } catch (NumberFormatException e) {
                return 0;    //если не число и не 'X' - возвращаем ноль
            }
        }
    }

    /*метод, обслуживающий взаимодействие с пользователем*/
    private static void userInterface( Good[] tableOfGoods,Order[] tableOfOrders) {
        boolean endOfSearch;
        String s;
        int fldNumOfPunkt,fldNumbOfGood,fldQuanOfGood;
        int indxOfpurchase = 0;
        Scanner in = new Scanner(System.in);       //организуем ввод с клавиатуры

        System.out.println("Добро пожаловать в наш магазин!");
        System.out.println("Как я могу к Вам обращаться?");
        s = in.nextLine();    //считываем имя пользователя
        System.out.println("Рады видеть Вас, " + s + " !");
        do {
            System.out.println("Укажите пункт желаемого действия:");
            System.out.println("1 - просмотр заказов");
            System.out.println("2 - просмотр заказов с фильтрацией по дате");
            System.out.println("3 - добавление заказа");
            System.out.println("X - выход из системы");
            fldNumOfPunkt = convertStr2Int(in.nextLine());    //ввод с клавиатуры и проверка на корректность
            switch (fldNumOfPunkt) {
            case -1:    //ввод признака выхода "X"
                System.out.println("Спасибо за посещение! Приходите снова!");
                break;
            case 1:    //выбор "вывести все заказы"
                System.out.println("Введёные в систему заказы:");
                System.out.println("ID   Клиент    Сумма заказа");
                int i=0;

                /*вывод заказов, обработка прерывания на случай указания на незаполненный элемент массива*/
                try {
                    while (tableOfOrders[i].giveNumber() != -1) {
                        System.out.println(tableOfOrders[i].giveNumber() + "    " + tableOfOrders[i].giveCustomer() + "         " + tableOfOrders[i].goodsCalculate() + " руб.     ");
                        i++;
                    }
                } catch (NullPointerException e) {
                }
                break;
            case 2:    //выбор "вывести заказы с фильтром по дате"
                int fldDateYear,fldDateMonth,fldDateDay;
                String  fldSDateYear,fldSDateMonth,fldSDateDay,fldSDate;
                System.out.println("Для отображения заказов совершённых после определённой даты введите:");
                System.out.println("год");
                fldDateYear = convertStr2Int(in.nextLine());    //ввод с клавиатуры и проверка на корректность
                if (fldDateYear<2011 || fldDateYear> 2018) {
                    fldDateYear=2018;
                }
                System.out.println("принят "+fldDateYear+ " год");
                System.out.println("введите месяц:");
                fldDateMonth = convertStr2Int(in.nextLine());    //ввод с клавиатуры и проверка на корректность
                if (fldDateMonth<1 || fldDateMonth> 12) {
                    fldDateMonth=1;
                }
                System.out.println("принят "+fldDateMonth+ " месяц");
                System.out.println("введите день:");
                fldDateDay = convertStr2Int(in.nextLine());    //ввод с клавиатуры и проверка на корректность
                if (fldDateDay<1 || fldDateDay> 31) {
                    fldDateDay=1;
                }
                System.out.println("принят "+fldDateDay+ " день");
                i=0;
                fldSDateDay=String.valueOf(fldDateDay);
                fldSDateMonth=String.valueOf(fldDateMonth);
                fldSDateYear=String.valueOf(fldDateYear);
                if (fldSDateDay.length()<2) {
                    fldSDateDay="0"+fldSDateDay;
                }
                if (fldSDateMonth.length()<2) {
                    fldSDateMonth="0"+fldSDateMonth;
                }
                fldSDate=fldSDateYear+"."+fldSDateMonth+"."+fldSDateDay;
                System.out.println("Введёные в систему заказы:");
                System.out.println("ID   Клиент    Сумма заказа");

                /*вывод заказов с проверкой каждого на соответствие введёной дате*/
                do {
                    if (compareDates(fldSDate,tableOfOrders[i].giveDate())) {
                        System.out.println(tableOfOrders[i].giveNumber() + "    " + tableOfOrders[i].giveCustomer() + "         " + tableOfOrders[i].goodsCalculate() + " руб.     ");
                    }
                    i++;

                    /*проверка указания на незаполненный элемент массива*/
                    try {
                        endOfSearch=Objects.equals(tableOfOrders[i].giveNumber(), new String("0"));
                    } catch (NullPointerException e) {
                        endOfSearch=true;
                    }
                } while (!(endOfSearch));
                break;
            case 3:    //создание и расчёт заказа
                Order basket = new Order(s, 1);    //создаём персонализированную корзину
                System.out.println("Сегодня, мы предлагаем Вам наши товары:");
                System.out.println("ID   Наименование    цена за шт.    цена доставки за шт.");
                for (i = 0; i < 8; i++) {    //вывод таблицы товаров
                    System.out.println(i + 1 + "    " + tableOfGoods[i].giveName() + "         " + tableOfGoods[i].givePrice() + " руб.     " + tableOfGoods[i].givePriceOfDelivery() + " руб.");
                }
                do {    //цикл общения с пользователем
                    System.out.println("Пожалуйста, укажите номер желаемого товара в списке");
                    System.out.println("Для рассчёта стоимости покупок или выхода - введите X");
                    fldNumbOfGood = convertStr2Int(in.nextLine());    //ввод с клавиатуры и проверка на корректность
                    switch (fldNumbOfGood) {
                    case -1:    //вариант - введено 'X'
                        float finPrice = 0, curPrice = 0;    //итоговая цена и цена товаров одного "слота" корзины
                        if (indxOfpurchase > 0) {
                            System.out.println("Вы добавили в корзину: ");    //если в корзину что-либо добавлялось
                            for (int indxOfGood = 0; indxOfGood < indxOfpurchase; indxOfGood++) {   //по всем "занятым" "слотам" корзины
                                if (basket.askQuant(indxOfGood) > 0) {   //если количество товара в слоте >0
                                    System.out.println(basket.askGood(indxOfGood));    //вывод наименования и количества в слоте
                                    curPrice = basket.goodCalculate(indxOfGood);    //рассчёт цены за количество товара в текущем слоте
                                    finPrice += curPrice;    //добавление затрат на товары в слоте в ИТОГО
                                    System.out.println("стоимость позиции с доставкой=" + curPrice);
                                }
                            }
                            System.out.println("стоимость Вашего заказа составляет " + finPrice + " рублей.");
                            System.out.println("Номер Вашего заказа - " + (100 + (int) (Math.random() * 200)));
                            System.out.println("Ваш заказ очень важен для нас! Свяжитесь с нашим менеджером для уточнения деталей доставки");    //минутка стёба
                        }
                        System.out.println("Спасибо за посещение! Приходите снова!");
                        break;
                    case 0:    //некорректный номер товара
                        System.out.println("Простите, вы ввели некорректный номер товара. Попробуйте снова >");
                        break;
                    default:    //корректный номер товара
                        System.out.println("Вы указали товар: " + tableOfGoods[fldNumbOfGood - 1].giveName());
                        System.out.println("Сколько единиц товара Вы хотите получить?");
                        fldQuanOfGood = convertStr2Int(in.nextLine());    //считывание и проверка количества товара
                        if (fldQuanOfGood == -1) {    //если введено X - это так же некорректное число, значит 0 товаров)
                            fldQuanOfGood = 0;
                        }
                        basket.addGood(indxOfpurchase, tableOfGoods[fldNumbOfGood - 1], fldQuanOfGood);    //добавление товара в корзину
                        System.out.println("Отлично! Вы выбрали " + basket.askGood(indxOfpurchase));    //информирование пользователя о удачном действии
                        System.out.println();
                        indxOfpurchase++;    //увеличение счётчика добавленных товаров
                        break;
                    }
                } while (fldNumbOfGood != -1);
                break;
            }
        } while (fldNumOfPunkt != -1);
    }
}