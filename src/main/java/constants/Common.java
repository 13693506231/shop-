package constants;

public class Common {
    public int mustInt(String num){
        int res = 1;
        try {
            res = Integer.parseInt(num);
        } catch (Exception e) {
        }
        return res;
    }
}
