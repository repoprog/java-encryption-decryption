package encryptdecrypt;

class SelectionContext {

    private SelectionAlgorithm algorithm;

    public SelectionContext(SelectionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String process(String message, int key) {

        return this.algorithm.getMessage(message, key);
    }
}

interface SelectionAlgorithm {
    /**
     * Each class Algorithm ↓ have to implement this method.
     * Method returns message processed by specific algorithm
     */
    String getMessage(String message, int key);
}

class UnicodeDecryptAlgorithm implements SelectionAlgorithm {
    public String getMessage(String message, int key) {
        char[] arr = message.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (arr[i] - key);
        }
        return String.valueOf(arr);
    }
}

class UnicodeEncryptAlgorithm implements SelectionAlgorithm {
    public String getMessage(String message, int key) {
        char[] arr = message.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (arr[i] + key);
        }
        return String.valueOf(arr);
    }
}

class ShiftDecryptAlgorithm implements SelectionAlgorithm {
    public String getMessage(String message, int key) {
        char[] arr = message.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isAlphabetic(arr[i])) {
                if ((arr[i] - key) < 'a') {  // if new char is before a
                    int gotoEnd = ('a' - (arr[i] - key) - 1);  //how many chars from end
                    arr[i] = (char) (('z' - gotoEnd));
                } else {
                    arr[i] = (char) (arr[i] - key);
                }
            }
        }
        return String.valueOf(arr);
    }
}

class ShiftEncryptAlgorithm implements SelectionAlgorithm {
    public String getMessage(String message, int key) {
        char[] arr = message.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isAlphabetic(arr[i])) {
                if ((arr[i] + key) > 'z') { // in new char is after z
                    int startFromBeginning = (arr[i] + key) - 'z'; // how many chars from start
                    arr[i] = (char) (('a' + startFromBeginning) - 1);
                } else {
                    arr[i] = (char) (arr[i] + key);
                }
            }
        }
        return String.valueOf(arr);
    }
}