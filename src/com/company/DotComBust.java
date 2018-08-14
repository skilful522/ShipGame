package com.company;

import java.util.ArrayList;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<>();
    private int numOfGuesses = 0;

    public void setUpGame() {

        DotCom one = new DotCom();
        one.setName("dom2.ru");
        DotCom two = new DotCom();
        two.setName("youtube.com");
        DotCom three = new DotCom();
        three.setName("vk.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        System.out.println("Ваша цель потопить три сайта ");
        System.out.println("dom2.ru, youtube.com, vk.com");
        System.out.println("Попытайтесь потопить их за минимальное количество попыток");

        for (DotCom dotComToSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
        }
    }

    public void startPlaying() {
        while (!dotComsList.isEmpty()) {
            String userGuess = helper.getUserInput("Сделайте ход");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Мимо";
        for (DotCom dotComToTest : dotComsList) {
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Попал")) {
                //  result += "Вы попали по " + dotComToTest.getName() ;
                break;
            }
            if (result.equals("Потопил")) {
                //result += "Вы потопили " + dotComToTest.getName();
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("Все сайты ушли ко дну");
        if (numOfGuesses <= 18) {
            System.out.println("Это заняло у вас " + numOfGuesses + " попыток");
        } else {
            System.out.println("Это заняло у вас довольно много времени " + numOfGuesses + " попыток ");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}
