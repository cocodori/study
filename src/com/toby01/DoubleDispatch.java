package com.toby01;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch {
    interface Post {void postOn(SNS sns);}

    static class Text implements Post {
        public void postOn(SNS sns) { sns.post(this); }
    }

    static class Picture implements Post {
        public void postOn(SNS sns) { sns.post(this); }
    }

    interface SNS {
        void post(Text text);
        void post(Picture picture);
    }
    static class Facebook implements SNS {
        public void post(Text text) { System.out.println("Text Facebook" );}
        public void post(Picture picture) { System.out.println("Picture Facebook"); }
    }
    static class Twitter implements SNS {
        public void post(Text text) { System.out.println("Text Facebook" );}
        public void post(Picture picture) { System.out.println("Picture Facebook"); }
    }
    static class GooglePlus implements SNS {
        public void post(Text text) { System.out.println("Text GooglePlus" );}
        public void post(Picture picture) { System.out.println("Picture GooglePlus"); }
    }

    public static void main(String[] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<SNS> sns = Arrays.asList(new Facebook(), new Twitter(), new GooglePlus());

        posts.forEach(p ->
                sns.forEach(s -> p.postOn(s)));
    }
}
