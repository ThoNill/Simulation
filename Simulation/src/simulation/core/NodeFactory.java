package simulation.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import allgemein.Check;

/**********
 * 
 * Erzeugt eine Node aus einer Beschreibung
 * 
 * Object
 * 
 * @author Thomas Nill
 *
 */
public class NodeFactory extends Check {

	public NodeFactory() {
		super();
	}

	public Node createNode(NodeDescription descr) throws NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		checkNull(descr);

		Class<?> clazz = Class.forName(descr.getClassName());
		Constructor<Node> constructor = null;
		try {
			constructor = (Constructor<Node>) clazz.getConstructor(String.class);
			if (constructor != null) {
				return constructor.newInstance(descr.getName());
			}
		} catch (NoSuchMethodException e1) {

		}
		try {
			constructor = (Constructor<Node>) clazz.getConstructor(String.class, String.class);
			if (constructor != null) {
				return constructor.newInstance(descr.getName(),descr.getTitle());
			}
		} catch (NoSuchMethodException e1) {

			throw new RuntimeException("kein passender Konstruktor für "
					+ descr.getClassName());
		}
		return null;
	}

}
