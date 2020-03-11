public class Main {

    public static void main(String[] args) {

        //Remember:
        //Stacks use "last in first out"
        //Queues use "first in first out"

        StackQueue stack = new StackQueue("Stack","NotMyStack");

        OneStackQueueAdder adder = new OneStackQueueAdder(stack, 100, "Nicco");
        OneStackQueueSubtractor subtractor = new OneStackQueueSubtractor(stack, 300, "Ethan");
        OneStackQueueAdder odder = new OneStackQueueAdder(stack, 200, "Hayden");
        OneStackQueueSubtractor sobtractor = new OneStackQueueSubtractor(stack, 300, "Sal");
        OneStackQueueAdder edder = new OneStackQueueAdder(stack, 300, "Jack");

        Thread addThread = new Thread(adder);
        Thread subtractThread = new Thread(subtractor);
        Thread oddThread = new Thread(odder);
        Thread sobtractThread = new Thread(sobtractor);
        Thread eddThread = new Thread(edder);


        addThread.start();
        oddThread.start();
        eddThread.start();

        /*try{
            Thread.sleep(1000);
        }catch(Exception e){

        }*/

        subtractThread.start();
        sobtractThread.start();


    }

    public static void sleep(double time){
        try{
            Thread.sleep((long) time*1000);
        }catch(Exception e){
            System.out.println("Oh no!");
        }
    }

}