/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.util.oauth;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.json.bind.JsonbException;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

/**
 * Thread-safe implementation of a OAuth2 Helper class
 * 
 * @author Marchino Alessandro
 *
 */
public abstract class OAuth2Helper {
	/** The logger */
	protected final LogUtil log = new LogUtil(getClass());
	/** The URL to invoke */
	final String oAuthURL;
	/** The consumer key */
	final String consumerKey;
	/** The consumer secret */
	final String consumerSecret;
	/** The connection timeout */
	final long timeout;
	/** The threshold after which the token is to be considered stale */
	private final long staleThreshold;
	/** The HTTP client to use */
	//private final HttpClient httpClient;
	/** timeout 30 secondi */
	// private final long tokenInvocationTimeout = 30000;
	/** timeout 2 secondi */
	private final long tokenInvocationTimeout = 2000;
	/** The lock to synchronize on */
	private final Lock lock;
	/** The token */
	private volatile String token;
	/** The time when the token was got */
	volatile long timeWhenGet;
	/** The token expiration */
	private volatile Long expires;
	/** The counter for token retrieval */
	private volatile AtomicLong counter;

	/**
	 * Package-protected constructor, to be used only by subclasses
	 * 
	 * @param oAuthURL       the URL to be used
	 * @param consumerKey    the consumer key
	 * @param consumerSecret the consumer secret
	 * @param timeout        the connection timeout
	 * @param staleThreshold the threshold for stale keys
	 */
	OAuth2Helper(String oAuthURL, String consumerKey, String consumerSecret, long timeout, long staleThreshold) {
		this.oAuthURL = oAuthURL;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.timeout = timeout;
		this.staleThreshold = staleThreshold;
		this.lock = new ReentrantLock();
		this.counter = new AtomicLong();
		//this.httpClient = HttpClient.newBuilder().version(Version.HTTP_1_1).followRedirects(Redirect.NORMAL).connectTimeout(Duration.ofMillis(timeout)).build();
	}

	/**
	 * Retrieves the token
	 * 
	 * @return the token
	 */
	public String getToken() {
		checkStaleness();
		if (token != null) {
			this.counter.incrementAndGet();
			return this.token;
		}

		if (token == null) {
			initToken();
		}
		return this.token;
	}

	/**
	 * Retrieves the token
	 * 
	 * @return the token
	 */
	@Deprecated
	public String getTokenLock() {
		checkStaleness();
		try {
			this.lock.lock();
			if (token == null) {
				initToken();
			}
			return this.token;
		} finally {
			this.lock.unlock();
		}
	}

	/**
	 * Initializes a new token
	 * 
	 * @return the token
	 */
	@Deprecated
	public String newTokenLock() {
		try {
			this.lock.lock();
			this.token = null;
		} finally {
			this.lock.unlock();
		}
		return this.getToken();
	}

	public String newToken() {
		this.token = null;
		return this.getToken();
	}

	/**
	 * @return the time when get
	 */
	public long getTimeWhenGet() {
		return timeWhenGet;
	}

	/**
	 * @return the counter
	 */
	public long getCounter() {
		return counter.get();
	}

	/**
	 * @return the expires
	 */
	public Long getExpires() {
		return expires;
	}

	/**
	 * Initialization of a token
	 */
	abstract void initToken();

	/**
	 * Checks for token staleness
	 */
	private void checkStaleness() {
		if (this.token == null || this.expires == null) {
			return;
		}
		long now = System.currentTimeMillis();
		Duration timeSinceTokenCreation = Duration.between(Instant.ofEpochMilli(this.timeWhenGet),
				Instant.ofEpochMilli(now));
		long tokenLifetimeSeconds = timeSinceTokenCreation.getSeconds();
		if (this.expires.longValue() - tokenLifetimeSeconds < this.staleThreshold) {
			// Stale token: refresh
			this.token = null;
			this.expires = null;
		}
	}

	/**
	 * Invokes the HTTP request and parses the received token
	 * 
	 * @param request the http request
	 */
	protected boolean invokeAndParseToken(HttpRequest request,int numProva) {
		final String methodName = "invokeAndParseToken";
		HttpResponse<String> httpResponse;
		try {
			this.timeWhenGet = System.currentTimeMillis();
			this.token = null;
			this.expires = null;
			try {
				log.info(methodName, "chiamo per stacco del token");		
				HttpClient httpClientLocal = HttpClient.newBuilder().version(Version.HTTP_1_1).followRedirects(Redirect.NORMAL).connectTimeout(Duration.ofMillis(timeout)).build();				
				//httpClientLocal          = HttpClient.newBuilder().version(Version.HTTP_1_1).followRedirects(Redirect.NORMAL).connectTimeout(Duration.ofMillis(timeout)).build();
				httpResponse = httpClientLocal.send(request,HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
				log.info(methodName, "Dopo stacco del token " + numProva);
			} catch (IOException e) {
				log.error(methodName, "Fatal transport error: " + e.getMessage());
				return false;
			}
		} catch (InterruptedException e) {
			log.error(methodName, "Fatal transport error: " + e.getMessage());
			return false;
		}
		if (httpResponse.statusCode() != Status.OK.getStatusCode()) {
			log.error(methodName, "Wrong HTTP status code: " + httpResponse.statusCode());
			return false;
		}
		log.trace(methodName, () -> "Response: " + httpResponse.body());
		OAuth2Response response;
		try {
			response = JsonUtility.deserialize(httpResponse.body(), OAuth2Response.class);
		} catch (JsonbException e) {
			log.error(methodName, "Fatal error in JSON deserialization: " + e.getMessage());
			return false;
		}
		if (response.getError() != null) {
			log.error(methodName, "Unexpected reply: " + httpResponse.body());
			return false;
		}
		this.token = response.getAccessToken();
		this.expires = response.getExpiresIn();
		if (this.token == null || this.expires == null) {
			log.error(methodName, "Unexpected reply: " + httpResponse.body());
			return false;
		}
		log.debug(methodName, "Token retrieved");
		return true;
	}

	/**
	 * Invokes the HTTP request and parses the received token
	 * non buttare potrebbe tornare utile 
	 * @param request the http request
	 */
	@Deprecated
	protected boolean invokeAndParseTokenAsync(HttpRequest request) {
		final String methodName = "invokeAndParseToken";
		HttpResponse<String> httpResponse;
		try {
			this.timeWhenGet = System.currentTimeMillis();
			this.token = null;
			this.expires = null;
			log.info(methodName, "chiamo per stacco del token");
			HttpClient httpClientLocal = HttpClient.newBuilder().version(Version.HTTP_1_1).followRedirects(Redirect.NORMAL).connectTimeout(Duration.ofMillis(timeout)).build();				
			CompletableFuture<HttpResponse<String>> future = httpClientLocal.sendAsync(request,HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
			log.info(methodName, "dopo lo stacco del token");
			httpResponse = future.get(this.tokenInvocationTimeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException e) {
			log.error(methodName, "Fatal transport error: " + e.getMessage());
			return false;
		} catch (TimeoutException e) {
			log.fatal(methodName, "Timeout in OAuth2 token invocation. URL " + this.oAuthURL + " did not respond in "+ this.tokenInvocationTimeout + "ms");
			return false;
		}
		if (httpResponse.statusCode() != Status.OK.getStatusCode()) {
			log.error(methodName, "Wrong HTTP status code: " + httpResponse.statusCode());
			return false;
		}

		log.trace(methodName, () -> "Response: " + httpResponse.body());
		OAuth2Response response;
		try {
			response = JsonUtility.deserialize(httpResponse.body(), OAuth2Response.class);
		} catch (JsonbException e) {
			log.error(methodName, "Fatal error in JSON deserialization: " + e.getMessage());
			return false;
		}
		if (response.getError() != null) {
			log.error(methodName, "Unexpected reply: " + httpResponse.body());
			return false;
		}

		this.token = response.getAccessToken();
		this.expires = response.getExpiresIn();
		if (this.token == null || this.expires == null) {
			log.error(methodName, "Unexpected reply: " + httpResponse.body());
			return false;
		}

		log.info(methodName, "Token retrieved");
		return true;
	}

	protected void retryInitToken(HttpRequest request, int tries) {
		final String methodName = "retryInitToken";
		boolean isTokenActive = false;
		// provo a staccare il token per tries volte
		for (int i = 0; i < tries; i++) {
			try {
				if (!isTokenActive) {
					if (i > 0) {
						long numMilliSec = 2000;
						try {
							Thread.sleep(numMilliSec);
						} catch (InterruptedException e) {
							log.error(methodName, "InterruptedException ", e);
						}
					}
					isTokenActive = invokeAndParseToken(request,i);
					if(!isTokenActive) {
						log.error(methodName, "token non attivo ");
					}
				}
			} catch (Exception e) {
				log.error(methodName, "Exception ", e);
				isTokenActive = invokeAndParseToken(request,i);
			}
		}
		if (!isTokenActive) {
			log.error(methodName, "**********************************************************************");
			log.error(methodName, "Problemi sul token verificare le configurazioni sul DB tabella parametri");
			log.error(methodName, "OAuth2 URI: " + this.oAuthURL + " - Consumer key: " + this.consumerKey);
			log.error(methodName, "**********************************************************************");
		}
	}

	public static class Builder {
		private static final ConcurrentMap<String, OAuth2Helper> INSTANCES = new ConcurrentHashMap<>();
		private static final String SEPARATOR = "|__|";

		private String oAuthURL;
		private String consumerKey;
		private String consumerSecret;
		private OAuth2Helper.Type type;
		private boolean reuseInstance = true;
		private long timeout = 2000;
		// In seconds
		private long staleThreshold = 10;

		public Builder oAuthURL(String url) {
			this.oAuthURL = url;
			return this;
		}

		public Builder consumerKey(String key) {
			this.consumerKey = key;
			return this;
		}

		public Builder consumerSecret(String secret) {
			this.consumerSecret = secret;
			return this;
		}

		public Builder reuseInstance(boolean doReuse) {
			this.reuseInstance = doReuse;
			return this;
		}

		public Builder timeout(long connectTimeout) {
			this.timeout = connectTimeout;
			return this;
		}

		public Builder staleThreshold(long threshold) {
			this.staleThreshold = threshold;
			return this;
		}

		public Builder type(OAuth2Helper.Type helperType) {
			this.type = helperType;
			return this;
		}

		public OAuth2Helper build() {
			if (this.oAuthURL == null || this.consumerKey == null || this.consumerSecret == null || this.type == null) {
				throw new IllegalStateException("Type, URL, Key and Secret MUST be set");
			}
			if (!reuseInstance) {
				// Simply create a new instance, ignoring whatever may be cached
				return type.initOAuth2Helper(oAuthURL, consumerKey, consumerSecret, timeout, staleThreshold);
			}

			String instanceKey = new StringBuilder().append(oAuthURL).append(SEPARATOR).append(consumerKey).toString();
			OAuth2Helper res = null;
			synchronized (SEPARATOR) {
				res = INSTANCES.compute(instanceKey, (key, oldValue) -> oldValue != null ? oldValue
						: type.initOAuth2Helper(oAuthURL, consumerKey, consumerSecret, timeout, staleThreshold));
			}
			return res;
		}
	}

	/**
	 * The type of the OAuth2 helper to be used
	 */
	public static enum Type {

		/** OAuth2 helper via the use of the grant in the body */
		BODY_GRANT {
			@Override
			OAuth2Helper initOAuth2Helper(String oAuthURL, String consumerKey, String consumerSecret, long timeout,
					long staleThreshold) {
				return new OAuth2HelperBodyGrant(oAuthURL, consumerKey, consumerSecret, timeout, staleThreshold);
			}
		},
		/** OAuth2 helper via the use of the grant in the header */
		HEADER_GRANT {
			@Override
			OAuth2Helper initOAuth2Helper(String oAuthURL, String consumerKey, String consumerSecret, long timeout,
					long staleThreshold) {
				return new OAuth2HelperHeaderGrant(oAuthURL, consumerKey, consumerSecret, timeout, staleThreshold);
			}
		};

		// private static final ConcurrentMap<String, OAuth2Helper> HELPERS_MAP = new
		// ConcurrentHashMap<>();

		/**
		 * Initialization of the OAuth2Helper
		 * 
		 * @param oAuthURL       the URL to be used
		 * @param consumerKey    the consumer key
		 * @param consumerSecret the consumer secret
		 * @param timeout        the connection timeout
		 * @param staleThreshold the threshold for stale keys
		 * @return the helper
		 */
		abstract OAuth2Helper initOAuth2Helper(String oAuthURL, String consumerKey, String consumerSecret, long timeout,
				long staleThreshold);

		/**
		 * Compone la chiave per trovare il valore in mappa
		 * 
		 * @param oAuthURL       the URL to be used
		 * @param consumerKey    the consumer key
		 * @param consumerSecret the consumer secret
		 * @param timeout        the connection timeout
		 * @param staleThreshold the threshold for stale keys
		 * @param cls            the implementation class
		 * @return the key
		 */
		// private static String toKey(String oAuthURL, String consumerKey, String
		// consumerSecret, long timeout, long staleThreshold, Class<?> cls) {
		// return oAuthURL + "||" + consumerKey + "||" + consumerSecret + "||" + timeout
		// + "||" + staleThreshold + "||" + cls.getCanonicalName();
		// }
	}

}
