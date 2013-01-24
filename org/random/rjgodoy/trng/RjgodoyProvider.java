package org.random.rjgodoy.trng;
import java.security.Provider;
import java.security.SecureRandomSpi;

//Properties

/**<tt>java.security.Provider</tt> implementating client for TRNG www.random.org from Mads Haahr and random.irb.hr at Ruder Boskovic Institute<P>
 * This provider defines the <tt>SecureRandom</tt> service <tt>MH_TRNG</tt>.
 *
 * @author Javier Godoy
 */
public final class RjgodoyProvider extends Provider {

	/**Initializes an instance of <tt>RjgodoyProvider</tt>*/
	public RjgodoyProvider() {
		super("RJGODOY",1.0,"RJGodoy Provider implementing client for TRNG www.random.org and random.irb.hr");
		synchronized (RjgodoyProvider.class) {
			init_sr("QRBG",hr.irb.random.QrbgSecureRandomSpi.class);
			init_sr("MH_TRNG",MH_SecureRandomSpi.class);
		}
	}

	private void init_sr(String algorithm, Class<? extends SecureRandomSpi> klass) {
		setProperty("SecureRandom."+algorithm, klass.getName());
	}
}
