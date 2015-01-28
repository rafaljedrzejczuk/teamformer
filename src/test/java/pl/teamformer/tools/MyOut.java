package pl.teamformer.tools;

public final class MyOut {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public static final void myOut(String message) {
                String top = "";
                final int count = 80;
                for (int i = 0; i < count; i++) {
                        top += '#';
                        if (i % 4 == 0)
                                message = " " + message;
                }
                System.out.println("\033[31m" + top + "\n" + message + "  \033[0m");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
