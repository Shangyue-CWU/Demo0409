import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

public class MoneyTest {
    
    private Money f12CHF;
    private Money f14CHF;
    private Money fNegativeCHF;

    @Before
    public void setUp() {
        f12CHF = new Money(12, "CAT");
        f14CHF = new Money(14, "CAT");
        fNegativeCHF = new Money(-5, "CAT");
    }

    @Test
    public void testSimpleAdd() { 
        Money expected = new Money(26, "CHF"); 
        Money result = f12CHF.add(f14CHF); 
        assertEquals(expected.amount(), result.amount());
        assertEquals(expected.currency(), result.currency());
    }

    @Test
    public void testAddNegativeAmount() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            f12CHF.add(fNegativeCHF);
        });
        assertEquals("Amount must be greater than 0", thrown.getMessage());
    }

    @Test
    public void testAddZeroAmount() { 
        Money zeroCHF = new Money(0, "CHF");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            f12CHF.add(zeroCHF);
        });
        assertEquals("Amount must be greater than 0", thrown.getMessage());
    }

    @Test
    public void testAddDifferentCurrencies() { 
        Money f10USD = new Money(10, "USD");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            f12CHF.add(f10USD);
        });
        assertEquals("Currencies must match", thrown.getMessage());
    }

    @Test
    public void testAddToZeroAmount() {
        Money zeroCHF = new Money(0, "CHF");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            zeroCHF.add(f14CHF);
        });
        assertEquals("Amount must be greater than 0", thrown.getMessage());
    }

    @Test
    public void testAddNull() {
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            f12CHF.add(null);
        });
        assertEquals("Null argument", thrown.getMessage());
    }
    
    @Test
    public void testAddLargeAmount() { 
        Money largeCHF = new Money(1000000, "CHF");
        Money expected = new Money(1000012, "CHF");
        Money result = f12CHF.add(largeCHF);
        assertEquals(expected.amount(), result.amount());
        assertEquals(expected.currency(), result.currency());
    }

    @Test
    public void testAddFractionalAmount() {
        // Note: Since the Money class currently only supports int amounts,
        // we would need to modify the Money class to support floating-point numbers for this test to be relevant.
    }
}
