import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {

  @Test
  public void positiveTest() {
    Assert.assertTrue(true);
  }

  @Test
  public void negativeTest() {
    Assert.assertTrue(false);
  }
}
