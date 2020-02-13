public class StackQueue implements DataStructure {

    private String type;
    private String name;
    private Object[] objects = new Object[100];
    private int obOn;
    private boolean inUse = false;
    private String guyUsing;

    //private int obRead = 0;

    public StackQueue(String type, String name){
        this.type = type;
        this.name = name;

        if(this.type.toLowerCase().equals("stack")){
            this.obOn = 0;
        }else {
            this.obOn = 99;
        }

    }


    synchronized public boolean put(Object object){
        if(type.toLowerCase().equals("stack")) {
            if(obOn >= 100){
                System.out.println(name + " is full!");
                return false;
            }else{
                objects[obOn] = object;
                obOn ++;
                return true;
            }
        }else{
            if(obOn < 0){
                System.out.println(name + " is full!");
                return false;
            }else{

                objects[obOn] = object;
                obOn --;
                return true;
            }
        }
        //return false;
    }

    synchronized public boolean putMany(Object[] objectz){
        for(int i = 0; i < objectz.length; i ++) {
            if (type.toLowerCase().equals("stack")) {
                if (obOn >= 100) {
                    System.out.println(name + " is full!");
                    return false;
                } else {
                    objects[obOn] = objectz[i];
                    obOn++;
                    //return true;
                }
            } else {
                if (obOn < 0) {
                    System.out.println(name + " is full!");
                    return false;
                } else {

                    objects[obOn] = objectz[i];
                    obOn--;
                    //return true;
                }
            }
            //return false;
        }
        return true;
    }


    synchronized public Object get(){
        Object temporary;

        if(type.toLowerCase().equals("stack")) {
            if(obOn >= 1) {
                obOn--;
                temporary = objects[obOn];
                objects[obOn] = null;
                System.out.print(temporary);
                return temporary;
            }else{
                System.out.println(name + " is empty!");
                return false;
            }
        }else{
            if(obOn < 99) {
                obOn++;
                temporary = objects[obOn];
                objects[obOn] = null;
                return temporary;
            }else{
                System.out.println(name + " is empty!");
                return false;
            }
        }

        //return false;
    }

    synchronized public void setUsage(boolean using, String user){
        inUse = using;
        guyUsing = user;
    }


}
