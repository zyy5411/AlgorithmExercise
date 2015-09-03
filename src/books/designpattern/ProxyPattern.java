package books.designpattern;
//package designpattern;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
//public class ProxyPattern {
//
//	public ProxyPattern() {
//
//		Car c = new Car();
//		c.out();
//		c.runit();
//
//		Shout s = (Shout) Proxy.newProxyInstance(Shout.class.getClassLoader(),
//				new Class<?>[] { Shout.class }, new ProxyInvocationHandler());
//		Run r = (Run) Proxy.newProxyInstance(Run.class.getClassLoader(),
//				new Class<?>[] { Run.class, Shout.class },
//				new ProxyInvocationHandler());
//		// 只能将代理对象转为为相应的接口，如下代码编译出错
//		// Car cc = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(),
//		// new Class<?>[] { Car.class }, new ProxyInvocationHandler());
//
//		s.out();
//		r.runit();
//		r.runit1();
//
//	}
//
//	interface Run {
//		void runit();
//
//		void runit1();
//	}
//
//	interface Shout {
//		void out();
//	}
//
//	class Car implements Run, Shout {
//
//		@Override
//		public void out() {
//			System.out.println("car shout!");
//		}
//
//		@Override
//		public void runit() {
//			System.out.println("car run!");
//		}
//
//		@Override
//		public void runit1() {
//			System.out.println("car run1!");
//
//		}
//
//	}
//
//	class ProxyInvocationHandler implements InvocationHandler {
//
//		// 所有的方法调用，都会在这里截获转化为该方法的调用
//		@Override
//		public Object invoke(Object obj, Method method, Object[] aobj)
//				throws Throwable {
//			System.out.println("proxy invoke!" + method.getName());
//			return obj;
//		}
//	}
//
//	public static void main(String[] args) {
//		new ProxyPattern();
//	}
//
//}
//
//
//
//interface Run {
//		void runit();
//		void runit1();
//	}
//	interface Shout {
//		void out();
//	}
//
//	class Car implements Run, Shout {
//
//		@Override
//		public void out() {
//			System.out.println("car shout!");
//		}
//
//		@Override
//		public void runit() {
//			System.out.println("car run!");
//		}
//
//		@Override
//		public void runit1() {
//			System.out.println("car run1!");
//
//		}
//
//	}
//
//	class ProxyInvocationHandler implements InvocationHandler {
//
//		// 所有的方法调用，都会在这里截获。转化为该方法的调用
//		@Override
//		public Object invoke(Object obj, Method method, Object[] aobj)
//				throws Throwable {
//			System.out.println("proxy invoke!" + method.getName());
//			return obj;
//		}
//	}
//	public static void main(String[] args) {
//
//		Car c = new Car();
//		c.out();
//		c.runit();
//
//		Shout s = (Shout) Proxy.newProxyInstance(Shout.class.getClassLoader(),
//				new Class<?>[] { Shout.class }, new ProxyInvocationHandler());
//		Run r = (Run) Proxy.newProxyInstance(Run.class.getClassLoader(),
//				new Class<?>[] { Run.class, Shout.class },
//				new ProxyInvocationHandler());
//
//		// 只能将代理对象转为为相应的接口，如下代码编译出错
//		// Car cc = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(),
//		// new Class<?>[] { Car.class }, new ProxyInvocationHandler());
//		s.out();
//		r.runit();
//		r.runit1();
// }