import java.util.Arrays;
/*
身分證、統一證規則:https://cynthiachuang.github.io/CheckUID/
*/

public class Lab2 {

    public static void main(String[] args) {
        isValidIDorRCNumber("A123456789");
    }

    static boolean isValidIDorRCNumber(String str) {

        if (str == null || "".equals(str)) {
            return false;
        }

        final char[] pidCharArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        // 原身分證英文字應轉換為10~33，這裡直接作個位數*9+10(身分證英文轉成ASCII)
        // 第一碼英文字比對數值(身分證)
        final int[] pidIDInt = { 1, 10, 19, 28, 37, 46, 55, 64, 39, 73, 82, 2, 11, 20, 48, 29, 38, 47, 56, 65, 74, 83, 21, 3, 12, 30 };

        // 第一碼英文字比對數值(統一證)
        // 原居留證第一碼英文字應轉換為10~33，十位數*1，個位數*9，這裡直接作[(十位數*1) mod 10] + [(個位數*9) mod 10]
        final int[] pidResidentFirstInt = { 1, 10, 9, 8, 7, 6, 5, 4, 9, 3, 2, 2, 11, 10, 8, 9, 8, 7, 6, 5, 4, 3, 11, 3, 12, 10 };
        // 第二碼英文字比對數值(統一證)
        // 原居留證第二碼英文字應轉換為10~33，並僅取個位數*8，這裡直接取[(個位數*8) mod 10]
        final int[] pidResidentSecondInt = {0, 8, 6, 4, 2, 0, 8, 6, 2, 4, 2, 0, 8, 6, 0, 4, 2, 0, 8, 6, 4, 2, 6, 0, 8, 4};

        str = str.toUpperCase();// 轉換大寫(將輸入字串英文字轉換成大寫)
        final char[] strArr = str.toCharArray();// 字串轉成char陣列()

        int verifyNum = 0;// 家總結果

        /* 檢查身分證字號 */

        // [A-Z]{1}1碼 [1-2]{1}2碼 [0-9]{8}3到9碼, matches:是否完全符合數值
        if (str.matches("[A-Z]{1}[1-2]{1}[0-9]{8}")) {

            // 第一碼:Arrays.binarySearch(尋找陣列內是否有該數值，並回傳該數值位置(index)),
            //       再用回傳值去尋找pidIDInt[]內相對的數字。
            verifyNum = verifyNum + pidIDInt[Arrays.binarySearch(pidCharArray, strArr[0])];
            // System.out.println(verifyNum);

            // 第二~九碼:從第二個數字開始*8依序遞減相乘
            for (int i = 1, j = 8; i < 9; i++, j--) {
                verifyNum += Character.digit(strArr[i], 10) * j;
            }
            // System.out.println(verifyNum);
            // (1*8)+(2*7)+(3*6)+(4*5)+(5*4)+(6*3)+(7*2)+(8*1)+(9*0),結果verifyNum = 121

            verifyNum = (10 - (verifyNum % 10)) % 10;
            // 檢查碼:將 (verifyNum % 10 = 1 -> 10 - 1 = 9 -> 9 % 10) = 9
            // System.out.println(verifyNum);
            // System.out.println(Character.digit(strArr[9], 10));

            return verifyNum == Character.digit(strArr[9], 10);
            // 比對尾碼與檢查碼是否相等 9 == 9 (相等為正確身分證，不相等為錯誤身分證)
        }

        /* 檢查統一證(居留證)編號 */
        verifyNum = 0;// 將上一個身分證計算結果歸零

        // [A-Z]{1}1碼 [1-2]{1}2碼 [0-9]{8}3到9碼, matches:是否完全符合數值
        if (str.matches("[A-Z]{1}[A-D]{1}[0-9]{8}")) {

            // 第一碼:Arrays.binarySearch(尋找陣列內是否有該數值，並回傳該數值位置(index)),
            //       再用回傳值去尋找pidResidentFirstInt[]內相對的數字。
            verifyNum += pidResidentFirstInt[Arrays.binarySearch(pidCharArray, strArr[0])];
            // System.out.println(verifyNum);

            // 第二碼:Arrays.binarySearch(尋找陣列內是否有該數值，並回傳該數值位置(index)),
            //       再用回傳值去尋找pidResidentSecondInt[]相對的數字。
            verifyNum += pidResidentSecondInt[Arrays.binarySearch(pidCharArray, strArr[1])];
            // System.out.println(verifyNum);

            // 第三~八碼:從第三個數字開始*9依序遞減相乘
            for (int i = 2, j = 7; i < 9; i++, j--) {
                verifyNum += Character.digit(strArr[i], 10) * j;
            }
            // System.out.println(verifyNum);

            verifyNum = (10 - (verifyNum % 10)) % 10;
            // 檢查碼:將 (verifyNum % 10 = 1 -> 10 - 1 = 9 -> 9 % 10) = 9
            // System.out.println(verifyNum);
            // System.out.println(Character.digit(strArr[9], 10));

            return verifyNum == Character.digit(strArr[9], 10);
            // 比對尾碼與檢查碼是否相等 9 == 9 (相等為正確統一證，不相等為錯誤統一證)
        }
        // 都不符合回傳 false
        return false;
    }
}


