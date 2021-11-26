package pl.jsystems.qa.qajunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tags({@Tag("junit"), @Tag("smoke"), @Tag("param")})
@DisplayName("Param test")
public class JavaParamTest {

    @DisplayName("Parameter test with value int tape")
    @ParameterizedTest(name = "Parameter test with value {0}")
    @ValueSource(ints = {5, 15, 25})
    public void firstParamTest(int number) {

        assertEquals(number % 5, 0);

    }

    @DisplayName("Parameter test for string say hello")
    @ParameterizedTest(name = "Parameter test: {0}")
    @ValueSource(strings = {"Hello", "Hello junit", "Hello students"})
    public void stringParamTest(String param) {

        assertThat(param).contains("Hello");

    }

    @DisplayName("Parameter test with multi param.")
    @ParameterizedTest(name = "Parameter test for multi param: {0} , {1}")
//    @CsvSource(value = {"Hello; 5", "Hello, junit; 15", "Hello students; 25"}, delimiter = ';')
    @CsvSource(value = {"Hello, 5", "'Hello, junit', 15", "Hello students, 25"})
    public void multiParamTest(String param, int number) {

        assertThat(param).contains("Hello");
        assertEquals(number % 5, 0);

    }

    @DisplayName("Parameter test with multi param.")
    @ParameterizedTest(name = "Parameter test for multi param: {0} , {1}")
    @CsvFileSource(resources = "/plik.csv", delimiter = ';')
    public void multiCsvParamTest(String param, int number) {

        assertThat(param).contains("Hello");
        assertEquals(number % 5, 0);

    }

    @DisplayName("Parameter Enum test")
    @ParameterizedTest(name = "Enum test: {0}")
    @EnumSource(value = ParamEnum.class)
    public void stringParamTest(ParamEnum param) {

        assertThat(param.toString()).contains("ENUM");

    }

    enum ParamEnum {
        ENUM_ONE,
        ENUM_TWO
    }



}
