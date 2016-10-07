package allgemein;

import java.util.ArrayList;
import java.util.List;

public class CheckAuftreten extends Check {
    public List<String> namen;

    public CheckAuftreten() {
        namen = new ArrayList<>();
    }

    public void nurEinmal(String name, String meldung) {
        if (namen.contains(name)) {
            throw new IllegalStateException(meldung);
        }
        namen.add(name);
    }

    public void istDa(String name, String meldung) {
        if (!namen.contains(name)) {
            throw new IllegalStateException(meldung);
        }
    }

    public void fuellen(String... neueNamen) {
        for (String name : neueNamen) {
            namen.add(name);
        }
    }
}