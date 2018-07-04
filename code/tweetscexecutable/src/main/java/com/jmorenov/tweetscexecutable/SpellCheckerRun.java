package com.jmorenov.tweetscexecutable;

import com.jmorenov.tweetsccore.evaluation.TweetNormEvaluator;
import com.jmorenov.tweetsccore.method.DictionaryMethod;
import com.jmorenov.tweetsccore.method.Method;
import com.jmorenov.tweetsccore.method.TweetSCMethod;
import com.jmorenov.tweetsccore.spellchecker.SpellChecker;
import org.apache.commons.cli.*;

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
     */
    public static void main(String[] args) {
        System.out.println(run(args));
    }

    /**
     * Method to run the Spell checker.
     * @param args List of String
     * @return String with the result
     */
    public static String run(String[] args) {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        String textValue, annotatedFileValue, idsFileValue,
                tweetsFileValue, workingDirectoryValue, resultFileValue, methodValue;

        cmd = parser.parse(options, args);

        textValue = cmd.getOptionValue("text");
        annotatedFileValue = cmd.getOptionValue("annotatedFile");
        idsFileValue = cmd.getOptionValue("idsFile");
        tweetsFileValue = cmd.getOptionValue("tweetsFile");
        workingDirectoryValue = cmd.getOptionValue("workingDirectory");
        resultFileValue = cmd.getOptionValue("resultFile");
        methodValue = cmd.getOptionValue("method");

        if (textValue == null && (annotatedFileValue == null || )) {

        }

        Method method;

        try {
            String methodToUseDescription = getValueOfArgument(args, "-method");
            method = getMethodFromArgument(methodToUseDescription);
        } catch (Exception ex) {
            return "Execution error";
        }

        if (isCorrectTextFuncionality(args)) {
            String text = getTextFromArguments(args);
            SpellChecker spellChecker = new SpellChecker(method);

            return spellChecker.correctText(text);
        }   else if (isEvaluateTweetNormFuncionality(args)) {
            String annotatedFile = getValueOfArgument(args, "-annotatedFile");
            String idsFile = getValueOfArgument(args, "-idsFile");
            String tweetFile = getValueOfArgument(args, "-tweetsFile");
            //String evaluatorScriptFile = getValueOfArgument(args, "-evaluatorScriptFile");
            String workingDirectory = getValueOfArgument(args, "-workingDirectory");
            String resultFile = getValueOfArgument(args, "-resultFile");

            TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator(annotatedFile, true);

            tweetNormEvaluator.setWorkingDirectory(workingDirectory);
            tweetNormEvaluator.setTweetsFile(tweetFile);
            //tweetNormEvaluator.setEvaluatorScriptFile(evaluatorScriptFile);
            tweetNormEvaluator.setIdsFile(idsFile);
            tweetNormEvaluator.setResultFile(resultFile);

            try {
                return tweetNormEvaluator.evalutate(method).toString();
            } catch (Exception ex) {
                return "Error on evaluation execution";
            }
        } else {
            return "Arguments error: -text, -anotatedFile, -idsFile, -tweetsFile, -workingDirectory, -resultFile, -method";
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
        if (methodToUseDescription.equals("TweetSCMethod")) {
            return new TweetSCMethod();
        } else {
            return new DictionaryMethod();
        }
    }

    private static Options getOptions() {
        Options options = new Options();

        Option text = new Option("t", "text", true, "Text input");
        options.addOption(text);

        Option annotatedFile = new Option("af", "annotatedFile", true, "Tweets annotated file");
        options.addOption(annotatedFile);

        Option idsFile = new Option("if", "idsFile", true, "Tweets ids file");
        options.addOption(idsFile);

        Option tweetsFile = new Option("tf", "tweetsFile", true, "Tweets content file");
        options.addOption(tweetsFile);

        Option workingDirectory = new Option("w", "workingDirectory", true, "Working directory");
        options.addOption(workingDirectory);

        Option resultFile = new Option("rf", "resultFile", true, "Output result file");
        options.addOption(resultFile);

        Option method = new Option("m", "method", true, "Method");
        options.addOption(method);

        return options;
    }
}