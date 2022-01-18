package encryptdecrypt;

public class Main {

    public static void main(String[] args) {
        SecurityUtils security = new SecurityUtils();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-alg":
                    security.setAlgorithm(args[i + 1]);
                    break;
                case "-mode":
                    security.setMode(args[i + 1]);
                    break;
                case "-key":
                    security.setKey(Integer.parseInt(args[i + 1]));
                    break;
                case "-in":
                    String message = security.readFile(args[i + 1]);
                    security.setMessage(message);
                    break;
                case "-data":
                    security.setMessage(args[i + 1]);
                    break;
                case "-out":
                    security.setWriteToFilePath(args[i + 1]);
                    break;
            }
        }
        security.processMessage();
    }
}