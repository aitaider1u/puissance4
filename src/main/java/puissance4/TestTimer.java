package puissance4;

import java.util.Date;

public class TestTimer {
    public static void main(String[] args) {
        long tic = System.currentTimeMillis();
        long time = 0L ;

        while (time < 2000 ) {
            time = (new Date()).getTime() - tic;
            if(time %1000 == 0)
                System.out.println(time/1000);
        }
    }
}
