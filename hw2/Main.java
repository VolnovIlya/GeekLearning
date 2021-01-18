package hw2;

public class Main {
    public static void main(String[] args) {
        String[][] str = {
                {"546", "123", "123", "453"},
                {"54", "345", "234", "4532"},
                {"4532", "4233", "4532", "4532"},
                {"543", "4532", "454", "2444"}
        };

        try {
            try {
                System.out.println(checking(str));
            }catch (MyArraySizeException e){
                System.out.println("Недопустимый размер массива");
            }
        }catch (MyArrayDataException e){
            System.out.println("Недопустимое значение массива");
        }
    }

    public static int checking(String[][] str) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (str.length != 4){
            throw new MyArraySizeException();
        }
        for (int i = 0; i < str.length; i++){
            if (str[i].length != 4){
                throw new MyArraySizeException();
            }
            for (int j = 0; j < str[i].length; j++){
                try{
                    sum = sum + Integer.parseInt(str[i][j]);
                }catch (NumberFormatException e){
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}
