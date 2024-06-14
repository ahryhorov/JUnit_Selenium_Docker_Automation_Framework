import com.googlecode.junittoolbox.ParallelSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(ParallelSuite.class)
@SuiteClasses({
        // Додайте сюди ваші класи з тестами
        GoogleTest.class,
        NascarTest.class
        // ...
})
public class ParallelTestRunner {
}