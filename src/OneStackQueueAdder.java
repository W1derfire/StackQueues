public class OneStackQueueAdder implements Runnable{
    StackQueue sharedThigamajig;
    int howManyTimes;
    String name;

    public OneStackQueueAdder(StackQueue stackQueue, int times, String name){
        sharedThigamajig = stackQueue;
        howManyTimes = times;
        this.name = name;
    }

    public void run(){
        for(int i = 0; i < howManyTimes; i ++){
            if(!sharedThigamajig.put(i)){
                Thread.yield();
                sharedThigamajig.put(i);
                System.out.println(i + " is true. Thanks, " + name + "!");
            }
            System.out.println(i + " is true. Thanks, " + name + "!");

        }
    }

}
