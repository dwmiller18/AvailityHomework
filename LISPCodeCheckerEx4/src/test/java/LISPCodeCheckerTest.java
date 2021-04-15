import org.junit.Assert;
import org.junit.Test;

public class LISPCodeCheckerTest {

    @Test
    public void isValidTrueTest() {
        String testString = "()";
        LISPCodeChecker codeChecker = new LISPCodeChecker();
        Assert.assertTrue(codeChecker.isValid(testString));

        testString = "(T(e($)T)s)";
        Assert.assertTrue(codeChecker.isValid(testString));

        testString = "Testing((()))";
        Assert.assertTrue(codeChecker.isValid(testString));

        testString = "((()))Testing";
        Assert.assertTrue(codeChecker.isValid(testString));

        testString = "(!(@(#($(%(^(&(*(!)@)#)$)%)^)&)*)!)";
        Assert.assertTrue(codeChecker.isValid(testString));

        testString = "((((((((((((()))))))))))))";
        Assert.assertTrue(codeChecker.isValid(testString));

        testString = "Test1234!@#$";
        Assert.assertTrue(codeChecker.isValid(testString));

        testString = "(((      )))";
        Assert.assertTrue(codeChecker.isValid(testString));
    }

    @Test
    public void isValidFalseTest() {
        String testString = "())))";
        LISPCodeChecker codeChecker = new LISPCodeChecker();
        Assert.assertFalse(codeChecker.isValid(testString));

        testString = "((((((((((((())";
        Assert.assertFalse(codeChecker.isValid(testString));

        testString = ")";
        Assert.assertFalse(codeChecker.isValid(testString));

        testString = ")))))))))))))))))";
        Assert.assertFalse(codeChecker.isValid(testString));

        testString = "(T(e(s(t(i(n(g(((((())";
        Assert.assertFalse(codeChecker.isValid(testString));

        testString = "((((      ))))))))";
        Assert.assertFalse(codeChecker.isValid(testString));

        testString = "((((((((((((())";
        Assert.assertFalse(codeChecker.isValid(testString));

        testString = "test((((((((((((())time";
        Assert.assertFalse(codeChecker.isValid(testString));
    }
}
