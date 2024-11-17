package com.cachedb.server.inputValidator;

public class InputValidator {
    public static boolean validateSingle(String input){
        String REGEX="(SAVE|GET|DEL) \\w+";
        return input.matches(REGEX);
    }
    public static boolean validatePUT(String input){
        String REGEX= "PUT \\w+ \\{ username: \\w+(, userdata: \\w+)? \\}( \\d+)?";
        return input.matches(REGEX);
    }
    public static boolean validateMPUT(String input){
        String REGEX= "MPUT \\[ ((\\w+ \\{ username: \\w+, userdata: \\w+ })(, )?)+ ]( \\d+)?";
        return input.matches(REGEX);
    }
    public static boolean validateMDEL(String input){
        String REGEX= "MDEL \\[ (\\w+(, )?)+ ]";
        return input.matches(REGEX);
    }
    public static boolean validateMGET(String input){
        String REGEX="MGET \\[ (\\w+(, )?)+ \\]";
        return input.matches(REGEX);
    }

}
