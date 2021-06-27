package com.wyh.newsclient.model;

import java.util.List;

/*
一般是根据网络请求的数据来定义网络模型的一般的做法:
1)[]: 代表的是List列表
2){}:代表的是Object
3)普通:基本类型
 */
public class TabNewsData {
    public TabNewsDetailData data;


    public class TabNewsDetailData {
        public String more;
        public List<News> news;
        public List<TopNews> topnews;

        @Override
        public String toString() {
            return "TabNewsDetailData{" +
                    "more='" + more + '\'' +
                    ", news=" + news +
                    ", topnews=" + topnews +
                    '}';
        }
    }

    public class News {
        public int id;
        public String listimage;
        public String pubdate;
        public String title;
        public String url;

        @Override
        public String toString() {
            return "News{" +
                    "id=" + id +
                    ", listimage='" + listimage + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public class TopNews {
        public int id;
        public String topimage;
        public String pubdate;
        public String title;
        public String url;

        @Override
        public String toString() {
            return "TopNews{" +
                    "id=" + id +
                    ", topimage='" + topimage + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TabNewsData{" +
                "data=" + data +
                '}';
    }
}
