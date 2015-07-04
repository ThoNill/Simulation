package allgemein;

import java.util.Vector;

public class CheckAuftreten extends Check {
	public Vector<String> namen;

	public CheckAuftreten() {
		namen = new Vector<>();
	}

	public void nurEinmal(String name, String meldung) {
		if (namen.contains(name)) {
			throw new RuntimeException(meldung);
		}
		namen.add(name);
	}

	public void istDa(String name, String meldung) {
		if (!namen.contains(name)) {
			throw new RuntimeException(meldung);
		}
	}

	public void fuellen(String... neueNamen) {
		for (String name : neueNamen) {
			namen.add(name);
		}
	}
}