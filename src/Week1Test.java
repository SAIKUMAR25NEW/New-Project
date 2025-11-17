import java.util.Scanner;
public class Week1Test {
    public static void main(String[] args) {
        System.out.println("Enter a number : ");
        System.out.println("Enter a String : ");
        Scanner sc = new Scanner(System.in);
        Scanner sca = new Scanner(System.in);
        int number = sc.nextInt();
        String word = sca.nextLine();
        String rev  = "";
        int factor = 1;
        if( number%2 == 0)
        {
            System.out.println("The number is even");

        }
        else
        {
            System.out.println("The number is odd");
        }
        for(int i = 1; i <= number; i++)
        {
            factor *= i;

        }
        System.out.println("The factorial of "  + number +  " is: " + factor);


        for( int j = word.length() - 1; j >= 0; j--)
        {
            rev += word.charAt(j);
        }
System.out.println("Reverse of "  +  word  + " is: " + rev);
        if(word.equals(rev))
            {
            System.out.println("The word "  +  word  + " is a palindrome.");
            }
        else
        {
            System.out.println("The word "  +  word  +  " is not a palindrome.");
        }

    }

}
