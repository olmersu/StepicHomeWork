package com.Algorithms.HaffmanCode.HaffmanDecoding;

/**
 * Created by bogomolov on 13.09.2016.
 * Задача на программирование: декодирование Хаффмана
 * Восстановите строку по её коду и беспрефиксному коду символов.
 * В первой строке входного файла заданы два целых числа k
 * и l через пробел — количество различных букв, встречающихся в строке, и размер получившейся закодированной строки, соответственно. В следующих k
 * строках записаны коды букв в формате "letter: code". Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке.
 * В качестве букв могут встречаться лишь строчные буквы латинского алфавита; каждая из этих букв встречается в строке хотя бы один раз.
 * Наконец, в последней строке записана закодированная строка. Исходная строка и коды всех букв непусты.
 * Заданный код таков, что закодированная строка имеет минимальный возможный размер.
 * В первой строке выходного файла выведите строку s.
 * Она должна состоять из строчных букв латинского алфавита. Гарантируется, что длина правильного ответа не превосходит 10^4 символов.
 * Sample Input 1:
 * 1 1
 * a: 0
 * 0
 * Sample Output 1:
 * a
 * Sample Input 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 * Sample Output 2:
 * abacabad
 *
 * Passed test #1. a
 * Passed test #2. abacabad
 * Passed test #3. aa
 * Passed test #4. bbb
 * Passed test #5. zzzzzyyyyy
 * Passed test #6. zzzzztyyyyy
 * Passed test #7. bcbdbcb
 * Passed test #8. bcbdbcbebcbdbcb
 * Passed test #9. accepted
 * Passed test #10. wronganswer
 * Passed test #11. abcdefghijklmnopqrstuvwxyz
 * Passed test #12. abcdefghijklmnopqrstuvwxyzzyxw
 * Passed test #13. abbcccddddeeeeeffffffggggggghh
 * Passed test #14. kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
 * Passed test #15. klkklkklkklkklkklkklkklkklkklk
 * Passed test #16. qpmsqwnupropwqxutpnvonxtvpvqxp
 * Passed test #17. xvvwxvvwyxvxvyvxvywvwvzzvwyxzx
 * Passed test #18. fgkiiijfkkhikgfghjihijkkiggghk
 * Passed test #19. onppopppohnoopnpmopnppopppoppi
 * Passed test #20. xxxyyyxxxyxxxyxxxyxyxxxxxxxxxx
 * Passed test #21. dcbbfdbdbdfdddbdccdbdddbddfddd
 * Passed test #22. fbbfffdfdfdddbddfcddfdddffddcf
 * Passed test #23. bcffbcffbcffbcffbcffbcffbcffbc
 * Passed test #24. cnjiokopgfglkpqhneeqheijihjgho
 * Passed test #25. tzstrnuuwsktzjsftmttfzcgguenqx
 * Passed test #26. zxwvupnnzmrzyyywfeztlxuzurvwxp
 * Passed test #27. nrzxzqmyyqxxywxrvwstxxzvkxxoox
 * Passed test #28. bprhxrvrlpsjrrbsrnvxxdshvrhxfl
 * Passed test #29. rzwgrpyxuytrwxwsrxirwsznjzsyxm
 * Passed test #30. yzyzzzyzyyzxzxxzxzxyxxyxzzxyxz
 * Passed test #31. uuzuzxcyvvywvycuxxaabzabvucwzu
 * Passed test #32. fjlnfzpxjlpnxuvbzhjddhyjblfrhp
 * Passed test #33. yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
 * Passed test #34. zfvktmuuczyszqiqqliorkwsopoxqi
 * Passed test #35. bcbdbcbebcbdbcbfbcbdbcbebcbdbc
 * Passed test #36. nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
 * Passed test #37. ukmdmvugcktggxpcrdrmditmijomgf
 * Passed test #38. iimtkfkkhspwxwimxvuhukfukfuxii
 * Passed test #39. wwncqevguhjuimunfzrqdddjegqlev
 * Passed test #40. yssyredetijiglytsttoicayxexesj
 * Passed test #41. jegjjebwyxekiqjtjjushpkfiutjtz
 * Passed test #42. rfmlgjfwizqemsidvemixfnnoojxtx
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HaffmanDecoding {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int volumeDict = in.nextInt();
        int amountBits = in.nextInt();
        Map<String, String> dictionary = new HashMap<>();
        for (int i=0; i < volumeDict; i++){
            String s1 = in.next().replaceAll(":","");
            String s2 = in.next();
            dictionary.put(s2,s1);
        }
//        String [] dictItems = {"a: 0","b: 10","c: 110","d: 111"};
//        String bitsString = "01001100100111";
//        String [] dictItems = {"a: 100","p: 101","t: 010","d: 011","c: 00","e: 11"};
//        String bitsString = "10000001110101011011";
        String bitsString = in.next();
/*
        for (String s: dictItems) {
            String [] sym = s.split(":\\s");
            dictionary.put(sym[1],sym[0]);
        }
*/
        char [] ch = bitsString.toCharArray();
        String temp = "";
        for (char c: ch) {
            temp+=String.valueOf(c);
            if(dictionary.containsKey(temp)){
                System.out.print(dictionary.get(temp));
                temp="";
            }
        }
    }
}

