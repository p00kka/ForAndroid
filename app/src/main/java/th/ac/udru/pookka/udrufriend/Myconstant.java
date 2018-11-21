package th.ac.udru.pookka.udrufriend;

public class Myconstant {
    private int [] iconInts = new int[]{R.drawable.ic_action_menu1,
                                        R.drawable.ic_action_menu2,
                                        R.drawable.ic_action_menu3,
                                        R.drawable.ic_action_exit};
    private String[] titleStrings = new String[]{"My Friends",
                                                "My Map",
                                                "My Info",
                                                "Sign Out"};

    public int[] getIconInts() {
        return iconInts;
    }

    public String[] getTitleStrings() {
        return titleStrings;
    }
}
