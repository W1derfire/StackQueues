public class Main {

    public static void main(String[] args) {

        StackQueue stack = new StackQueue("Stack","NotMyStack");

        OneStackQueueAdder adder = new OneStackQueueAdder(stack, 300, "Nicco");
        OneStackQueueSubtractor subtractor = new OneStackQueueSubtractor(stack, 300, "Ethan");
        OneStackQueueAdder odder = new OneStackQueueAdder(stack, 150, "Hayden");
        OneStackQueueSubtractor sobtractor = new OneStackQueueSubtractor(stack, 500, "Sal");
        OneStackQueueAdder edder = new OneStackQueueAdder(stack, 400, "Jack");

        Thread addThread = new Thread(adder);
        Thread subtractThread = new Thread(subtractor);
        Thread oddThread = new Thread(odder);
        Thread sobtractThread = new Thread(sobtractor);
        Thread eddThread = new Thread(edder);


        addThread.start();
        subtractThread.start();
        oddThread.start();
        sobtractThread.start();
        eddThread.start();


    }
}
