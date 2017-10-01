import static org.junit.Assert.*;

import org.junit.*;

public class LaboonCoinTest {

    // Re-usable LaboonCoin reference.
    LaboonCoin _l;

    // Create a new LaboonCoin instance before each test.
    @Before
    public void setup() {
	_l = new LaboonCoin();
    }
    
    // Assert that creating a new LaboonCoin instance
    // does not return a null reference
    @Test
    public void testLaboonCoinExists() {
	assertNotNull(_l);
    }

    // Creating a block String with valid data should return
    // a valid block.  This includes printing data out
    // normally, the previous hash and current hash as a hex
    // representations of themself, and the nonce in hex
    // repretentation.  Values should be delimited by
    // pipes.
    @Test
    public void testCreateBlockValid() {
	int prevHash = 0x0;
	int nonce = 0x16f2;
	int hash = 0x545ac;
	String validBlock = _l.createBlock("kitten", prevHash, nonce, hash);
	assertEquals("kitten|00000000|000016f2|000545ac", validBlock);
    }

    // Trying to represent a blockchain which has no blocks
    // in it should just return an empty string.
    @Test
    public void testGetBlockChainNoElements() {
	// By default, _l.blockchain has no elements in it.
	// So we can just test it immediately.
	String blockChain = _l.getBlockChain();
	assertEquals("", blockChain);
    }
    

    // Viewing the blockchain as a full string which has valid
    // elements should include all of the elements.  Note that the
    // final element DOES have a trailing \n!
    @Test
    public void testGetBlockChainElements() {
	_l.blockchain.add("TESTBLOCK1|00000000|000010e9|000a3cd8");
	_l.blockchain.add("TESTBLOCK2|000a3cd8|00002ca8|0008ff30");
	_l.blockchain.add("TESTBLOCK3|0008ff30|00002171|0009f908");
	String blockChain = _l.getBlockChain();
	assertEquals("TESTBLOCK1|00000000|000010e9|000a3cd8\nTESTBLOCK2|000a3cd8|00002ca8|0008ff30\nTESTBLOCK3|0008ff30|00002171|0009f908\n", blockChain);
    }
	    
    // Assert that entering an emptry String hashes to 10000000.
	// Note that this tests the integer value directly hashed from the method.
	@Test
	public void testHashNullValue() {
		String emptyString = "";
		int emptyHash = _l.hash(emptyString);
		assertEquals(10000000, emptyHash);
	}
	
	
	// Assert that entering the string "laboon" hashes to 4e4587d6.
	// Note that this tests the string and hex value of the integer
	// hashed from the method.
	@Test
	public void testHashLaboon() {
		String laboon = "laboon";
		int laboonHash = _l.hash(laboon);
		String lHashed = String.format("%08x", laboonHash);
		assertEquals("4e4587d6", lHashed);
	}
	
	
	// Assert that entering a String with a space will 
	// successfuly convert the space to its proper ASCII value.
	// The final hash value should be 1312d020.
	@Test
	public void testHashSpace() {
		String spacey = " ";
		int spaceHash = _l.hash(spacey);
		String intHashed = String.format("%08x", spaceHash);
		assertEquals("1312d020", intHashed);
	}
	
	
	// Assert that, given a difficulty value of 3 (the default),
	// that a hash with exactly 3 leading 0s will be valid.
	@Test
	public void testValidHashDefault() {
		int def = 3;
		int testHash = 738238;
		boolean isValid = _l.validHash(def, testHash);
		assertTrue(isValid);
	}
	
	
	// Assert that, given a difficulty value of 3 (the default),
	// that a hash with less than 3 leading 0s will be invalid.
	@Test
	public void testInvalidHashDefault() {
		int def = 3;
		int testHash = 44775772;
		boolean isValid = _l.validHash(def, testHash);
		assertFalse(isValid);
	}
	
	
	// Assert that, given a difficulty level of 3 (the default),
	// that a hash with more than 3 leading 0s will be valid.
	@Test
	public void testValidHashGrtDefault() {
		int def = 3;
		int testHash = 212;
		boolean isValid = _l.validHash(def, testHash);
		assertTrue(isValid);
	}
    
}