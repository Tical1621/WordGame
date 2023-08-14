import java.io.IOException;
import java.util.Scanner;

public class Launcher {
    Scanner scanner = new Scanner(System.in);
    WordGenerator word = new WordGenerator();
    Game game = new Game();
    public void init()  {
        System.out.println("Здравствуйте!вы готовы начать играть? Введите букву в консоль [y/n]");
        String choose = scanner.nextLine();
        switch(choose.toLowerCase()) {
            case"y":
                startGame();
            case"n":
                System.out.println("Жаль,увидимся в следующий раз,удачи!");
                scanner.close();
        }
        scanner.close();

    }

    public void startGame()  {
       game.processGame(word.generateWord());
    }


}
