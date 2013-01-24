import hr.irb.random.QrbgSecureRandom;

import java.security.SecureRandom;
import java.security.Security;

import org.random.rjgodoy.trng.RjgodoyProvider;

/**
 * Example of how to use this library with Quantum Random Bit Generator at Ruder Boskovic Institute (Croatia, http://random.irb.hr).
 * @author Javier Godoy
 */
public class TestQRBG {

	/** Sample <code>main</code> method.
	 * @param args {username, password}.
	 * @throws Throwable if the algorithm is not happy.
	 */
	public static void main(String[] args) throws Throwable {

		if (args.length<2) {
			System.out.println("Use java TestQRBG username password");
			return;
		}
		String username = args[0];
		String password = args[1];
		//IMPORTANT: system properties must be set before requesting the SecureRandom instance!

		//Sets the username and password
		System.setProperty(QrbgSecureRandom.USERNAME,username);
		System.setProperty(QrbgSecureRandom.PASSWORD,password);

		//register the provider
		Security.addProvider(new RjgodoyProvider());

		//creates a SecureRandom object using the QRBG algorithm (which access random.irb.hr)
		SecureRandom srandom = SecureRandom.getInstance("QRBG");

		System.err.flush(); Thread.yield();
		System.out.println("Now, ask for some random numbers");


		for (int i=0;i<8193;i++) System.out.println(srandom.nextInt());

	}

}
