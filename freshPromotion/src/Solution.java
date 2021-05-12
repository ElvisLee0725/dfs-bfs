// Two Pointers:
// Use an index ofr shopping cart, and the other for codeList row index since we want to know
// if a whole row of code is matched.
// While the cart index is within boundary, check if current codeList matches? If so, move cart index
// to the length of that codeList row; Otherwise, just move cart index
// Return if the codeList index has reached the end?
public class Solution {
    public static void main(String[] args) {
        String [][] codeList = {{"apple", "apple"}, {"banana", "anything", "banana"}};
        String [] shoppingCart = new String[]{"orange", "apple", "apple", "banana", "orange", "banana"};
        System.out.println(new Solution().freshPromotion(codeList, shoppingCart));
    }
    private int freshPromotion(String[][] codeList, String[] shoppingCart) {
        int cartIdx = 0;
        int codeRowIndex = 0;
        while(cartIdx < shoppingCart.length && codeRowIndex < codeList.length) {
            if(matches(cartIdx, shoppingCart, codeList[codeRowIndex])) {
                cartIdx += codeList[codeRowIndex].length;
                codeRowIndex++;
            }
            else {
                cartIdx++;
            }
        }
        return codeRowIndex == codeList.length ? 1 : 0;
    }

    private boolean matches(int i, String [] shoppingCart, String [] list) {
        int j = 0;
        while(i < shoppingCart.length && j < list.length) {
            if(shoppingCart[i].equals(list[j]) || list[j].equals("anything")) {
                i++;
                j++;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
