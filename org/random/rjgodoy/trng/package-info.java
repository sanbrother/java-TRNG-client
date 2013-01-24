/**

This library provides a SecureRandom service, integrated with the Java Security API,
for accessing RANDOM.ORG (a web-service that generates true random numbers from atmospheric noise).<P>

A sample application demonstrating the usage of this library is provided in the source file <code>TRNG.java</code>.

<H4>Initialization</H4>

Some system properties may be {@linkplain java.lang.System#setProperty(String, String) set} before requesting the first SecureRandom instance.
Class {@link org.random.rjgodoy.trng.MH_SecureRandom} provides static fields with the names of each property.<P>

The only required property is {@link org.random.rjgodoy.trng.MH_SecureRandom#USER USER},
which configures the client to supply an email address in the User-Agent field of the request, as required by random.org terms of service.<P>

In order to set this property you should do either
<code>System.setProperty("org.random.rjgodoy.trng.user", "user@example.org");</code>
or
<code>System.setProperty(MH_SecureRandom.USER,"user@example.org");</code>
Note you may also set this property by
<A href="http://java.sun.com/javase/6/docs/technotes/tools/windows/java.html">
passing a <code>-D</code> parameter</A> to the Java application launcher.
(The latter form is required if the provider is configured in the java.security file)
<P>

There are other properties for enabling features such as SSL/TLS and an auxiliar PRNG, which may be used either as a backup source of pseudo-randomness (for avoiding blocking when the quota is exceeded)
 or for operating in mixed mode (i.e. TRNG xor PRNG) in case you are too paranoid to trust third party randomness.<P>
</UL>


<H4>Instantiating the generator</H4>

<DL>
<DT> Method A:
   <DD>1. Create an instance of <code>MH_SecureRandom</code>, which extends <code>SecureRandom</code>.<BR>
   2. In the code, explictly instantiate the generator as <code>SecureRandom srandom = new MH_SecureRandom();</code><P>
<DT>Method B (preferred):
   <DD>Register the provider and request an implemenation of the "MH_TRNG algorithm, which access www.random.org.
   (Note: This method may fail if the code is executing without privileges for inserting a new provider.)<BR>
   <code>
      Security.addProvider(new RjgodoyProvider());<BR>
      SecureRandom srandom = SecureRandom.getInstance("MH_TRNG");
   </code><P>
<DT>Method C:
   <DD>1.Edit the <code><i>JRE_HOME</i>/lib/security/java.security</code> file and configure the provider.<BR>
   2.In the code, instantiate the generator by doing <code>SecureRandom srandom = SecureRandom.getInstance("MH_TRNG");</code>
</DL>

<H4>Usage</H4>

Once you have instantiated the generator, as described above, you may use it as a normal {@link java.lang.Random} instance.
Indeed: it extends class <code>Random</code>, but the values are generated from atmosferic noise instead of using a linear congruential PRNG.<P>

If you haven't already, you may want to look at <A href="http://www.random.org">www.random.org</A>
in order to know more about this service.

<H4>Logging</H4>

Logging will use the <A href="http://commons.apache.org/logging/">Apache Commons Logging</A> library, if available.
If this library is not in the classpath, then a version of <A href="http://commons.apache.org/logging/apidocs/org/apache/commons/logging/impl/SimpleLog.html">org.apache.commons.logging.impl.SimpleLog</A> (already included) will be used.

Four log modules are defined by this library.
<DL>
 <DT><code>MH_SecureRandomSpi</code>
   <DD>the {@linkplain java.security.SecureRandomSpi <code>SecureRandom</code> engine} .
 <DT><code>MH_HttpClient</code>
   <DD>the HTTP client connecting to random.org
 <DT><code>MH_Daemon</code>
   <DD>the {@linkplain org.random.rjgodoy.trng.MH_SecureRandom#POOL_DAEMON pool daemon}.
 <DT><code>RjgodoyProvider</code>
   <DD>the {@link java.security.Provider} which registers <code>MH_SecureRandomSpi</code> with the Java API.
</DL>

If the default logger is used, the detail level for each modules may be configured
by setting a system property
"<code>org.apache.commons.logging.simplelog.log.<I>xxxxxx</I></code>" (where
<code><i>xxxxxx</i></code> is the module name) with one of the following values:
"trace", "debug", "info", "warn", "error", or "fatal".
*/
package org.random.rjgodoy.trng;



