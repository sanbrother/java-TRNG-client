package hr.irb.random;

import java.io.IOError;
import java.security.SecureRandomSpi;

/** <tt>SecureRandomSpi</tt> implementation that uses the Quantum Random Bit Generator service from
* the <A href="http://random.irb.hr">Ruder Boskovic Institute</A>.
*
* Before the first instance of this class is constructed, USERNAME and PASSWORD must be defined
* Other properties are related to the TRNG behaviour, then they are instance-specific and may be specified before constructing each instance.
* See {@link QrbgSecureRandom}.<P>
*
* @author Javier Godoy
*/
public class QrbgSecureRandomSpi extends SecureRandomSpi {

	private static QRBG qrbg;

	public QrbgSecureRandomSpi() {
		String username = System.getProperty(QrbgSecureRandom.USERNAME);
		String password = System.getProperty(QrbgSecureRandom.PASSWORD);
		synchronized (QrbgSecureRandomSpi.class) {
			if (qrbg==null) {
				 qrbg = new QRBG(username,password);
		}}
	}

  @Override
  protected byte[] engineGenerateSeed(int numBytes) {

  	byte bytes[]=new byte[numBytes];
  	engineNextBytes(bytes);
  	return bytes;
  }

  @Override
  protected void engineSetSeed(byte[] seed) {;}


	@Override
	protected void engineNextBytes(byte[] bytes) {
		try {qrbg.getBytes(bytes, bytes.length);}
		catch (Exception e) {
			e.printStackTrace();
			throw new IOError(e);
		}
	}
}
