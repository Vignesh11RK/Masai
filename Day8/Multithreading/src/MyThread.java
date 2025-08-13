public class MyThread extends Thread {

    public void run(){
        for(int i=0;i<100;i++){
            System.out.println(i+"from" +Thread.currentThread().getName()+"Thread");
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.getStackTrace();
            }
        }
    }

}
