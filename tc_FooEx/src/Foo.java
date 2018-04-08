/**
 * Created by manib on 4/7/18.
 */

public interface Foo {
    String hello_word = "Hello World";
    String ANY_NEW_TOPICS = "Are there any new topics?";

    String greet();
    String question(String question);
    String questionStrictly(String question) throws InvalidQuestion;

    String WHAT_IS_TODAYS_TOPIC = "What is todays topic?";
    String YES_NEW_TOPICS_AVAILABLE = "Yes";
    String NO_NEW_TOPIC = "No";
    String TOPIC_MOCKITO = "Mockito";
    int getPrice(String tutorial);
    void bye();

}
