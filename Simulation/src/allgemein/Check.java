package allgemein;

public class Check {

    public void checkNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException(
                    "Null darf nicht übergeben werden");
        }
    }

    public void checkNulls(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                throw new IllegalArgumentException(
                        "Null darf nicht übergeben werden");
            }
        }
    }

    public void check(boolean ok, String meldung) {
        if (!ok) {
            throw new IllegalArgumentException(meldung);
        }
    }

}
