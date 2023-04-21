package battleship;

import java.util.*;

public class Main {
    public static boolean gameOn;

    public static void main(String[] args) {
        final int fieldSize = 10;

        char[][] field = new char[fieldSize][fieldSize];
        for (char[] row : field) {
            Arrays.fill(row, '~');
        }

        char[][] emptyField = new char[fieldSize][fieldSize];
        for (char[] row : emptyField) {
            Arrays.fill(row, '~');
        }
        char[][] notCloseField = new char[fieldSize][fieldSize];
        for (char[] row : notCloseField) {
            Arrays.fill(row, '~');
        }

        char[][] pvpField = new char[fieldSize][fieldSize];
        for (char[] row : pvpField) {
            Arrays.fill(row, '~');
        }

        char[][] pvpEmptyField = new char[fieldSize][fieldSize];
        for (char[] row : pvpEmptyField) {
            Arrays.fill(row, '~');
        }

        char[][] pvpNotClosedField = new char[fieldSize][fieldSize];
        for (char[] row : pvpNotClosedField) {
            Arrays.fill(row, '~');
        }

        char[][] justPrintField = new char[fieldSize][fieldSize];
        for (char[] row : justPrintField) {
            Arrays.fill(row, '~');
        }


        makeTheField(field, notCloseField);

        System.out.println("Press Enter and pass the move to another player \n");

        Scanner sc = new Scanner(System.in);

        sc.nextLine();

        pvpMakeTheField(pvpField, pvpNotClosedField);

        System.out.println("Press Enter and pass the move to another player");

        sc.nextLine();

        justPrintField(justPrintField);
        System.out.println("---------------------\n");
        printField(field);

        int count = 1;


        while (true) {


            if (count % 2 != 0) {
                System.out.println("Player 1, it's your turn:");

                while (gameOn) {
                    Scanner scanner = new Scanner(System.in);

                    String firstShot = scanner.next();

                    char ch11 = firstShot.charAt(0);

                    int x1 = ch11 - 64;
                    int y1 = Integer.parseInt(firstShot.substring(1));


                    int isValue = 0;

                    try {
                        isValue = pvpHitTheShips(pvpField, x1, y1);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        gameOn = true;
                    }

                    gameOn = pvpGameOn(x1, y1, isValue, pvpField);

                    System.out.println("Press Enter and pass the move to another player");

                    sc.nextLine();

                    pvpEmptyField(pvpEmptyField, x1, y1, isValue);

                    justHitTheSpot(emptyField);
                    System.out.println("---------------------\n");
                    pvpShowTheChanges(pvpField, x1, y1, isValue);

                    count++;

                    if (count % 2 == 0) {
                        break;
                    }

                }


            } else if (count % 2 == 0) {
                System.out.println("Player 2, it's your turn:");

                while (gameOn) {
                    Scanner scanner = new Scanner(System.in);

                    String firstShot = scanner.next();

                    char ch11 = firstShot.charAt(0);

                    int x1 = ch11 - 64;
                    int y1 = Integer.parseInt(firstShot.substring(1));

                    int isValue = 0;

                    try {
                        isValue = hitTheShips(field, x1, y1);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        gameOn = true;
                    }

                    gameOn = gameOnMethod(x1, y1, isValue, field);

                    System.out.println("Press Enter and pass the move to another player");

                    scanner.nextLine();

                    emptyField(emptyField, x1, y1, isValue);

                    pvpJustHitTheSpot(pvpEmptyField);
                    System.out.print("---------------------\n");
                    showTheChanges(field, x1, y1, isValue);

                    count++;

                    if (count % 2 != 0) {
                        break;
                    }
                }
            }
        }
    }

    private static void justPrintField(char[][] justPrintField) {

        System.out.print(" ");
        for (int i = 1; i <= justPrintField[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int j = 0; j < justPrintField.length; j++) {
            int char1 = 65 + j;
            System.out.print((char) char1);
            for (int k = 0; k < justPrintField[j].length; k++) {
                System.out.print(" " + justPrintField[j][k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void justHitTheSpot(char[][] emptyField) {


        for (int i = 1; i <= emptyField[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int j = 0; j < emptyField.length; j++) {
            int char1 = 65 + j;
            System.out.print((char) char1);
            for (int k = 0; k < emptyField[j].length; k++) {
                System.out.print(" " + emptyField[j][k]);
            }
            System.out.println();
        }
    }

    public static void pvpJustHitTheSpot(char[][] pvpEmptyField) {


        for (int i = 1; i <= pvpEmptyField[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int j = 0; j < pvpEmptyField.length; j++) {
            int char1 = 65 + j;
            System.out.print((char) char1);
            for (int k = 0; k < pvpEmptyField[j].length; k++) {
                System.out.print(" " + pvpEmptyField[j][k]);
            }
            System.out.println();
        }
    }

    public static void makeTheField(char[][] field, char[][] notCloseField) {

        System.out.println("Player 1, place your ships to the game field \n");

        String[] ship = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] size = {5, 4, 3, 3, 2};

        printField(field);

        for (int i = 0; i < size.length; i++) {
            System.out.println("Enter the coordinates of the " + ship[i] + " (" + size[i] + " cells):");
            gameOn = false;
            while (!gameOn) {
                Scanner scanner = new Scanner(System.in);
                String cord1 = scanner.next();
                String cord2 = scanner.next();
                play(field, cord1, cord2, ship[i], size[i], notCloseField);
            }
            printField(field);
        }
    }

    public static void pvpMakeTheField(char[][] pvpField, char[][] pvpNotClosedField) {

        System.out.println("Player 2, place your ships to the game field \n");


        String[] ship = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] size = {5, 4, 3, 3, 2};

        pvpPrintField(pvpField);

        for (int i = 0; i < size.length; i++) {
            System.out.println("Enter the coordinates of the " + ship[i] + " (" + size[i] + " cells):");
            gameOn = false;
            while (!gameOn) {
                Scanner scanner = new Scanner(System.in);
                String cord1 = scanner.next();
                String cord2 = scanner.next();
                pvpPlay(pvpField, cord1, cord2, ship[i], size[i], pvpNotClosedField);
            }
            pvpPrintField(pvpField);
        }
    }

    public static void showTheChanges(char[][] field, int x1, int y1, int isValue) {

        System.out.print(" ");
        for (int i = 1; i <= field[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int j = 0; j < field.length; j++) {
            int char1 = 65 + j;
            System.out.print((char) char1);
            for (int k = 0; k < field[j].length; k++) {

                if (isValue == 1) {
                    field[x1 - 1][y1 - 1] = 'X';

                } else if (isValue == 2) {
                    field[x1 - 1][y1 - 1] = 'M';
                }
                System.out.print(" " + field[j][k]);
            }
            System.out.println();
        }
    }

    public static void pvpShowTheChanges(char[][] pvpField, int x1, int y1, int isValue) {

        System.out.print(" ");
        for (int i = 1; i <= pvpField[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int j = 0; j < pvpField.length; j++) {
            int char1 = 65 + j;
            System.out.print((char) char1);
            for (int k = 0; k < pvpField[j].length; k++) {

                if (isValue == 1) {
                    pvpField[x1 - 1][y1 - 1] = 'X';

                } else if (isValue == 2) {
                    pvpField[x1 - 1][y1 - 1] = 'M';
                }
                System.out.print(" " + pvpField[j][k]);
            }
            System.out.println();
        }
    }


    public static boolean theWarEnds(char[][] field, int x1, int y1) {

        boolean isValueRight = false;
        boolean isValueLeft = false;
        boolean isValueUp = false;
        boolean isValueDown = false;
        int i = 0;
        int j = 0;
        int k = 0;
        int n = 0;

////// while to search x right
        while (y1 + i <= 9 && field[x1 - 1][y1 + i] != '~' && field[x1 - 1][y1 + i] != 'M') {

            if (field[x1 - 1][y1 + i] == 'O') {
                isValueRight = true;
                break;
            }
            i++;
        }
//////while to search  x left
        while (y1 - j >= 0 && y1 - j <= 9 && field[x1 - 1][y1 - j] != '~' && field[x1 - 1][y1 + i] != 'M') {

            if (field[x1 - 1][y1 - j] == 'O') {
                isValueLeft = true;
                break;
            }
            j++;
        }

        //while to search x up
        while (x1 + k <= 9 && field[x1 + k][y1 - 1] != '~' && field[x1 + k][y1 - 1] != 'M') {
            if (field[x1 + k][y1 - 1] == 'O') {

                isValueUp = true;
                break;
            }
            k++;
        }
        //while to search x down
        while (x1 - n >= 0 && x1 - n <= 9 && field[x1 - n][y1 - 1] != '~' && field[x1 - n][y1 - 1] != 'M') {
            if (field[x1 - n][y1 - 1] == 'O') {
                isValueDown = true;

                break;
            }
            n++;
        }

        boolean value;


        value = isValueDown || isValueUp || isValueRight || isValueLeft;

        return value;
    }

    public static boolean pvpTheWarEnds(char[][] pvpField, int x1, int y1) {

        boolean isValueRight = false;
        boolean isValueLeft = false;
        boolean isValueUp = false;
        boolean isValueDown = false;
        int i = 0;
        int j = 0;
        int k = 0;
        int n = 0;

////// while to search x right
        while (y1 + i <= 9 && pvpField[x1 - 1][y1 + i] != '~' && pvpField[x1 - 1][y1 + i] != 'M') {

            if (pvpField[x1 - 1][y1 + i] == 'O') {
                isValueRight = true;
                break;
            }
            i++;
        }
//////while to search  x left
        while (y1 - j >= 0 && y1 - j <= 9 && pvpField[x1 - 1][y1 - j] != '~' && pvpField[x1 - 1][y1 + i] != 'M') {

            if (pvpField[x1 - 1][y1 - j] == 'O') {
                isValueLeft = true;
                break;
            }
            j++;
        }

        //while to search x up
        while (x1 + k <= 9 && pvpField[x1 + k][y1 - 1] != '~' && pvpField[x1 + k][y1 - 1] != 'M') {
            if (pvpField[x1 + k][y1 - 1] == 'O') {

                isValueUp = true;
                break;
            }
            k++;
        }
        //while to search x down
        while (x1 - n >= 0 && x1 - n <= 9 && pvpField[x1 - n][y1 - 1] != '~' && pvpField[x1 - n][y1 - 1] != 'M') {
            if (pvpField[x1 - n][y1 - 1] == 'O') {
                isValueDown = true;

                break;
            }
            n++;
        }

        boolean value;


        value = isValueDown || isValueUp || isValueRight || isValueLeft;

        return value;
    }

    public static int allShipsSank(char[][] field) {
        int counter = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'X') {
                    counter++;

                }
            }
        }

        return counter;
    }

    public static int pvpAllShipsSank(char[][] pvpField) {
        int counter = 0;

        for (int i = 0; i < pvpField.length; i++) {
            for (int j = 0; j < pvpField[i].length; j++) {
                if (pvpField[i][j] == 'X') {
                    counter++;

                }
            }
        }

        return counter;
    }

    public static boolean gameOnMethod(int x1, int y1, int isValue, char[][] field) {
        boolean gameOn = true;

        if (x1 < 1 || x1 > 10 || y1 < 1 || y1 > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again: \n");
            System.out.println();

        } else if (isValue == 1) {
            hitTheShips(field, x1, y1);
            boolean isWarEnds = theWarEnds(field, x1, y1);

            int counterSank = allShipsSank(field);

            if (counterSank < 17) {
                if (isWarEnds) {

                    System.out.println("You hit a ship!\n");

                } else {
                    System.out.println("You sank a ship! \n");
                }
            } else {
                System.out.println("You sank the last ship. You won. Congratulations! \n");
                gameOn = false;

            }

        } else if (isValue == 2) {
            hitTheShips(field, x1, y1);
            // emptyField(emptyField, x1, y1, isValue);
            System.out.println("You missed! \n");

        } else {
            //emptyField(emptyField, x1, y1, isValue);
            System.out.println("You hit a ship!\n");

        }
        return gameOn;
    }

    public static boolean pvpGameOn(int x1, int y1, int isValue, char[][] pvpField) {
        boolean gameOn = true;

        if (x1 < 1 || x1 > 10 || y1 < 1 || y1 > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again: \n");
            System.out.println();

        } else if (isValue == 1) {
            pvpHitTheShips(pvpField, x1, y1);
            // pvpEmptyField(pvpEmptyField, x1, y1, isValue);
            boolean isWarEnds = pvpTheWarEnds(pvpField, x1, y1);

            int counterSank = pvpAllShipsSank(pvpField);

            if (counterSank < 17) {
                if (isWarEnds) {
                    System.out.println();
                    System.out.println("You hit a ship! \n");

                } else {
                    System.out.println();
                    System.out.println("You sank a ship! \n");
                }
            } else {
                System.out.println();
                System.out.println("You sank the last ship. You won. Congratulations! \n");
                gameOn = false;

            }

        } else if (isValue == 2) {
            pvpHitTheShips(pvpField, x1, y1);
            //pvpEmptyField(pvpEmptyField, x1, y1, isValue);
            System.out.println();
            System.out.println("You missed! \n");

        } else {
            // pvpEmptyField(pvpEmptyField, x1, y1, isValue);
            System.out.println("You hit a ship! Try again: \n");

        }
        return gameOn;
    }

    public static void emptyField(char[][] emptyField, int x1, int y1, int isValue) {

        for (int j = 0; j < emptyField.length; j++) {
            for (int k = 0; k < emptyField[j].length; k++) {

                if (isValue == 1) {
                    emptyField[x1 - 1][y1 - 1] = 'X';

                } else if (isValue == 2) {
                    emptyField[x1 - 1][y1 - 1] = 'M';
                }
            }
        }
    }

    public static void pvpEmptyField(char[][] pvpEmptyField, int x1, int y1, int isValue) {

        for (int j = 0; j < pvpEmptyField.length; j++) {

            for (int k = 0; k < pvpEmptyField[j].length; k++) {

                if (isValue == 1) {
                    pvpEmptyField[x1 - 1][y1 - 1] = 'X';

                } else if (isValue == 2) {
                    pvpEmptyField[x1 - 1][y1 - 1] = 'M';
                }
            }
        }
    }

    public static int hitTheShips(char[][] field, int x1, int y1) {

        int isValue = 0;

        for (int i = 0; i < field.length - 1; i++) {
            for (int j = 0; j < field[i].length - 1; j++) {
                if (field[x1 - 1][y1 - 1] == 'O') {
                    field[x1 - 1][y1 - 1] = 'X';
                    isValue = 1;
                    break;
                } else if (field[x1 - 1][y1 - 1] == '~') {
                    field[x1 - 1][y1 - 1] = 'M';
                    isValue = 2;
                }
            }
        }

        return isValue;
    }

    public static int pvpHitTheShips(char[][] pvpField, int x1, int y1) {

        int isValue = 0;

        for (int i = 0; i < pvpField.length - 1; i++) {
            for (int j = 0; j < pvpField[i].length - 1; j++) {
                if (pvpField[x1 - 1][y1 - 1] == 'O') {
                    pvpField[x1 - 1][y1 - 1] = 'X';
                    isValue = 1;
                    break;
                } else if (pvpField[x1 - 1][y1 - 1] == '~') {
                    pvpField[x1 - 1][y1 - 1] = 'M';
                    isValue = 2;
                }
            }
        }

        return isValue;
    }

    public static void printField(char[][] field) {
        System.out.print(" ");
        for (int i = 1; i <= field[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int j = 0; j < field.length; j++) {
            int char1 = 65 + j;
            System.out.print((char) char1);
            for (int k = 0; k < field[j].length; k++) {
                System.out.print(" " + field[j][k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void pvpPrintField(char[][] pvpField) {
        System.out.print(" ");
        for (int i = 1; i <= pvpField[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int j = 0; j < pvpField.length; j++) {
            int char1 = 65 + j;
            System.out.print((char) char1);
            for (int k = 0; k < pvpField[j].length; k++) {
                System.out.print(" " + pvpField[j][k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean play(char[][] field, String cord1, String cord2, String ship, int size,
                                char[][] notCloseField) {
        char ch11 = cord1.charAt(0);
        char ch21 = cord2.charAt(0);
        int x1 = ch11 - 64;
        int x2 = ch21 - 64;
        int y1 = Integer.parseInt(cord1.substring(1));
        int y2 = Integer.parseInt(cord2.substring(1));
        int start;
        int end;
        boolean horizontal;

        if (x1 < 1 || x1 > 10 || x2 < 1 || x2 > 10 || y1 < 1 || y1 > 10 || y2 < 1 || y2 > 10) {
            System.out.println("Error! Wrong ship location! Try again: \n");
            System.out.println();
            return gameOn = false;
        }

        int startHor;
        int endHor;
        int startVert;
        int endVert;
        int startNotClose;
        int endNotClose;


        if (x1 == x2) {
            start = Math.min(y1, y2);
            end = Math.max(y1, y2);
            horizontal = true;
        } else if (y1 == y2) {
            start = Math.min(x1, x2);
            end = Math.max(x1, x2);
            horizontal = false;
        } else {
            System.out.println("Error! Wrong ship location! Try again: \n");
            System.out.println();
            return gameOn = false;
        }
        if ((end - (start - 1)) == size && !horizontal) {
            if (start < 2) {
                startNotClose = 1;
            } else {
                startNotClose = start - 2;
            }

            if (end > 9) {
                endNotClose = 10;
            } else {
                endNotClose = end + 1;
            }

            if (y1 < 2) {
                startVert = 1;
            } else {
                startVert = y1 - 2;
            }

            if (y1 > 9) {
                endVert = 10;
            } else {
                endVert = y1 + 1;
            }
            for (int i = start - 1; i <= end - 1; i++) {
                if (notCloseField[i][y1 - 1] == 'O') {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.println();
                    return gameOn = false;
                }
            }
            for (int j = start - 1; j <= end - 1; j++) {
                field[j][y1 - 1] = 'O';
            }
            for (int i = startNotClose; i < endNotClose; i++) {
                for (int j = startVert; j < endVert; j++) {
                    notCloseField[i][j] = 'O';
                }
            }
            return gameOn = true;
        } else if ((end - (start - 1)) == size && horizontal) {
            if (start < 2) {
                startNotClose = 1;
            } else {
                startNotClose = start - 2;
            }

            if (end > 9) {
                endNotClose = 10;
            } else {
                endNotClose = end + 1;
            }

            if (x1 < 2) {
                startHor = 1;
            } else {
                startHor = x1 - 2;
            }

            if (x1 > 9) {
                endHor = 10;
            } else {
                endHor = x1 + 1;
            }

            for (int i = start - 1; i <= end - 1; i++) {
                if (notCloseField[x1 - 1][i] == 'O') {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.println();
                    return gameOn = false;
                }
            }
            for (int j = start - 1; j <= end - 1; j++) {
                field[x1 - 1][j] = 'O';
            }
            for (int i = startHor; i < endHor; i++) {
                for (int j = startNotClose; j < endNotClose; j++) {
                    notCloseField[i][j] = 'O';
                }
            }
            return gameOn = true;
        } else {
            System.out.println("Error! Wrong length of the " + ship + "! Try again:");
            System.out.println();
            return gameOn = false;
        }
    }

    private static boolean pvpPlay(char[][] pvpField, String cord1, String cord2, String ship, int size,
                                   char[][] pvpNotCloseField) {
        char ch11 = cord1.charAt(0);
        char ch21 = cord2.charAt(0);
        int x1 = ch11 - 64;
        int x2 = ch21 - 64;
        int y1 = Integer.parseInt(cord1.substring(1));
        int y2 = Integer.parseInt(cord2.substring(1));
        int start;
        int end;
        boolean horizontal;

        if (x1 < 1 || x1 > 10 || x2 < 1 || x2 > 10 || y1 < 1 || y1 > 10 || y2 < 1 || y2 > 10) {
            System.out.println("Error! Wrong ship location! Try again:");
            System.out.println();
            return gameOn = false;
        }

        int startHor;
        int endHor;
        int startVert;
        int endVert;
        int startNotClose;
        int endNotClose;


        if (x1 == x2) {
            start = Math.min(y1, y2);
            end = Math.max(y1, y2);
            horizontal = true;
        } else if (y1 == y2) {
            start = Math.min(x1, x2);
            end = Math.max(x1, x2);
            horizontal = false;
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            System.out.println();
            return gameOn = false;
        }
        if ((end - (start - 1)) == size && !horizontal) {
            if (start < 2) {
                startNotClose = 1;
            } else {
                startNotClose = start - 2;
            }

            if (end > 9) {
                endNotClose = 10;
            } else {
                endNotClose = end + 1;
            }

            if (y1 < 2) {
                startVert = 1;
            } else {
                startVert = y1 - 2;
            }

            if (y1 > 9) {
                endVert = 10;
            } else {
                endVert = y1 + 1;
            }
            for (int i = start - 1; i <= end - 1; i++) {
                if (pvpNotCloseField[i][y1 - 1] == 'O') {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.println();
                    return gameOn = false;
                }
            }
            for (int j = start - 1; j <= end - 1; j++) {
                pvpField[j][y1 - 1] = 'O';
            }
            for (int i = startNotClose; i < endNotClose; i++) {
                for (int j = startVert; j < endVert; j++) {
                    pvpNotCloseField[i][j] = 'O';
                }
            }
            return gameOn = true;
        } else if ((end - (start - 1)) == size && horizontal) {
            if (start < 2) {
                startNotClose = 1;
            } else {
                startNotClose = start - 2;
            }

            if (end > 9) {
                endNotClose = 10;
            } else {
                endNotClose = end + 1;
            }

            if (x1 < 2) {
                startHor = 1;
            } else {
                startHor = x1 - 2;
            }

            if (x1 > 9) {
                endHor = 10;
            } else {
                endHor = x1 + 1;
            }

            for (int i = start - 1; i <= end - 1; i++) {
                if (pvpNotCloseField[x1 - 1][i] == 'O') {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.println();
                    return gameOn = false;
                }
            }
            for (int j = start - 1; j <= end - 1; j++) {
                pvpField[x1 - 1][j] = 'O';
            }
            for (int i = startHor; i < endHor; i++) {
                for (int j = startNotClose; j < endNotClose; j++) {
                    pvpNotCloseField[i][j] = 'O';
                }
            }
            return gameOn = true;
        } else {
            System.out.println("Error! Wrong length of the " + ship + "! Try again:");
            System.out.println();
            return gameOn = false;
        }
    }
}
