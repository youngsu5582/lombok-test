## Lombok

[공식 링크](https://projectlombok.org/)

> Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.

> - 프로젝트 롬복은 에디터와 빌드 도구에 자동으로 연결되는 자바 라이브러리로, 자바를 더욱 멋지게 만들어줍니다.
> - 어노테이션 하나로 클래스에 모든 기능을 갖춘 빌더를 추가하고, 로깅 변수를 자동화하는 등 더 이상 다른 getter나 equals 메서드를 작성하지 않아도 됩니다.

### Features

#### NoArgsConstructor

빈 생성자를 생성한다.

- access : 생성자의 접근 제한자 지정 (default:public)
- force : 컴파일 타임에 기본값 0 / null / false 로 설정

```java
@NoArgsConstructor(force = true)
public class Customer {
    private final String product;
    private final String firstName;
    private final String secondName;
}
```
멤버변수에 final 을 사용하고 싶다면, force 를 무조건 사용해야 한다

```java
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    private final String product;
    private final String firstName;
    private final String secondName;
}
```
JPA Entity 생성 편의를 위해 사용한다면 생성자는 protected 로 사용하자
( 불필요한 접근 방지, protected 로 해당 엔티티를 상속받는 프록시 생성 가능 )


#### Builder

빌더 패턴으로 생성 가능하게 해준다.
```java
public class Customer {
    private Long id;
    private String product;
    private String firstName;
    private String secondName;
    
    @Builder
    public Customer(final String product, final String firstName, final String secondName) {
        this.product = product;
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
```
생성자에도 사용 가능하다. ( 클래스 최상단에도 가능 )
-> id 를 제외한 나머지 멤버변수로만 생성하므로, 불필요한 setter 노출 방지



#### ToString

toString 자동으로 오버라이딩

- @Exclude : `toString` 에 사용하지 않을 멤버 변수 지정
- @Include : `toString`에 사용할 멤버 변수 지정 

```java
@ToString
public class Customer {

    @ToString.Exclude
    private Long id;
    private String product;
    private String firstName;
    private String secondName;
}
```
자동으로 Include 되므로, Exclude 만 명시하자

##### EqualsAndHashCode

```java
@EqualsAndHashCode(of = {"firstName", "secondName"})
public class Customer {
    private Long id;
    private String product;
    private String firstName;
    private String secondName;
}
```

특정 변수로, equals 와 hashCode 를 오버라이딩

사실, `@EqualsAndHashCode` 와 `@Equals` 는 불필요한 거 같다.

#### slf4j

sl4fj? : Simple Logging Facade For Java
-> 로깅 프레임워크에 대한 추상화(인터페이스) 역활을 해주는 라이브러리

```java
@Slf4j
public class Customer {
    public void introduce() {
        log.info("로깅 : 자기소개");
        System.out.println("제 이름은 " + firstName + secondName + " 입니다.");
    }
}
```
선언부 없이 log 를 바로 사용 가능하다
