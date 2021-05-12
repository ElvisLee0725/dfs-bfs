// Iterating each item in shopping cart, if it matches the first item in codeList, keep looking
// to find if the rest of items matches the code list? If so, set global found to be true
// Else, move on to the next item in cart and keep doing previous step
// Return 0 at the end
// Time: O(mn*k) while mn is the length of codeList and k is the length of shopping cart.
// Space: O(1)
public class Solution {
    public static void main(String[] args) {
        String [][] codeList = {{"apple", "apple"}, {"banana", "anything", "banana"}};
        String [] shoppingCart = new String[]{"apple", "banana", "apple", "banana", "orange", "banana"};
        System.out.println(new Solution().freshPromotion(codeList, shoppingCart));
    }
    private int freshPromotion(String[][] codeList, String[] shoppingCart) {
        boolean winner = false;
        int countCode = 0;
        for(String [] arr : codeList) {
            countCode += arr.length;
        }
        for(int i = 0; i < shoppingCart.length && i <= shoppingCart.length - countCode; i++) {
            String item = shoppingCart[i];
            int index = i;
            if(item.equals(codeList[0][0])) {
                String code;
                boolean notFound = false;
                for(int j = 0; j < codeList.length; j++) {
                    for(int k = 0; k < codeList[0].length; k++) {
                        item = shoppingCart[index];
                        code = codeList[j][k];
                        if(item.equals(code) || code.equals("anything")) {
                            index++;
                        }
                        else {
                            notFound = true;
                            break;
                        }

                        if(index == shoppingCart.length && k != codeList[0].length - 1) {
                            notFound = true;
                            break;
                        }
                    }
                    if(notFound || (index == shoppingCart.length && j != codeList.length - 1)) {
                        break;
                    }
                    else {
                        winner = true;
                    }
                }
            }

            if(winner) {
                return 1;
            }
        }
        return 0;
    }
}
