package org.example.lomboktest.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class CustomerTest {
    @Test
    @DisplayName("NoArgsConstructor 의 접근 제한자를 지정할 수 있다.")
    void some() throws NoSuchMethodException {
        final var constructor = Customer.class.getDeclaredConstructor();
        final var modifier = constructor.getModifiers();

        assertThat(Modifier.isProtected(modifier)).isTrue();
    }

    @Test
    @DisplayName("ToString.Exclude 를 통해 문자열에서 값을 제외할 수 있다.")
    void some1() {
        final var customer = new Customer("아샷추", "조", "이썬");
        assertThat(customer.toString()).doesNotContain("id");
    }

    @Test
    @DisplayName("Builder 를 생성자에 지정시, 생성자에 있는 매개변수만 활용해 빌더를 생성한다.")
    void some2() throws NoSuchMethodException {
        final var builderClass = Customer.CustomerBuilder.class;
        assertThatCode(() -> builderClass.getDeclaredMethod("firstName", String.class))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> builderClass.getMethod("id"))
                .isInstanceOf(NoSuchMethodException.class);
    }

    @Test
    @DisplayName("EqualsAndHashCode 를 통해 명시적으로 동등성 비교 변수를 지정 가능하다.")
    void some3() {
        final var customer1 = new Customer("고기", "조", "이썬");
        final var customer2 = new Customer("야채", "조", "이썬");

        assertThat(customer1.hashCode()).isEqualTo(customer2.hashCode());
    }
}
