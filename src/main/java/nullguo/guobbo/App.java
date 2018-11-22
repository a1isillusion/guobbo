package nullguo.guobbo;

import zookeeper.Registy;
import zookeeper.Subscriber;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        Registy registy=new Registy("111.230.100.33",2181);
        registy.register("fuck");
        Thread.sleep(2000);
        Subscriber subscriber=new Subscriber("111.230.100.33",2181);
        subscriber.subscribe();
        
    }
}
