import javax.naming.Context;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    Scanner scanner = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    String choose;
    char[] questionWord;
    int attempts = 0;
    int maxAttempts = 5;
    Set<String> charsAttempt = new HashSet<>();



    public void processGame(String word) {
        questionWord = word.toCharArray();
        char[] hiddenChars = word.toCharArray();
        hideChars(hiddenChars);
        updateUI(attempts);
        lookingForNextChar(true);
    }



    public void lookingForNextChar(boolean isFirstTry) {
        if(isFirstTry) {
            System.out.print("Введите букву: ");
            choose = scanner.nextLine().toLowerCase();
            charsAttempt.add(choose);
            boolean contains = new String(questionWord).toLowerCase().contains(choose.toLowerCase());
            if (contains) {
                updateUI(-1);
            } else {
                updateUI(attempts = attempts + 1);
            }
        } else {
            boolean contains = new String(questionWord).toLowerCase().contains(choose.toLowerCase());
            if (contains) {
                updateUI(-1);
            } else {
                updateUI(attempts = attempts + 1);
            }
        }

    }

    public void hideChars(char[] hiddenChars) {
        for(char c : hiddenChars) {
            sb.append("_ ");
        }
    }

    public void tryToFindCharDuplicates() {
        System.out.print("Введите букву: ");
        choose = scanner.nextLine().toLowerCase();
        boolean containsSearch = charsAttempt.stream().anyMatch(choose::equalsIgnoreCase);
        while (containsSearch) {
            System.out.println("Извините,такая буква уже есть");
            System.out.print("Введите букву: ");
            choose = scanner.nextLine();
            if(charsAttempt.stream().noneMatch(choose::equalsIgnoreCase))
                containsSearch = false;
        }
        lookingForNextChar(false);

    }

    public Set<String> calculateCurrentCharState() {
        charsAttempt.add(choose);
        return charsAttempt;
    }

    public void updateUI(int attempts)  {
        switch(attempts) {
            case(-1):
                System.out.println(" -------------\n" +
                        " |           \n"  +
                        " |           \n"  +
                        " |           \n"  +
                        " |           \n"  +
                        "/|\\"
                );
                System.out.println("Такая буква есть!");
                System.out.println("Загаданное вам слово: " + calculateSb(sb,choose,questionWord));
                tryToFindCharDuplicates();
                break;
            case(0):
                System.out.println(" -------------\n" +
                        " |           \n"  +
                        " |           \n"  +
                        " |           \n"  +
                        " |           \n"  +
                        "/|\\"
                );
                System.out.println("Загаданное вам слово: " + sb);
                break;
            case(1):
                System.out.println(" -------------\n" +
                        " |           |\n" +
                        " |           |\n"  +
                        " |            \n"  +
                        " |           \n"  +
                        " |           \n"  +
                        "/|\\"
                );
                System.out.println("Такой буквы в слове нет! Осталось попыток : " + (maxAttempts = maxAttempts -1)
                        + ", использованные буквы = " +calculateCurrentCharState());
                tryToFindCharDuplicates();
                break;
            case(2):
                System.out.println(" -------------\n" +
                        " |           |\n" +
                        " |           |\n"  +
                        " |           O\n"  +
                        " |           \n"  +
                        " |           \n"  +
                        "/|\\"
                );
                System.out.println("Такой буквы в слове нет! Осталось попыток : " + (maxAttempts = maxAttempts -1)
                        + ", использованные буквы = " +calculateCurrentCharState());
                System.out.println("Загаданное вам слово: " + calculateSb(sb,choose,questionWord));
                tryToFindCharDuplicates();
                break;
            case(3):
                System.out.println(" -------------\n" +
                        " |           |\n" +
                        " |           |\n"  +
                        " |           O\n"  +
                        " |          /|\\\n"  +
                        " |           \n"  +
                        "/|\\"
                );
                System.out.println("Такой буквы в слове нет! Осталось попыток : " + (maxAttempts = maxAttempts -1)
                        + ", использованные буквы = " +calculateCurrentCharState());
                System.out.println("Загаданное вам слово: " + calculateSb(sb,choose,questionWord));
                tryToFindCharDuplicates();
                break;
            case(4):
                System.out.println(" -------------\n" +
                        " |           |\n" +
                        " |           |\n"  +
                        " |           O\n"  +
                        " |          /|\\ \n"  +
                        " |          / \\ \n"  +
                        "/|\\"
                );
                System.out.println("Такой буквы в слове нет! Осталось попыток : " + (maxAttempts = maxAttempts -1)
                        + ", использованные буквы = " +calculateCurrentCharState());
                System.out.println("Загаданное вам слово: " + calculateSb(sb,choose,questionWord));
                tryToFindCharDuplicates();
                break;
            case(5):
                System.out.println(" -------------\n" +
                        " |           |\n" +
                        " |           |\n"  +
                        " |           O\n"  +
                        " |          /|\\\n"  +
                        " |          / \\\n"  +
                        "/|\\        DEAD     "
                );
                System.out.println("Вы проиграли! Загаданное слово было : " + (new String(questionWord)));
                System.out.print("Вы хотите сыграть заново?: [y/n]");
                String endOrNot = scanner.nextLine();
                if(endOrNot.equals("y")) {
                    Launcher launcher = new Launcher();
                    launcher.startGame();
                } else if (endOrNot.equals("n")) {
                    System.out.println("Всего хорошего!");
                    System.exit(0);
                }
                break;

        }
    }


    private String calculateSb(StringBuilder sb, String nextChar, char[] word) {
        String str = new String(word);
        List<Integer> indexes = new ArrayList<>();
        Matcher matcher = Pattern.compile(nextChar).matcher(str);
        while (matcher.find()) {
            indexes.add(matcher.start());
        }
        for(Integer i : indexes) {
          sb.setCharAt(i*2,nextChar.charAt(0));
        }
        return sb.toString();
    }

}
