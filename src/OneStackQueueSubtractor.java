public class OneStackQueueSubtractor implements Runnable{
    StackQueue sharedThigamajig;
    int howManyTimes;
    String name;

    public OneStackQueueSubtractor(StackQueue stackQueue, int times, String name){
        sharedThigamajig = stackQueue;
        howManyTimes = times;
        this.name = name;
    }

    public void run(){
        for(int i = 0; i < howManyTimes; i ++){
            if(sharedThigamajig.get().equals(Boolean.FALSE)) {
                Thread.currentThread().yield();
                System.out.println(sharedThigamajig.get().toString() + " - Taken out by " + name + "!");
            }else{
                System.out.print(" - Taken out by " + name + "!\n");
            }
        }
    }

}
