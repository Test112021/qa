package pl.jsystems.qa.qajunit;


import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tags({@Tag("junit"), @Tag("smoke"), @Tag("noparam")})
@DisplayName("Junit tests")
public class JunitTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("=====beforeAll======");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("=====afterAll======");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("=====beforeEach======");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("=====afterEach======");
    }

    private static final String STRING_TESTOWY = "stringTestowy";

    @Order(1)
    @RepeatedTest(5)
    @Tag("junit")
    @DisplayName("First junit test")
    public void firstTest() {

        assertEquals(2 + 3, 5);
        assertNotEquals(2 + 2, 5);
        assertTrue(STRING_TESTOWY.contains("st"));
        assertTrue(STRING_TESTOWY.endsWith("wy"));
        assertEquals(STRING_TESTOWY,  "stringTestowy");

    }


    @Order(3)
    @Disabled("Jira task: 15684")
    @Tag("junit")
    @DisplayName("Junit test")
    @Test
    public void junitTest() {
        System.out.println(0.2 * 0.2);
//        assertEquals(0.2 * 0.2 , 0.04);

        double result = new BigDecimal("0.2").multiply(new BigDecimal("0.2")).doubleValue();
        assertEquals(result , 0.04);
    }

    @Order(2)
    @Tags({@Tag("junit"), @Tag("string")})
    @DisplayName("String junit test")
    @Test
    public void stringTest() {
        String simpleString = "simpleString";
        String simple = "simpleString";

        String simpleString_2 = new String("simpleString");
        String simpleString_3 = new String("simpleString");

        assertSame("simpleString", simpleString);
        assertSame(simple, simpleString);
        assertSame(simple, simpleString);
        assertNotSame(simple, simpleString_2);

        assertTrue(simpleString == simple, "porownanie");
        assertFalse(simpleString == simpleString_3, "false");
        assertTrue(simpleString.equals(simpleString_3));

        assertEquals(simple , simpleString);

        assertEquals(simpleString, simpleString_2);

        assertEquals(simpleString_3 , simpleString_2);

    }

    @Order(4)
    @Tag("junit")
    @DisplayName("Google truth test")
    @Test
    public void googleTruthTest() {
        assertThat(STRING_TESTOWY).contains("ingT");
    }

    @Order(5)
    @Tags({@Tag("junit"), @Tag("zadania")})
    @DisplayName("Zad 1 test")
    @Test
    public void zad1() {
        String resultString = "Wordpress powers 100% of the internet";
        String expectedString = "Wordpress powers [number]% of the internet";
        assertThat(resultString).startsWith("Wordpress powers ");
        assertThat(resultString).endsWith("% of the internet");
        assertThat(resultString).matches("Wordpress powers \\d+% of the internet");
        String result = resultString.replace("Wordpress powers ", "").replace("% of the internet", "");
        assertThat(result).matches("^\\d+$");
        int intResult = new Integer(result);
        assertThat(intResult >= 0);
        assertThat(intResult).isLessThan(101);
    }


    @Tags({@Tag("nested"), @Tag("junit")})
    @DisplayName("Nested")
    @Nested
    public class NestedTest {

        @Order(2)
        @DisplayName("List test")
        @Test
        public void listTest() {
            List<Integer> result = Arrays.asList(1,2,3,4,5);
            List<Integer> expected = Arrays.asList(3,4,5);

            assertThat(result).containsAnyIn(expected);
            assertTrue(result.containsAll(expected));
            assertThat(result).hasSize(5);
            assertThat(result).containsAnyOf(1,2);

        }
        @Order(1)
        @DisplayName("Exception test")
        @Test
        public void exceptionTest() {

            GamePlay gamePlay = new GamePlay();
            Assertions.assertThrows(IllegalArgumentException.class, () -> gamePlay.play(0));

        }

    }

}
