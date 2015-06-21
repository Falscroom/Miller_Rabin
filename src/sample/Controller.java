package sample;
import java.math.BigInteger;

public class Controller {
    public int log2(BigInteger n) {
        return n.bitLength() - 1;
    }
    public boolean simpleTest(BigInteger n) {
        if(n.getLowestSetBit() == 0)
            return false;
        if(n.mod(new BigInteger("5")).equals(new BigInteger("0")) )
            return false;
        return n.mod(new BigInteger("7")).equals(new BigInteger("0"));
    }

    public boolean millerRabin(BigInteger n) {
        long rounds = log2(n),s = 0;
        BigInteger t = n.clearBit(0),
                x,a;
        while(t.getLowestSetBit() != 0 && t.bitLength() > 4) {
            t = t.shiftRight(1);
            s ++; // Я полагаю, что наши потребности не настолько высоки, что степень выйдет за long
        }
        for(int i = 0; i < rounds; i ++) {

            if(n.bitLength() > 10)
                a = n.shiftRight(n.bitLength() - 10);
            else
                a = n; // Это ужасно, но я МОГУ, а поэтому почему бы и нет!
            a = new BigInteger(String.valueOf(2 + (long) (Math.random() * (Long.parseLong(String.valueOf(a)) - 2 ) ))); // выбор случайного свидетеля простоты
            x = a.modPow(t, n);

            if(x.bitCount() == 1 || x.equals(n.subtract(new BigInteger("1"))))
                continue;
            for (int j = 0; j < s - 1; j++) {
                x = x.modPow(new BigInteger("2"),n);
                if(x.bitCount() == 1)
                    return false;
                if (x.equals(n.subtract(new BigInteger("1"))))
                    break;
            }
            if (x.equals(n.subtract(new BigInteger("1"))))
                continue;
            return false;
        }
        return true; // вероятно простое
    }
}
