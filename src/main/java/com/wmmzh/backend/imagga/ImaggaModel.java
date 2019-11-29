package com.wmmzh.backend.imagga;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImaggaModel {

    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("status")
    @Expose
    private Status status;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public static class Result {

        @SerializedName("tags")
        @Expose
        private List<Tag> tags = null;

        public List<Tag> getTags() {
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }

    }


    public static class Status {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("type")
        @Expose
        private String type;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }


    public static class Tag {

        @SerializedName("confidence")
        @Expose
        private Double confidence;
        @SerializedName("tag")
        @Expose
        private Tag_ tag;

        public Double getConfidence() {
            return confidence;
        }

        public void setConfidence(Double confidence) {
            this.confidence = confidence;
        }

        public Tag_ getTag() {
            return tag;
        }

        public void setTag(Tag_ tag) {
            this.tag = tag;
        }


        public static class Tag_ {

            @SerializedName("de")
            @Expose
            private String de;

            public String getDe() {
                return de;
            }

            public void setDe(String de) {
                this.de = de;
            }

        }
    }
}

