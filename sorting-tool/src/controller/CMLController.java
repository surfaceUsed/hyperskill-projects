package controller;

import parse.CMLParser;
import parse.Parser;
import util.io.IOUtil;
import java.io.IOException;
import java.util.List;

public class CMLController {

    private final CMLParser cml;

    public CMLController(String[] args) {
        this.cml = new CMLParser(args);
    }

    public void runParser() {

        if (!this.cml.terminateProgram()) {

            try {

                List<String> dataToAnalyze = getDataToAnalyze();
                String receiveParsedData = Parser.parseData(this.cml.getSortingType(), this.cml.getDataType(), dataToAnalyze);
                writeData(receiveParsedData);

            } catch (IOException e) {
                System.out.println("Something went wrong when retrieving data:" + e.getMessage());
            }
        }
    }

    private List<String> getDataToAnalyze() throws IOException {
        return (this.cml.readFromFile()) ? IOUtil.readFromFile(this.cml.getInputPath()) : IOUtil.readFromSystemInput();
    }

    private void writeData(String data) throws IOException {
        if (this.cml.writeToFile()) {
            IOUtil.writeToFile(this.cml.getOutputPath(), data);
        } else {
            System.out.println(data);
        }
    }
}