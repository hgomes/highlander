package br.com.highlander.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public abstract class Consumer<T>  {

   private BlockingQueue<T> queue;
   private int           MAX_ITEMS_CONSUMED_PER_RUN;
   private long          SLEEP_TIME;

   protected Consumer(BlockingQueue<T> q, int micpr, long st)  {
      this.queue                 = q;
      this.MAX_ITEMS_CONSUMED_PER_RUN = micpr;
      this.SLEEP_TIME               = st;

      // Start consumer thread
      new Thread(new ConsumerRunnable()).start();
   }

   public boolean add(T t)  {
      return queue.offer(t);
   }

   private class ConsumerRunnable implements Runnable {


      public void run()  {

         List<T> list = new ArrayList<T>(MAX_ITEMS_CONSUMED_PER_RUN);

         while(true) {
            try  { Thread.sleep(SLEEP_TIME); }
            catch(InterruptedException e)  { return; }

            queue.drainTo(list, MAX_ITEMS_CONSUMED_PER_RUN);

            if(list.size() > 0) {
               consume(list);
               list.clear();
            }
         }
      }
   }

   protected abstract void consume(List<T> l);

}