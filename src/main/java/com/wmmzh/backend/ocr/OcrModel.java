package com.wmmzh.backend.ocr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OcrModel {

    @SerializedName("ParsedResults")
    @Expose
    private List<ParsedResult> parsedResults = null;
    @SerializedName("OCRExitCode")
    @Expose
    private Integer oCRExitCode;
    @SerializedName("IsErroredOnProcessing")
    @Expose
    private Boolean isErroredOnProcessing;
    @SerializedName("ProcessingTimeInMilliseconds")
    @Expose
    private String processingTimeInMilliseconds;
    @SerializedName("SearchablePDFURL")
    @Expose
    private String searchablePDFURL;

    public List<ParsedResult> getParsedResults() {
        return parsedResults;
    }

    public void setParsedResults(List<ParsedResult> parsedResults) {
        this.parsedResults = parsedResults;
    }

    public Integer getOCRExitCode() {
        return oCRExitCode;
    }

    public void setOCRExitCode(Integer oCRExitCode) {
        this.oCRExitCode = oCRExitCode;
    }

    public Boolean getIsErroredOnProcessing() {
        return isErroredOnProcessing;
    }

    public void setIsErroredOnProcessing(Boolean isErroredOnProcessing) {
        this.isErroredOnProcessing = isErroredOnProcessing;
    }

    public String getProcessingTimeInMilliseconds() {
        return processingTimeInMilliseconds;
    }

    public void setProcessingTimeInMilliseconds(String processingTimeInMilliseconds) {
        this.processingTimeInMilliseconds = processingTimeInMilliseconds;
    }

    public String getSearchablePDFURL() {
        return searchablePDFURL;
    }

    public void setSearchablePDFURL(String searchablePDFURL) {
        this.searchablePDFURL = searchablePDFURL;
    }


    public static class ParsedResult {

        @SerializedName("TextOverlay")
        @Expose
        private TextOverlay textOverlay;
        @SerializedName("TextOrientation")
        @Expose
        private String textOrientation;
        @SerializedName("FileParseExitCode")
        @Expose
        private Integer fileParseExitCode;
        @SerializedName("ParsedText")
        @Expose
        private String parsedText;
        @SerializedName("ErrorMessage")
        @Expose
        private String errorMessage;
        @SerializedName("ErrorDetails")
        @Expose
        private String errorDetails;

        public TextOverlay getTextOverlay() {
            return textOverlay;
        }

        public void setTextOverlay(TextOverlay textOverlay) {
            this.textOverlay = textOverlay;
        }

        public String getTextOrientation() {
            return textOrientation;
        }

        public void setTextOrientation(String textOrientation) {
            this.textOrientation = textOrientation;
        }

        public Integer getFileParseExitCode() {
            return fileParseExitCode;
        }

        public void setFileParseExitCode(Integer fileParseExitCode) {
            this.fileParseExitCode = fileParseExitCode;
        }

        public String getParsedText() {
            return parsedText;
        }

        public void setParsedText(String parsedText) {
            this.parsedText = parsedText;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorDetails() {
            return errorDetails;
        }

        public void setErrorDetails(String errorDetails) {
            this.errorDetails = errorDetails;
        }

    }

    public static class TextOverlay {

        @SerializedName("Lines")
        @Expose
        private List<Object> lines = null;
        @SerializedName("HasOverlay")
        @Expose
        private Boolean hasOverlay;
        @SerializedName("Message")
        @Expose
        private String message;

        public List<Object> getLines() {
            return lines;
        }

        public void setLines(List<Object> lines) {
            this.lines = lines;
        }

        public Boolean getHasOverlay() {
            return hasOverlay;
        }

        public void setHasOverlay(Boolean hasOverlay) {
            this.hasOverlay = hasOverlay;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}