package com.jmorenov.tweetscexecutable;

import com.jmorenov.tweetsccore.evaluation.TweetNormEvaluator;
import com.jmorenov.tweetsccore.method.DictionaryMethod;
import com.jmorenov.tweetsccore.method.Method;
import com.jmorenov.tweetsccore.spellchecker.SpellChecker;

import java.io.IOException;
import java.util.Arrays;

/**
 * SpellCheckerRun class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class SpellCheckerRun {
    /**
     * Main method
     * @param args String[] with the arguments.
     * @throws IOException when the files are not found.
     */
    public static void main(String[] args) throws IOException {
        if (isCorrectTextFuncionality(args)) {
            String text = getTextFromArguments(args);
            SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());

            System.out.println(spellChecker.correctText(text));
        } else if (isEvaluateTweetNormFuncionality(args)) {
            String annotatedFile = getValueOfArgument(args, "-annotatedFile");
            String idsFile = getValueOfArgument(args, "-idsFile");
            String tweetFile = getValueOfArgument(args, "-tweetsFile");
            //String evaluatorScriptFile = getValueOfArgument(args, "-evaluatorScriptFile");
            String workingDirectory = getValueOfArgument(args, "-workingDirectory");
            String resultFile = getValueOfArgument(args, "-resultFile");
            String methodToUseDescription = getValueOfArgument(args, "-method");

            TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator(annotatedFile, true);

            tweetNormEvaluator.setWorkingDirectory(workingDirectory);
            tweetNormEvaluator.setTweetsFile(tweetFile);
            //tweetNormEvaluator.setEvaluatorScriptFile(evaluatorScriptFile);
            tweetNormEvaluator.setIdsFile(idsFile);
            tweetNormEvaluator.setResultFile(resultFile);

            Method method = getMethodFromArgument(methodToUseDescription);

            System.out.println(tweetNormEvaluator.evalutate(method));
        } else {
            System.out.println("Arguments error: -text, -anotatedFile, -idsFile, -tweetsFile, -workingDirectory, -resultFile, -method");
        }
    }

    /**
     * Method to control if the execution is to correct a text.
     * @param arguments String[] arguments of the execution.
     * @return Boolean to control if the execution is to correct a text.
     */
    private static boolean isCorrectTextFuncionality(String[] arguments) {
        return Arrays.asList(arguments).contains("-text");
    }

    /**
     * Method to control if the execution is to evaluate tweet norm funcionality.
     * @param arguments String[] arguments of the execution.
     * @return Boolean to control if the execution is to evaluate tweet norm funcionality.
     */
    private static boolean isEvaluateTweetNormFuncionality(String[] arguments) {
        return (Arrays.asList(arguments).contains("-annotatedFile")
                && (Arrays.asList(arguments).contains("-idsFile") || Arrays.asList(arguments).contains("-tweetsFile")));
    }

    /**
     * Method to get the text from the arguments.
     * @param arguments String[] arguments of the execution.
     * @return String with the text.
     */
    private static String getTextFromArguments(String[] arguments) {
        int argumentIndex = Arrays.asList(arguments).indexOf("-text") + 1;
        String text = arguments[argumentIndex];

        for (int i = argumentIndex + 1; i < arguments.length; i++) {
            text = text.concat(" " + arguments[i]);
        }

        return text;
    }

    /**
     * Method to get the value of a argument.
     * @param arguments String[] arguments of the execution.
     * @param argument String with the argument to get the value.
     * @return String with the value of the argument.
     */
    private static String getValueOfArgument(String[] arguments, String argument) {
        int argumentIndex = Arrays.asList(arguments).indexOf(argument);

        if (argumentIndex != -1) {
            return arguments[argumentIndex+1];
        } else {
            return "";
        }
    }

    private static Method getMethodFromArgument(String methodToUseDescription) throws IOException {
        if (methodToUseDescription.equals("")) {
            return new DictionaryMethod();
        }

        return new DictionaryMethod();
    }
}