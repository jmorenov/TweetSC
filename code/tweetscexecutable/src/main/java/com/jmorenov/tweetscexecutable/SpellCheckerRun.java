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
    public static SpellCheckerRunResult run(String[] args) throws ParseException {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        cmd = parser.parse(options, args);

        if (isCorrectTextFuncionality(cmd)) {
            return new SpellCheckerRunResult(runCorrectText(cmd, method), options);
        } else if (isEvaluateTweetNormFuncionality(cmd)) {
            return new SpellCheckerRunResult(runTweetNorm(cmd, method), options);
        } else {
            throw new ParseException("Missing required option: text");
        }

        textValue = cmd.getOptionValue("text");
        annotatedFileValue = cmd.getOptionValue("annotatedFile");
        idsFileValue = cmd.getOptionValue("idsFile");
        tweetsFileValue = cmd.getOptionValue("tweetsFile");
        workingDirectoryValue = cmd.getOptionValue("workingDirectory");
        resultFileValue = cmd.getOptionValue("resultFile");
        methodValue = cmd.getOptionValue("method");

        Method method;

        if (isCorrectTextFuncionality(args)) {
            String text = getTextFromArguments(args);

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
     * @param cmd CommandLine
     * @return Boolean to control if the execution is to correct a text
     */
    private static boolean isCorrectTextFuncionality(CommandLine cmd) {
        return cmd.getOptionValue("text") != null;
    }

    /**
     * Method to control if the execution is to evaluate tweet norm funcionality.
     * @param cmd
     * @return Boolean to control if the execution is to evaluate tweet norm funcionality
     */
    private static boolean isEvaluateTweetNormFuncionality(CommandLine cmd) {
        return (cmd.getOptionValue("annotatedFile")  != null
                && (cmd.getOptionValue("idsFile") != null || cmd.getOptionValue("tweetsFile") != null));
    }

    private static String runCorrectText(CommandLine cmd, Method method) {
        SpellChecker spellChecker = new SpellChecker(method);
        String textValue = cmd.getOptionValue("text");

        return spellChecker.correctText(textValue);
    }

    private static String runTweetNorm(CommandLine cmd, Method method) {
        String annotatedFileValue = cmd.getOptionValue("annotatedFile");
        String idsFileValue = cmd.getOptionValue("idsFile");
        String tweetsFileValue = cmd.getOptionValue("tweetsFile");
        String workingDirectoryValue = cmd.getOptionValue("workingDirectory");
        String resultFileValue = cmd.getOptionValue("resultFile");
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