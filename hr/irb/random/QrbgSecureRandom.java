package hr.irb.random;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.random.rjgodoy.trng.RjgodoyProvider;


/** <tt>SecureRandomSpi</tt> implementation that uses the Quantum Random Bit Generator service from
 * the <A href="http://random.irb.hr">Ruder Boskovic Institute</A>.
 * Instances of this class do not require {@link RjgodoyProvider} to be registered.<P>
 *
 * Before an instance of this class is constructed (either implicitly by {@link SecureRandom} methods, or explicitly by invoking {@link #QrbgSecureRandom() this class constructor}),
 * the System properties USERNAME and PASSWORD must be defined.<P>
 *
 * @author Javier Godoy
 */
public class QrbgSecureRandom extends SecureRandom {

	/**Initializes a new instance of <code>SecureRandom</code> using the {@link QrbgSecureRandomSpi} implementation.<P>
	 * This constructor is only intended for using <tt>QrbgSecureRandom</tt> as a stand-alone class. The preferred mechanism is registering the provider {@link RjgodoyProvider}
	 * and creating an instance by {@link SecureRandom#getInstance(String, String) SecureRandom.getInstance}<tt>("RJGODOY","QRBG")</tt>.
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 *
	 */
	public QrbgSecureRandom() throws NoSuchAlgorithmException, NoSuchProviderException {
		 super(new QrbgSecureRandomSpi(),null);
	 }

	/**Username.<P>
	 * This property is <B>required</B> in order to login into the service.
	 * <P><TABLE>
	 * <TR><TD><B>Property<TD> <tt>hr.irb.random.username</tt>.
	 * <TR><TD><B>Scope<TD>global
   * </TABLE>*/
	public final static String  USERNAME = "hr.irb.random.user";

	/**Password.<P>
	 * This property is <B>required</B> in order to login into the service.
	 * <P><TABLE>
	 * <TR><TD><B>Property<TD> <tt>hr.irb.random.username</tt>.
	 * <TR><TD><B>Scope<TD>global
   * </TABLE>*/
	public final static String  PASSWORD = "hr.irb.random.pass";


}
