package com.tastycode.remy.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.MethodCallback;

/**
 * Registers all beans in a context to the given {@code target}, using
 * the method name {@code methodName}.  Only beans matching the method
 * parameter type will be registered. 
 * 
 * Simple example:
 * <code>
 *	&lt;bean id="registry" class="com.tastycode.remy.util.RegisteringBeanPostProcessor"&gt;
 *	  &lt;property name="target" ref="registry"/&gt;
 *	  &lt;property name="methodName" value="registerPlugin"/&gt;
 *	&lt;/bean&gt;
 * </code>
 * 
 * Alternatively, if the {@code target} is not set, then the target and methodName are set via
 * the {@code bean} XML id.
 * 
 *  This equivalent example {@code target} as bean {@code registry}, and calling the {@code registerPlugin} method
 *  for each object in the context which matches the type of the single-parameter of {@code register#registerPlugin}:
 *  <code>
 *  	&lt;bean id="registry.registerPlugin" 
 *  			class="com.tastycode.remy.util.RegisteringBeanPostProcessor"/&gt;
 *  </code>
 * 
 * Optionally, beans can be filtered based on {@code beanClass} or {@code beanPatterns}. 
 * 
 * For instance, given a context with the following beans of the given types:
 * <code>
 *   <ul>
 *     <li>aObject: Map
 *     <li>registry: Registry
 *     <li>plugin1: Plugin1 implements Plugin
 *     <li>plugin2: Plugin2 implements Plugin
 *     <li>extra:   Plugin3 implements Plugin
 *   </ul>
 * </code>
 * then the following processor attributes:
 * <code>
 *   <ul>
 *     <li>beanClass = Plugin
 *     <li>target = registry
 *     <li>methodName = "registerPlugin"
 *   </ul>
 * </code>
 * would result in the three calls to {@code registry#registerPlugin(bean)} with
 * {@code plugin1}, {@code plugin2} and {@code extra} as arguments.  
 * 
 * Adding an additional attribute:
 * <code>
 *   <ul>
 *     <li>...
 *     <li>beanPattern = "plugin*"
 *   </ul>
 * </code>
 * would result in only {@code plugin1} and {@code plugin2} being registered.
 *
 * Does not implementation {@link BeanPostProcessor} for two reasons:
 * <ol> 
 *   <li>a {@code BeanPostProcessor} is only in effect after instantiation, leading to definition order dependencies
 *   <li>a {@code BeanPostProcessor} looks at all beans, where at this object only needs to look at 1 {@code target}
 * </ol>
 * Implementation contains commented out sections which could later be revived BeanPostProcessor interface is desired.
 * @author broberts
 */
public class RegisteringBeanPostProcessor extends BeanFilteringSupport
implements
//BeanPostProcessor,
//ApplicationContextAware,
InitializingBean,
ApplicationListener,
BeanNameAware,
BeanFactoryAware
{
	public static final String METHOD_NAME_SEPARATOR = "\\.";
	public static final String METHOD_NAME_SUFFIX_SEPARATOR = "-";
	public static final String BEAN_METHOD_SUFFIX_PATTERN_STRING 
			= "(.*)"   
			+ METHOD_NAME_SEPARATOR + "(.*?)"  
			+ "("  + METHOD_NAME_SUFFIX_SEPARATOR + "(.*)" + ")?"
		    + "$";
	protected static final Pattern BEAN_METHOD_SUFFIX_PATTERN = Pattern.compile(BEAN_METHOD_SUFFIX_PATTERN_STRING);
	
//	private ApplicationContext context;
	
	private Object target;
	private Method targetMethod;
	private String methodName;
	
	// Alternative 
	private String beanName;
	private String suffix;
	private BeanFactory beanFactory;
	// public methods
	/**
	 * Register all matching beans in {@code context} to {@link #target}, using {@link #targetMethod}.
	 * 
	 * @param beanFactory bean context from which to get matching beans
	 */
	public void registerBeans(ListableBeanFactory beanFactory, Object obj) {
		Map<String, ?> beans = gatherQualifiedBeans(beanFactory);
		if (log.isLoggable(Level.FINE))
		{
			log.fine("Registering beans to: " + obj + " with " + beans.values());
		}
		
		for (Entry<String, ?> entry : beans.entrySet()) {
			registerBean(obj, entry.getKey(), entry.getValue());
		}
	}
	
	public void registerBeans(ListableBeanFactory beanFactory) {
		registerBeans(beanFactory, target);
	}

	public void afterPropertiesSet() throws Exception {
		determineTargetAndMethodFromBeanName();
		Assert.notNull(target, "target object must not be null");
		determineMethodOrMethodName();
		determineBeanClassIfNull();
	}

// // Uncomment out for BeanPostProcessor implementation
//	public Object postProcessAfterInitialization(Object obj, String s)
//			throws BeansException {
//		if (target.equals(obj))
//		{
//			registerBeans(context, obj);
//		}
//		return obj;
//	}
//
//	public Object postProcessBeforeInitialization(Object obj, String s)
//			throws BeansException {
//		return obj;
//	}
//
//	public void setApplicationContext(ApplicationContext context)
//			throws BeansException {
//		this.context = context;
//	}

//// Alternate method of hooking into bean processing, but less descriptive given name
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent)
		{
			ContextRefreshedEvent refreshed = (ContextRefreshedEvent) event;
			registerBeans(refreshed.getApplicationContext(), target);
		}
	}
	
	// protected methods
	protected void registerBean(Object obj, String name, Object bean) {
		log.info("Registering bean " + name + " with " + obj + " via " + methodName);
		ReflectionUtils.invokeMethod(targetMethod, obj, new Object[] { bean });
	}

	@SuppressWarnings("unchecked")
	protected void determineBeanClassIfNull() {
		if (getBeanClass() == null)
		{
			// Use parameter type if not set
			setBeanClass((Class<?>) targetMethod.getParameterTypes()[0]);
		}
	}

	protected void determineTargetAndMethodFromBeanName() {
		Matcher matcher = BEAN_METHOD_SUFFIX_PATTERN.matcher(beanName);
		
		Assert.state(matcher.matches(), "Cannot split method from target name, use form 'bean.method'");

		String targetName = matcher.group(1);
		String methodName = matcher.group(2);
		this.suffix       = matcher.group(3);
		
		// Find the target and method name, if not already set
		if (getTarget() == null)
		{
			setTarget(beanFactory.getBean(targetName));
		}
		
		if (getMethodName() == null)
		{
			setMethodName(methodName);
		}
	}

	protected void determineMethodOrMethodName() {
		if (targetMethod == null)
		{
			// Find method by name "methodName".  
			try {
				// Default to Object.class if beanClass not defined
				Class<?> paramClass = getBeanClass() == null ? Object.class : getBeanClass();
				targetMethod = findMatchingMethod(target.getClass(), methodName, paramClass);
			} catch (Exception e) {
				throw new IllegalStateException("Cannot retrieve method: " + getFullyQualifiedMethodName(), e);
			}
		}
		
		if (methodName == null)
		{
			// method must now be filled in
			methodName = targetMethod.getName();
		}
	}

	protected Method findMatchingMethod(Class<?> clazz,
			final String name, Class<?> parameterType) {
		CollectingMethodCallback allMethods = new CollectingMethodCallback();
		ReflectionUtils.doWithMethods(clazz, allMethods, new NameMethodAndParameterSizeFilter(name, 1));
		
		return findBestMethod(allMethods.getMethods());
	}

	protected Method findBestMethod(List<Method> methods) {
		if (methods.size() > 1)
		{
			log.info("Multiple methods possible found for " + getFullyQualifiedMethodName() + ", consider overriding #determineBestMethod or filing bug for better matching.  Methods found: " + methods);
		}
		
		 // TODO Should find closest for argument types, not just "a" method
		return methods.get(0);
	}

	protected String getFullyQualifiedMethodName() {
		return target.getClass().getCanonicalName() + "#" + methodName;
	}

	
	// Setter + getter methods
	/**
	 * Method name in target class
	 */
	public void setMethodName(String method)
	{
		this.methodName = method;
	}
	
	/**
	 * Method name in target class
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * Target method name in target class
	 * Should mostly call {@link #setMethodName(String)} instead.
	 */
	public void setTargetMethod(Method method) {
		this.targetMethod = method;
	}
	
	/**
	 * Target method to call on {@link #target}. 
	 */
	public Method getTargetMethod() {
		return targetMethod;
	}

	/**
	 * Target object of all {@code beans}.  
	 *  
	 * @param target
	 */
	public void setTarget(Object target) {
		this.target = target;
	}
	
	/**
	 * Target object
	 * @return target object
	 */
	public Object getTarget() {
		return target;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	// Inner classes
	/**
	 * Collections all methods given. 
	 */
	public static class CollectingMethodCallback implements MethodCallback {
		private List<Method> methods = new ArrayList<Method>();

		public void doWith(Method method) throws IllegalArgumentException,
				IllegalAccessException {
			methods.add(method);
		}

		public List<Method> getMethods() {
			return methods;
		}
	}

	/**
	 * Only matches methods with the given name. 
	 */
	public static class NameMethodAndParameterSizeFilter implements
			ReflectionUtils.MethodFilter {
		private final String name;
		private final int parameterCount;

		public NameMethodAndParameterSizeFilter(String name, int parameterCount) {
			this.name = name;
			this.parameterCount = parameterCount;
		}

		public boolean matches(Method method) {
			return method.getParameterTypes().length == parameterCount 
			       && method.getName().equals(name);
		}
	}
	
	private static Logger log = Logger.getLogger(RegisteringBeanPostProcessor.class.getName());
}
