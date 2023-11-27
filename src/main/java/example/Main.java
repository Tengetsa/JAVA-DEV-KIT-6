package example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    /*
    В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла —
        Википедия ) и наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
    Необходимо:
        Создать свой Java Maven или Gradle проект;
        Самостоятельно реализовать прикладную задачу;
        Сохранить результат в HashMap<шаг теста, результат>
        Вывести на экран статистику по победам и поражениям
     */

    public static void main(String[] args) {
        MontyHallParadox();
    }

    private static void MontyHallParadox() {
        Map<Integer, Boolean> firstPlayer = new HashMap<>();
        Map<Integer, Boolean> SecondPlayer = new HashMap<>();
        int numberIterations = 1000;

        for (int i = 0; i < numberIterations; i++) {
            game(i, firstPlayer, SecondPlayer);
        }

        System.out.println("\nПарадокс Монти Холла");

        statisticsFirstPlayer(firstPlayer, numberIterations);

        statisticsSecondPlayer(SecondPlayer, numberIterations);
    }

    private static void statisticsFirstPlayer(Map<Integer, Boolean> FirstPlayer, int numberIterations) {
        int win = 0;
        for (Map.Entry<Integer, Boolean> entry: FirstPlayer.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }

        System.out.println("\nСтатистика выигрышей для игрока, не меняющего свой выбор:");
        System.out.println("Количество побед: " + win + " раз из " + numberIterations + " попыток.");
    }

    private static void statisticsSecondPlayer(Map<Integer, Boolean> SecondPlayer, int numberIterations) {
        int win = 0;
        for (Map.Entry<Integer, Boolean> entry: SecondPlayer.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }
        System.out.println("\nСтатистика выигрышей для игрока, изменяющего свой выбор:");
        System.out.println("Количество побед: " + win + " раз из " + numberIterations + " попыток.");
    }

    private static void game(int numRound, Map<Integer, Boolean> firstPlayer, Map<Integer, Boolean> secondPlayer) {
        Random random = new Random();

        int doorsNumber = 3;
        int success = random.nextInt(doorsNumber);
        int firstChoice = random.nextInt(doorsNumber);
        int freeOpenDoor = -1;
        int secondChoice = -1;

        for (int i = 0; i < doorsNumber; i++) {
            if (i != success && i != firstChoice){
                freeOpenDoor = i;
            }
        }

        for (int i = 0; i < doorsNumber; i++) {            // Игрок не изменяет свой выбор.
            if (i != freeOpenDoor && i != firstChoice) {
                secondChoice = firstChoice;
                break;
            }
        }
        firstPlayer.put(numRound, success == secondChoice);   // Статистика для первого игрока.

        for (int i = 0; i < doorsNumber; i++) {            // Игрок не изменяет свой выбор.
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = i;
            }
        }
        secondPlayer.put(numRound, success == secondChoice);   // Статистика для второго игрока.
    }
}
