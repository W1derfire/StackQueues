public class OneStackQueueAdder implements Runnable {
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
            boolean didWork = false;
            didWork = sharedThigamajig.put((i + " from " + this.name));
            while(!didWork){
                Thread.yield();
                didWork = sharedThigamajig.put((i + " from " + this.name));
            }

        }
    }

}
