package org.example.lomboktest.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(of = {"firstName", "secondName"})
@Slf4j
public class Customer {

    @ToString.Exclude
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

    public void introduce() {
        log.info("로깅 : 자기소개");
        System.out.println("제 이름은 " + firstName + secondName + " 입니다.");
    }
}
