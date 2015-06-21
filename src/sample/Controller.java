package sample;


import javafx.event.ActionEvent;

public class Controller {
    public int log2(long n) {
        return 63 - Long.numberOfLeadingZeros(n);
    }
    public long fPower(long a,long n, long m) {
        long res = 1;
        while (n > 0) {
            if(n % 2 == 1 || Long.numberOfLeadingZeros(a) <= 32){
                System.out.println("res" + res);
                res %= m;
/*                if(Long.numberOfLeadingZeros(res) + Long.numberOfLeadingZeros(a) < 63)
                    throw new Error("Числа слишком большие!");*/
                res *= a;
                n--;
            }
            else {
                a *= a;
                n >>= 1;
            }
        }
        return res % m;
    }
    public boolean fisherMiller(long n) {
        if(n % 2 == 0 || n % 5 == 0 || n % 7 == 0)
            return false;
        int r = log2(n), s = 0;long t = n - 1,a,x;
        while(t % 2 != 1 && t / 2 > 1) {
            t /= 2;
            s ++;
        }

        for(int i = 0; i < r; i ++) {
            a = 2 + (long) (Math.random() * (n - 2)); // выбор случайного свидетеля простоты
            if(Long.numberOfLeadingZeros(a) < 53)
                a >>= 53 - Long.numberOfLeadingZeros(a);
            x = fPower(a, t, n);

            if(x == 1 || x == n - 1)
                continue;
            for (int j = 0; j < s - 1; j++) {
                x = fPower(x,2,n);
                if(x == 1)
                    return false;
                if (x == n - 1)
                    break;
            }
            if (x == n - 1)
                continue;
            return false;
        }
        return true; // вероятно простое
    }


}
