## [토비의 봄 TV 1회 - 재사용성과 다이나믹 디스패치, 더블 디스패치](https://www.youtube.com/watch?v=s-tXAHub6vg)를 보며 따라한 예제 코드입니다.

필기한 내용은 [블로그 포스팅](https://coco-log.tistory.com/119?category=910694)에 있습니다.

## Static Dispatch

```java
public class Dispatch {
    static class Service {
        void run() {
            System.out.println("run()..");
        }
    }

    public static void main(String[] args) {
        new Service().run();
    }
}
```

이런 코드가 있다고 하자. 이 코드는 컴파일 시점에 Service클래스의 run메소드가 실행된다는 것을 이미 알고 있다. 컴파일된 바이트 코드에서도 그 정보를 확인할 수 있다. 이처럼 컴파일 시점에 이미 어느 클래스의 어느 메소드를 사용할지가 결정되는 것을 static dispatch라고 한다.

그럼 run()메소드가 오버로딩 되어 여러 개 존재하는 경우를 보자.

```java
public class Dispatch {
    static class Service {
        void run(int number) {
            System.out.println("run() " + number);
        }

        void run(String str) {
            System.out.println("run() " + str);
        }
    }

    public static void main(String[] args) {
        new Service().run(1);
        new Service().run("1");
    }
}
```

같은 이름의 run()메소드가 두 개 있지만 오버로딩 되어 있기 때문에 파라미터 타입이 다르다. 파라미터 타입으로 run()메소드를 구분할 수 있으므로 위 코드 역시 컴파일 시점에 이미 어느 메소드를 사용할지 결정되는 정적 디스패치다.

## Dymamic Dispatch

추상 클래스의 경우는 어떤지 살펴 보자.

```java
public class Dispatch2 {
    private static abstract class Service {
        abstract void run();
    }

    private static class MyService1 extends Service {
        @Override
        void run() {
            System.out.println("My Service1....");
        }
    }

    private static class MyService2 extends Service {
        @Override
        void run() {
            System.out.println("My Service2....");
        }
    }

    public static void main(String[] args) {
        List<Service> service = 
        Arrays.asList(new MyService1(), new MyService2());

        service.forEach(Service::run);
    }
}
```

이 경우는 스태틱 디스패치라고 부를 수 있을까? `Arrays.asList(new MyService1(), new MyService2())`으로 초기화 한 부분을 보면 쉽게 어떤 클래스의 run()메소드를 사용하게 될 것을 쉽게 유추할 수 있지만 컴파일러는 컴파일 시점에 참조변수 service에 어떤 오브젝트가 할당되는지 모른다. 단지 추상 클래스 Service에 run()메소드가 정의되어 있으니 Service를 구현한 어떤 클래스를 사용하겠거니 하고 넘어간다. 참조 변수 service에 어떤 오브젝트를 할당할 것인지는 런타임 시점에 결정된다. 따라서 위 코드는 Dynamic Dispatch라고 볼 수 있다.

## Double Dispatch

더블 디스패치는 쉽게 말해 다이내믹 디스패치를 두 번 사용한다는 뜻이다. 다이나믹 디스패칭부터 더블 디스패칭까지 점진적으로 발전시켜보자.

```java
public class DoubleDispatch {
    interface Post {void postOn(SNS sns);}

    static class Text implements Post {
        @Override
        public void postOn(SNS sns) {
            if (sns instanceof Facebook) System.out.println("text - Facebook");
            if (sns instanceof Twitter) System.out.println("text - Twitter");
        }
    }

    static class Picture implements Post {
        @Override
        public void postOn(SNS sns) {
            if (sns instanceof Facebook) System.out.println("Picture - Facebook");
            if (sns instanceof Twitter) System.out.println("Picture - Twitter");
        }
    }

    interface SNS {}
    static class Facebook implements SNS {}
    static class Twitter implements SNS {}


    public static void main(String[] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());

       posts.forEach(p ->
                    sns.forEach(s -> p.postOn(s)));
    }
}

```

이 코드는 텍스트로 된 게시물이나 사진으로 된 게시물을 사용 중인 SNS(facebook/twitter)에 모두 게시하는 코드다. 각각의 postOn()메소드는 instanceof로 SNS가 구체적으로 어떤 타입으로 들어오는지 검사한다. 어느 SNS에 게시하라는 요청인지 if문에서 instanceof로 검사하고 요청에 맞는 SNS에 글을 게시한다. 이것 역시 런타임 시점이 되어서야 구체적으로 어느 클래스의 postOn메소드를 사용할지 알 수 있다. 그런데 이 코드엔 문제가 좀 있다. if문으로 타입을 검사하고 있는데, 만약 새로운 SNS가 추가되면 어떤 일이 벌어질지 보자. GooglePlus라는 SNS가 추가된 코드다.

```java
      public static void main(String[] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<SNS> sns = Arrays.asList(new Facebook(), new Twitter(), new GooglePlus());

       posts.forEach(p ->
                    sns.forEach(s -> p.postOn(s)));
    }

```

postOn() 메소드는 Facebook인지 Twitter인지만 체크한다. 이 코드는 실행은 되겠지만 우리가 원하는 결과를 얻을 수 없다. 만약 postOn()메소드에 예외처리를 했다면 처리할 수 없는 타입이라는 에러가 발생했을 것이다. SNS가 늘어날 때마다 일일이 instanceof로 검사하는 코드를 추가한다면 꽤 번거로울 뿐더러 Text에만 추가하고 Picture에는 추가하지 않는 실수를 할 수도 있다. 지금은 간단한 코드지만, 좀 더 중요한 작업이라면 치명적인 에러가 발생할 수도 있고... 뭐 여러 문제가 터질지도 모른다.

그럼 메소드 내에서 타입 체크를 하지 않고 오버로딩해서 각각 다른 로직을 처리할 수 있게 만들어주면 되지 않을까?

```java
public void postOn(Facebook f) {}
public void postOn(Twiiter t) {}
public void postOn(GooglePlus g) {}

...

posts.forEach(p ->
    sns.forEach(s -> p.postOn(s))); //컴파일 에러

```

컴파일 시점에 s는 SNS타입인데 postOn은 구체 클래스를 인자로 받으니까 컴파일 에러가 발생한다. 오버로딩은 스태틱 디스패치가 사용하는 방식이므로 다이내믹 디스패치를 적용할 수 없다.  
이제 더블 디스패칭을 사용해보자. postOn에서 타입체크를 하지 않고 SNS인터페이스를 구현하는 클래스들이 게시물을 게시하는 메소드를 가지게 한다.

```java

public class DoubleDispatch {
    interface Post {void postOn(SNS sns);}

    //sns의 post를 호출하여 자기 자신을 인자로 넣는다.
    static class Text implements Post { 
        public void postOn(SNS sns) { sns.post(this); }
    }

    static class Picture implements Post {
        public void postOn(SNS sns) { sns.post(this); }
    }

    interface SNS {    //post()를 오버로딩해서 Text, Picture를 받도록 한다.
        void post(Text text);
        void post(Picture picture);
    }
    static class Facebook implements SNS { //각각의 SNS가 post() 구현
        public void post(Text text) { System.out.println("Text Facebook" );}
        public void post(Picture picture) { System.out.println("Picture Facebook"); }
    }
    static class Twitter implements SNS {
        public void post(Text text) { System.out.println("Text Facebook" );}
        public void post(Picture picture) { System.out.println("Picture Facebook"); }
    }


    public static void main(String[] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());

        posts.forEach(p ->
                sns.forEach(s -> p.postOn(s)));
    }
}

```

뭔가 괜히 꼬운 것 같은 느낌인데 왜 이렇게 해야 할까? 만약 이 상태에서 GooglePlus나 Instagram이 추가된다고 가정해보자.

```java
  //추가된 SNS
static class GooglePlus implements SNS {
    public void post(Text text) { System.out.println("Text GooglePlus" );}
    public void post(Picture picture) { System.out.println("Picture GooglePlus"); }
}

...

//main메소드에 sns들을 가져오는 부분에 GooglePlus도 추가해준다.
List<SNS> sns = Arrays.asList(new Facebook(), new Twitter(), new GooglePlus());

```

이렇게 했을 때 어떤 결과가 나올까? 위에서는 직접 postOn()메소드마다 일일이 GooglePlus를 처리하는 코드를 작성해줘야 했지만 이제 postOn()메소드를 건드리지 않고도 GooglePlus에까지 문제 없이 게시할 수 있다. 어떻게 이게 가능할까? 여기서 두 번의 다이나믹 디스패칭이 이뤄진다.  
첫 번째는 Post타입의 post를 호출하는 `p.post()`부분이다. 컴파일 시점에 구체적으로 어느 post가 실행될지 알 수 없다. 런타임 시점에 동적으로 알맞는 post()메소드를 찾아서 실행한다. 그리고 두 번째는 당연히 Text와 Picture클래스가 사용하는 SNS타입의 `sns.post()`메소드다. 이 메소드 또한 컴파일 시점에 어느 클래스의 post()메소드를 사용하게 될 지 모른다. 런타임 시점에 파라미터의 인자로 Text타입이 들어오느냐, Picture타입이 들어오느냐에 따라 어느 post()메소드를 사용하게 될 지 동적으로 결정한다. 이렇게 중첩하여 다이나믹 디스패칭을 사용하는 것을 더블 디스매치라고 부른다.  
더블 디스패치는 위에서 보는 것처럼 코드가 약간 복잡해진다는 단점이 있다. 정말 특별한 경우가 아니라면 싱글 디스패치만으로도 다형성을 적용한 로직을 짜는데 무리가 없다. 파라미터 인자로 들어오는 값의 타입을 따져서 각각 다른 로직을
