package sample;


import javafx.event.ActionEvent;

import java.math.BigInteger;
import java.util.Locale;

public class Controller {
    public int log2(BigInteger n) {
        return n.bitLength() - 1;
    }

    public boolean millerRabin(BigInteger n) {
        long rounds = log2(n),s = 0;
        BigInteger t = n.clearBit(0),
                x,a;
        while(t.getLowestSetBit() != 0 && t.bitLength() > 4) {
            t = t.shiftRight(1);
            s ++;
        }

        long g = Long.parseLong(String.valueOf(n.shiftRight(n.bitLength() - 10)));
        for(int i = 0; i < rounds; i ++) {
            if(n.bitLength() > 10)
                a = n.shiftRight(n.bitLength() - 10);
            else
                a = n;
            a = new BigInteger(String.valueOf(2 + (long) (Math.random() * Long.parseLong(String.valueOf(a))) )); // выбор случайного свидетеля простоты
            x = a.modPow(t, n);

            if(x.bitCount() == 1 || x.equals(n.clearBit(n.getLowestSetBit())))
                continue;
            for (int j = 0; j < s - 1; j++) {
                x = x.modPow(new BigInteger("2"),n);
                if(x.bitCount() == 1)
                    return false;
                if (x.equals(n.clearBit(n.getLowestSetBit())))
                    break;
            }
            if (x.equals(n.clearBit(n.getLowestSetBit())))
                continue;
            return false;
        }
        return true; // вероятно простое
    }


}
