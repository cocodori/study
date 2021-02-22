import java.util.Stack;

public class StackUndoRedo {
    public static Stack<String> back = new Stack<>();
    public static Stack<String> forward = new Stack<>();

    public static void main(String[] args) {
        System.out.println("=============");
        goUrl("1.google");
        goUrl("2.naver");
        goUrl("3.yahoo!");
        goUrl("4.junior-naver");
        goBack();
        goBack();
        goForward();
        goUrl("facebook.com");
        
    }

    public static void print() {
        System.out.println("back : " + back);
        System.out.println("forward : " + forward);
        System.out.println("current page : " + back.peek());
    }

    public static void goUrl(String url) {
        if(validNotEmpty(forward))
            forward.clear();

        back.push(url);

        System.out.println("=============");
        System.out.println("move to " + url);
        print();
    }

    public static void goForward() {
        if(validNotEmpty(forward))
            back.push(forward.pop());

        System.out.println("=============");
        System.out.println("Redo");
        print();
    }

    public static void goBack() {
        if(validNotEmpty(back))
            forward.push(back.pop());
            
        System.out.println("=============");
        System.out.println("Undo");
        print();
    }

    public static boolean validNotEmpty(Stack<String> stack) {
        return !stack.empty();
    } 

}