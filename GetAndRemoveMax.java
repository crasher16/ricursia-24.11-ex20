package ex1;
import unit4.collectionsLib.Stack;
//מגיש: עידן דרור
public class GetAndRemoveMax {

    public static double getAndRemoveMax(Stack<Double> s) {

        // Temporary stack used to hold elements while scanning
        Stack<Double> temp = new Stack<>();

        // Step 1: pop the first element and assume it is the maximum
        double max = s.pop();
        temp.push(max);

        // Step 2: scan all elements to find the true maximum
        while (!s.isEmpty()) {
            double x = s.pop();
            temp.push(x);
            if (x > max) {
                max = x; // update maximum
            }
        }

        // Step 3: restore all elements except the maximum found
        boolean removed = false; // ensures only one occurrence of max is removed
        while (!temp.isEmpty()) {
            double x = temp.pop();
            if (x == max && !removed) {
                removed = true; // skip restoring the maximum
            } else {
                s.push(x); // restore all non-maximum elements
            }
        }

        // Return the maximum value found and removed
        return max;
    }


    // Prints the stack without changing its logical content
    public static void printStack(Stack<Double> s) {
        Stack<Double> temp = new Stack<>();
        System.out.print("[");
        boolean first = true;

        // Pop all elements for printing (store them temporarily)
        while (!s.isEmpty()) {
            double x = s.pop();
            temp.push(x);
            if (!first) System.out.print(", ");
            System.out.print(x);
            first = false;
        }

        System.out.println("]");

        // Restore original stack
        while (!temp.isEmpty()) s.push(temp.pop());
    }


    public static void main(String[] args) {

        Stack<Double> s = new Stack<>();
        s.push(3.5);
        s.push(8.2);
        s.push(1.7);
        s.push(5.9);

        System.out.print("Original stack: ");
        printStack(s);

        double max = getAndRemoveMax(s);
        System.out.println("Max removed: " + max);

        System.out.print("Stack after removal: ");
        printStack(s);
    }
}


/*
סיבוכיות זמן:
הפעולה מעבירה את כל איברי המחסנית למחסנית עזר 
תוך כדי חיפוש המקסימום
ולאחר מכן מחזירה אותם חזרה פרט לערך המקסימלי

כל איבר זז פעמיים 
פעם אחת ב
pop 
ופעם אחת ב
push 
ולכן סיבוכיות הזמן היא 
O(n)

סיבוכיות מקום:
הפעולה משתמשת במחסנית עזר 
temp 
שיכולה להכיל עד 
n 
איברים

לכן סיבוכיות המקום היא 
O(n)

*/
