package hu.cookerybook.desktop.validation;

public class InputValidation {

    /**
     * Function for validate usernames. username contains a minimum of 3 and a maximum of 20 characters and consists of
     * letters of alphabet and numbers.
     *
     * @param username Input text string.
     * @return boolean Returns true if the username is valid and false if not.
     */

    public static boolean validateUsername(String username) {
        return !username.equals("") && username.matches("^[a-zA-Z0-9]{3,20}$");
    }

    /**
     * Function for validate email addresses. The regex that validates the addresses implements the RFC 5322 format.
     * This regex is only works with 99.99% accuracy, which means there could be some valid emails that will be rejected.
     *
     * @param email Input email string.
     * @return boolean Returns true if the email is valid and false if not.
     */

    public static boolean validateEmail(String email) {
        return (!email.equals("") && email.matches("(?im)^(?=.{1,64}@)(?:(\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\"@)|((?:[0-9a-z](?:\\.(?!\\.)|[-!#\\$%&'\\*\\+/=\\?\\^`\\{\\}\\|~\\w])*)?[0-9a-z]@))(?=.{1,255}$)(?:(\\[(?:\\d{1,3}\\.){3}\\d{1,3}\\])|((?:(?=.{1,63}\\.)[0-9a-z][-\\w]*[0-9a-z]*\\.)+[a-z0-9][\\-a-z0-9]{0,22}[a-z0-9])|((?=.{1,63}$)[0-9a-z][-\\w]*))$"));
    }

    /**
     * This function validates text without numbers and other characters.
     *
     * @param name Input string.
     * @return Returns true if text is valid and false if not.
     */

    public static boolean validateName(String name) {
       return name.equals("") || name.matches("^[a-zA-Z][a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð .,'-]*$");
    }

    /**
     * Validates if password is between 8 and 32 characters.
     *
     * @param password Input password string.
     * @return Returns true if password's length is between 8 and 32.
     */

    public static boolean validatePassword(String password) {
        return (!password.equals("")) && password.length() >= 8 && password.length() <= 32;
    }

}
