package com.wyh.newsclient.model;

import java.util.List;

public class NewsData {
    public List<NewsSubData> data;

    public class NewsSubData{
        public int id;
        public String title;
        public int type;
        public List<NewsTabData> children;

        @Override
        public String toString() {
            return "NewsSubData{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", children=" + children +
                    '}';
        }
    }

    public class NewsTabData{
        public int id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "NewsTabData{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "data=" + data +
                '}';
    }
}
